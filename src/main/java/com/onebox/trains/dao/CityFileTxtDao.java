package com.onebox.trains.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import com.onebox.trains.TrainsConstants;
import com.onebox.trains.model.City;
import com.onebox.trains.model.Route;
import com.onebox.trains.model.Town;
import com.onebox.trains.model.utils.RouteUtil;
import com.onebox.trains.model.utils.TownUtil;

/**
 * Implementation of the interface CityFileDao.
 * Manage city in TXT files.
 *
 * @author mauro-sanchez
 */
public class CityFileTxtDao implements CityFileDao {
	
	Set<Town> towns;
	Set<Route> routes;
	
	@Override
	public City load(String path) {
		routes = null;
		try (Stream<String> stream = Files.lines(Paths.get(path))) {
			towns = new HashSet<Town>();
			routes = new HashSet<Route>();
			stream.forEach(line -> {
					if (RouteUtil.isValidRoute(line)) {
						Town fromTown = TownUtil.getTown(towns, RouteUtil.getFromTown(line));
						Town toTown = TownUtil.getTown(towns, RouteUtil.getToTown(line));
						Route route = Route.of(fromTown, toTown, RouteUtil.getDistance(line));
						fromTown.addRoute(route);
						routes.add(route);
						towns.add(fromTown);
						towns.add(toTown);
					}
			});
		} catch (IOException e) {
			System.out.println("File not found.");
		}
		
		if (towns == null || routes == null)
			return null;
		
		City city = new City(TrainsConstants.CITY_NAME);
		city.setTowns(towns);
		city.setRoutes(routes);
		return city;
	}
	
	@Override
	public boolean isExistCity(String path) {
		File cityFile = new File(path);
		return cityFile.exists();
	}
	
	@Override
	public void createInitialCity(String pathFrom, String pathTo) {
		File cityFile = new File(pathTo);
		if (!cityFile.exists()) {
			try
			{
				cityFile.createNewFile();
				InputStream inputStreamCity = getClass().getResourceAsStream(pathFrom);
				BufferedInputStream bufferedInput = new BufferedInputStream(inputStreamCity);
				FileOutputStream fileOutputCity = new FileOutputStream (cityFile);
				BufferedOutputStream bufferedOutput = new BufferedOutputStream(fileOutputCity);
				byte [] array = new byte[1000];
				int leidos = bufferedInput.read(array);
				while (leidos > 0)
				{
					bufferedOutput.write(array,0,leidos);
					leidos=bufferedInput.read(array);
				}
	
				bufferedOutput.close();
				bufferedInput.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
}

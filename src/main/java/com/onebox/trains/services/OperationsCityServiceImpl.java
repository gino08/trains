package com.onebox.trains.services;

import java.util.List;

import com.onebox.trains.TrainsConstants;
import com.onebox.trains.model.City;
import com.onebox.trains.model.Route;
import com.onebox.trains.model.Town;
import com.onebox.trains.model.utils.RouteUtil;
import com.onebox.trains.model.utils.TownUtil;
import com.onebox.trains.services.operations.Dfs;
import com.onebox.trains.services.operations.Dijkstra;

/**
 * Implementation of the interface OperationsCityService.
 *
 * @author mauro-sanchez
 */
public class OperationsCityServiceImpl implements OperationsCityService {
	
	@Override
	public Integer computeDistanceAlongRoutes(City city, String indications) {
		String[] towns = indications.split(TrainsConstants.TOWNS_SPLITTER);
		
		Integer totalDistance = null;
		Town townInit = TownUtil.findTownByName(city.getTowns(), towns[0]);
		
		if (townInit != null && towns.length > 1)
			totalDistance = calculateDistance(townInit, towns, 1, new Integer(0));
		
		return totalDistance;
	}

	@Override
	public List<List<Route>> routesBetweenTwoTowns(Town fromTown, Town toTown) {
		Dfs dfs = new Dfs();
		return dfs.getRoutesBetweenTowns(fromTown, toTown);
	}

	@Override
	public List<Route> shortestRouteBetweenTwoTowns(City city, Town fromTown, Town toTown) {
		Dijkstra dijkstra = new Dijkstra();
		return dijkstra.getShortestRouteBetweenTowns(city, fromTown, toTown);
	}
	
	private Integer calculateDistance(Town town, String[] towns, int i, Integer totalDistance) {
		if (i < towns.length) {
			Route route = RouteUtil.findRouteTo(town.getRoutes(), towns[i]);
			if (route != null)
				return calculateDistance(route.getToTown(), towns, i+1, totalDistance + route.getDistance());
			else 
				return null;
		}
		return totalDistance;
	}
}

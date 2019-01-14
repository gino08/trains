package com.onebox.trains.services;

import java.util.List;

import com.onebox.trains.model.City;
import com.onebox.trains.model.Route;
import com.onebox.trains.model.Town;

/**
 * Interface operations city.
 *
 * @author mauro-sanchez
 */
public interface OperationsCityService {
	
	public Integer computeDistanceAlongRoutes(City city, String indications);
	
	public List<List<Route>> routesBetweenTwoTowns(Town fromTown, Town toTown);
	
	public List<Route> shortestRouteBetweenTwoTowns(City city, Town fromTown, Town toTown);
}

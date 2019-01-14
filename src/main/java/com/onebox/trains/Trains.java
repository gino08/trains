package com.onebox.trains;

import java.util.List;

import com.onebox.trains.model.Route;
import com.onebox.trains.ui.TrainsUI;

/**
 * Interface Trains.
 *
 * @author mauro-sanchez
 */
public interface Trains {

	void initTrains();
	
	Integer computeDistanceAlongRoutes();
	
	List<List<Route>> routesBetweenTwoRoutes();
	
	List<Route> shortestRouteBetweenTwoTowns();
	
	void exitTrains();
	
}

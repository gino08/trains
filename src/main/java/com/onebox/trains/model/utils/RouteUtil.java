package com.onebox.trains.model.utils;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;

import com.onebox.trains.TrainsConstants;
import com.onebox.trains.model.Route;
import com.onebox.trains.model.Town;

/**
 * Util methods for {@link Route}.
 *
 * @author mauro-sanchez
 */
public final class RouteUtil {

    public static Comparator<Route> ROUTES_ORDER_BY_DISTANCE = Comparator.comparing(Route::getDistance);
    
    /**
     * Determines if a plain string representation of a Route is valid.
     *
     * @param route  a simple string representation of a route
     * @return true if the input string is a valid route, otherwise, false
     */
    public static boolean isValidRoute(String route) {
    	if (route == null) 
    		return false;
    	
        String[] dataRoutes = route.trim().split(TrainsConstants.ROUTE_SPLITTER);
        return dataRoutes.length == 3 && !dataRoutes[0].isEmpty() && !dataRoutes[1].isEmpty() && !dataRoutes[2].isEmpty()
        		&& RouteUtil.isValidDistance(dataRoutes[2]);
    }
	
    /**
     * Determines if a plain string representation of a distance is valid.
     *
     * @param distance  a simple string representation of a distance
     * @return true if the input string is a valid integer distance, otherwise, false
     */
    public static boolean isValidDistance(String distance) {
		try {
			Integer.parseInt(distance);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
    /**
     * Given a simple string representation of a route it tries to
     * create a new From Town with a name.
     *
     * @param route  a simple string representation of a route
     * @return a {@link Town}
     */
    public static Town getFromTown(String route) {
		if (RouteUtil.isValidRoute(route))
			return new Town(route.trim().split(TrainsConstants.ROUTE_SPLITTER)[0]);
		return null;
	}

	
	/**
     * Given a simple string representation of a route it tries to
     * create a new To Town with a name.
     *
     * @param route  a simple string representation of a route
     * @return a {@link Town}
     */
    public static Town getToTown(String route) {
		if (RouteUtil.isValidRoute(route))
			return new Town(route.trim().split(TrainsConstants.ROUTE_SPLITTER)[1]);
		return null;
	}
	
	/**
     * Given a simple string representation of a route it tries to
     * create a new Distance.
     *
     * @param route  a simple string representation of a route
     * @return a {@link Integer} distance
     */
    public static Integer getDistance(String route) {
		if (RouteUtil.isValidRoute(route))
			return NumberUtils.createInteger(route.trim().split(TrainsConstants.ROUTE_SPLITTER)[2]);
		return null;
	}

    /**
     * Given a set of a route and name To Town 
     * it filter a set by name of toTown.
     *
     * @param routes  a set or route
     * @param toTownName  a simple string representarion the name of To town
     * @return a {@link Route}
     */
    public static Route findRouteTo(Set<Route> routes, String toTownName) {
    	Objects.requireNonNull(routes, "routes cannot be null");
    	Objects.requireNonNull(toTownName, "toTownName cannot be null");
		return routes.stream().filter(route -> route.getToTown().getName().toUpperCase().equals(toTownName.toUpperCase())).findAny().orElse(null);
	}
    
    /**
     * Given a list of Route representation of a route it tries to
     * calculate the distance.
     *
     * @param route  a list of {@link Route} representation of a route
     * @return a {@link Integer} distance
     */
    public static Integer getDistanceRoute(List<Route> route) {
    	Objects.requireNonNull(route, "route cannot be null");
		return route.stream().mapToInt(Route::getDistance).sum();
	}
}

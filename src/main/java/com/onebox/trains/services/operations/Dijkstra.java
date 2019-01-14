package com.onebox.trains.services.operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.onebox.trains.model.City;
import com.onebox.trains.model.Route;
import com.onebox.trains.model.Town;
import com.onebox.trains.model.utils.RouteUtil;

/**
 * Algorithm Dijkstra.
 * 
 * Dijkstra's algorithm is an algorithm for finding the shortest paths between nodes in a graph.
 *
 * @author mauro-sanchez
 */
public class Dijkstra {
	
	private Queue<DijkstraTown> townsQueue;
	private Map<Town, DijkstraTown> dataTowns;
	
	public List<Route> getShortestRouteBetweenTowns(City city, Town fromTown, Town toTown) {
		this.townsQueue = new PriorityQueue<DijkstraTown>(DijkstraTown.DIJKSTRA_TOWN_ORDER_BY_DISTANCE);
		
		dataTowns = new HashMap<Town, DijkstraTown>();
		city.getTowns().stream().forEach(town -> {
			dataTowns.put(town, new DijkstraTown(town));
		});

		dataTowns.get(fromTown).setDistance(new Integer(0));
		townsQueue.add(dataTowns.get(fromTown));
		
		while (!townsQueue.isEmpty()) {
			Town town = townsQueue.poll().getTown();
			if (!dataTowns.get(town).getVisited()) {
				dataTowns.get(town).setVisited(Boolean.TRUE);
				
				town.getRoutes().stream().forEach(route -> {
					if (!dataTowns.get(route.getToTown()).getVisited()) {
						Integer fromTownDistance = dataTowns.get(route.getFromTown()).getDistance();
						Integer toTownDistance = dataTowns.get(route.getToTown()).getDistance();
						if (fromTownDistance + route.getDistance() < toTownDistance) {
							dataTowns.get(route.getToTown()).setDistance(fromTownDistance + route.getDistance());
							dataTowns.get(route.getToTown()).setPreviousTown(town);
							townsQueue.add(dataTowns.get(route.getToTown()));
						}
					}
				});
			}
			
		}
		
		return reverseLoadRoute(toTown, null);
	}
	
	private List<Route> reverseLoadRoute(Town toTown, Town fromTown) {
		List<Route> route = new ArrayList<Route>();
		if (dataTowns.get(toTown).getPreviousTown() != null) {
			route.addAll(reverseLoadRoute(dataTowns.get(toTown).getPreviousTown(), toTown));
		}
		if (fromTown != null)
			route.add(RouteUtil.findRouteTo(toTown.getRoutes(), fromTown.getName()));
		return route;
	}
	
}

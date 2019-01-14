package com.onebox.trains.services.operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.onebox.trains.model.Route;
import com.onebox.trains.model.Town;

/**
 * Algorithm Depth First Search.
 * 
 * Depth-first search (DFS) is an algorithm for traversing or searching tree or graph data structures.
 * The algorithm starts at the root node (selecting some arbitrary node as the root node in the case 
 * of a graph) and explores as far as possible along each branch before backtracking.
 *
 * @author mauro-sanchez
 */
public class Dfs {
	
	private Stack<Town> townsVisited;
	
	public List<List<Route>> getRoutesBetweenTowns(Town fromTown, Town toTown) {
		this.townsVisited = new Stack<Town>();
		return dfs(fromTown, toTown, new ArrayList<List<Route>>(), new ArrayList<Route>()); 
	}
	
	private List<List<Route>> dfs(Town fromTown, Town toTown, List<List<Route>> result, List<Route> currentRoute) {
		townsVisited.push(fromTown);
		
		if (fromTown.equals(toTown)) {
			result.add(new ArrayList<Route>(currentRoute));
		}
		
		for (Route route : fromTown.getRoutes()) {
			if (!townsVisited.contains(route.getToTown())) {
				currentRoute.add(route);
				dfs(route.getToTown(), toTown, result, currentRoute);
				currentRoute.remove(route);
			}
		}
		townsVisited.pop();
		return result;
	}
}

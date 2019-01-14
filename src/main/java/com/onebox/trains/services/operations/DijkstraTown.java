package com.onebox.trains.services.operations;

import java.util.Comparator;
import java.util.Objects;

import com.onebox.trains.model.Route;
import com.onebox.trains.model.Town;

/**
 * Object representing a data town for algorithm Dijkstra.
 * 
 * @author mauro-sanchez
 */
public class DijkstraTown {
	
	public static Comparator<DijkstraTown> DIJKSTRA_TOWN_ORDER_BY_DISTANCE = Comparator.comparing(DijkstraTown::getDistance);
	
	private Town town;
	private Integer distance;
	private Boolean visited;
	private Town previousTown;
	
	public DijkstraTown(Town town) {
		Objects.requireNonNull(town, "town cannot be null");
		this.town = town;
		this.distance = Integer.MAX_VALUE;
		this.visited = Boolean.FALSE;
		this.previousTown = null;
	}

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Boolean getVisited() {
		return visited;
	}

	public void setVisited(Boolean visited) {
		this.visited = visited;
	}

	public Town getPreviousTown() {
		return previousTown;
	}

	public void setPreviousTown(Town previousTown) {
		this.previousTown = previousTown;
	}
	
	@Override
    public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
	        return true;
		}
	    if (obj.getClass() != getClass())
	        return false;
	    
	    DijkstraTown dijkstraTown = (DijkstraTown) obj;
        return town.equals(dijkstraTown.getTown());
    }

    @Override
    public int hashCode() {
        return town.hashCode();
    }
}

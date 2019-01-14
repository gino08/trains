package com.onebox.trains.model;

import java.util.Objects;

/**
 * Object representing a Route between cities.
 * 
 * @author mauro-sanchez
 */
public final class Route {
	
	private Town fromTown;
	private Town toTown;
	private Integer distance;
	
	
	private Route(Town fromTown, Town toTown, Integer distance) {
		Objects.requireNonNull(fromTown, "Town 'from' cannot be null");
		Objects.requireNonNull(toTown, "Town 'to' cannot be null");
		Objects.requireNonNull(distance, "distance cannot be null");
		this.fromTown = fromTown;
		this.toTown= toTown;
		this.distance = distance;
	}
	
	public static Route of(Town fromTown, Town toTown, Integer distance) {
        return new Route(fromTown, toTown, distance);
    }
	
	public Town getFromTown() {
		return fromTown;
	}

	public Town getToTown() {
		return toTown;
	}

	public Integer getDistance() {
		return distance;
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
	    
        Route route = (Route) obj;
        return fromTown.equals(route.getFromTown())
        		&& toTown.equals(route.getToTown());
    }

    @Override
    public int hashCode() {
        return fromTown.hashCode() + toTown.hashCode();
    }

	public String getData() {
		return fromTown.getName() + toTown.getName() + distance.toString();
    }
}

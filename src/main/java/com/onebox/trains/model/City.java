package com.onebox.trains.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Object representing a city.
 * 
 * @author mauro-sanchez
 */
public class City {
	
	private String name;
	private Set<Town> towns;
	private Set<Route> routes;
	
	public City(String name) {
		Objects.requireNonNull(name, "name cannot be null");
		this.name = name;
		this.routes = new HashSet<Route>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Town> getTowns() {
		return towns;
	}

	public void setTowns(Set<Town> towns) {
		this.towns = towns;
	}

	public Set<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(Set<Route> routes) {
		this.routes = routes;
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
	    
        City town = (City) obj;
        return name.toUpperCase().equals(town.getName().toUpperCase());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}

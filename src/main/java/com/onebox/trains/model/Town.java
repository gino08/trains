package com.onebox.trains.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Object representing a town.
 * 
 * @author mauro-sanchez
 */
public class Town {
	
	private String name;
	private Set<Route> routes;
	
	public Town(String name) {
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

	public Set<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(Set<Route> routes) {
		this.routes = routes;
	}
	
	public void addRoute(Route route) {
		if (route != null)
			this.routes.add(route);
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
	    
        Town town = (Town) obj;
        return name.toUpperCase().equals(town.getName().toUpperCase());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

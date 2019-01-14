package com.onebox.trains.model.utils;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;

import com.onebox.trains.model.Route;
import com.onebox.trains.model.Town;

/**
 * Util methods for {@link Town}.
 *
 * @author mauro-sanchez
 */
public final class TownUtil {

    public static Comparator<Town> TOWNS_ORDER_BY_NAME = Comparator.comparing(Town::getName);
    
    /**
     * Given a set of a towns and name 
     * it filter a set by name.
     *
     * @param towns  a set or towns
     * @param townName  a simple string representarion the name of town
     * @return a {@link Town}
     */
    public static Town findTownByName(Set<Town> towns, String townName) {
    	Objects.requireNonNull(towns, "towns cannot be null");
    	Objects.requireNonNull(townName, "townName cannot be null");
		return towns.stream().filter(town ->  townName.toUpperCase().equals(town.getName().toUpperCase())).findAny().orElse(null);
	}
    
    /**
     * Given a set of a towns and town 
     * it filter a set by town, if not exist return the town.
     *
     * @param towns  a set or towns
     * @param town  a simple town
     * @return a {@link Town}
     */
    public static Town getTown(Set<Town> towns, Town town) {
    	Objects.requireNonNull(towns, "towns cannot be null");
    	Objects.requireNonNull(town, "town cannot be null");
    	return towns.stream().filter(townCity -> town.equals(townCity)).findAny().orElse(town);
	}
}

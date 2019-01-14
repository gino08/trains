package com.onebox.trains.model.utils;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.onebox.trains.model.City;
import com.onebox.trains.model.Town;

/**
 * Unit tests for {@link TownUtil} class.
 * 
 * @author mauro-sanchez
 */
public class TownUtilTest {
	
	@Test
	public void given_notEmptyTowns_when_findATownName_then_returnEquals() {
		Set<Town> towns = new HashSet<Town>();
		Town town1 = new Town("town1");
		Town town2 = new Town("town2");
		Town town3 = new Town("town3");
		
		towns.add(town1);
		towns.add(town2);
		towns.add(town3);
		
		Assert.assertEquals(town2, TownUtil.findTownByName(towns, "town2"));
	}
	
	@Test
	public void given_notEmptyTowns_when_findATownName_then_returnNotEquals() {
		Set<Town> towns = new HashSet<Town>();
		Town town1 = new Town("town1");
		Town town2 = new Town("town2");
		Town town3 = new Town("town3");
		
		towns.add(town1);
		towns.add(town2);
		towns.add(town3);
		
		Assert.assertNotEquals(town2, TownUtil.findTownByName(towns, "town1"));
	}
	
	@Test
	public void given_emptyTowns_when_findATownName_then_returnNull() {
		Set<Town> towns = new HashSet<Town>();
		Assert.assertNull(TownUtil.findTownByName(towns, "town1"));
	}
	
	@Test(expected = NullPointerException.class)
	public void given_nullTowns_when_findATownName_then_nullPointerException() {
		TownUtil.findTownByName(null, "town1");
	}
	
	@Test
	public void given_notEmptyTowns_when_containsTown_then_returnEquals() {
		Set<Town> towns = new HashSet<Town>();
		Town town1 = new Town("town1");
		Town town2 = new Town("town2");
		Town town3 = new Town("town3");
		
		towns.add(town1);
		towns.add(town2);
		towns.add(town3);
		
		Assert.assertEquals(town2, TownUtil.getTown(towns, town2));
	}
	
	@Test
	public void given_notEmptyTowns_when_notContainsTown_then_returnEquals() {
		Set<Town> towns = new HashSet<Town>();
		Town town1 = new Town("town1");
		Town town2 = new Town("town2");
		Town town3 = new Town("town3");
		Town town4 = new Town("town4");
		
		towns.add(town1);
		towns.add(town2);
		towns.add(town3);
		
		Assert.assertEquals(town4, TownUtil.getTown(towns, town4));
	}
	
	@Test
	public void given_emptyTowns_when_getTown_then_returnEquals() {
		Set<Town> towns = new HashSet<Town>();
		Town town1 = new Town("town1");
		Assert.assertEquals(town1, TownUtil.getTown(towns, town1));
	}
	
	@Test(expected = NullPointerException.class)
	public void given_notEmptyTowns_when_nullTown_then_nullPointerException() {
		Set<Town> towns = new HashSet<Town>();
		Town town1 = new Town("town1");
		Town town2 = new Town("town2");
		Town town3 = new Town("town3");
		
		towns.add(town1);
		towns.add(town2);
		towns.add(town3);
		
		TownUtil.getTown(towns, null);
	}
}

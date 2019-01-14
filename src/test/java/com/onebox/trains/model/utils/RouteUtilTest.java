package com.onebox.trains.model.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.onebox.trains.model.City;
import com.onebox.trains.model.Route;
import com.onebox.trains.model.Town;

/**
 * Unit tests for {@link RouteUtil} class.
 * 
 * @author mauro-sanchez
 */
public class RouteUtilTest {
	
	@Test
	public void given_aValidString_when_validRoute_then_true() {
		Assert.assertTrue(RouteUtil.isValidRoute("A|B|5"));
		Assert.assertTrue(RouteUtil.isValidRoute("B|C|500"));
	}
	
	@Test
	public void given_aInvalidString_when_validRoute_then_false() {
		Assert.assertFalse(RouteUtil.isValidRoute("A"));
		Assert.assertFalse(RouteUtil.isValidRoute("A|B"));
		Assert.assertFalse(RouteUtil.isValidRoute("A|B|C"));
		Assert.assertFalse(RouteUtil.isValidRoute("A|5|C"));
		Assert.assertFalse(RouteUtil.isValidRoute("|C|9"));
		Assert.assertFalse(RouteUtil.isValidRoute("D||10"));
		Assert.assertFalse(RouteUtil.isValidRoute("H|I|"));
		Assert.assertFalse(RouteUtil.isValidRoute("||"));
		Assert.assertFalse(RouteUtil.isValidRoute(null));
	}
	
	@Test
	public void given_aValidValue_when_validDistance_then_true() {
		Assert.assertTrue(RouteUtil.isValidDistance("5"));
		Assert.assertTrue(RouteUtil.isValidDistance("500"));
	}
	
	@Test
	public void given_aInvalidValue_when_validDistance_then_false() {
		Assert.assertFalse(RouteUtil.isValidDistance("A"));
		Assert.assertFalse(RouteUtil.isValidDistance(null));
	}
	
	@Test
	public void given_aValidRoute_when_getFromTown_then_returnEquals() {
		Assert.assertEquals(new Town("name1"), RouteUtil.getFromTown("name1|name2|5"));
	}
	
	@Test
	public void given_aValidRoute_when_getFromTown_then_returnNotEquals() {
		Assert.assertNotEquals(new Town("name1"), RouteUtil.getFromTown("name2|name1|5"));
	}
	
	@Test
	public void given_aInvalidRoute_when_getFromTown_then_returnNull() {
		Assert.assertNull(RouteUtil.getFromTown("name1|name2"));
		Assert.assertNull(RouteUtil.getFromTown("name1|5"));
		Assert.assertNull(RouteUtil.getFromTown("|name2|5"));
		Assert.assertNull(RouteUtil.getFromTown(null));
	}
	
	@Test
	public void given_aValidRoute_when_getToTown_then_returnEquals() {
		Assert.assertEquals(new Town("name2"), RouteUtil.getToTown("name1|name2|5"));
	}
	
	@Test
	public void given_aValidRoute_when_getToTown_then_returnNotEquals() {
		Assert.assertNotEquals(new Town("name2"), RouteUtil.getToTown("name2|name1|5"));
	}
	
	@Test
	public void given_aInvalidRoute_when_getToTown_then_returnNull() {
		Assert.assertNull(RouteUtil.getToTown("name1|name2"));
		Assert.assertNull(RouteUtil.getToTown("name1|5"));
		Assert.assertNull(RouteUtil.getToTown("|name2|5"));
		Assert.assertNull(RouteUtil.getToTown(null));
	}
	
	@Test
	public void given_aValidRoute_when_getDistance_then_returnEquals() {
		Assert.assertEquals(new Integer(5), RouteUtil.getDistance("name1|name2|5"));
	}
	
	@Test
	public void given_aValidRoute_when_getDistance_then_returnNotEquals() {
		Assert.assertNotEquals(new Integer(5), RouteUtil.getDistance("name1|name2|15"));
	}
	
	@Test
	public void given_aInvalidRoute_when_getDistance_then_returnNull() {
		Assert.assertNull(RouteUtil.getDistance("name1"));
		Assert.assertNull(RouteUtil.getDistance(null));
	}
	
	@Test
	public void given_notEmptyRoutes_when_findAToTownName_then_returnEquals() {
		Set<Route> routes = new HashSet<Route>();
		Route route1 = Route.of(new Town("fromTown1"), new Town("toTown1"), new Integer(5));
		Route route2 = Route.of(new Town("fromTown2"), new Town("toTown2"), new Integer(15));
		Route route3 = Route.of(new Town("fromTown3"), new Town("toTown3"), new Integer(25));
		
		routes.add(route1);
		routes.add(route2);
		routes.add(route3);
		
		Assert.assertEquals(route2, RouteUtil.findRouteTo(routes, "toTown2"));
	}
	
	@Test
	public void given_notEmptyRoutes_when_findAToTownName_then_returnNotEquals() {
		Set<Route> routes = new HashSet<Route>();
		Route route1 = Route.of(new Town("fromTown1"), new Town("toTown1"), new Integer(5));
		Route route2 = Route.of(new Town("fromTown2"), new Town("toTown2"), new Integer(15));
		Route route3 = Route.of(new Town("fromTown3"), new Town("toTown3"), new Integer(25));
		
		routes.add(route1);
		routes.add(route2);
		routes.add(route3);
		
		Assert.assertNotEquals(route2, RouteUtil.findRouteTo(routes, "toTown1"));
	}
	
	@Test
	public void given_emptyRoutes_when_findAToTownName_then_returnNull() {
		Set<Route> routes = new HashSet<Route>();
		Assert.assertNull(RouteUtil.findRouteTo(routes, "toTown1"));
	}
	
	@Test(expected = NullPointerException.class)
	public void given_nullRoutes_when_findAToTownName_then_nullPointerException() {
		RouteUtil.findRouteTo(null, "toTown1");
	}
	
	@Test
	public void given_notEmptyRoutes_when_getDistanceRoute_then_returnEquals() {
		List<Route> routes = new ArrayList<Route>();
		Route route1 = Route.of(new Town("A"), new Town("B"), new Integer(5));
		Route route2 = Route.of(new Town("B"), new Town("C"), new Integer(15));
		Route route3 = Route.of(new Town("C"), new Town("D"), new Integer(25));
		
		routes.add(route1);
		routes.add(route2);
		routes.add(route3);
		
		Assert.assertEquals(new Integer(45), RouteUtil.getDistanceRoute(routes));
	}
	
	@Test
	public void given_emptyRoutes_when_getDistanceRoute_then_returnEquals() {
		List<Route> routes = new ArrayList<Route>();
		Assert.assertEquals(new Integer(0), RouteUtil.getDistanceRoute(routes));
	}
	
	@Test(expected = NullPointerException.class)
	public void given_nullRoutes_when_getDistanceRoute_then_nullPointerException() {
		RouteUtil.getDistanceRoute(null);
	}
}

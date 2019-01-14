package com.onebox.trains.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link Route} class.
 * 
 * @author mauro-sanchez
 */
public class RouteTest {
	
	@Test(expected = NullPointerException.class)
	public void given_aNullFromTown_when_createRoute_then_nullPointerException() {
		Route.of(null, new Town("name"), new Integer(1));
	}
	
	@Test(expected = NullPointerException.class)
	public void given_aNullToTown_when_createRoute_then_nullPointerException() {
		Route.of(new Town("name"), null, new Integer(1));
	}
	
	@Test(expected = NullPointerException.class)
	public void given_aNullDistance_when_createRoute_then_nullPointerException() {
		Route.of(new Town("name1"), new Town("name2"), null);
	}
	
	@Test
	public void given_allData_when_createRoute_then_createSuccess() {
		Assert.assertNotNull(Route.of(new Town("name1"), new Town("name2"), new Integer(1)));
	}
	
	@Test
	public void given_aTwoRouteWithSameFromAndTo_when_useEquals_then_returnTrue() {
		Route routeOne = Route.of(new Town("name1"), new Town("name2"), new Integer(1));
		Route routeTwo = Route.of(new Town("name1"), new Town("name2"), new Integer(2));
        Assert.assertTrue(routeOne.equals(routeTwo));
    }

    @Test
    public void given_aTwoParrotsWithDifferentFromAndTo_when_useEquals_then_returnFalse() {
    	Route routeOne = Route.of(new Town("name1"), new Town("name2"), new Integer(1));
		Route routeTwo = Route.of(new Town("name2"), new Town("name1"), new Integer(2));
        Assert.assertFalse(routeOne.equals(routeTwo));
    }
    
}

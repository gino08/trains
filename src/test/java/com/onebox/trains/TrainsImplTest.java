package com.onebox.trains;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.onebox.trains.model.City;
import com.onebox.trains.model.Route;
import com.onebox.trains.model.Town;
import com.onebox.trains.model.utils.RouteUtil;
import com.onebox.trains.ui.TrainsUI;

/**
 * Unit tests for {@link TrainsImpl} class.
 *
 * @author mauro-sanchez
 */
public class TrainsImplTest {
	
	private City city;
	
	@Before
	public void setUp() {
		Town townA = new Town("A");
		Town townB = new Town("B");
		Town townC = new Town("C");
		Town townD = new Town("D");
		Town townE = new Town("E");
		
		Set<Route> routes = new HashSet<Route>();
		Route route1 = Route.of(townA, townB, new Integer(5));
		Route route2 = Route.of(townB, townC, new Integer(4));
		Route route3 = Route.of(townC, townD, new Integer(8));
		Route route4 = Route.of(townD, townC, new Integer(8));
		Route route5 = Route.of(townD, townE, new Integer(6));
		Route route6 = Route.of(townA, townD, new Integer(5));
		Route route7 = Route.of(townC, townE, new Integer(2));
		Route route8 = Route.of(townE, townB, new Integer(3));
		Route route9 = Route.of(townA, townE, new Integer(7));
		routes.add(route1);
		routes.add(route2);
		routes.add(route3);
		routes.add(route4);
		routes.add(route5);
		routes.add(route6);
		routes.add(route7);
		routes.add(route8);
		routes.add(route9);

		townA.addRoute(route1);
		townA.addRoute(route6);
		townA.addRoute(route9);
		townB.addRoute(route2);
		townC.addRoute(route3);
		townC.addRoute(route7);
		townD.addRoute(route4);
		townD.addRoute(route5);
		townE.addRoute(route8);
		
		Set<Town> towns = new HashSet<Town>();
		towns.add(townA);
		towns.add(townB);
		towns.add(townC);
		towns.add(townD);
		towns.add(townE);
		
		city = new City("City");
		city.setRoutes(routes);
		city.setTowns(towns);
	}
	
	@Test 
	public void given_aValidRoute_when_computeDistanceAlongRoutes_then_returnEquals() {
		// Given: a mocked TrainsUI
		TrainsUI mockedTrainsUI = mock(TrainsUI.class);
		doReturn("A-E-B-C-D").when(mockedTrainsUI).getIndicationsTowns();
		
		Trains trains = TrainsImpl.createTrains(city, mockedTrainsUI);
		Assert.assertEquals(new Integer(22), trains.computeDistanceAlongRoutes());
		Assert.assertNotEquals(new Integer(10), trains.computeDistanceAlongRoutes());
	}
	
	@Test 
	public void given_aInvalidRoute_when_computeDistanceAlongRoutes_then_returnNull() {
		// Given: a mocked TrainsUI
		TrainsUI mockedTrainsUI = mock(TrainsUI.class);
		doReturn("A-E-D").when(mockedTrainsUI).getIndicationsTowns();
		
		Trains trains = TrainsImpl.createTrains(city, mockedTrainsUI);
		Assert.assertNull(trains.computeDistanceAlongRoutes());
	}
	
	@Test
	public void given_aValidRoute_when_computeRoutesBetweenTwoRoutes_then_returnEquals() {
		// Given: a mocked TrainsUI
		TrainsUI mockedTrainsUI = mock(TrainsUI.class);
		doReturn("A-E").when(mockedTrainsUI).getIndicationsTowns();
		
		Trains trains = TrainsImpl.createTrains(city, mockedTrainsUI);
		Assert.assertEquals(new Integer(5), new Integer(trains.routesBetweenTwoRoutes().size()));
		Assert.assertNotEquals(new Integer(4), new Integer(trains.routesBetweenTwoRoutes().size()));
	}
	
	@Test
	public void given_aInvalidRoute_when_computeRoutesBetweenTwoRoutes_then_returnNull() {
		// Given: a mocked TrainsUI
		TrainsUI mockedTrainsUI = mock(TrainsUI.class);
		doReturn("A").when(mockedTrainsUI).getIndicationsTowns();
		
		Trains trains = TrainsImpl.createTrains(city, mockedTrainsUI);
		Assert.assertNull(trains.routesBetweenTwoRoutes());
	}

	@Test
	public void given_aValidRoute_when_computeShortestRouteBetweenTwoTowns_then_returnEquals() {
		// Given: a mocked TrainsUI
		TrainsUI mockedTrainsUI = mock(TrainsUI.class);
		doReturn("A-C").when(mockedTrainsUI).getIndicationsTowns();
		
		Trains trains = TrainsImpl.createTrains(city, mockedTrainsUI);
		Assert.assertEquals(new Integer(9), RouteUtil.getDistanceRoute(trains.shortestRouteBetweenTwoTowns()));
		Assert.assertNotEquals(new Integer(10), RouteUtil.getDistanceRoute(trains.shortestRouteBetweenTwoTowns()));
	}
	
	@Test
	public void given_aInvalidRoute_when_computeShortestRouteBetweenTwoTowns_then_returnNull() {
		// Given: a mocked TrainsUI
		TrainsUI mockedTrainsUI = mock(TrainsUI.class);
		doReturn("A").when(mockedTrainsUI).getIndicationsTowns();
		
		Trains trains = TrainsImpl.createTrains(city, mockedTrainsUI);
		Assert.assertNull(trains.shortestRouteBetweenTwoTowns());
	}
}

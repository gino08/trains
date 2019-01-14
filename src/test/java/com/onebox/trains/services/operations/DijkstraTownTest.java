package com.onebox.trains.services.operations;

import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import com.onebox.trains.model.Town;

/**
 * Unit tests for {@link DijkstraTown} class.
 * 
 * @author mauro-sanchez
 */
public class DijkstraTownTest {
	
	@Test(expected = NullPointerException.class)
	public void given_aNullTown_when_createDijkstraTown_then_nullPointerException() {
		new DijkstraTown(null);
	}
	
	@Test
	public void given_aTown_when_createDijkstraTown_then_createSuccess() {
		Assert.assertNotNull(new DijkstraTown(new Town("name")));
	}
	
	@Test
	public void given_aTwoDijkstraTownWithSameTown_when_useEquals_then_returnTrue() {
		DijkstraTown dijkstraTownOne = new DijkstraTown(new Town("name"));
		DijkstraTown dijkstraTownTwo = new DijkstraTown(new Town("name"));
        Assert.assertTrue(dijkstraTownOne.equals(dijkstraTownTwo));
    }

    @Test
    public void given_aTwoDijkstraTownWithDifferentTown_when_useEquals_then_returnFalse() {
    	DijkstraTown dijkstraTownOne = new DijkstraTown(new Town("name1"));
		DijkstraTown dijkstraTownTwo = new DijkstraTown(new Town("name2"));
        Assert.assertFalse(dijkstraTownOne.equals(dijkstraTownTwo));
    }

    @Test
    public void given_aEmptyPriorityQueue_when_addThreeDijkstraTowns_then_orderAscending() {
    	DijkstraTown dijkstraTownOne = new DijkstraTown(new Town("name1"));
    	dijkstraTownOne.setDistance(new Integer(3));
		DijkstraTown dijkstraTownTwo = new DijkstraTown(new Town("name2"));
		dijkstraTownTwo.setDistance(new Integer(2));
		DijkstraTown dijkstraTownThree = new DijkstraTown(new Town("name3"));
		dijkstraTownThree.setDistance(new Integer(1));
		
		Queue<DijkstraTown> queue = new PriorityQueue<DijkstraTown>(DijkstraTown.DIJKSTRA_TOWN_ORDER_BY_DISTANCE);
		queue.add(dijkstraTownOne);
		queue.add(dijkstraTownTwo);
		queue.add(dijkstraTownThree);
		
        Assert.assertEquals(dijkstraTownThree, queue.poll());
        Assert.assertEquals(dijkstraTownTwo, queue.poll());
        Assert.assertEquals(dijkstraTownOne, queue.poll());
    }

}

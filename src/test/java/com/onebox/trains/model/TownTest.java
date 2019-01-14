package com.onebox.trains.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link Town} class.
 * 
 * @author mauro-sanchez
 */
public class TownTest {
	
	@Test(expected = NullPointerException.class)
	public void given_aNullName_when_createTown_then_nullPointerException() {
		new Town(null);
	}
	
	@Test
	public void given_aName_when_createTown_then_createSuccess() {
		Assert.assertNotNull(new Town("name"));
	}
	
	@Test
	public void given_aTwoTownsWithSameName_when_useEquals_then_returnTrue() {
		Town townOne = new Town("name");
		Town townTwo = new Town("name");
        Assert.assertTrue(townOne.equals(townTwo));
    }

    @Test
    public void given_aTwoTownsWithDifferentName_when_useEquals_then_returnFalse() {
    	Town townOne = new Town("name1");
    	Town townTwo = new Town("name2");
        Assert.assertFalse(townOne.equals(townTwo));
    }
    
}

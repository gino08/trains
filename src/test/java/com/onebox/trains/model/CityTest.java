package com.onebox.trains.model;

import org.junit.Assert;
import org.junit.Test;

import com.onebox.trains.dao.CityFileTxtDao;

/**
 * Unit tests for {@link City} class.
 * 
 * @author mauro-sanchez
 */
public class CityTest {
	
	@Test(expected = NullPointerException.class)
	public void given_aNullName_when_createCity_then_nullPointerException() {
		new City(null);
	}
	
	@Test
	public void given_aName_when_createCity_then_createSuccess() {
		Assert.assertNotNull(new City("name"));
	}
	
	@Test
	public void given_aTwoCitysWithSameName_when_useEquals_then_returnTrue() {
		City cityOne = new City("name");
		City cityTwo = new City("name");
        Assert.assertTrue(cityOne.equals(cityTwo));
    }

    @Test
    public void given_aTwoCitysWithDifferentName_when_useEquals_then_returnFalse() {
    	City cityOne = new City("name1");
    	City cityTwo = new City("name2");
        Assert.assertFalse(cityOne.equals(cityTwo));
    }
    
}

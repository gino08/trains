package com.onebox.trains.dao;

import org.junit.Assert;
import org.junit.Test;

import com.onebox.trains.TrainsImpl;
import com.onebox.trains.model.City;

/**
 * Unit tests for {@link CityFileTxtDao} class.
 * 
 * @author mauro-sanchez
 */
public class CityFileTest {
	
	public static final String CITY_FILE_VALID_PATH = "./src/test/resources/city.txt";
	public static final String CITY_FILE_INVALID_PATH = "./src/test/resources/test.txt";
	public static final String CITY_FILE_VALID_LINE = "./src/test/resources/cityValidLine.txt";
	public static final String CITY_FILE_INVALID_LINE = "./src/test/resources/cityInvalidLine.txt";
	

	@Test
	public void given_aValidPath_when_tryReadFile_then_loadCity() {
		CityFileTxtDao cityFile = new CityFileTxtDao();
		City city = cityFile.load(CITY_FILE_VALID_PATH);
		Assert.assertNotNull(city);
	}
	
	@Test
	public void given_aInvalidPath_when_tryReadFile_then_cityIsNull() {
		CityFileTxtDao cityFile = new CityFileTxtDao();
		City city = cityFile.load(CITY_FILE_INVALID_PATH);
		Assert.assertNull(city);
	}
	
	@Test
	public void given_aFileWithOneValidLine_when_tryReadFile_then_routesSizeIsOne() {
		CityFileTxtDao cityFile = new CityFileTxtDao();
		City city = cityFile.load(CITY_FILE_VALID_LINE);
		Assert.assertEquals(1, city.getRoutes().size());
	}
	
	@Test
	public void given_aFileWithOneInvalidLine_when_tryReadFile_then_routesEmpty() {
		CityFileTxtDao cityFile = new CityFileTxtDao();
		City city = cityFile.load(CITY_FILE_INVALID_LINE);
		Assert.assertTrue(city.getRoutes().isEmpty());
	}

	@Test
	public void given_aExistFile_when_verifiedIfExist_then_returnTrue() {
		CityFileTxtDao cityFile = new CityFileTxtDao();
		Assert.assertTrue(cityFile.isExistCity(CITY_FILE_VALID_PATH));
	}
	
	@Test
	public void given_aNotExistFile_when_verifiedIfExist_then_returnFalse() {
		CityFileTxtDao cityFile = new CityFileTxtDao();
		Assert.assertFalse(cityFile.isExistCity(CITY_FILE_INVALID_PATH));
	}
}

package com.onebox.trains.services;

import java.io.File;

import com.onebox.trains.TrainsConstants;
import com.onebox.trains.dao.CityFileDao;
import com.onebox.trains.dao.CityFileTxtDao;
import com.onebox.trains.model.City;

/**
 * Implementation of the interface CityService.
 * Use CityFileDao for load city.
 *
 * @author mauro-sanchez
 */
public class CityServiceFiles implements CityService {
	
	private CityFileDao dao;
	
	public CityServiceFiles() {
		dao = new CityFileTxtDao();
	}
	
	@Override
	public City loadCity() {
		return dao.load(TrainsConstants.FOLDER_RESOURCES_PATH + TrainsConstants.CITY_FILE_PATH);
	}

	@Override
	public void setUpCity() {
		createFolderIfNotExist(TrainsConstants.FOLDER_RESOURCES_PATH);
		if (!dao.isExistCity(TrainsConstants.FOLDER_RESOURCES_PATH + TrainsConstants.CITY_FILE_PATH))
			dao.createInitialCity(TrainsConstants.CITY_FILE_PATH, TrainsConstants.FOLDER_RESOURCES_PATH + TrainsConstants.CITY_FILE_PATH);
	}
	
	private void createFolderIfNotExist(String path) {
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}
	}
}

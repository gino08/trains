package com.onebox.trains.dao;

import com.onebox.trains.model.City;

/**
 * Interface manage city in files.
 *
 * @author mauro-sanchez
 */
public interface CityFileDao {
	
	City load(String path);
	
	boolean isExistCity(String path);
	
	void createInitialCity(String pathFrom, String pathTo);
}

package com.onebox.trains.ui;

import java.util.List;

import com.onebox.trains.model.City;
import com.onebox.trains.model.Route;

/**
 * Interface for interaction with the user.
 *
 * @author mauro-sanchez
 */
public interface TrainsUI {

	void initTrains(City city);
	
	void printMessage(String change);
	
	void exitTrains();
	
	void showOptions();
	
	int getSelectedOption();
	
	String getIndicationsTowns();
	
	void printRoute(List<Route> routes);
}

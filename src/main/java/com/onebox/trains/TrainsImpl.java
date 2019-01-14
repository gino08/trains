package com.onebox.trains;

import java.util.List;

import com.onebox.trains.model.City;
import com.onebox.trains.model.Route;
import com.onebox.trains.model.Town;
import com.onebox.trains.model.utils.TownUtil;
import com.onebox.trains.services.CityService;
import com.onebox.trains.services.CityServiceFiles;
import com.onebox.trains.services.OperationsCityService;
import com.onebox.trains.services.OperationsCityServiceImpl;
import com.onebox.trains.ui.TrainsUI;

/**
 * Implementation of the interface Trains.
 * 
 * Maintains the state of the City.
 *
 * @author mauro-sanchez
 */
public class TrainsImpl implements Trains {
	
	private City city;
	private OperationsCityService operationsCityService;
    private TrainsUI trainsUI;
    
    private TrainsImpl(City city, TrainsUI trainsUI) {
    	this.operationsCityService = new OperationsCityServiceImpl();
    	this.city = city;
    	this.trainsUI = trainsUI;
    }
    
    public static Trains createTrains(City city, TrainsUI trainsUI) {
        return new TrainsImpl(city, trainsUI);
    }
    
	@Override
	public void initTrains() {
		this.trainsUI.initTrains(city);
		
		int selectedOption = 0;
		while (selectedOption != 4) {
			this.trainsUI.showOptions();
			selectedOption = this.trainsUI.getSelectedOption();
			switch (selectedOption) {
			case 1:
				this.computeDistanceAlongRoutes();
				break;
			case 2:
				this.routesBetweenTwoRoutes();
				break;
			case 3:
				this.shortestRouteBetweenTwoTowns();
				break;
			case 4:
				this.exitTrains();
				break;
			default:
				break;
			}
		}
	}
	
	@Override
	public Integer computeDistanceAlongRoutes() {
		this.trainsUI.printMessage("\n-----------------------------------------");
		this.trainsUI.printMessage("\nInput the route (use '" + TrainsConstants.TOWNS_SPLITTER +"' between towns)");
		String indications = this.trainsUI.getIndicationsTowns();
		this.trainsUI.printMessage("\n");
		
		Integer totalDistance = this.operationsCityService.computeDistanceAlongRoutes(city, indications);
		
		if (totalDistance != null)
			this.trainsUI.printMessage("The distance of the route " + indications + ": " + totalDistance);
		else
			this.trainsUI.printMessage("NO SUCH ROUTE.");
		
		return totalDistance;
	}

	@Override
	public List<List<Route>> routesBetweenTwoRoutes() {
		this.trainsUI.printMessage("\n-----------------------------------------");
		this.trainsUI.printMessage("\nInput the 'from town' and the 'to town' (use '" + TrainsConstants.TOWNS_SPLITTER +"' between towns)");
		String indications = this.trainsUI.getIndicationsTowns();
		this.trainsUI.printMessage("\n");
		
		if (!validDataRoute(indications)) {
			this.trainsUI.printMessage("\nInvalid data! (Wrong format or the town/s not exist)");
			return null;
		}
		
		Town fromTown = TownUtil.findTownByName(city.getTowns(), indications.split(TrainsConstants.TOWNS_SPLITTER)[0]);
		Town toTown = TownUtil.findTownByName(city.getTowns(), indications.split(TrainsConstants.TOWNS_SPLITTER)[1]);
		List<List<Route>> routes = this.operationsCityService.routesBetweenTwoTowns(fromTown, toTown);
		
		if (routes.isEmpty()) {
			this.trainsUI.printMessage("NO SUCH ROUTE.");
		}
		else {
			routes.stream().forEach(routeFind -> {
				this.trainsUI.printRoute(routeFind);
			});
		}
		
		return routes;
	}
	
	@Override
	public List<Route> shortestRouteBetweenTwoTowns() {
		this.trainsUI.printMessage("\n-----------------------------------------");
		this.trainsUI.printMessage("\nInput the 'from town' and the 'to town' (use '" + TrainsConstants.TOWNS_SPLITTER +"' between towns)");
		String indications = this.trainsUI.getIndicationsTowns();
		this.trainsUI.printMessage("\n");
		
		if (!validDataRoute(indications)) {
			this.trainsUI.printMessage("\nInvalid data! (Wrong format or the town/s not exist)");
			return null;
		}
		
		Town fromTown = TownUtil.findTownByName(city.getTowns(), indications.split(TrainsConstants.TOWNS_SPLITTER)[0]);
		Town toTown = TownUtil.findTownByName(city.getTowns(), indications.split(TrainsConstants.TOWNS_SPLITTER)[1]);
		List<Route> route = this.operationsCityService.shortestRouteBetweenTwoTowns(city, fromTown, toTown);
		this.trainsUI.printRoute(route);
		
		return route;
	}
	
	@Override
	public void exitTrains() {
		this.trainsUI.exitTrains();
	}

	private Boolean validDataRoute(String indications) {
		String[] towns = indications.split(TrainsConstants.TOWNS_SPLITTER);
		if (towns.length != 2) {
			return Boolean.FALSE;
		}
		if (TownUtil.findTownByName(city.getTowns(), towns[0]) == null) {
			return Boolean.FALSE;
		}
		if (TownUtil.findTownByName(city.getTowns(), towns[1]) == null) {
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
}

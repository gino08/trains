package com.onebox.trains.ui;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.onebox.trains.TrainsConstants;
import com.onebox.trains.model.City;
import com.onebox.trains.model.Route;
import com.onebox.trains.model.utils.RouteUtil;

/**
 * Implementation of the interface TrainsUI.
 * use the system console.
 *
 * @author mauro-sanchez
 */
public class TrainsUIConsole implements TrainsUI {
	
	private final Scanner scanner;

    public TrainsUIConsole() {
        scanner = new Scanner(System.in);
    }

	@Override
	public void initTrains(City city) {
		System.out.println("\nCity " + city.getName() + " created.");
		listRoutesCity(city);
	}
	
	private void listRoutesCity(City city) {
		if (city.getTowns() != null && !city.getTowns().isEmpty()) {
			System.out.println("\nTowns found:");
			city.getTowns().stream().forEach(town -> {
	            System.out.println("\t " + town.getName());
	        });
		}
		else {
			System.out.println("\nNot towns found");
		}
		
		if (city.getRoutes() != null && !city.getRoutes().isEmpty()) {
			System.out.println("\nRoutes found:");
			city.getRoutes().stream().forEach(route -> {
	            System.out.println("\t " + route.getData());
	        });
		}
		else {
			System.out.println("\nNot routes found");
		}
	}
	
	@Override
	public void printMessage(String change) {
		System.out.println(change);
	}
	
	@Override
	public void exitTrains() {
		System.out.println("\n***************************************************");
    	System.out.println(".:                   Trains finalized               :.");
    	System.out.println("***************************************************\n");
	}
	
	public void showOptions() {
		System.out.println("\n-----------------------------------------");
		System.out.println("Select an option:\n");
		System.out.println("\t 1 - Compute the distance along a certain route");
        System.out.println("\t 2 - Compute the number of different routes between two towns");
        System.out.println("\t 3 - Compute the shortest route between two towns");
        System.out.println("\t 4 - Exit");
        System.out.println("\n[SELECT OPTION AND PRESS ENTER TO READ THE NEXT OPTION]");
	}
	
	@Override
	public int getSelectedOption() {
		int selectedOption = 0;
		boolean read = true;
		while (read) {
			try {
				selectedOption = scanner.nextInt();
				if (invalidSelectedOption(selectedOption)) {
					System.out.println("Invalid value!");
				} else {
					read = false;
				}
			} catch (InputMismatchException e) {
			    System.out.println("Invalid value!");
			    scanner.next();
			} 
        }
        scanner.nextLine();
        return selectedOption;
	}
	
	private boolean invalidSelectedOption(int selectedOption) {
		return selectedOption != 1 && selectedOption != 2 && selectedOption != 3 && selectedOption != 4;
	}

	@Override
	public String getIndicationsTowns() {
		String indications = null;
		boolean read = true;
		while (read) {
			indications = scanner.nextLine();
			if (indications != null && !indications.isEmpty())
				read = false;
        }
        return indications;
	}

	@Override
	public void printRoute(List<Route> routes) {
		if (routes.isEmpty()) {
			printMessage("NO SUCH ROUTE.");
		}
		else {
			final StringBuilder path = new StringBuilder(routes.get(0).getFromTown().getName());
			routes.stream().forEach(route -> {
				path.append(TrainsConstants.TOWNS_SPLITTER + route.getToTown().getName());
			});
			printMessage("Route found: " + path + " | Distance: " + RouteUtil.getDistanceRoute(routes));
		}
	}

}

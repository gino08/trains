package com.onebox.trains;

import com.onebox.trains.services.CityService;
import com.onebox.trains.services.CityServiceFiles;
import com.onebox.trains.ui.TrainsUIConsole;

/**
 * Convenient factory of different
 * implementations of the Trains.
 *
 * @author mauro-sanchez
 */
public final class TrainsFactory {

    public static Trains newTrainsImplFromFileSystemAndUIConsole() {
        CityService cityService = new CityServiceFiles();
        cityService.setUpCity();
        return TrainsImpl.createTrains(cityService.loadCity(), new TrainsUIConsole());
    }

}

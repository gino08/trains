package com.onebox.trains;

/**
 * Application's entry point.
 *
 * @author mauro-sanchez
 */
public final class TrainsMain {
	
	public static void main(String[] args) {

		System.out.println("\n*****************************************");
        System.out.println("********      .:  Trains  :.     ********");
        System.out.println("*****************************************");
        
        TrainsFactory.newTrainsImplFromFileSystemAndUIConsole().initTrains();

    }
}

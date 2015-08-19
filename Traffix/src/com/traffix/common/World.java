package com.traffix.common;

import java.util.ArrayList;
import java.util.List;

import com.traffix.interfaces.CycleListener;

public class World {
	
	private List<CycleListener> listeners = new ArrayList<CycleListener>();
	private List<Vehicle> vehicles = new ArrayList<Vehicle>();
	
	int currentVehicleId = 1;
	
	double currentCycle = 0;
	
	public World() {
		
	}
	
	public void addListener(CycleListener newListener){
		listeners.add(newListener);
	}

	public void startTheClock(int cycles) {
		
		for(int i = cycles; i>= 0 ; i--){
			System.out.println("Current Cycle: " + i );
			currentCycle++;
			
			for(CycleListener cl : listeners){
				cl.nextCycle();
			}
		}
		
	}
	
	public boolean addVehicle(Vehicle vehicle){
		
		vehicle.setId(currentVehicleId);
		currentVehicleId++;
		
		this.addListener(vehicle);
		
		vehicles.add(vehicle);
		
		return vehicles.contains(vehicle);
	}
	
	public List getVehicles(){
		return vehicles;
	}
	
}

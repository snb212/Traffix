package com.traffix.common;

import java.util.ArrayList;
import java.util.List;

import com.traffix.interfaces.CycleListener;

public class World {
	
	private List<CycleListener> listeners = new ArrayList<CycleListener>();
	private List<Driver> drivers = new ArrayList<Driver>();
	
	int currentDriverId = 1;
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
	
	public boolean addDriver(Driver driver){
		
		driver.setId(currentDriverId);
		currentDriverId++;
		
		this.addListener(driver);
		this.addListener(driver.getVehicle());
		
		drivers.add(driver);
		
		return drivers.contains(driver);
	}
	
	public List getDrivers(){
		return drivers;
	}
	
}

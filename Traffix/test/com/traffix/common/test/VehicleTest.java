package com.traffix.common.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.traffix.ai.driver.Action;
import com.traffix.ai.driver.Actions;
import com.traffix.common.Driver;
import com.traffix.common.World;
import com.traffix.interfaces.CycleListener;

public class VehicleTest implements CycleListener{
	
	static World world;
	
	@Before
	public void main() {
		world  = new World();
		System.out.println("New world created");
		Driver driver1 = new Driver(world, 65, .5, 0, 0.7);
		world.addDriver(driver1);
		
		Action ai = Actions.accelerate("medium", driver1.getVehicle(), driver1);
		driver1.setAction(ai);
		
	}
	
	public void testSpeedChanges(){
		List<Driver> drivers = world.getDrivers();
		
		for(Driver v : drivers){
			System.out.println("Vehicle " + v.getId() + " Speed: " + v.getVehicle().getSpeedMphFormatted() + " mph" );
		}
		
	}

	@Override
	public void nextCycle() {
		
		// tests to run during each cycle
		testSpeedChanges();
	}
	
	@Test
	public void start(){
		world.addListener(this);
		
		world.startTheClock(100);
	}

}
 
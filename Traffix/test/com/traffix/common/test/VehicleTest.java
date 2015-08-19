package com.traffix.common.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.traffix.common.Vehicle;
import com.traffix.common.World;
import com.traffix.interfaces.CycleListener;

public class VehicleTest implements CycleListener{
	
	static World world;
	
	@Before
	public void main() {
		world  = new World();
		System.out.println("New world created");
		Vehicle car = new Vehicle((short) 4.0, (short) 5.0, (short) 1.0, (short) 60.0);
		Vehicle car2 = new Vehicle((short) 4.0, (short) 5.0, (short) 1.0, (short) 60.0);
		world.addVehicle(car);
		world.addVehicle(car2);
		
		car.giveCommand("accelerate light");
		car2.giveCommand("accelerate heavy");
		
	}
	
	public void testSpeedChanges(){
		List<Vehicle> vehicles = world.getVehicles();
		
		for(Vehicle v : vehicles){
			System.out.println("Vehicle " + v.getId() + " Speed: " + v.getSpeedFormatted() + " mph" );
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
		
		world.startTheClock(20);
	}

}

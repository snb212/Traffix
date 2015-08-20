package com.traffix.ai.driver;

import java.util.HashMap;
import java.util.logging.Level;

import com.traffix.common.Driver;
import com.traffix.common.Vehicle;
import com.traffix.common.World;

public class AccelerateToDesiredSpeed extends Action{
	final static private String ACCELERATION_MODIFIER_LIGHT_LABEL = "light";
	final static private String ACCELERATION_MODIFIER_MEDIUM_LABEL = "medium";
	final static private String ACCELERATION_MODIFIER_HEAVY_LABEL = "heavy";
	final static private String ACCELERATION_MODIFIER_MAX_LABEL = "max";
	
	final static private double ACCELERATION_MODIFIER_LIGHT_COEFF = .75;
	final static private double ACCELERATION_MODIFIER_MEDIUM_COEFF = 1;
	final static private double ACCELERATION_MODIFIER_HEAVY_COEFF = 1.25;
	final static private double ACCELERATION_MODIFIER_MAX_COEFF = 1.5;
	
	public AccelerateToDesiredSpeed(String level, Vehicle vehicle, Driver driver){
		
		super();
		
		accelerationModifierMap.put(ACCELERATION_MODIFIER_LIGHT_LABEL, ACCELERATION_MODIFIER_LIGHT_COEFF);
		accelerationModifierMap.put(ACCELERATION_MODIFIER_MEDIUM_LABEL, ACCELERATION_MODIFIER_MEDIUM_COEFF);
		accelerationModifierMap.put(ACCELERATION_MODIFIER_HEAVY_LABEL, ACCELERATION_MODIFIER_HEAVY_COEFF);
		accelerationModifierMap.put(ACCELERATION_MODIFIER_MAX_LABEL, ACCELERATION_MODIFIER_MAX_COEFF);
		
		
		switch(level){
		case (ACCELERATION_MODIFIER_LIGHT_LABEL):
			this.accelerationModiferLabel = ACCELERATION_MODIFIER_LIGHT_LABEL;
			this.accelerationModifer  = ACCELERATION_MODIFIER_LIGHT_COEFF;
			break;
		case (ACCELERATION_MODIFIER_MEDIUM_LABEL): 
			this.accelerationModiferLabel = ACCELERATION_MODIFIER_MEDIUM_LABEL;
			this.accelerationModifer  = ACCELERATION_MODIFIER_MEDIUM_COEFF;
			break;
		case (ACCELERATION_MODIFIER_HEAVY_LABEL): 
			this.accelerationModiferLabel = ACCELERATION_MODIFIER_HEAVY_LABEL;
			this.accelerationModifer  = ACCELERATION_MODIFIER_HEAVY_COEFF;
			break;
		case (ACCELERATION_MODIFIER_MAX_LABEL): 
			this.accelerationModiferLabel = ACCELERATION_MODIFIER_MAX_LABEL;
			this.accelerationModifer  = ACCELERATION_MODIFIER_MAX_COEFF;
			break;
		}
		
		System.out.println("Driver " + driver.getId() + " acceleration modifier is " + accelerationModifer);
		
	}
	
	
	private HashMap<String, Double> accelerationModifierMap = new HashMap<String, Double>();
	private String accelerationModiferLabel;
	private double accelerationModifer;
	private Vehicle vehicle;
	private Driver driver;
	
	@Override
	public void reset() {
		/*
		accelerationModifer = null;
		accelerationModiferLabel = null;
		driver = null;
		vehicle = null;
		accelerationModifierMap.clear();
		*/
		
		start();
	}
	@Override
	public void act(World world, Vehicle vehicle, Driver driver) {
		System.out.println("driver desired speed " + driver.getDesiredSpeed() + " mph and vehicle current speed " + vehicle.getSpeedMph() + " mph"); 
		if (vehicle.getSpeedMph() < driver.getDesiredSpeed()){
			vehicle.accelerate(accelerationModifer);
			System.out.println("driver " + driver.getId() + " - speed " + vehicle.getSpeedMphFormatted() + " mph acceleration " + vehicle.getAccelerationMphFormatted() + " mph");
		}
		
	} 
}

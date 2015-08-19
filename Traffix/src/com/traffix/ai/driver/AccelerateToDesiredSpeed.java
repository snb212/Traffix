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
	
	final static private double ACCELERATION_MODIFIER_LIGHT_COEFF = .5;
	final static private double ACCELERATION_MODIFIER_MEDIUM_COEFF = .75;
	final static private double ACCELERATION_MODIFIER_HEAVY_COEFF = .9;
	final static private double ACCELERATION_MODIFIER_MAX_COEFF = 1;
	
	public AccelerateToDesiredSpeed(String level, Vehicle vehicle, Driver driver){
		
		super();
		
		accelerationModifierMap.put(ACCELERATION_MODIFIER_LIGHT_LABEL, ACCELERATION_MODIFIER_LIGHT_COEFF);
		accelerationModifierMap.put(ACCELERATION_MODIFIER_MEDIUM_LABEL, ACCELERATION_MODIFIER_MEDIUM_COEFF);
		accelerationModifierMap.put(ACCELERATION_MODIFIER_HEAVY_LABEL, ACCELERATION_MODIFIER_HEAVY_COEFF);
		accelerationModifierMap.put(ACCELERATION_MODIFIER_MAX_LABEL, ACCELERATION_MODIFIER_MAX_COEFF);
		
		double accelerationModifer;
		
		
		switch(level){
		case (ACCELERATION_MODIFIER_LIGHT_LABEL):
			accelerationModiferLabel = ACCELERATION_MODIFIER_LIGHT_LABEL;
			accelerationModifer  = ACCELERATION_MODIFIER_LIGHT_COEFF;
			break;
		case (ACCELERATION_MODIFIER_MEDIUM_LABEL): 
			accelerationModiferLabel = ACCELERATION_MODIFIER_MEDIUM_LABEL;
			accelerationModifer  = ACCELERATION_MODIFIER_MEDIUM_COEFF;
			break;
		case (ACCELERATION_MODIFIER_HEAVY_LABEL): 
			accelerationModiferLabel = ACCELERATION_MODIFIER_HEAVY_LABEL;
			accelerationModifer  = ACCELERATION_MODIFIER_HEAVY_COEFF;
			break;
		case (ACCELERATION_MODIFIER_MAX_LABEL): 
			accelerationModiferLabel = ACCELERATION_MODIFIER_MAX_LABEL;
			accelerationModifer  = ACCELERATION_MODIFIER_MAX_COEFF;
			break;
		}
		
	}
	
	
	private HashMap<String, Double> accelerationModifierMap = new HashMap<String, Double>();
	private String accelerationModiferLabel;
	private double accelerationModifer;
	private Vehicle vehicle;
	private Driver driver;
	
	@Override
	public void reset() {
		accelerationModifer = null;
		accelerationModiferLabel = null;
		driver = null;
		vehicle = null;
		accelerationModifierMap.clear();
		
		start();
	}
	@Override
	public void act(World world, Vehicle vehicle, Driver driver) {
		
		
		
		
	} 
}

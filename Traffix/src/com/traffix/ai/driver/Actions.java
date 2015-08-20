package com.traffix.ai.driver;

import com.traffix.common.*;

//factor for creating actions
public class Actions {
	
	public static Action sequence(Action... actions){
		Sequence sequence = new Sequence();
		
		for(Action action : actions){
			sequence.addAction(action);
		}
		
		return sequence;
	}
	
	public static Action selector(Action... actions){
		Selector selector = new Selector();
		for (Action action : actions){
			selector.addAction(action);
		}
		return selector;
	}
	
	public static Action accelerate(String level, Vehicle vehicle, Driver driver){
		return new AccelerateToDesiredSpeed(level, vehicle, driver);
	}
}

package com.traffix.ai.driver;

import com.traffix.common.Driver;
import com.traffix.common.Vehicle;
import com.traffix.common.World;

public class Repeat extends Action{

	private final Action action;
	private int times;
	private int origTimes;
	
	public Repeat (Action action) {
		super();
		this.action = action;
		this.times = -1; //infinite
		this.origTimes = times;
	}
	
	public Repeat (Action action, int times){
		super();
		
		if (times < 1){
			throw new RuntimeException("Cant repeat less than 1 time");
		}
		this.action = action;
		this.times = times;
		this.origTimes = times;
	}
	
	@Override
	public void start(){
		super.start();
		this.action.start();
	}
	
	public void reset(){
		this.times = origTimes;
	}
	
	@Override
	public void act(World world, Vehicle vehicle, Driver driver){
		if(action.isFailure()){
			fail();
		}
		else if (action.isSuccess()){
			if (times == 0){
				succeed();
				return;
			}
			if (times > 0 || times <= -1){
				times--;
				action.reset();
				action.start();
			}
		} 
		if(action.isRunning()){
			action.act(world, vehicle, driver);
		}
		
	}
	
}

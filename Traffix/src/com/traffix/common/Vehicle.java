package com.traffix.common;

import com.traffix.interfaces.CycleListener;

import java.lang.Math;
import java.text.DecimalFormat;

public class Vehicle implements CycleListener{
	
	//vehicle attributes
	int id;
	
	//state variables
	private String state;
	private String modifier;
	
	//physical properties
	private double maxAcceleration;
	private double drag;
	private double maxDeceleration;
	//speed is in mph
	private double speed;
	
	
	public Vehicle(double acceleration, double deceleration, double drag, double speed) {
		this.maxAcceleration = acceleration;
		this.drag = drag;
		this.maxDeceleration = deceleration;
		this.speed = speed;

		this.state = "null";
	}
	
	@Override
	public void nextCycle(){
		step();
	}
	
	public void step(){
		
		//Driver command action
		switch (state) {
		case "accelerate":	speed =  (speed + accelerate(modifier));
		
		case "decelerate":	;
							break;
		default:			break;
		}
		
		//Environmental variables
		speed =  (speed - drag);
	}
	
	public double getSpeed(){
		return speed;
	}
	
	public double getSpeedFormatted(){
		return Double.parseDouble(formatMph(speed));
	}
	
	protected void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	//returns true of command was recognized
	public boolean giveCommand(String cmd){
		String firstWord = cmd.substring(0, cmd.indexOf(' '));
		String secondWord = cmd.substring(cmd.indexOf(' ') + 1, cmd.length());
		
		this.state = firstWord;
		this.modifier = secondWord;
		
		System.out.println(firstWord + ":" + secondWord);
		
		return true;
	}
	
	private double accelerate(String level){
		
		double acceleration = 0;
		double baseAcceleration = determineAcceleration(speed);
		
		switch(level){
			case "light": 	acceleration =  (baseAcceleration * ( 0.7));
							break;
			case "medium": 	acceleration =  (baseAcceleration * ( 1));
							break;
			case "heavy":	acceleration =  (baseAcceleration * ( 1.3));
							break;
			case "max":		acceleration = maxAcceleration;
							break;
			default:		acceleration = 0;
		}
		
		System.out.println("Final acceleration: " + formatMph(acceleration) + " mph+ from " + formatMph(baseAcceleration) );
		return acceleration;
	}
	
	
	//acceleration model retrieved using CS car on 4-lane highway
	// http://www.istiee.org/te/papers/N55/ET_2013_55_1_Mehar.pdf
	private double determineAcceleration(double speed){
		double constant_a = 1.70;
		double constant_b = -0.04;
		
		double acc = ms2mph((constant_a * Math.exp(constant_b * mph2ms(speed))));
		System.out.println("acc: " + formatMph(acc) + " mph+");
		return acc;
	}
	
	private double mph2ms(double mph){
		return (mph * 0.44704);
	}
	
	private double ms2mph(double ms){
		return (ms * 2.2369362920544);
	}
	
	private String formatMph(double speed){
		DecimalFormat number = new DecimalFormat("#.00");
		
		return number.format(speed);
	}
	
}

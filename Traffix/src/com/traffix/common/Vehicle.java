package com.traffix.common;

import com.traffix.interfaces.CycleListener;

import java.lang.Math;
import java.text.DecimalFormat;

public class Vehicle implements CycleListener{
	static final double ACCELERATION_OF_GRAVITY = 9.81; 	//in m/s
	
	//vehicle attributes
	int id;
	
	//state variables
	private String state;
	private String modifier;
	
	//physical properties
	private double drag;
	private double maxDeceleration;
	private double speed;	//in m/s

	private double coeffFrict;
	private double maxAcceleration; // in m/s
	private double acceleration; //in m/s
	
	//example coeffFrict is 7.2  (http://www.engineeringtoolbox.com/friction-coefficients-d_778.html)
	public Vehicle(double acceleration, double deceleration, double drag, double speed, double coeffFrict) {
		this.drag = drag;
		this.maxDeceleration = deceleration;
		this.speed = speed;
		this.coeffFrict = coeffFrict;
		
		//a = ug
		this.maxAcceleration = coeffFrict * ACCELERATION_OF_GRAVITY;

		this.state = "null";
	}
	
	@Override
	public void nextCycle(){
		environmentalEffects();
	}
	
	public void environmentalEffects(){
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
//	public boolean giveCommand(String cmd){
//		String firstWord = cmd.substring(0, cmd.indexOf(' '));
//		String secondWord = cmd.substring(cmd.indexOf(' ') + 1, cmd.length());
//		
//		this.state = firstWord;
//		this.modifier = secondWord;
//		
//		System.out.println(firstWord + ":" + secondWord);
//		
//		return true;
//	}

	//sets acceleration using modifier
	public double accelerate(double modifier){
		
		//double acceleration = 0;
		/*
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
		*/
		
		this.acceleration = modifier * determineAcceleration();
		
		//update speed
		speed = speed + this.acceleration;
		System.out.println("Accelerated " + ms2mph(modifier * determineAcceleration()) + " mph to a speed of " + formatMph(ms2mph(this.speed)) + " with a total acceleration of " + ms2mph(this.acceleration));
		
		return acceleration;
	}
	
	public double decelerate(double modifier){
		updateMaxDeceleration();
				
		this.acceleration = acceleration - modifier * maxDeceleration;
		
		//update speed
		speed = speed + this.acceleration;
		System.out.println("Decelerated " + ms2mph(modifier * maxDeceleration) + " mph to a speed of " + formatMph(ms2mph(this.speed)) + " with a total acceleration of " + ms2mph(this.acceleration));
		
		return acceleration;
	}
	
	
	//acceleration model retrieved using CS car on 4-lane highway
	// http://www.istiee.org/te/papers/N55/ET_2013_55_1_Mehar.pdf
	private double determineAcceleration(){
		double constant_a = 1.70;
		double constant_b = -0.04;
		
		double acc = (constant_a * Math.exp(constant_b * mph2ms(this.speed)));
		System.out.println("acc: " + formatMph(ms2mph(acc)) + " mph+");
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
	
	//deceleration model  
	// deceleration rate = -k3*velocity^2 + k4*velocity + k5
	//http://www.ijtte.com/uploads/2012-10-01/5ebd8343-9b9c-b1d4IJTTE%20vol2%20no3%20(7).pdf
	
	private double car_k3 = 0.005;
	private double car_k4 = 0.154;
	private double car_k5 = 0.493;
	
	private void updateMaxDeceleration(){
		this.maxDeceleration = -car_k3 * Math.pow(this.speed, 2) + car_k4 * this.speed + car_k5;
	}
	
}

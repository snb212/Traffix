package com.traffix.common;

import com.traffix.ai.driver.Action;
import com.traffix.interfaces.CycleListener;

public class Driver implements CycleListener{

		public Driver(World world, double desiredSpeed, double drag, double speed, double coeffFrict){
			this.desiredSpeed = desiredSpeed;
			this.canMakeDecision = true;
			
			this.vehicle = new Vehicle(drag, speed, coeffFrict);
		}
		
		private static double desiredSpeed;
		private static boolean canMakeDecision;
		private World world;
		private Vehicle vehicle;
		private static int id;
		
		Action action;

		@Override
		public void nextCycle() {
			
			if(canMakeDecision){
				asessSituation();
			}
			
		}
		
		public void asessSituation(){
			System.out.println(action.getState());
			if (action.getState() == null){
				action.start();
			}
			action.act(this.world, vehicle, this);
			
		}
		
		public static double getDesiredSpeed() {
			return desiredSpeed;
		}

		public static void setDesiredSpeed(double desiredSpeed) {
			Driver.desiredSpeed = desiredSpeed;
		}

		public Vehicle getVehicle() {
			return vehicle;
		}

		public void setVehicle(Vehicle vehicle) {
			this.vehicle = vehicle;
		}

		public World getWorld() {
			return world;
		}

		public void setWorld(World world) {
			this.world = world;
		}

		public Action getAction() {
			return action;
		}

		public void setAction(Action action) {
			this.action = action;
		}

		public static int getId() {
			return id;
		}
		
		public void setId(int currentDriverId) {
			this.id = currentDriverId;
		}
}

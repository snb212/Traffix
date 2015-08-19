package com.traffix.common;

import com.traffix.interfaces.CycleListener;

public class Driver implements CycleListener{
	
		private static double desiredSpeed;

		private static boolean canMakeDecision;

		public Driver(double desiredSpeed){
			this.desiredSpeed = desiredSpeed;
			this.canMakeDecision = true;
		}

		@Override
		public void nextCycle() {
			
			if(canMakeDecision){
				asessSituation();
			}
			
		}
		
		public String asessSituation(){
			
			
			return "";
		}
		
		public static double getDesiredSpeed() {
			return desiredSpeed;
		}

		public static void setDesiredSpeed(double desiredSpeed) {
			Driver.desiredSpeed = desiredSpeed;
		}
}

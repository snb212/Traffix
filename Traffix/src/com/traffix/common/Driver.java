package com.traffix.common;

import com.traffix.interfaces.CycleListener;

public class Driver implements CycleListener{
	
		static double desiredSpeed;
		static boolean canMakeDecision;

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
		
}

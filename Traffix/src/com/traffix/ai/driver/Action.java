//https://github.com/obviam/behavior-trees/blob/master/src/net/obviam/bt/ai/Routine.java

package com.traffix.ai.driver;

import com.traffix.common.Driver;
import com.traffix.common.Vehicle;
import com.traffix.common.World;

public abstract class Action {

		public enum ActionState {
			Success,
			Failure,
			Running,
		}
		
		protected ActionState state;
		
		protected Action() {}
		
		public void start(){
			System.out.println("---- Performing Action: " + this.getClass().getSimpleName());
			this.state = ActionState.Running;
		}
		
		public abstract void reset();
		
		public abstract void act(World world, Vehicle vehicle, Driver driver);
		
		protected void succeed() {
			System.out.println("---- Action: " + this.getClass().getSimpleName() + " SUCCEEDED" );
			this.state = ActionState.Success;
		}
		
		protected void fail() {
			System.out.println("---- Action: " + this.getClass().getSimpleName() + " FAILED" );
			this.state = ActionState.Failure;
		}
		
		public boolean isSuccess(){
			return state.equals(ActionState.Success);
		}
		
		public boolean isFailure(){
			return state.equals(ActionState.Failure);
		}
		
		public boolean isRunning(){
			return state.equals(ActionState.Running);
		}
		
		public ActionState getState(){
			return state;
		}
}

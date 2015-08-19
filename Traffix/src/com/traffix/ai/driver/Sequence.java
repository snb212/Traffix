//https://github.com/obviam/behavior-trees/blob/master/src/net/obviam/bt/ai/Sequence.java

package com.traffix.ai.driver;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.traffix.common.Driver;
import com.traffix.common.Vehicle;
import com.traffix.common.World;

public class Sequence extends Action{
	
	public Sequence(){
		super();
		this.currentAction = null;
	}
	
	private Action currentAction;
	List<Action> actions = new LinkedList<Action>();
	Queue<Action> actionQueue = new LinkedList<Action>();
	
	public void addAction(Action action){
		actions.add(action);
	}
	
	@Override
	public void reset(){
		for (Action action : actions){
			action.reset();
		}
	}
	
	@Override
	public void start(){
		super.start();
		
		actionQueue.clear();
		actionQueue.addAll(actions);
		currentAction = actionQueue.poll();
		currentAction.start();
	}
	
	@Override
	public void act(World world, Vehicle vehicle, Driver driver){
		
		currentAction.act(world, vehicle, driver);
		
		if (currentAction.isRunning()){
			return;
		}
		
		if (actionQueue.peek() == null){
			this.state = currentAction.getState();
			return;
		}
		
		if (actionQueue.peek() == null){
			this.state = currentAction.getState();
		} else {
			currentAction = actionQueue.poll();
			currentAction.start();
		}
		
	}

}

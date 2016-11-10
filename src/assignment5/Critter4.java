/* CRITTERS Critter4.java
 * EE422C Project 5 submission by
 * <Gaurav Nagar>
 * <gn3544>
 * <16480>
 * <Minkoo Park>
 * <mp32454>
 * <16480>
 * Slip days used: <0>
 * Fall 2016
 */

package assignment5;

import assignment5.Critter.CritterShape;

public class Critter4 extends Critter{
	
	boolean alreadyMoved;
	
	/**
	 * Constructor that keeps track of if a MyCritterN object has moved
	 */
	public Critter4(){
		alreadyMoved = false;
	}
	
	/**
	 * MyCritterN object will run, walk, or reproduce randomly with equal chance
	 */
	@Override
	public void doTimeStep() {
		int random = getRandomInt(9);
		if (random < 3){
			run(getRandomInt(8));
			alreadyMoved = true;
		}
		else if (random < 6){
			walk(getRandomInt(8));
			alreadyMoved = true;
		}
		else if (random < 9){
			reproduce(new Critter4(), getRandomInt(8));
		}
	}
	
	/**
	 * Runs if it hasn't moved before, else fights
	 */
	@Override
	public boolean fight(String opponent) {
		if (!alreadyMoved){
			run(getRandomInt(8));
			return false;
		}
		return true;
	}
	
	/**
	 * Returns String characterization of MyCritterN
	 */
	public String toString() {
		return "4";
	}
	
	@Override
	public CritterShape viewShape(){
		return CritterShape.DIAMOND;
	}
	
	@Override
	public javafx.scene.paint.Color viewOutlineColor() { 
		return javafx.scene.paint.Color.RED; 
	}
	
	@Override
	public javafx.scene.paint.Color viewFillColor() {
		return javafx.scene.paint.Color.RED; 
	}
}

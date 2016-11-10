/* CRITTERS Critter3.java
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

public class Critter3 extends Critter{
	
	int dir;
	
	/**
	 * Gets a random direction variable
	 */
	public Critter3(){
		dir = getRandomInt(8);
	}
	
	/**
	 * Will run/walk if energy permits, or does nothing
	 */
	@Override
	public void doTimeStep() {
		if (getEnergy() > Params.run_energy_cost){
			run(dir);
		}
		else if (getEnergy() > Params.walk_energy_cost){
			walk(dir);
		}
	}
	
	/**
	 * Fights if opponent is Algae, if not walks away if energy permit or tries to reproduce
	 */
	@Override
	public boolean fight(String opponent) {
		if (opponent.equals("@")){
			return true;
		}
		else if (getEnergy() > Params.walk_energy_cost){
			walk(dir);
			return false;
		}
		reproduce(new Critter3(), getRandomInt(8));
		return false;
	}
	
	/**
	 * Returns String characterization of MyCritterG
	 */
	public String toString() {
		return "3";
	}
	
	@Override
	public CritterShape viewShape(){
		return CritterShape.TRIANGLE;
	}
	
	@Override
	public javafx.scene.paint.Color viewOutlineColor() { 
		return javafx.scene.paint.Color.AZURE; 
	}
	
	@Override
	public javafx.scene.paint.Color viewFillColor() {
		return javafx.scene.paint.Color.AZURE; 
	}
	
}

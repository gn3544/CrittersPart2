/* CRITTERS Critter1.java
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

/*
 * Critter1 is represented by a character '1'. CritterM always fights Algae to gain additonal energy
 * but they don't always fight other Critters. They decide to fight another Critter if they get an odd
 * random number. They decide to runaway in random direction if they get an even ramdom number 
 * (nubers range from 0 to 7). CritterK behaves very simply! They always walk to the right and 
 * They do not reproduce but only gain energy by consuming Algae. So the survival of their entire 
 * type depends on initial CritterK's surviving by eating Algae. 
 */

package assignment5;


public class Critter1 extends Critter {
	
	/**
	 * Describes Critter1 objects activity every time step
	 */
	@Override
	public void doTimeStep() {
		look(0, false);
		walk(0);
	}

	/**
	 * Describes Critter1 objects fighting strategy
	 */
	@Override
	public boolean fight(String type) { 
		if(type == "@"){
			return true;
		}
		else{
			int num = Critter.getRandomInt(8);
			if(num % 2 == 1){
				return true;
			}
		run(getRandomInt(8));	
		return false;
		}
	}
	
	/**
	 * Returns string characterization of Critter1
	 */
	@Override
	public String toString() { return "1"; }
	
	@Override
	public CritterShape viewShape(){
		return CritterShape.STAR;
	}
	
	@Override
	public javafx.scene.paint.Color viewOutlineColor() { 
		return javafx.scene.paint.Color.ORANGE; 
	}
	
	@Override
	public javafx.scene.paint.Color viewFillColor() {
		return javafx.scene.paint.Color.ORANGE; 
	}
}

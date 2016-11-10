/* CRITTERS Critter2.java
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

/* Here CritterM is Critter2 and CritterK is Critter1.
 * CritterM is represented by a chracter 'M.' CritterM always fights Algae to gain additonal energy
 * but they don't always fight other Critters. They decide to fight another Critter if they get an even
 * random number. They decide to runaway in random direction if they get an odd ramdom number 
 * (nubers range from 0 to 7). CritterM does not reproduce but it moves in random directions by 6 spots
 * in randome directions, 2 at a time. to encounter Algae more easily. They survice by eating Algae to 
 * obtain extra energy. If no energy, they die. Since no CritterM reproduces, they eventually go distinct 
 * if no new CritterM are palced.
 */

package assignment5;

public class Critter2 extends Critter {
	
	/**
	 * Describes Critter2 objects activity every time step
	 */
	@Override
	public void doTimeStep() {
		int dir = Critter.getRandomInt(8);
		look(dir, true);
		run(dir);
		run(dir);
		run(dir);
	}
	
	/**
	 * Describes Critter2 objects fighting strategy
	 */
	@Override
	public boolean fight(String type) { 
		if(type == "@"){
			return true;
		}
		else{
			int num = Critter.getRandomInt(8);
			if(num % 2 == 0){
				return true;
			}
		run(getRandomInt(8));
		return false;
		}
	}
	
	/**
	 * Returns string characterization of Critter2
	 */
	@Override
	public String toString() { return "2"; }

	@Override
	public CritterShape viewShape(){
		return CritterShape.SQUARE;
	}
	
	@Override
	public javafx.scene.paint.Color viewOutlineColor() { 
		return javafx.scene.paint.Color.BLACK; 
	}
	
	@Override
	public javafx.scene.paint.Color viewFillColor() {
		return javafx.scene.paint.Color.BLACK; 
	}
}

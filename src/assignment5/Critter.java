/* CRITTERS GUI <Critter.java>
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public abstract class Critter {
	/* NEW FOR PROJECT 5 */
	public enum CritterShape {
		CIRCLE,
		SQUARE,
		TRIANGLE,
		DIAMOND,
		STAR
	}
	
	/* the default color is white, which I hope makes critters invisible by default
	 * If you change the background color of your View component, then update the default
	 * color to be the same as you background 
	 * 
	 * critters must override at least one of the following three methods, it is not 
	 * proper for critters to remain invisible in the view
	 * 
	 * If a critter only overrides the outline color, then it will look like a non-filled 
	 * shape, at least, that's the intent. You can edit these default methods however you 
	 * need to, but please preserve that intent as you implement them. 
	 */
	public javafx.scene.paint.Color viewColor() { 
		return javafx.scene.paint.Color.WHITE; 
	}
	
	public javafx.scene.paint.Color viewOutlineColor() { return viewColor(); }
	public javafx.scene.paint.Color viewFillColor() { return viewColor(); }
	
	public abstract CritterShape viewShape(); 
	
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	protected String look(int direction, boolean steps) {
		
		int offset = 1, new_x = this.x_coord, new_y = this.y_coord;
		HashMap<ArrayList<Integer>, ArrayList<Critter>> coordMap = getCoordMap();
		if (steps){
			offset = 2;
		}
		
		if ((direction == 0) || (direction == 1) || (direction == 7)){
			new_x = this.x_coord + offset;
		}
		else if ((direction == 3) || (direction == 4) || (direction == 5)){
			new_x = this.x_coord - offset;
		}
		
		if ((direction == 1) || (direction == 2) || (direction == 3)){
			new_y = this.y_coord - offset;
		}
		else if ((direction == 5) || (direction == 6) || (direction == 7)){
			new_y = this.y_coord + offset;
		}
		
		ArrayList<Integer> coordinates = new ArrayList<Integer>(2);
		coordinates.set(0, new_x);
		coordinates.set(1, new_y);
 		this.energy -= Params.look_energy_cost;
		
		if (coordMap.containsKey(coordinates)){
			return coordMap.get(coordinates).get(0).toString();
		}
		return "";
	}
	
	/* rest is unchanged from Project 4 */
	
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
	protected final void walk(int direction) {}
	
	protected final void run(int direction) {}
	
	protected final void reproduce(Critter offspring, int direction) {}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	
	public static void worldTimeStep() {}
	
	public static void displayWorld() {
	}
	
	/* create and initialize a Critter subclass
	 * critter_class_name must be the name of a concrete subclass of Critter, if not
	 * an InvalidCritterException must be thrown
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {}
	
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		return null;
	}
	
	public static void runStats(List<Critter> critters) {}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correct up update your grid/data structure.
	 */
	
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}
	
	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
	}
	
	/**
	 * Gets a map that maps a coordinate to a list of critters on the coordinate
	 * @return HashMap<ArrayList<Integer>, ArrayList<Critter>> the map which contains the relation
	 * between coordinates to critters within the coordinate
	 */
	private static HashMap<ArrayList<Integer>, ArrayList<Critter>> getCoordMap(){
		HashMap<ArrayList<Integer>, ArrayList<Critter>> coordMap = new HashMap<ArrayList<Integer>, ArrayList<Critter>>();
		//coordMap is a mapping from coordinates to an arrayList of Critters to keep track of multiple Critters on a single coordinate
		for (Critter critter: population){
			ArrayList<Integer> coordinates = new ArrayList<Integer>(2);
			coordinates.add(0, critter.x_coord);
			coordinates.add(1, critter.y_coord);
			if (!coordMap.containsKey(coordinates)){
				coordMap.put(coordinates, new ArrayList<Critter>());
			}
			coordMap.get(coordinates).add(critter);
		}
		return coordMap;
	}
}

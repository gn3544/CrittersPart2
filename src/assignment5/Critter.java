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
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;


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
	private boolean alreadyMoved = false;
	private boolean isFighting = false;
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
		return null;
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
	
	/**
	 * Critter will walk 1 unit in the given direction
	 * @param direction denotes which direction the Critter object walks
	 */
	 protected final void walk(int direction) {
	 	int offset = 1; //changes direction of this by walking 1 unit
	 	int x_offset = 0, y_offset = 0;
	 	this.energy -= Params.walk_energy_cost;
	 	HashMap<ArrayList<Integer>, ArrayList<Critter>> coordMap = getCoordMap();
	 	
	 	if ((direction == 0) || (direction == 1) || (direction == 7)){
	 		x_offset = offset;
	 	}
	 	else if ((direction == 3) || (direction == 4) || (direction == 5)){
	 		x_offset = -1*offset;
	 	}
	 	
	 	if ((direction == 1) || (direction == 2) || (direction == 3)){
	 		y_offset = -1*offset;
	 	}
	 	else if ((direction == 5) || (direction == 6) || (direction == 7)){
	 		y_offset = offset;
	 	}
	 	
	 	if (!alreadyMoved){
	 		ArrayList<Integer> newCoord = new ArrayList<Integer>(2);
	 		newCoord.add(0, x_coord + x_offset);
	 		newCoord.add(1, y_coord + y_offset);
	 		if ((isFighting && !coordMap.containsKey(newCoord)) || !isFighting){
	 			x_coord += x_offset;
	 			y_coord += y_offset;
	 			alreadyMoved = true;
	 		}
	 	}
	 }
	
	 /**
	  * Critter will run 2 units in the given direction
	  * @param direction denotes which direction the Critter object walks
	  */
	 protected final void run(int direction) {
	 	int offset = 2; //changes direction of this by running 2 units
	 	int x_offset = 0, y_offset = 0;
	 	this.energy -= Params.run_energy_cost;
	 	HashMap<ArrayList<Integer>, ArrayList<Critter>> coordMap = getCoordMap();
	 	
	 	if ((direction == 0) || (direction == 1) || (direction == 7)){
	 		x_offset = offset;
	 	}
	 	else if ((direction == 3) || (direction == 4) || (direction == 5)){
	 		x_offset = -1*offset;
	 	}
	 	
	 	if ((direction == 1) || (direction == 2) || (direction == 3)){
	 		y_offset = -1*offset;
	 	}
	 	else if ((direction == 5) || (direction == 6) || (direction == 7)){
	 		y_offset = offset;
	 	}
	 	
	 	if (!alreadyMoved){
	 		ArrayList<Integer> newCoord = new ArrayList<Integer>(2);
	 		newCoord.add(0, x_coord + x_offset);
	 		newCoord.add(1, y_coord + y_offset);
	 		if ((isFighting && !coordMap.containsKey(newCoord)) || !isFighting){
	 			x_coord += x_offset;
	 			y_coord += y_offset;
	 			alreadyMoved = true;
	 		}
	 	}
	 }
	
	 /**
	  * Critter reproduces new offspring of the same type and moves it in given direction
	  * @param offspring denotes a new object to resemble the offspring
	  * @param direction denotes which direction the offspring walks
	  */
	 protected final void reproduce(Critter offspring, int direction) {
	 	int x_offset = 0, y_offset = 0; //changes direction of offspring
	 	if ((direction == 0) || (direction == 1) || (direction == 7)){
	 		x_offset = 1;
	 	}
	 	else if ((direction == 3) || (direction == 4) || (direction == 5)){
	 		x_offset = -1;
	 	}
	 	
	 	if ((direction == 1) || (direction == 2) || (direction == 3)){
	 		y_offset = -1;
	 	}
	 	else if ((direction == 5) || (direction == 6) || (direction == 7)){
	 		y_offset = 1;
	 	}
	 	try{
	 		if (this.energy > 0 && this.energy >= Params.min_reproduce_energy){
	 			offspring = (Critter) this.getClass().newInstance();
	 			offspring.energy = this.energy/2; //rounding down energy
	 			this.energy -= offspring.energy; //rounding up energy
	 			offspring.x_coord = this.x_coord + x_offset;
	 			offspring.y_coord = this.y_coord + y_offset;
	 			babies.add(offspring);
	 		}
	 	}
	 	catch (Exception e1){ //must catch exceptions thrown by newInstance() 
	 		
	 	}
	 }

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	
	/**
	 * Conducts all activities done by critters for 1 time step
	 */
	 public static void worldTimeStep() {
	 	
	 for (Critter critter: population){
	 	critter.alreadyMoved = false;
	 }
	 
	 for (Critter critter: population){ //first do time step, precondition is that all are alive
	 	critter.doTimeStep();
	 	
	 }

	 wrapAround();
	 
	 for (int i = 0; i < population.size(); i++){ //remove dead critters from doTimeStep()
	 	if (population.get(i).getEnergy() <= 0){
	 		population.remove(i);
	 		i--;
	 	}
	 }
	 
	 //getCoordMap()
	 encounter(); //fix all encounters
	 wrapAround();
	 
	 for (Critter critter: population){ //update rest energy
	 	critter.energy -= Params.rest_energy_cost;
	 }
	 
	 for (int i = 0; i < population.size(); i++){ //remove dead critters after encounter and staying still
	 	if (population.get(i).getEnergy() <= 0){
	 		population.remove(i);
	 		i--;
	 	}
	 }
	 
	 generateAlgae();
	 population.addAll(babies);
	 babies.clear();
	 }

	/**
	  * Performs all encounters with critters in the same coordinates
	  */
	 private static void encounter(){
	 	 	 for (int i = 0; i < population.size(); i++){
	 	 	for (int j = i + 1; j < population.size(); j++){
	 	 		if (population.get(i) != null && population.get(j) != null){
	 		 		if (population.get(i).x_coord == population.get(j).x_coord && population.get(i).y_coord == population.get(j).y_coord){
	 		 			critterEncounter(population.get(i), population.get(j));
	 		 		}
	 	 		}
	 	 	}
	 	 }
	 	 for (int i = 0; i < population.size(); i++){
	 		 if (population.get(i) == null){
	 			 population.remove(i);
	 			 i--;
	 		 }
	 	 }
	 }
	 
	 /**
	  * Performs a specific encounter between two Critters within the same coordinate
	  * @param A is the first Critter
	  * @param B is the second Critter
	  */
	 private static void critterEncounter(Critter A, Critter B){
	 	
	 	boolean fightA = false, fightB = false;
	 	int rollA = 0, rollB = 0, removeIndex;
	 	if (A.energy > 0 && B.energy > 0){
	 		B.isFighting = true;
	 		A.isFighting = true;
	 		fightA = A.fight(B.toString()); //invoke the fight, add flag for walking and running
	 		fightB = B.fight(A.toString());
	 		B.isFighting = false;
	 		A.isFighting = false;
	 	}
	 	if (A.energy > 0 && B.energy > 0 && (A.x_coord == B.x_coord) && (A.y_coord == B.y_coord)){
	 		if (fightA){ //roll the dies
	 			rollA = Critter.getRandomInt(A.energy + 1);
	 		}
	 		
	 		if (fightB){
	 			rollB = Critter.getRandomInt(B.energy + 1);
	 		}
	 		
	 		if (rollA >= rollB){ //by default A is winner if rollA == rollB
	 			A.energy += B.energy/2; //if we round down on divide by 2
	 			B.energy = 0;
	 			removeIndex = population.indexOf(B);
	 			population.set(removeIndex, null); //maintains the invariant that only alive critters are on the board without changing indices
	 		}
	 		else{
	 			B.energy += A.energy/2;
	 			A.energy = 0;
	 			removeIndex = population.indexOf(A);
	 			population.set(removeIndex, null);
	 		}
	 	}
	 }
	 
	 /**
	  * Generates Algae at every time step
	  */
	 private static void generateAlgae(){
	 	try{
	 		for (int i = 0; i < Params.refresh_algae_count; i++){
	 			makeCritter("Algae");
	 		}
	 	}
	 	catch (Exception e1){
	 		
	 	}
	}
	 
	 /**
	  * create and initialize a Critter subclass.
	  * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	  * an InvalidCritterException must be thrown.
	  * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	  * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	  * an Exception.)
	  * @param critter_class_name denotes the string representing a class type that extends Critter
	  * @throws InvalidCritterException denotes that no Critter class type exists with the name critter_class_name
	  */
	 public static void makeCritter(String critter_class_name) throws InvalidCritterException {
	 	try{
	 		Class<?> critterClass = Class.forName(myPackage + "." + critter_class_name);
	 		Critter newCritter = (Critter) critterClass.newInstance();
	 		newCritter.x_coord = getRandomInt(Params.world_width) + 1;
	 		newCritter.y_coord = getRandomInt(Params.world_height) + 1;
	 		newCritter.energy = Params.start_energy;
	 		population.add(newCritter);
	 	}
	 	catch (ClassNotFoundException e1){
	 		throw new InvalidCritterException(critter_class_name);
	 	}
	 	catch (InstantiationException e2){
	 		throw new InvalidCritterException(critter_class_name);
	 	}
	 	catch (IllegalAccessException e3){
	 		throw new InvalidCritterException(critter_class_name);
	 	}
	 	finally{
	 	}
	 }
	 
	 /**
	  * Gets a list of critters of a specific type.
	  * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	  * @return result denotes a list of Critters
	  * @throws InvalidCritterException denotes that no Critter class type exists with the name critter_class_name
	  */
	 public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		 List<Critter> result = new java.util.ArrayList<Critter>();
	 	 try{
	 		 Class<?> critterClass = Class.forName(myPackage + "." + critter_class_name);
	 		 for (Critter critter: population){
				if (critter.getClass().equals(critterClass)){ 
	 		 			result.add(critter);
	 		 		}
	 		 	}
	 		}
	 	catch (ClassNotFoundException e1){
	 		throw new InvalidCritterException(critter_class_name);
	 		}
	 		 	return result;
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
		population.clear();
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
			if (critter != null){
				ArrayList<Integer> coordinates = new ArrayList<Integer>(2);
				coordinates.add(0, critter.x_coord);
				coordinates.add(1, critter.y_coord);
				if (!coordMap.containsKey(coordinates)){
					coordMap.put(coordinates, new ArrayList<Critter>());
				}
				coordMap.get(coordinates).add(critter);
			}
		}
		return coordMap;
	}
	
	/**
	 * This method draw each critter on the board 
	 * after updating its positions correctly
	 */
	public static void displayWorld(){
		//iterate critter collection to take care of wrap-arounds
		wrapAround();
		//construct a map of size World_Height by World_Width
		Main.worldStageGrid.setPadding(new Insets(10,10,10,10));
		Main.size2 = 850/Params.world_height;
		Main.size1 = 1350/Params.world_width;
		int size;
		if(Main.size2 < Main.size1) size = Main.size2; else size = Main.size1;
		for(int i = 0; i < Params.world_width; i++){
			for(int j = 0; j < Params.world_height; j++){
				Shape s = new Rectangle(size, size);
				s.setFill(null); 
				s.setStroke(Color.BLACK);
				Main.worldStageGrid.add(s, i, j); 
			}
		}
		//add all existing critters on the board
		for(Critter c : population){
			if (c != null){
				Shape s = null;
				/*
				if(s.getClass().equals(new Algae().getClass())){
					Shape crit = new Circle(size/2);
					crit.setFill(Color.RED);
					crit.setStroke(Color.BROWN);
					Main.worldStageGrid.add(crit, s.x_coord, s.y_coord);
				}
				else if(s.getClass().equals(new Craig().getClass())){
					Shape crit = new Rectangle(size, size);
					crit.setFill(Color.BLUE);
					crit.setStroke(Color.WHITE);
					Main.worldStageGrid.add(crit, s.x_coord, s.y_coord);
				}
				*/
				switch (c.viewShape()) {
				case CIRCLE:
					s = new Circle(size/2);
				case SQUARE:
					s = new Rectangle(size, size);
					/*
				case TRIANGLE: //HOW TO FIX THIS?
					s = new Triangle(size);
				case DIAMOND:
					s = new Diamond(size);
				case STAR:
					s = new Star(size);
					*/
				}
				s.setFill(c.viewFillColor()); //PLEASE CHECK THIS WHOLE SECTION!
				s.setStroke(c.viewOutlineColor());
				Main.worldStageGrid.add(s, c.x_coord, c.y_coord);
			}
		}
		//show window
		Main.worldStage.setScene(Main.worldScene);
		Main.worldStage.show();
	}
	
	private static void wrapAround(){
		for(Critter s : population){
			//take care of wraparounds for walk and run
			if(s.y_coord >= Params.world_height){
				s.y_coord = s.y_coord % Params.world_height;
			}
			else if(s.y_coord < 0){
				s.y_coord = (s.y_coord % Params.world_height) + Params.world_height;
			}
			
			if(s.x_coord >= Params.world_width){
				s.x_coord = s.x_coord % Params.world_width;
			}
			else if(s.x_coord < 0){
				s.x_coord = (s.x_coord % Params.world_width) + Params.world_width;
			}
		} 
	}

}

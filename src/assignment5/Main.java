/* CRITTERS GUI <Main.java>
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.Scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application{
	
	static GridPane worldStageGrid = new GridPane();
	static Stage worldStage = new Stage();
	static Scene worldScene = new Scene(worldStageGrid, 1400, 900); // width, height
	static int size1;
	static int size2;
	
	public static void main(String[] args) { 
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Two stages for VIEW and CONTROL
		Stage worldStage = new Stage();
		worldStage = primaryStage;
		Stage controlStage = new Stage();
		
		worldStage.setTitle("Critter World");
		controlStage.setTitle("Controller");
		 
		/*view components*/
		/*view components*/
		/*view components*/
		/*view components*/
		/*view components*/	
		Critter.displayWorld();
		
		/*controller components*/
		/*controller components*/
		/*controller components*/
		/*controller components*/
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(20);
		grid.setHgap(10);
		//quit command
		Button quitBtn = new Button();
		quitBtn.setText("Q U I T");
		GridPane.setConstraints(quitBtn, 0, 5);
		//show command
		Button showBtn = new Button();
		showBtn.setText("S H O W");
		GridPane.setConstraints(showBtn, 0, 6);
		//step command
		TextField stepInput = new TextField();
		stepInput.setPromptText("number of steps");
		Button stepBtn = new Button(); 
		stepBtn.setText("step");
		stepBtn.setFont(new Font(20));
		GridPane.setConstraints(stepInput, 0, 0);
		GridPane.setConstraints(stepBtn, 1, 0);
		//seed command
		TextField seedInput = new TextField();
		seedInput.setPromptText("number of seeds");
		Button seedBtn = new Button();
		seedBtn.setText("seed");
		seedBtn.setFont(new Font(20));
		GridPane.setConstraints(seedInput, 0, 1);
		GridPane.setConstraints(seedBtn, 1, 1);
		//make command
		TextField makeType = new TextField();
		makeType.setPromptText("Type");
		TextField makeNum = new TextField();
		makeNum.setPromptText("Number");
		Button makeBtn = new Button();
		makeBtn.setText("make");
		makeBtn.setFont(new Font(20));
		GridPane.setConstraints(makeType, 0, 2);
		GridPane.setConstraints(makeNum, 1, 2);
		GridPane.setConstraints(makeBtn, 2, 2);
		//stats command
		TextField statsInput = new TextField();
		statsInput.setPromptText("Type");
		Button statsBtn = new Button();
		statsBtn.setText("stats");
		statsBtn.setFont(new Font(20));
		GridPane.setConstraints(statsInput, 0, 3);
		GridPane.setConstraints(statsBtn, 1, 3);
		//controller board label
		Label title = new Label("CONTROLLER BOARD");
		GridPane.setConstraints(title, 1, 5);
		//toggle button for animation
		ToggleButton tg = new ToggleButton();
		tg.setText("A N I M A T I O N");
		GridPane.setConstraints(tg, 0, 7);
		
		grid.getChildren().addAll(title, stepInput, stepBtn, seedInput, seedBtn, makeType, makeNum, makeBtn, statsInput, statsBtn, showBtn, quitBtn, tg);
		
		Scene scene = new Scene (grid, 500, 700);
		controlStage.setScene(scene);
		controlStage.show();
		
		/*event handlers for controller board*/
		quitBtn.setOnAction(e -> {
			controlStage.close();
			
		});
		showBtn.setOnAction(e -> {
			Critter.displayWorld();
		});
		stepBtn.setOnAction(e -> {
			try{
				int numStep = Integer.parseInt(stepInput.getText());
				if(numStep < 0)
					ErrorMessageBox.displayError("Processing Error - negative input", "please enter nonnegative integer");
				for(int i = 0; i < numStep; i ++){
					Critter.worldTimeStep();
				}
			}
			catch(NumberFormatException e1){
				ErrorMessageBox.displayError("Processing Error - NumberFormatException", "please enter nonnegative integer");
			}

		});
		seedBtn.setOnAction(e -> {
			try{
			Long numSeed = Long.parseLong(seedInput.getText());	
			if(numSeed < 0)
				ErrorMessageBox.displayError("Processing Error", "please enter nonnegative number");
			else
				Critter.setSeed(numSeed);			
			}
			catch(NumberFormatException e1){
				ErrorMessageBox.displayError("Processing Error", "please enter nonnegative number");
			}

		});
		makeBtn.setOnAction(e -> {
			String critterType = makeType.getText();			
			try{
				String input = makeNum.getText();
				if(input.length() == 0){
					Critter.makeCritter(critterType);
				}
				else{
					int critterNum = Integer.parseInt(input);
					if(critterNum < 0)
						ErrorMessageBox.displayError("Processing Error", "please enter nonnegative integer");
					else
						for(int i = 0; i < critterNum; i ++)
							Critter.makeCritter(critterType);			 			
				}
			}
			catch(InvalidCritterException e1){
				ErrorMessageBox.displayError("Processing Error - InvalidCritterException", "please enter valid critter type");
			}
			catch(NumberFormatException e1){
				ErrorMessageBox.displayError("Processing Error - NumberFormatException", "please enter nonnegative integer");
			}
		}); 
		statsBtn.setOnAction(e -> {
			try{
			String className = statsInput.getText();
			String myPackage = Critter.class.getPackage().toString().split(" ")[1]; //get package
			List<Critter> instances = Critter.getInstances(className); //get list of specific critter instances
			Class <?> cls = Class.forName(myPackage + "." + className); //get class type object
			if(!(Modifier.isAbstract( cls.getModifiers() ))){ //if concrete class, perform "stats"
				Method method = cls.getMethod("runStats", List.class);
				method.invoke(cls, instances);				
			}
			if(Modifier.isAbstract( cls.getModifiers())){ //if abstract class, do not perform "stats"
				ErrorMessageBox.displayError("Processing Error - Abstract Class", "That was an absract class!");
			}
			}
			catch(InvalidCritterException e1){
				ErrorMessageBox.displayError("Processing Error - InvalidCritterException", "please enter valid critter type");
			} 
			catch(ClassNotFoundException e1){
				ErrorMessageBox.displayError("Processing Error - ClassNotFoundException", "please enter valid critter type");
			}
			catch(NoSuchMethodException e1){ 
				ErrorMessageBox.displayError("Processing Error", "NoSuchMethodFoundException");
			}
			catch(InvocationTargetException e1){
				ErrorMessageBox.displayError("Processing Error", "InvocationTargetException");
			}
			catch(IllegalArgumentException e1){
				ErrorMessageBox.displayError("Processing Error", "IllegalArgumentException");
			}
			catch(IllegalAccessException e1){
				ErrorMessageBox.displayError("Processing Error", "IllegalAccessException");
			}
		});
		tg.setOnAction(e -> {
			 
		});
	}

}

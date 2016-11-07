package assignment5;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.Scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application{

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
		
		//controller components
		Button quitBtn = new Button();
		quitBtn.setText("QUIT");
		Button showBtn = new Button();
		showBtn.setText("SHOW");
		Button stepBtn = new Button(); 
		stepBtn.setText("step");
		//TextField 
		Button seedBtn = new Button();
		seedBtn.setText("seed");
		Button makeBtn = new Button();
		makeBtn.setText("make");
		Button statsBtn = new Button();
		statsBtn.setText("stats");
		VBox commands = new VBox(stepBtn, seedBtn, makeBtn, statsBtn);
		HBox gameMenu = new HBox(quitBtn, showBtn);		
		commands.setSpacing(10);
		commands.setPadding(new Insets(20,20,20,20));
		gameMenu.setSpacing(10);
		gameMenu.setPadding(new Insets(20,20,20,20));
		
		FlowPane root = new FlowPane();
		root.getChildren().addAll(commands, gameMenu);
		
		Scene scene = new Scene (root, 300, 250);
		controlStage.setScene(scene);
		controlStage.show();
	}

}

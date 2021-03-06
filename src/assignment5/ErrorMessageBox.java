/* CRITTERS GUI <ErrorMessageBox.java>
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

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.*;

public class ErrorMessageBox{
	 
	/**
	 * display pop-up error message box that describes error type
	 * and print out an instruction message for the user
	 * @param title title of the window is the erro type
	 * @param message message in the window is the instruction
	 */
	public static void displayError(String title, String message){
		Stage window = new Stage();
		//create error message window
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(450);
		window.setMinHeight(200);
		//label holds the error message
		Label label = new Label();
		label.setText(message);
		label.setFont(new Font(25));
		Button closeBtn = new Button("Close");
		closeBtn.setOnAction(e -> window.close());
		//VBox layout for the button and label
		VBox layout = new VBox(80);
		layout.getChildren().addAll(label, closeBtn);
		layout.setPadding(new Insets(60,20,20,20));
		layout.setAlignment(Pos.CENTER);
		//set scene, and show window
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}

}

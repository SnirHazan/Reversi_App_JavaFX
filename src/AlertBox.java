package myapp;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * Class of AlertBox.
 *
 */
public class AlertBox {
	
	/**
	 * This function display the AlertBox.
	 * @param title - the title of the window.
	 * @param message - the message to the user.
	 */
	static void Display(String title, String message){
		
		Stage win = new Stage();
		win.setTitle(title);
		
		win.initModality(Modality.APPLICATION_MODAL);
		win.setMinWidth(20);
		
		Label l = new Label();
		l.setText(message);
		l.setFont(new Font(20.0));
		Button exit = new Button("Exit");
		win.setOnCloseRequest(e ->{
			win.close();
		});
		exit.setOnAction(e -> {
			win.close();
			
		});
		
		VBox lay = new VBox(10);
		lay.getChildren().addAll(l,exit);
		lay.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(lay,350,150);
		win.setScene(scene);
		
		win.showAndWait();
	
	}

}

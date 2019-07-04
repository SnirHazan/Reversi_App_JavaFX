package myapp;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * Class of ConfirmBox.
 */
public class ConfirmBox {

	static String answer;
	Button yes;
	Button no;
/**
 * @param title - the title of the window.
 * @param message - the question we ask the user.
 * @return answer - the answer of the user.
 */
	static String Display(String title, String message){

		Stage win = new Stage();
		win.setTitle(title);
		win.initModality(Modality.APPLICATION_MODAL);
		win.setMinWidth(300);

		Label l = new Label();
		l.setText(message);

		Button yes = new Button("yes");
		yes.setOnAction(e -> {
			answer = yes.getText();
			win.close();
		});

		Button no = new Button("no");
		no.setOnAction(e -> {
			answer = no.getText();
			win.close();
		});

		win.setOnCloseRequest(e ->{
			answer = no.getText();
			win.close();
		});

		VBox lay = new VBox(10);
		lay.getChildren().addAll(l,yes,no);
		lay.setAlignment(Pos.CENTER);

		Scene scene = new Scene(lay,150,150);
		win.setScene(scene);
		win.showAndWait();

		return answer;

	}
}

package myapp;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
/**
 * Main class.
 */
public class Main extends Application {
	private Stage root1 = null;
	
	public void start(Stage primaryStage) {
		
		try {
			VBox root = (VBox)FXMLLoader.load(getClass().getResource("ReversiGame.fxml"));
			Scene scene = new Scene(root,800,600);
			primaryStage.setOnCloseRequest(e->{
				String ans = ConfirmBox.Display("Exit", "Do you really want to exit?");
				if(ans == "yes"){
					primaryStage.close();
				}
				e.consume();
			});
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			root1 = primaryStage;
			primaryStage.setTitle("Reversi Game");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	public Stage getStage(){
		return this.root1;
	}

}
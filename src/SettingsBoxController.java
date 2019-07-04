package myapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * Class of SettingsBoxController.
 */
public class SettingsBoxController implements Initializable{
	@FXML
	public Button Exit = new Button();
	@FXML
	public Button Apply = new Button();
	@FXML
	public ChoiceBox<String> beginning = new ChoiceBox<>();
	@FXML
	public ComboBox<String> SizeBoard = new ComboBox<>();
	@FXML
	public ColorPicker player1Color = new ColorPicker();
	@FXML
	public ColorPicker player2Color = new ColorPicker();

	Main m = new Main();
	private final int minBoardSize = 4;
	private final int maxBoardSize = 20;
	/**
	 * This function that called by a click on apply button.
	 * call to WriteConfigToFile function ad close the settings window.
	 */
	public void ApplyClick() {

		WriteConfigToFile();
		Stage stage = (Stage) Apply.getScene().getWindow();
		stage.close();
	}
	/**
	 * This function that called by a click on Exit button.
	 * Close the settings window.
	 */
	public void ExitClick() {

		Stage stage = (Stage) Exit.getScene().getWindow();
		stage.close();

	}
	/**
	 * This function write a new configuration
	 * file according to the user choices.
	 */
	private void WriteConfigToFile() {

		PrintWriter writer = null;

		try{
			writer = new PrintWriter(
					new OutputStreamWriter(
							new FileOutputStream(new File("settings.txt"))));

		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			writer.println("start_player;" + beginning.getValue());
			writer.println("color_player1;" + player1Color.getValue().toString());
			writer.println("color_player2;" + player2Color.getValue().toString());
			writer.println("board_size;" + SizeBoard.getValue());
			writer.close();
		}

	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		beginning.getItems().addAll("player1","player2");
		beginning.setValue("player1");

		for(int i = minBoardSize ; i <= maxBoardSize ; i+=2) {
			SizeBoard.getItems().add(String.valueOf(i));
		}
		SizeBoard.setVisibleRowCount(5);
		readFromFile();

	}
	/**
	 * This function read the setting os the game from the settings file,
	 * and update the the default values of the settings tools.
	 */
	private void readFromFile(){
		File f = new File("settings.txt");
		//if file exist - break lines to fileds
		if(f.exists()) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(f.getAbsolutePath()));
				// read all lines into the buffer
				String line = reader.readLine();
				while (line != null) {
					StringBuilder contentBuffer = new StringBuilder();
					contentBuffer.append(line.trim());
					String[] parts = contentBuffer.toString().split(";");
					String s1 = parts[0];
					String s2 = parts[1];

					if(s1.equals("start_player")) {
						beginning.setValue(s2);
					} else if(s1.equals("color_player1")) {
						player1Color.setValue(Color.web(s2));
					} else if(s1.equals("color_player2")) {
						player2Color.setValue(Color.web(s2));
					} else if(s1.equals("board_size")) {
						SizeBoard.setValue(s2);
					}

					line = reader.readLine();
				}
				reader.close();
			} catch (Exception e) {
				System.out.println("not found");
			}

		} else { 
			//if not exist - default
			beginning.setValue("player1");
			player1Color.setValue(Color.BLACK);
			SizeBoard.setValue("8");
		}
	}


}


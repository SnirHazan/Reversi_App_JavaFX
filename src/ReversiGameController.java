package myapp;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Class of ReversiGameController.
 */
public class ReversiGameController implements Initializable {
	@FXML
	private VBox root;
	@FXML
	private HBox son;
	@FXML
	private Label currentPlayer = new Label();
	@FXML
	private Label xPoints = new Label("2");
	@FXML
	private Label oPoints = new Label("2");
	@FXML
	private Button newGame = new Button();
	@FXML
	private ReversiBoard reversiBoard;
	@FXML
	private MenuBar bar = new MenuBar();
	@FXML
	private Menu reversi = new Menu("Reversi");
	@FXML
	private Menu info = new Menu("Info");
	@FXML
	private MenuItem exit = new MenuItem("Exit");
	@FXML
	private MenuItem settings = new MenuItem("Settings");
	@FXML
	private MenuItem creators = new MenuItem("Rules");
	@FXML
	Hyperlink link = new Hyperlink();
	
	private SettingBox set = new SettingBox();

	private Main m = new Main();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		reversiBoard = new ReversiBoard(this);

		reversiBoard.setPrefWidth(400);
		reversiBoard.setPrefHeight(400);
		son.getChildren().add(0,reversiBoard);

		root.widthProperty().addListener((observable, oldValue, newValue) -> {
			double boardNewWidth = newValue.doubleValue() - 200;
			reversiBoard.setPrefWidth(boardNewWidth);
			reversiBoard.draw();
		});

		root.heightProperty().addListener((observable, oldValue, newValue) -> {
			reversiBoard.setPrefHeight(newValue.doubleValue());
			reversiBoard.draw();
		});
		exit.setOnAction(event -> {

			String ans = ConfirmBox.Display("Exit", "Are you sure?");
			if(ans.equals("yes")) {
				Stage window =(Stage) root.getScene().getWindow();
				window.close();
			}
		});
		settings.setOnAction(event ->{
				set.Display("Settings");	
		});
		
		creators.setOnAction(e->{
			AlertBox.Display("Info", " Cohen & Hazan Ltd.");
		});
	}
	/** 
	 * @return xPoints - int the point of the first player.
	 */
	public Label getxPoints() {
		return xPoints;
	}
	/** 
	 * @return oPoints - int the point of the second player.
	 */
	public Label getoPoints() {
		return oPoints;
	}
	/**
	 * @return Label - currentPlayer Label.
	 */
	public Label getCurrentPlayer() {
		return currentPlayer;
	}
	/**
	 * @return Char - currentPlayer symbol.
	 */
	public char getCurrentPlayerSymbol(){
		if(this.getCurrentPlayer().getText().equals("player1")){
			return 'X';
		}
		return 'O';
	}
	/**
	 * This function switch current player.
	 */
	public void switchPlayer(){
		if(this.getCurrentPlayerSymbol() == 'X'){
			this.currentPlayer.setText("player2");
		} else {
			this.currentPlayer.setText("player1");
		}
	}
	/**
	 * This function called when the user click on the new game button.
	 * the function create a new game with the new settings(if the user change it's).
	 */
	public void newGameClick(){
		Stage window =(Stage) root.getScene().getWindow();
		m.start(new Stage());
		window.close();
	}
	public void rulesOpenBrowser() throws IOException, URISyntaxException{
		if (Desktop.isDesktopSupported()) {
		    Desktop.getDesktop().browse(new URI("https://en.wikipedia.org/wiki/Reversi"));
		}
	}
}

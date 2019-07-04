package myapp;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
/**
 * class of ReversiBoard extenf GridPane 
 * @author Oren
 * this is the javafx board
 */
public class ReversiBoard extends GridPane {
	private Main m = new Main();
	private GameLogic gl;
	private static final char PLAYER_1 = 'X';
	private static final char PLAYER_2 = 'O';
	private ReversiGameController rgc;
	/**
	 * constructor of ReversiBoard.
	 * @param rgc - ReversiGameController is an object of the controller of Reversi Game
	 */
	public ReversiBoard(ReversiGameController rgc) {
		this.gl = new GameLogic();
		this.rgc = rgc;
		rgc.getCurrentPlayer().setText(gl.firstPlayer);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiBoard.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
	/**
	 * draw this GridPane (Board)
	 */
	public void draw() {

		boolean noMoveFlag = false;
		boolean endGameFlag = false;
		ArrayList<Point> start_points = new ArrayList<Point>();
		ArrayList<Point> end_points = new ArrayList<Point>();

		//finds the cuurent player options
		gl.find_options(rgc.getCurrentPlayerSymbol(),start_points,end_points);
		// if has options draw this board and his options
		//else:
		if(start_points.isEmpty()){
			rgc.switchPlayer();
			start_points.clear();
			end_points.clear();
			//raise this flag - meaning this player cannot move
			noMoveFlag =true;
			//search for other player options - if has option - draw , else end of game
			gl.find_options(rgc.getCurrentPlayerSymbol(),start_points,end_points);
			if(start_points.isEmpty()){
				//2 players not have a move- end of game
				endGameFlag = true;
			}
		} else { 
			//before drawing - clear board
			this.getChildren().clear();
		}
		//set points to Game controller
		this.rgc.getxPoints().setText(String.valueOf(gl.getBoard().player_points('X')));
		this.rgc.getoPoints().setText(String.valueOf(gl.getBoard().player_points('O')));

		double height = (double)this.getPrefHeight() - 75;
		double width = (double)this.getPrefWidth() - 75;
		double size = gl.getBoard().getSize();
		double cellHeight = (double)height /(double) size;
		double cellWidth = (double)width / (double) size;
		//run all matrix and for each cell create a rectangle - if needed circle add it later
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {

				Rectangle rect = new Rectangle(cellWidth, cellHeight, Color.web("0xfddb6e"));
				rect.setStroke(Color.BLACK.darker().darker());
				if (gl.getBoard().get_cell(i, j) == PLAYER_1) {
					this.add(rect, j, i);
					this.addDisk(j , i , cellHeight, start_points, end_points, gl.get_player(1).getColor());
				} else if(gl.getBoard().get_cell(i, j) == PLAYER_2) {
					this.add(rect, j, i);
					this.addDisk(j , i , cellHeight, start_points, end_points, gl.get_player(2).getColor());
				} else  {
					this.add(rect, j, i);
				}
			}
		}
		//add option to board
		for (Point point : start_points) {
			this.addDisk(point.getY(), point.getX(), cellHeight, start_points, end_points, Color.TRANSPARENT);
		}
		//if end of game - alert box
		if(gl.board_full() || endGameFlag){
			endOfGame();
		} else if(noMoveFlag==true){ // if just 1 player not have move
			AlertBox.Display("Reversi", "No available move!");
		}
	}
	/**
	 * prints message to screen - winning player is.
	 */
	private void endOfGame(){
		int player1Point = Integer.parseInt(this.rgc.getxPoints().getText());
		int player2Point = Integer.parseInt(this.rgc.getoPoints().getText());
		String message = ""; 
		
		if(!gl.board_full()) {
			message = "No Available moves for both players\n";

		}
	
		if(player2Point > player1Point) {
			message = message + "THE WINNER IS PLAYER2";
		} else if ( player1Point == player2Point) {
			message = message + "TIKO TIKO SHIVAION";
		} else {
			message = message + "THE WINNER IS PLAYER1";
		}
		AlertBox.Display("End of Game", message);
		Stage window =(Stage) this.rgc.getCurrentPlayer().getScene().getWindow();
		this.m.start(new Stage());
		window.close();

	}
	/**
	 * add 3D disk to grid, and add a listener for circles 
	 * the listener is activate in mouse click - and play 1 turn.
	 * @param j - int col of grid
	 * @param i - int row of grid
	 * @param cellHeight -int size of cell in grid
	 * @param start_points - ArrayList<Points> start points for play 1 turn
	 * @param end_points -  ArrayList<Points> end points for play 1 turn
	 * @param playerColor - Color color of circle
	 */
	private void addDisk(int j, int i, double cellHeight ,ArrayList<Point> start_points,ArrayList<Point> end_points,Color playerColor){
		Circle down_circle;
		if(playerColor.toString().equals("0x000000ff")){
			down_circle = new Circle(cellHeight/2,Color.WHITE);
		} else if(playerColor.toString().equals("0xffffffff")) {
			down_circle = new Circle(cellHeight/2,Color.BLACK);
		} else {
			down_circle = new Circle(cellHeight/2,Color.TRANSPARENT);
		}
		down_circle.setStroke(Color.BLACK);
		this.add(down_circle, j, i);

		Circle up_circle = new Circle(cellHeight/2 - 1,playerColor);
		up_circle.setStroke(Color.BLACK);
		GridPane.setHalignment(up_circle, HPos.RIGHT);
		GridPane.setValignment(up_circle, VPos.TOP);

		this.add(up_circle, j, i);

		if(playerColor.toString().equals("0x00000000")){
			up_circle.setOnMouseEntered(e-> {
				char symbol = rgc.getCurrentPlayerSymbol();
				if(symbol == 'X'){
					up_circle.setFill(this.gl.get_player(1).getColor());
				} else {
					up_circle.setFill(this.gl.get_player(2).getColor());
				}
			});
			up_circle.setOnMouseExited(e-> {
				up_circle.setFill(Color.TRANSPARENT);
			});
		}

		up_circle.setOnMouseClicked(event ->{
			int row = GridPane.getRowIndex(up_circle);
			int col = GridPane.getColumnIndex(up_circle);

			String s = this.rgc.getCurrentPlayer().getText();
			if(s.equals(gl.get_player(1).getName())){
				if(gl.play_one_turn(gl.get_player(1).getSymbol(),start_points,end_points, row, col) != -1) {
					rgc.getCurrentPlayer().setText(gl.get_player(2).getName());
					this.draw();
				}
			} else {
				if(gl.play_one_turn(gl.get_player(2).getSymbol(),start_points,end_points, row, col) != -1) {
					rgc.getCurrentPlayer().setText(gl.get_player(1).getName());
					this.draw();
				}
			}
		});
	}

}


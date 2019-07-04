package myapp;
import javafx.scene.paint.Color;
/**
 * Class of Player
 */
public class Player {
	
	private String name;
	private char symbol;
	private Color color = null;
	
	/**
	 * @param n - String the name of the player.
	 * @param symbol - char the symbol of the player.
	 */
	public Player(String n, char symbol){
		this.name = n;
		this.symbol = symbol;
	}
	
	/**
	 * @return char - the symbol of the player.
	 */
	public char getSymbol(){
		return this.symbol;
	}
	
	/**
	 * @return String - the name of the player.
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * @return color - Color the color of the player.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * This function set a new color to the player.
	 * @param color - Color the new color of the player.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

}

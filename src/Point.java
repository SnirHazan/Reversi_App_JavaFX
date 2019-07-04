package myapp;
/**
 * Class of Point.
 */
public class Point implements Comparable<Object> {
	
	private int xCor;
	private int yCor;
	
	/**
	 * The constructor of class point
	 * @param x - int the x coordinate of the point.
	 * @param y - int the y coordinate of the point.
	 */
	public Point(int x , int y){
		
		this.xCor = x;
		this.yCor = y;
	}	
	
	/**
	 * @return int the x coordinate of the point.
	 */
	public int getX(){
		return this.xCor;
	}
	
	/**
	 * @return int the y coordinate of the point.
	 */
	public int getY(){
		return this.yCor;
	}
	/**
	 * This function check if Point p is equal to this point.
	 * @param p - Point the point we want to check if equal to this point.
	 * @return true - Boolean if the points are equal, other - Boolean false.
	 */
	public boolean isEqual(Point p){
		if(this.xCor == p.getX() && this.yCor == p.getY()) {
			return true;
		}
		return false;
	}
	@Override
	public String toString(){
		return "("+ (this.getX()) + "," + (this.getY()) + ") ";
	}
	
	/**
	 * This function check if the point is a valid point.
	 * @return false - Boolean if the point is'nt valid,
	 *         true - Boolean if the point is valid.
	 */
	public boolean valid_point() {
		if (this.getX() < 0 || this.getY() < 0){
			return false;
		}
		return true;
	}
	@Override
	public int compareTo(Object other){
		if(other instanceof Point){
			Point p1 = (Point) other;
			if(this.xCor == p1.getX() && this.yCor == p1.getY()){
				return 0;
			} else {
				return -1;
			}
		} else {
			return -2;
		}
	}

}

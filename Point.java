package kaleidoscope;

/**
 * @author James Park
 * @author Siyang Shu
 * 
 * This a class representing the point on the canvas.
 *
 */
public class Point {
	public double x;
	public double y;
	
	/**
	 * @param x: x coordinates
	 * @param y: y coordinates
	 */
	Point(double x, double y){
		this.x = x;
		this.y = y;
	}

	/**
	 * @param radius: rotation radius. anticlockwise
	 * rotate the point 
	 */
	public void rotate(double radius){
		double length = Math.sqrt(x*x + y*y);
		double oldRadius = Math.atan(y / x);
		double newRadius = oldRadius + radius;
		x = length * Math.cos(newRadius);
		y = length * Math.sin(newRadius);
	}
	
	public void rotate(Rotation r){
		double xx, yy;
		xx = x * r.a[0][0] + y * r.a[0][1]; 
		yy = x * r.a[1][0] + y * r.a[1][1]; 
		x = xx;
		y = yy;
	}
	
	/**
	 * rotate the point horizontally
	 */
	public void reflectX(){
		x = -x;
	}

	public void reflectY(){
		y = -y;
	}
}

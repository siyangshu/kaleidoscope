package kaleidoscope;

import java.awt.Color;
import java.util.Random;

/**
 * @author James Park
 * @author Siyang Shu
 * 
 * This class record the location and other information of geometry.
 *
 */
public class Status {
	public int x;
	public int y;
	public double size;
	public Color color;
	// do rotate first, and then reflect
	public Rotation rotation;
	public double radius;
	// if the geometry has been reflected horizontally? that is (x, y) -> (-x, y)
	boolean xReflected;
	// if the geometry has been reflected horizontally? that is (x, y) -> (-x, y)
	boolean yReflected;

	/**
	 * constructor
	 * randomly set initial location
	 */
	public Status(){
		Random rand = new Random();
		//		x = rand.nextInt(100);
		//		y = rand.nextInt(100);
		x = 50;
		y = 100;
		radius = 0;
		size = 30;
		color = Color.BLACK;
		xReflected = false;
		yReflected = false;
		rotation = new Rotation();
	}


	public Status(Status another) {
		this.x = another.x;
		this.y = another.y;
		this.size = another.size;
		this.color = another.color;
		this.radius = another.radius;
		this.xReflected = another.xReflected;
		this.yReflected = another.yReflected;
		this.rotation = new Rotation(another.rotation);
	}
}

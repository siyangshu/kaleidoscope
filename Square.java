package kaleidoscope;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This is the Square class extending the Geometry class.
 * It has its own method  
 *     public void draw(Graphics g, Status s)
 * to draw itself
 * 
 * @author David Matuszek
 * @author James Park
 * @author Siyang Shu
 */

public class Square extends Geometry {
	
	public Square(){
		super();
	}
	
	public Square(int xDelta, int yDelta, Color color) {
		super(xDelta, yDelta, color);
	}
	
	
	
	@Override
    public void draw(Graphics g, Status status){
		g.fillRect(status.x, status.y, (int)(status.size), (int)(status.size));           

	}

}
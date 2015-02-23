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
 * This is the Triangle class extending the Geometry class.
 * It has its own method  
 *     public void draw(Graphics g, Status s)
 * to draw itself
 * 
 * @author David Matuszek
 * @author James Park
 * @author Siyang Shu
 */
public class Triangle extends Geometry {
	
	public Triangle(){
		super();
	}
	
	public Triangle(int xDelta, int yDelta, Color color) {
		super(xDelta, yDelta, color);
//		status.rotation.rotate(new Rotation(0.3));
	}
	
	@Override
    public void draw(Graphics g, Status s){
		Point point1, point2;
		point1 = new Point(s.size, 0);
		point2 = new Point(0, s.size);	
		
		point1.rotate(s.rotation);
		point2.rotate(s.rotation);
		
    	int [] Xcoord = {s.x, (int) (s.x + point1.x), (int) (s.x + point2.x)};
        int [] Ycoord = {s.y, (int) (s.y + point1.y), (int) (s.y + point2.y)};
    	g.drawPolygon(Xcoord, Ycoord, 3);
    	g.fillPolygon(Xcoord, Ycoord, 3);
	}

}
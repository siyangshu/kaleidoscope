package kaleidoscope;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This is the base class of all geometries. It is stored by Model, and draw picture when called by View
 * 
 * @author David Matuszek
 * @author James Park
 * @author Siyang Shu
 */

public class Geometry {
	protected Status status;
    protected int xDelta;
    protected int yDelta;
    static int width;
    static int height;
    
    /**
     * constructor
     */
    public Geometry(){
    	status = new Status();
    	xDelta = 10;
    	yDelta = 10;
    	status.color = Color.BLACK;
    }
    
    /**
     * @param xDelta: speed of x direction
     * @param yDelta: speed of y direction
     * @param color: the color of that geometry
     */
    public Geometry(int xDelta, int yDelta, Color color) {
    	status = new Status();
    	this.xDelta = xDelta;
    	this.yDelta = yDelta;
    	this.status.color = color;
    }
    
    /**
     * @param c: new color of the geometry
     */
    public void setColor(Color c) {
    	this.status.color = c;
    }
    
    /**
     * @param vx: x velocity of the geometry
     * @param vy: y velocity of the geometry
     */
    public void setSpeed(int vx, int vy){
    	xDelta = vx;
    	yDelta = vy;
    }
    
    /**
     * @return: velocity in x direction
     */
    public int getXSpeed(){
    	return xDelta;
    }

    /**
     * @return: velocity in y direction
     */
    public int getYSpeed(){
    	return yDelta;
    }
    
    /**
     * @return the current status, including location, color and so on.
     */
    public Status getStatus(){
    	return status;
    }

    /**
     * @return The geometry's X position.
     */
    public int getX() {
        return status.x;
    }

    /**
     * @return The geometry's Y position.
     */
    public int getY() {
        return status.y;
    }
    
    /**
     * Tells the shape to advance one step in the direction that it is moving.
     * If it hits a wall, its direction of movement changes.
     */
    public void makeOneStep() {
        // Do the work
        status.x -= xDelta;
        if (status.x < 0 || status.x >= width) {
            xDelta = -xDelta;
            status.x -= xDelta;
        }
        status.y -= yDelta;
        if (status.y < 0 || status.y >= height) {
            yDelta = -yDelta;
            status.y -= yDelta;
        }
    }
    
    /**
     * @param g: Graphics
     * @param status: location and other information of geometry
     * empty method. need to override by derived class
     */
    public void draw(Graphics g, Status status){

    }
    
}
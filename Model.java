package kaleidoscope;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

//import kaleidoscope.Geometry.Strobe;

/**
 * This is the Model class for a bouncing ball. It is an Observable,
 * which means that it can notifyObservers that something in the
 * model has changed, and they should take appropriate actions.
 * 
 * @author David Matuszek
 * @author James Park
 * @author Siyang Shu
 */
public class Model extends Observable{
    public ArrayList<Geometry> geometry;
    
    protected Timer timer;
    
    /**
     * constructor
     */
    public Model(){
    	geometry = new ArrayList<Geometry>();
    }
    
    /**
     * @param geo
     * add the geometry to model
     */
    public void addGeometry(Geometry geo){
    	geometry.add(geo);
    }
    
    /**
     * for each geometry in model, randomly change its color
     */
    public void randomColor() {
    	Random rand = new Random();
    	for(Geometry geo : geometry) {
	    	// Java 'Color' class takes 3 floats, from 0 to 1.
	    	float r = rand.nextFloat();
	    	float g = rand.nextFloat();
	    	float b = rand.nextFloat();
	    	float max = Math.max(r, Math.max(g, b));
	    	r = r / max;
	    	g = g / max;
	    	b = b / max;
	    	Color randomColor = new Color(r, g, b);
	    	geo.setColor(randomColor);
    	}
    }
    
    /**
     * for each geometry in model, randomly change its speed
     */
    public void randomSpeed() {
    	Random rand = new Random();
    	for(Geometry geo : geometry) {
    		int vx, vy;
    		vx = geo.getXSpeed();
    		vy = geo.getYSpeed();
    		vx = Integer.signum(vx) * (rand.nextInt(10) + 1);
    		vy = Integer.signum(vy) * (rand.nextInt(10) + 1);
    		geo.setSpeed(vx, vy);
    	}
    }
    
    /**
     * Tells the model to start moving. This is done by starting a Timer
     * that periodically executes a TimerTask. The TimerTask then tells
     * the shape to make one "step."
     */
    public void start() {
        timer = new Timer(true);
        timer.schedule(new Strobe(), 0, 40); // 25 times a second        
    }
    
    /**
     * Tells the model to stop where it is.
     */
    public void pause() {
        timer.cancel();
    }
    
    /**
     * for each geometry in model, tells that geometry to advance one "step."
     */
    protected class Strobe extends TimerTask {
        @Override
        public void run() {
        	for(Geometry geo : geometry) {
        		geo.makeOneStep();
        	}
        	setChanged();
        	notifyObservers();
        }
    }
}
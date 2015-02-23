package kaleidoscope;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
/**
 * The View "observes" and displays what is going on in the Model.
 * In this example, the Model contains only a single bouncing ball.
 * 
 * @author David Matuszek
 * @author James Park
 * @author Siyang Shu
 */
public class View extends JPanel implements Observer {

    /** This is what we will be observing. */
    private Model model;
    /**
     * Constructor.
     * @param circle The Model whose working is to be displayed.
     */
    
    /**
     * @param m: the model that is observed
     * 
     */
    public View(Model m){
    	model = m;
        this.addComponentListener(new ComponentAdapter() {
        	// every time when the view is resized, inform the geometry new width and height
            @Override
            public void componentResized(ComponentEvent arg0) {
            	int width, height;
            	// keep width and height the same value, since we need a square canvas
            	width = height = Math.min(View.this.getWidth(), View.this.getHeight());
            	Geometry.width = width;
            	Geometry.height = height;
            	View.this.setSize(width, height);
            }
        });
    }

    /**
     * Displays what is going on in the Model. Note: This method should
     * NEVER be called directly; call repaint() instead.
     * 
     * @param g The Graphics on which to paint things.
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g) {   
    	g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        for(Geometry geo : model.geometry) {
        	Status base = geo.getStatus();
        	g.setColor(base.color);
        	ArrayList<Status> li = createMirror(base);
        	for(Status s : li) {
        		geo.draw(g, s);
        	}
        }
    }

    /**
     * @param base: the status of the 'real' geometry
     * @return a list of Status representing the mirrors of the 'real' geometry
     */
    public ArrayList<Status> createMirror(Status base) {
    	ArrayList<Status> li = new ArrayList<Status>();
    	li.add(base);

    	li = reflectHorizontal(li);
    	li = reflectVertical1(li);
    	li = reflectDiagonal(li);
    	return li;
    }
    
    /**
     * @param li: a list of Status
     * @return old Status and new Status, which is the horizontally reflected mirrors of old ones.
     */
    public ArrayList<Status> reflectHorizontal(ArrayList<Status> li) {
    	ArrayList<Status> tmp = new ArrayList<Status>();
    	for(Status s : li) {
    		Status status = new Status(s);
    		status.x = Geometry.width - s.x;
    		status.rotation = new Rotation(-1, 0, 0, 1);
    		status.rotation.rotate(s.rotation);
//    		if(s.xReflected){
//    			status.xReflected = false;
//    		} else {
//    			status.xReflected = true;
//    		}
    		tmp.add(status);
    	}
    	li.addAll(tmp);
    	return li;
    }
    
    /**
     * @param li: a list of Status
     * @return old Status and new Status, which is the vertically reflected mirrors of old ones.
     */
    public ArrayList<Status> reflectVertical1(ArrayList<Status> li) {
    	ArrayList<Status> tmp = new ArrayList<Status>();
    	for(Status s : li) {
    		Status status = new Status(s);
    		status.y = Geometry.height - status.y;
    		status.rotation = new Rotation(1, 0, 0, -1);
    		status.rotation.rotate(s.rotation);
//
//    		if(status.yReflected){
//    			status.yReflected = false;
//    		} else {
//    			status.yReflected = true;
//    		}
    		tmp.add(status);
    	}
    	li.addAll(tmp);
    	return li;
    }
    
    /**
     * @param li: a list of Status
     * @return old Status and new Status, which is the diagonally reflected mirrors of old ones.
     */
    public ArrayList<Status> reflectDiagonal(ArrayList<Status> li) {
    	ArrayList<Status> tmp = new ArrayList<Status>();
    	for(Status s : li) {
    		Status status = new Status(s);
    		status.x = s.y;
    		status.y = s.x;
    		status.rotation = new Rotation(0, 1, 1, 0);
    		status.rotation.rotate(s.rotation);

//    		if(s.xReflected){
//    			status.xReflected = false;
//    		} else {
//    			status.xReflected = true;
//    		}
//    		if(s.yReflected){
//    			status.yReflected = false;
//    		} else {
//    			status.yReflected = true;
//    		}
    		tmp.add(status);
    	}
    	li.addAll(tmp);
    	return li;
    }
     
    /**
     * When an Observer notifies Observers (and this View is an Observer),
     * this is the method that gets called.
     * 
     * @param obs Holds a reference to the object being observed.
     * @param arg If notifyObservers is given a parameter, it is received here.
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable obs, Object arg) {
        repaint();
    }
    

}

package kaleidoscope;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The Controller sets up the GUI and handles all the controls (buttons,
 * menu items, etc.)
 * 
 * @author David Matuszek
 * @author James Park
 * @author Siyang Shu
 */
public class Controller extends JFrame {
    JPanel buttonPanel = new JPanel();
    JButton runButton = new JButton("Run");
    JButton stopButton = new JButton("Stop");
    JButton colorButton = new JButton("Change Color");
    JButton speedButton = new JButton("Change Speed");
    Timer timer;

    /** The Model is the object that does all the computations. It is
     * completely independent of the Controller and View objects. */

    /** The View object displays what is happening in the Model. */
    View view;
    Model model;
    
    /**
     * Runs the kaleidoscope program
     * @param args Ignored.
     */
    public static void main(String[] args) {
        Controller c = new Controller();
        c.init();
        c.display();
    }

    /**
     * Sets up communication between the components.
     */
    private void init() {
    	model = new Model();
    	view = new View(model);
    	model.addGeometry(new Square(10, 9, Color.BLUE));
    	model.addGeometry(new Circle(8, 7, Color.CYAN));
//    	model.addGeometry(new Square(13, 9, Color.BLUE));
    	model.addGeometry(new Circle(7, 8, Color.CYAN));
    	model.addGeometry(new Triangle(6, 5, Color.GREEN));
    	model.addGeometry(new Circle(4, 3, Color.RED));
    	model.addGeometry(new Triangle(5, 5, Color.GREEN));
    	model.addObserver(view);
    }

    /**
     * Displays the GUI.
     */
    private void display() {
        layOutComponents();
        attachListenersToComponents();
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Arranges the various components in the GUI.
     */
    private void layOutComponents() {
        setLayout(new BorderLayout());
        this.add(BorderLayout.SOUTH, buttonPanel);
        buttonPanel.add(runButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(colorButton);
        buttonPanel.add(speedButton);
        stopButton.setEnabled(false);
        this.add(BorderLayout.CENTER, view);
    }
    
    /**
     * Attaches listeners to the components, and schedules a Timer.
     */
    private void attachListenersToComponents() {
        // The Run button tells the Models to start
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                runButton.setEnabled(false);
                stopButton.setEnabled(true);
                model.start();
            }
        });
        // The Stop button tells the Models to pause
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                runButton.setEnabled(true);
                stopButton.setEnabled(false);
                model.pause();
            }
        });
        
        // The Color button tells the Models to change color
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	colorButton.setEnabled(true); 
            	model.randomColor();
            }
        });
        
        //The reflect button changes speed and direction
        speedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	speedButton.setEnabled(true); 
            	model.randomSpeed();
            }
        });
            
        // When the window is resized, the Models are given the new limits
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
            	int width = Controller.this.getWidth();
            	int height = Controller.this.getHeight();
            	width = height = Math.max(width, height);
            	Controller.this.setSize(view.getWidth(), height);
            }
        });
    }
}
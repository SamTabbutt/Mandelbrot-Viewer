package events;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel{

	//Initiate Mandelbrot to be the main system for the main panel.
	//in future editions of the program, the mandelbrot will implement the 
	//interface MainSystem, which is implemented by objects which occupy the main focus of the program
	//in future editions, the MainSystem could be a differential equation solver, fractal generator, etc.
	 Mandelbrot mb = new Mandelbrot(new Dimension(300,300));
	 
	 //the select square object will be used as a graphic illustrator to draw and contain information
	 //regarding the new zoom frame in the viewing section
	 SelectSqaure ss = new SelectSqaure();
	 
	 //gs is the status of grid overlay
	 static boolean gs;
	 boolean showGrid;
	 JGrid grid1;
	 
	 //systemIndex for this current program is entirely arbitrary. in the future it will determine the 
	 //type of system which is being viewed
	 int systemIndex;

	 int Iterate;
	
	 public MainPanel() {
    	
    	//the main panel is established with grid overlay initiated to false
    	gs=false;

    	setBorder(BorderFactory.createLineBorder(Color.black));

    	
    	//add the mouse listener for zoom feature
        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                setSquareCorner(e.getX(),e.getY());
            }
        });

        addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent e){
                dragSquareCorner(e.getX(),e.getY());
            }
        });
        
        addMouseListener(new MouseAdapter() {
        	public void mouseReleased(MouseEvent e) {
        		unclickMethod(e.getX(),e.getY());
        	}
        });

    }
    
    //as an extension of JPanel, paintComponent is called automatically
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        mb.makeIcon(g);
        ss.paintSquare(mb.currentPlane,g,getPreferredSize());
        
        if(gs==true) {
            overlayGrid(g,true);

        }else {
        	overlayGrid(g,false);
        }
    }  
    
    //called when mouse is unclicked, sends information to selectsquare
    private void unclickMethod(int x,int y) {
    	
    	updateMandBounds(x,y);
    	
    	final int CURR_X = ss.getX();
        final int CURR_Y = ss.getY();
        final int CURR_W = ss.getWidth();
        final int CURR_H = ss.getHeight();
        final int OFFSET = 1;

        if (((CURR_X!=x) || (CURR_Y!=y))&& (x>CURR_X &&y>CURR_Y)) {

            // The square is moving, repaint background 
            // over the old square location. 
            repaint(CURR_X,CURR_Y,CURR_W+OFFSET,CURR_H+OFFSET);

            // Update coordinates.
        	ss.setX(0);
        	ss.setY(0);
        	ss.setXMove(0);
        	ss.setYMove(0);

            // Repaint the square at the new location.
            repaint(ss.getX(), ss.getY(), 
                    ss.getWidth()+OFFSET, 
                    ss.getHeight()+OFFSET);
        }
        
		
    
    }
    
    //called when mouse is unclicked, maintains bounds of drawn ractangle and establishes
    //ComplexPlane extremes based on what is entered
    public void updateMandBounds(int x, int y){
    	double[] minvals = mb.currentPlane.getPlaneValue(ss.getX(), ss.getY()+ss.getHeight());
    	double[] maxvals = mb.currentPlane.getPlaneValue(ss.getX()+ss.getWidth(), ss.getY());
    	double iMin = maxvals[1];
    	double iMax = minvals[1];
    	double rMin = minvals[0];
    	double rMax = maxvals[0];
    	mb.updateBounds(iMin,iMax,rMin,rMax);
    	repaint();
    }
    
    private void dragSquareCorner(int x, int y) {
    	final int CURR_X = ss.getX();
        final int CURR_Y = ss.getY();
        final int CURR_W = ss.getWidth();
        final int CURR_H = ss.getHeight();
        final int OFFSET = 1;
       
        
        if (((CURR_X!=x) || (CURR_Y!=y))&& (x>CURR_X &&y>CURR_Y)) {

            // The square is moving, repaint background 
            // over the old square location. 
           repaint(CURR_X,CURR_Y,CURR_W+OFFSET,CURR_H+OFFSET);

            // Update coordinates.
            ss.setXMove(x);
            ss.setYMove(y);

            // Repaint the square at the new location.
            repaint(ss.getX(), ss.getY(), 
                    ss.getWidth()+OFFSET, 
                    ss.getHeight()+OFFSET);
        }
    }

    private void setSquareCorner(int x, int y){

        // Current square state, stored as final variables 
        // to avoid repeat invocations of the same methods.
        final int CURR_X = ss.getX();
        final int CURR_Y = ss.getY();
        final int CURR_W = ss.getWidth();
        final int CURR_H = ss.getHeight();
        final int OFFSET = 1;

        if (((CURR_X!=x) || (CURR_Y!=y))&& (x>CURR_X &&y>CURR_Y)) {

            // The square is moving, repaint background 
            // over the old square location. 
            repaint(CURR_X,CURR_Y,CURR_W+OFFSET,CURR_H+OFFSET);

            // Update coordinates.
            ss.setX(x);
            ss.setY(y);

            // Repaint the square at the new location.
            repaint(ss.getX(), ss.getY(), 
                    ss.getWidth()+OFFSET, 
                    ss.getHeight()+OFFSET);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(300,300);
    }
    
    //method called on instantiation to create the grid object under initial grid condition: false
    public void overlayGrid(Graphics g,boolean show) {
    	grid1 = new JGrid(mb.currentPlane,g,getPreferredSize(),show);    	
    }

    //called when the checkbox on the controlpanel is changed. 
    //changes the gridstatus and repaints with new setting
	public void setGridStatus(boolean c) {
		gs = c;
		repaint();
	}
	
	//sets model to interact with the control panel
	public void setModel(Model model) {
	      model.addPropertyChangeListener(new PropertyChangeListener() {

	         @Override
	         public void propertyChange(PropertyChangeEvent pcEvt) {
	            if (Model.SystemTYPE.equals(pcEvt.getPropertyName())) {
	               int ind = (int) pcEvt.getNewValue();
	               systemIndex = ind;
	            } else if (Model.Grid.equals(pcEvt.getPropertyName())) {
	               boolean gd = (boolean) pcEvt.getNewValue();
	               showGrid = gd;
	               setGridStatus(gd);
	            } else if(Model.Iter.equals(pcEvt.getPropertyName())) {
	               int iter  = (int) pcEvt.getNewValue();
	               Iterate = iter;
	               mb.setIterationLevel(Iterate);
	               repaint();
	            }
	         }
	      });
	   }
}


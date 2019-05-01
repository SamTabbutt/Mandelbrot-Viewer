package events;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


//This set contains all of the information in the image displayed.
//when called, the mandelbrot class initiates a mandelbrot set at the default perspective
//it contains a ComplexPlane object which is mutable and controls the frame of the image
//the dimension is controlled by the initiator in the MainPanel
public class Mandelbrot{
	
		CompPlane P;
	    
	    int[][] intSet;
	    
	    Dimension dim;
		CompPlane currentPlane;
		double power;
		int pixMult;
		int Iterations;	
		
		public Mandelbrot(Dimension d) {
			power = 2;
			dim=d;
			pixMult = 100000;
			Iterations = 100;
			currentPlane = new CompPlane(0,-2,1,d.getWidth(),d.getHeight());
			intSet = makeMandel(d);
		}
		
		
		//when a new zoom frame is established, this class is called and recreates the mandelbrot
		//set under the new frame conditions.
		public void updateBounds(double iMin,double iMax, double rMin, double rMax) {
			currentPlane = new CompPlane(iMin,iMax,rMin,rMax,dim.getWidth(),dim.getHeight());
			intSet = makeMandel(dim);
		}
		
		
		public void makeIcon(Graphics g) {	
			 for ( int r = 1; r < dim.width; r++ ) {
		          for ( int i = 1; i < dim.height; i++ ) {
		        	  //im.setRGB(r, i, intSet[r][i]);
		        	g.setColor(Color.getColor("n",intSet[r][i]));
		       		g.fillRect(r,i, dim.width, dim.height);
		          }}
			// ImageIcon ii = new ImageIcon(im);
			// return ii;
		}

		//this method is really all that is going on here. 
		//the mandelbrot set is an evaluation of the slope of the orbit at each point in the 
		//complex plane. We have established a plane which we are viewing from, and a resolution
		//based on the width and height of the image we are viewing. 
		private int[][] makeMandel(Dimension d) {
			int h = currentPlane.plane.length;
			int w = currentPlane.plane[0].length;
			int[][] init = new int[w][h];
			ImNum origin = new ImNum(0,0);
			for(int r=0;r<h;r++) {
				for(int i=0;i<w;i++) {
					Orbit temp = new Orbit(origin,power,Iterations,currentPlane.plane[r][i]);
					init[r][i]=(int) (temp.diverge)*pixMult;
				}
			}	
			return init;
		}

		public void setIterationLevel(int iterate) {
			Iterations = iterate;
			intSet = makeMandel(dim);
			
		}
	}


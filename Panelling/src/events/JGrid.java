package events;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

//Class used to implement grid layover of set when checkbox is checked
public class JGrid extends JLabel{
	int width;
	int height;
	int numcols = 20;
	int numrows = 20;
	Plane p;
	
	
	public JGrid(CompPlane cp, Graphics g,Dimension d,boolean show) {
		//paints the grid on graphics g when the box is checked and paints nothing when it is not
		p=cp;
		height = d.height;
		width = d.width;
		setGrid(g,show);
		//includes maximum values
		setNumericalValues(g,show);
		
	}
	
	private void setNumericalValues(Graphics g, boolean show) {
		double rMin = p.returnExtremes()[0];
		double rMax = p.returnExtremes()[1];
		double iMin = p.returnExtremes()[2];
		double iMax = p.returnExtremes()[3];
		
		if(show) {
			g.setFont(new Font(null,10,10));
			g.drawString(""+rMin, 0, height/2);
			
			g.drawString(""+rMax, width-20, height/2);
			
			g.drawString(""+iMax, width/2, 10);
			
			g.drawString(""+iMin, width/2, height-5);
		}
		
	}

	private void setGrid(Graphics g,boolean show) {
		int rowWd = (int)width/numrows;
		int rowH = (int)height/numcols;
		if(show) {
		for(int i =0;i<width;i+=rowWd) {
			g.setColor(Color.BLACK);
	    	g.drawLine(i, 0, i, height);
		}
		for(int i =0;i<height;i+=rowH) {
			g.setColor(Color.BLACK);
	    	g.drawLine(0, i, width, i);
		}}
	}
	
	

		
	
}

package events;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;

//this is the object which paints, displays, and records the information tracked on 
//mouse clicking, dragging, and releasing. 
public class SelectSqaure {
	private int xPos = 0;
    private int yPos = 0;
    private int width = 0;
    private int height = 0;
    
    public void setX(int xPos){ 
        this.xPos = xPos;
    }

    public int getX(){
        return xPos;
    }

    public void setY(int yPos){
        this.yPos = yPos;
    }

    public int getY(){
        return yPos;
    }
    
    public void setXMove(int XMove) {
    	if(XMove>xPos) {
        	width=XMove-xPos;
    	}else {
    		width = 0;
    	}
    }
    
    public void setYMove(int YMove) {
    	if(YMove>yPos) {
	    	height=YMove-yPos;
    	}else {
    		height = 0;
    	}
    }

    public int getWidth(){
        return width;
    } 

    public int getHeight(){
        return height;
    }
    

    //called when the mainpanel is painted
    public void paintSquare(Plane P,Graphics g,Dimension d){
    	g.setColor(Color.BLACK);
    	g.drawRect(xPos,yPos,width,height);
    	g.setFont(new Font(null, 10, 10));
    	double[]axisVals = P.getPlaneValue(xPos,yPos);
    	g.drawString("("+axisVals[0]+", "+axisVals[1]+")", xPos+10, yPos+10);
        
       
       // return g;
    }
}

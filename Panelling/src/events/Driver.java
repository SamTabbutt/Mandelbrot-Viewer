package events;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


//Run Driver to initiate program
public class Driver {
	public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }
	
	private static void createAndShowGUI() {
		//Establish MainFrame
        MainFrame f = new MainFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        //Create MainPanel for image display and ControlPanel for control and text display
        MainPanel MP = new MainPanel();
        ControlPanel CP = new ControlPanel();
        Model model = new Model();
        CP.setModel(model);
        MP.setModel(model);

        //Set flow layout
        f.getContentPane().setLayout(new FlowLayout());
        
        f.add(MP);
        f.add(CP);
        
        //Establish dimension of 500X500 for ManeFrame
        f.setSize(500,500);
        f.setVisible(true);
    } 
}

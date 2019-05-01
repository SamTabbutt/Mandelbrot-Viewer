package events;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPanel extends JPanel{
	
	//Define three main components to controlpanel and a model to be used to communicate changes in controls 
	//with mainpanel
	JTextArea TextArea;
	JComboBox SystemChoice;
	JCheckBox GridlineCheckBox;
    static final String NEWLINE = System.getProperty("line.separator");
	Model model;
	
	public ControlPanel() {
		
			//Set four main components of Control Panel with distinct dimensions
	        setTextArea(new Dimension(200,50));
	        
	        setComboBox(new Dimension(100,25));
	        
	        setGridlineCheckBox(new Dimension(50,25));
	        
	        setIterationControl(new Dimension(50,25));
	        
	        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}
	
	 public void setTextArea(Dimension d) {
	    	TextArea = new JTextArea();
	        TextArea.setEditable(false);
	        JScrollPane scrollPane = new JScrollPane(TextArea,
	                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        scrollPane.setPreferredSize(d);
	       add(scrollPane);
	       TextArea.append("Welcome to the Mandelbrot Set Explorer\n"
	       		+ "In its current state, this program is designed to explore the Mandelbrot\n"
	       		+ "set under rather strict operating conditions\n\n"
	       		+ "The program has initialized to display the set under 100 iterations in the complex\n"
	       		+ "plane from -2 through 1 on the real axis and -1.5 to 1.5 on the imaginary axis\n"
	       		+ "to zoom, create a zoom frame by clicking and dragging down and to the right.\n"
	       		+ "At a certain point, you may find that the detail is lost, at which point \n"
	       		+ "you can increase the iteration to enhance the definition in a small frame.\n"
	       		+ "Enjoy exploring!\n");
	    }
	    
	    public void setComboBox(Dimension d) {
	    	String[] SystemChoices = {"Mandelbrot","Something Else"};
	        SystemChoice = new JComboBox(SystemChoices);
	        SystemChoice.setSelectedIndex(0);
	        SystemChoice.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	JComboBox jb = (JComboBox)e.getSource();
	        		eventOutput("System set to: "+jb.getSelectedItem(),e);
	        		model.setSystemType(jb.getSelectedIndex());
	            }
	        });
	        SystemChoice.setPreferredSize(d);
	       add(SystemChoice);
	    }
	    
	    public void setGridlineCheckBox(Dimension d) {
	    	JCheckBox GridC = new JCheckBox("Show Gridlines");
	    	GridC.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	JCheckBox cb = (JCheckBox)e.getSource();
	        		eventOutput("Grid set to: "+cb.isSelected(),e);
	        		model.setGridDisplay(cb.isSelected());
	            	
	            }
	        });
	    	GridC.setPreferredSize(d);
	    	add(GridC);
	    	
	    }
	    
	    public void setIterationControl(Dimension d) {
	    	String[] IterOptions = {"25","50","75","100","150","175","200","225","250","275","300","325","350","375","400"};
	    	JComboBox itCont = new JComboBox(IterOptions);
	    	
	    	itCont.addActionListener(new ActionListener() {
	    		@Override
	    		public void actionPerformed(ActionEvent e) {
	    			JComboBox js = (JComboBox) e.getSource();
	    			int selected = 25*(js.getSelectedIndex() +1);
	    			eventOutput("Iter Set to: "+selected,e);
	    			model.setIteration(selected);
	    		}
	    	});
	    	itCont.setPreferredSize(d);
	    	add(itCont);
	    }
	    
	    void eventOutput(String eventDescription, MouseEvent e) {
	        TextArea.append(eventDescription 
	                + "." + NEWLINE);
	        TextArea.setCaretPosition(TextArea.getDocument().getLength());
	    }
	    
	    void eventOutput(String eventDescription, ActionEvent e) {
	        TextArea.append(eventDescription  
	                + "." + NEWLINE);
	        TextArea.setCaretPosition(TextArea.getDocument().getLength());
	    }
	    
	    void eventOutput(String eventDescription, ChangeEvent e) {
	        TextArea.append(eventDescription  
	                + "." + NEWLINE);
	        TextArea.setCaretPosition(TextArea.getDocument().getLength());
	    }
	    public void setModel(Model model) {
	        this.model = model;
	     }

		
}

package events;
import java.beans.PropertyChangeListener;
import javax.swing.event.SwingPropertyChangeSupport;


//adapted from https://stackoverflow.com/questions/16566590/passing-data-between-jpanels
//this is a class that acts as a mediator between the main panel and the control panel. 
//when a value is changed in the control panel, the corresponding class is called in the Model class
//when the model class is called, the property change is fired and the main panel responds via the property change listener
//there are three main properties which are changed: grid status, iteration value, and system type

public class Model {
	public static final String SystemTYPE = "System type";
	public static final String Grid = "Grid";
	public static final String Iter = "Iter";
	private SwingPropertyChangeSupport pcSupport = 
	         new SwingPropertyChangeSupport(this);
	private int iter;
   private boolean gridDisplay;
   private int systemIndex;

   public void addPropertyChangeListener(PropertyChangeListener listener) {
      pcSupport.addPropertyChangeListener(listener);
   }

   public void removePropertyChangeListener(PropertyChangeListener listener) {
      pcSupport.removePropertyChangeListener(listener);
   }

   public boolean getGridDisplay() {
      return gridDisplay;
   }

   public void setGridDisplay(boolean gd) {
      boolean oldValue = this.gridDisplay;
      boolean newValue = gd;

      this.gridDisplay = gd;
      pcSupport.firePropertyChange(Grid, oldValue, newValue);
   }

   public int getSystemType() {
      return systemIndex;
   }

   public void setSystemType(int ind) {
      int oldValue = this.systemIndex;
      int newValue = ind;

      this.systemIndex = ind;
      pcSupport.firePropertyChange(SystemTYPE, oldValue, newValue);
   }

public void setIteration(int value) {
	 int oldValue = this.iter;
     int newValue = value;

     this.systemIndex = value;
     pcSupport.firePropertyChange(Iter, oldValue, newValue);
	
}

}
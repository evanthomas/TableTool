package physicalObjects;

import expressionParsing.ParseException;

public class Current {
	
	
	private String myName;
	
	private boolean enabled;
	private boolean includeInPlots;

	public Current(String myName) { 
		setName(myName);
		enabled = true;
		includeInPlots = true;
	}
	
	
	public void setName(String myName) {
		this.myName = myName;
	}
	
	public String getName() { return myName; };

	public void initialise() throws ParseException {};
	
	public void setEnabled(boolean e) { this.enabled = e; }
	public boolean enabled() { return enabled; } 
	
	public void setIncludeInPlots(boolean e) { this.includeInPlots = e; }
	public boolean getIncludeInPlots() { return includeInPlots; }
}

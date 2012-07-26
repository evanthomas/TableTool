package physicalObjects;


import java.util.ArrayList;
import java.util.Iterator;

import modelView.Expression;

import expressionEvaluator.ParseException;

public class HHCurrent extends Current {

	private ArrayList<HHGate> gateList;
	
	private Expression gMaxEH;
	private Expression ErEH;
	private double gMax;
	private double Er;
	
	
	public HHCurrent(String name, Expression gMaxEH, Expression ErEH) {
		super(name);
		gateList = new ArrayList<HHGate>();
		this.gMaxEH = gMaxEH;
		this.ErEH   = ErEH;
	}

	public void addGate(HHGate g) {
		gateList.add(g);
	}
	
	public void removeGate(HHGate g) {
		gateList.remove(g);
	}
	
	public ArrayList<HHGate> getGateList() { return gateList; }
	
	public void initialise() throws ParseException {
		gMax = gMaxEH.eval();
		Er   = ErEH.eval();
	}
	
	public double I(double v) {
		double x = gMax*(Er-v);
		Iterator<HHGate> i = gateList.iterator();
		while( i.hasNext() ) {
			x *= i.next().openProbability();
		}
		return x;
	}
	
}

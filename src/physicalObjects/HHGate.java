package physicalObjects;

import modelState.AppState;
import modelView.Expression;
import expressionEvaluator.ParseException;

public class HHGate {
	
	private Expression tauFn;
	private Expression infFn;
	private Expression alphaFn;
	private Expression betaFn;
	
	private double [][] infTable;
	private double [][] tauTable;
	
	private double vLo;
	private double vHi;
	private int numEntries;
	
	private double state;
	private int exponent;
	private boolean enabled;
	private HHCurrent owner;
	
	public HHGate(HHCurrent owner, Expression tauFn, Expression infFn, Expression alphaFn, Expression betaFn) {
		this.tauFn   = tauFn;
		this.infFn   = infFn;
		this.alphaFn = alphaFn;
		this.betaFn  = betaFn;
		enabled = true;
		this.owner = owner;
		this.owner.addGate(this);
		exponent = 1;
	}

	public void initialise() throws ParseException {
		// Called before starting a simulation
		genInfTable();
		genTauTable();
		state = 0;
	}
	public double initialCondition(double v) {
		return inf(v);
	}
	
	public double deriv(double v, double x) {
		state = x;
		return (inf(v)-x)/tau(v);
	}
	
	public double openProbability() { return Math.pow(state, exponent); }
	
	// Called by solver
	public double inf(double v) { 
		return interp(v, infTable);
		}
	public double tau(double v) { return interp(v, tauTable); }
	
	private double interp(double v, double[][] table) {
		// linear interpolation
		if( v<= vLo ) return table[0][0];
		if( v>= vHi ) return table[0][numEntries-1];
		double dv = (vHi-vLo)/numEntries;
		int i     = (int)Math.floor((v-vLo)/dv);
		double x  = table[0][i];
		double dx = (v-table[1][i])/dv;
		assert(dx<1);
		return x + dx*(table[0][i+1]-table[0][i]); 
	}
	public boolean isReady() {
		return (tauFn!=null && infFn!=null) || (betaFn!=null && alphaFn!=null);
	}


	private void genInfTable() throws ParseException {
		double [][] t = null;
		vLo = AppState.getvLo();
		vHi = AppState.getvHi();
		numEntries = AppState.getNumEntries();
		t = new double[2][numEntries];
		double v = vLo;
		double dv = (vHi-vLo)/numEntries;
		for(int i=0; i<numEntries; i++) {
			double x;
			if( !infFn.isValid() ) {
				x = alphaFn.eval(v)/(alphaFn.eval(v)+betaFn.eval(v));
			} else {
				x = infFn.eval(v);
			}
			t[0][i] = x;
			t[1][i] = v;
			v += dv;
		}
		infTable = t;;
	}
	
	private void genTauTable() throws ParseException {
		double [][] t = null;
		vLo = AppState.getvLo();
		vHi = AppState.getvHi();
		numEntries = AppState.getNumEntries();
		t = new double[2][numEntries];
		double v = vLo;
		double dv = (vHi-vLo)/numEntries;
		for(int i=0; i<numEntries; i++) {
			double x;
			if( !tauFn.isValid() ) {
				x = 1/(alphaFn.eval(v)+betaFn.eval(v));
			} else {
				x = tauFn.eval(v);
			}
			t[0][i] = x;
			t[1][i] = v;
			v += dv;
		}
		tauTable = t;
	}
	
	public double[] getTauTable() throws ParseException { 
		genTauTable();
		double x[] = new double[numEntries];
		for(int i=0; i<numEntries; i++) x[i] = tauTable[0][i];
		return x;
	}

	public double[] getInfTable() throws ParseException {
		genInfTable();
		double x[] = new double[numEntries];
		for(int i=0; i<numEntries; i++) x[i] = infTable[0][i];
		return x;
	}

	public double[] getVoltage() throws ParseException {
		genInfTable();
		double v[] = new double[numEntries];
		for(int i=0; i<numEntries; i++) v[i] = infTable[1][i];
		return v;
	}
	
	public void setExponent(int e) { this.exponent = e; }

	public void setEnabled(boolean e) { this.enabled = e; }
	public boolean enabled() { return enabled; } 
}

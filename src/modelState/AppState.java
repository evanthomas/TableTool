package modelState;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.JFrame;

import modelView.ModelDesignerView;
import modelView.PopupHelper;
import physicalObjects.HHCurrent;
import physicalObjects.HHGate;
import solving.ClampMode;
import solving.SolverType;
import expressionHandling.NumericExpression;
import expressionHandling.SymbolTable;
import expressionParsing.ParseException;
import floatingPoint.FloPoCoException;
import floatingPoint.FloPoCoFloat;
import floatingPoint.FloPoCoTable;

// Essentially a singleton object that contains the state of the application
public class AppState {

	private static SolverType odeMethod;
	
	private static ClampMode clampMode = ClampMode.CURRENT_CLAMP;
	
	private static NumericExpression vLo;
	private static NumericExpression vHi;
	private static NumericExpression numEntries;
	private static NumericExpression tolerance;
	private static NumericExpression capacitance;
	private static NumericExpression rundur;
	
	private static boolean doCurrentPlots = true;
	private static boolean doVoltagePlots = true;
	private static boolean doGatePlots = true;
	private static boolean doStimulusPlots = true;
	
	private static ArrayList<CurrentState> currentList = new ArrayList<CurrentState>();
	
	private static ArrayList<JFrame> graphPanelList = new ArrayList<JFrame>();
	
	private static SymbolTable symbolTable = new SymbolTable();
 	
	private static final int saveFileVersion = 1;
	private static final int tableFileVersion = 1;

	private static ModelDesignerView ui;
	
	public static final boolean doCurrentPlots() {
		return doCurrentPlots;
	}

	public static final void setDoCurrentPlots(boolean doCurrentPlots) {
		AppState.doCurrentPlots = doCurrentPlots;
	}

	public static final boolean doVoltagePlots() {
		return doVoltagePlots;
	}

	public static final void setDoVoltagePlots(boolean doVoltagePlots) {
		AppState.doVoltagePlots = doVoltagePlots;
	}

	public static final boolean doGatePlots() {
		return doGatePlots;
	}

	public static final void setDoGatePlots(boolean doGatePlots) {
		AppState.doGatePlots = doGatePlots;
	}

	public static final boolean doStimulusPlots() {
		return doStimulusPlots;
	}

	public static final void setDoStimulusPlots(boolean doStimulusPlots) {
		AppState.doStimulusPlots = doStimulusPlots;
	}

	
	private AppState() {} // Prevent instantiation

	public static void setClampMode(ClampMode m) { clampMode=m; }
	public static ClampMode getClampMode() { return clampMode; }
	public static void setUI(ModelDesignerView ui) { AppState.ui = ui; }
	public static void addCurrent(CurrentState c) { currentList.add(c); }
	public static void removeCurrent(CurrentState c) { currentList.remove(c); }
	public static ArrayList<CurrentState> getCurrentList() { return currentList; }
	
	public static void setCapacitance(String expr) throws ParseException {
		capacitance.setString(expr);
	}
	
	public static final double getvLo() throws ParseException {
		try {
			return vLo.eval();
		} catch (NullPointerException npe) { // Occurs when text field is empty - couldn't find a more elegant solution
			throw new ParseException("table low voltage is undefined.");
		}
	}
	
	public static final void setvLo(NumericExpression vLo) {
		AppState.vLo = vLo;
	}
	
	public static final double getvHi() throws ParseException {
		try {
			return vHi.eval();
		} catch (NullPointerException npe) { // Occurs when text field is empty - couldn't find a more elegant solution
			throw new ParseException("table high voltage is undefined.");
		}
	}
	
	public static final void setvHi(NumericExpression vHi) {
		AppState.vHi = vHi;
	}
	
	public static final int getNumEntries() throws ParseException {
		try {
			return (int)(Math.round(numEntries.eval()));
		} catch (NullPointerException npe) { // Occurs when text field is empty - couldn't find a more elegant solution
			throw new ParseException("number of table entries is undefined.");
		}
	}
	
	public static final void setNumEntries(NumericExpression numEntries) {
		AppState.numEntries = numEntries;
	}
	
	public static final double getCapacitance() throws ParseException {
		try {
			return capacitance.eval();
		} catch (NullPointerException npe) { // Occurs when text field is empty - couldn't find a more elegant solution
			throw new ParseException("capacitance is undefined.");
		}
	}
	
	public static final NumericExpression getCapacitanceEH() {
		return capacitance;
	}
	
	public static final NumericExpression getRundurEH() {
		return rundur;
	}
	
	public static final NumericExpression getToleranceEH() {
		return tolerance;
	}
	
	public static final NumericExpression getNumEntriesEH() {
		return numEntries;
	}
	
	public static final NumericExpression getvLoEH() {
		return vLo;
	}
	
	public static final NumericExpression getvHiEH() {
		return vHi;
	}
	
	public static final void setCapatiance(NumericExpression capacitance) {
		AppState.capacitance = capacitance;
	}
	
	public static final double getTolerance() throws ParseException {
		try {
			return tolerance.eval();
		} catch (NullPointerException npe) { // Occurs when text field is empty - couldn't find a more elegant solution
			throw new ParseException("tolerance is undefined.");
		}
	}
	
	public static final void setRundur(NumericExpression rundur) {
		AppState.rundur = rundur;
	}
	
	public static final double getRundur() throws ParseException {
		try {
			return rundur.eval();
		} catch (NullPointerException npe) { // Occurs when text field is empty - couldn't find a more elegant solution
			throw new ParseException("run duration is undefined.");
		}
	}
	
	public static final void setTolerance(NumericExpression tolerance) {
		AppState.tolerance = tolerance;
	}
	
	public static void setCapacitance(NumericExpression capacitance) {
		AppState.capacitance = capacitance;
	}
	
	public static SolverType getOdeMethod() {
		return odeMethod;
	}
	
	public static void setOdeMethod(SolverType odeMethod) {
		AppState.odeMethod = odeMethod;
	}
	
	public static void reset() {
		currentList = new ArrayList<CurrentState>();
		clampMode = ClampMode.CURRENT_CLAMP;
		doCurrentPlots = true;
		doVoltagePlots = true;
		doGatePlots = true;
		doStimulusPlots = true;
		vHi.setString(null);
		vLo.setString(null);
		numEntries.setString(null);
		capacitance.setString(null);
		tolerance.setString(null);
		rundur.setString(null);
	}
	
	public static void save(File f) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
		out.writeInt(saveFileVersion);
		out.writeObject(vLo.getExpr());
		out.writeObject(vHi.getExpr());
		out.writeObject(numEntries.getExpr());
		out.writeObject(tolerance.getExpr());
		out.writeObject(capacitance.getExpr());
		out.writeObject(rundur.getExpr());
		out.writeObject(odeMethod);
		out.writeObject(clampMode);
		out.writeBoolean(doCurrentPlots);
		out.writeBoolean(doGatePlots);
		out.writeBoolean(doVoltagePlots);
		out.writeObject(currentList);
		out.writeObject(symbolTable);

		out.close();
	}
	
	// Restore UI State
	@SuppressWarnings("unchecked")
	public static void restore(File f) throws IOException  {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
		try {
			@SuppressWarnings("unused")
			int sfv = in.readInt();
			vLo.setString((String)in.readObject());
			vHi.setString((String)in.readObject());
			numEntries.setString((String)in.readObject());
			tolerance.setString((String)in.readObject());
			capacitance.setString((String)in.readObject());
			rundur.setString((String)in.readObject());
			odeMethod = (SolverType)in.readObject();
			ui.setSolver(odeMethod);
			clampMode = (ClampMode)in.readObject();
			ui.setClampMode(clampMode);
			doCurrentPlots = in.readBoolean();
			ui.setDoCurrentPlots(doCurrentPlots);
			doGatePlots = in.readBoolean();
			ui.setDoGatePlots(doGatePlots);
			doVoltagePlots = in.readBoolean();
			ui.setDoVoltagePlots(doVoltagePlots);
			currentList = (ArrayList<CurrentState>)in.readObject();

			int N = currentList.size();
			for(int i=0; i<N; i++) {
				CurrentState c = currentList.get(i); 
				c.restore(ui);
			}
			
			symbolTable = (SymbolTable)in.readObject();
			ui.restoreGlobalsTable(symbolTable);
			
		} catch (ClassNotFoundException e) {
			PopupHelper.fatalException("Logic error loading save file", e);
		}

		in.close();
	}

	static FileOutputStream tableFileStream;
	public static void generateTables(File tableFile) throws ParseException, IOException {
		/*
		 *    Table format is as follows:
		 *    Field					Format		Length
		 *    version				int			4
		 *    Cm					float		5
		 *    table low voltage		float		5
		 *    table high value		float		5
		 *    # table entries		int			4
		 *    number of currents	int			4
		 *    
		 *    (repeated for each current)
		 *    Gmax					float		5
		 *    Er					float		5
		 *    number of gates		int			4
		 *    
		 *    (repeated for each gate)
		 *    exponent				int			4
		 *    inf table				float		5*(# table entries)
		 *    1/tau table			float		5*(# table entries)
		 *    
		 */
		
		tableFileStream = new FileOutputStream(tableFile);
		
		try {
			
			writeInt(tableFileVersion);
			
			writeFloat(getCapacitance());
			
			writeFloat(getvLo());
			writeFloat(getvHi());
			writeInt(getNumEntries());

			int currentCnt = 0;
			for(CurrentState c : currentList) {
				if( c instanceof HHCurrentState ) currentCnt++;
			}
			writeInt(currentCnt);
			
			for(CurrentState c : currentList) {
				if( ! (c instanceof HHCurrentState) ) continue;
				HHCurrent hhc = (HHCurrent) c.getPhysicalCurrent();
				
				writeInt(hhc.getGateList().size());
				for( HHGate g : hhc.getGateList()) {
					writeInt(g.getExponent());
					writeFloatTable(g.getInfTable());
					writeFloatTable(g.getTauTable());
				}
			}
			
		} catch (FloPoCoException ex) {
			PopupHelper.fatalException("Floating point exception writing table file", ex);
		}
		
		tableFileStream.close();
	}
	
	private static void writeInt(int i) throws IOException {
		tableFileStream.write(intToLittleEndianByteArray(i));
	}
	private static void writeFloatTable(double []x) throws IOException, FloPoCoException {
		FloPoCoTable ft    = new FloPoCoTable(x);
		for(Byte b : ft) tableFileStream.write(b);
	}
	private static void writeFloat(double f) throws IOException, FloPoCoException {
		writeFloatTable(new double[] {f});
	}
	public static byte[] intToLittleEndianByteArray(int value) {
		return new byte[] {
				(byte)value,
				(byte)(value >>> 8),
				(byte)(value >>> 16),
				(byte)(value >>> 24)
				};
	}

	public static void closeAllGraphs() {
		for(JFrame f : graphPanelList) 
			f.dispose();
		graphPanelList.clear();
	}
	public static void registerGraph(JFrame f) {
		graphPanelList.add(f);
	}

	public static SymbolTable getSymbolTable() {
		return symbolTable;
	}

	public static void generateCOEFiles(File tableDirectory) throws ParseException, IOException {
		try {
			
			for(CurrentState c : currentList) {
				if( ! (c instanceof HHCurrentState) ) continue;
				HHCurrent hhc = (HHCurrent) c.getPhysicalCurrent();
				for( HHGate g : hhc.getGateList()) {
					writeCOETables(hhc, g, tableDirectory);
				}
			}
		} catch (FloPoCoException ex) {
			PopupHelper.fatalException("Floating point exception writing table file", ex);
		}
		
	}
	
	private static void writeCOETables(HHCurrent hhc, HHGate g, File tableDirectory)  throws ParseException, IOException, FloPoCoException {
		{
			String gateName = g.getName();
			String currentName = hhc.getName();
			String fileName = currentName+"_"+gateName+"_tau.coe";
			double [] table = g.getTauTable();
			File out = new File(tableDirectory, fileName);
			PrintStream p = new PrintStream(out);
			p.println(";");
			p.println(";   Current="+currentName+" Gate="+gateName+" Table=1/tau");
			p.println(";");
			p.println("memory_initialization_radix=2;");
			p.println("memory_initialization_vector=");
			for(int i=0; i<table.length; i++) {
				double t = 1/table[i];
				FloPoCoFloat f = new FloPoCoFloat(t, 8, 23);
				p.print(f.toString().replaceAll(" ", ""));
				if( i==table.length-1 )
					p.println(";");
				else
					p.println(",");
			}
			p.close();
		}

		{
			String gateName = g.getName();
			String currentName = hhc.getName();
			String fileName = currentName+"_"+gateName+"_inf.coe";
			double [] table = g.getInfTable();
			File out = new File(tableDirectory, fileName);
			PrintStream p = new PrintStream(out);
			p.println(";");
			p.println(";   Current="+currentName+" Gate="+gateName+" Table=inf");
			p.println(";");
			p.println("memory_initialization_radix=2;");
			p.println("memory_initialization_vector=");
			for(int i=0; i<table.length; i++) {
				double t = table[i];
				FloPoCoFloat f = new FloPoCoFloat(t, 8, 23);
				p.print(f.toString().replaceAll(" ", ""));
				if( i==table.length-1 )
					p.println(";");
				else
					p.println(",");
			}
			p.close();
		}
	}
}

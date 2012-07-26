package modelState;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import modelView.Expression;
import modelView.ModelDesignerView;
import modelView.PopupHelper;

import solving.ClampMode;
import solving.SolverType;
import expressionEvaluator.ParseException;

// Essentially a singleton object that contains the state of the application
public class AppState {

	private static SolverType odeMethod;
	
	private static ClampMode clampMode;
	
	private static Expression vLo;
	private static Expression vHi;
	private static Expression numEntries;
	private static Expression tolerance;
	private static Expression capacitance;
	private static Expression rundur;
	
	private static boolean doCurrentPlots;
	private static boolean doVoltagePlots;
	private static boolean doGatePlots;
	private static boolean doStimulusPlots;
	
	private static ArrayList<CurrentState> currentList;
	
	private static final int saveFileVersion = 1;

	static {
		currentList = new ArrayList<CurrentState>();
		clampMode = ClampMode.CURRENT_CLAMP;
		doCurrentPlots = true;
		doVoltagePlots = true;
		doGatePlots = true;
		doStimulusPlots = true;
	}
	
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
	
	public static final void setvLo(Expression vLo) {
		AppState.vLo = vLo;
	}
	
	public static final double getvHi() throws ParseException {
		try {
			return vHi.eval();
		} catch (NullPointerException npe) { // Occurs when text field is empty - couldn't find a more elegant solution
			throw new ParseException("table high voltage is undefined.");
		}
	}
	
	public static final void setvHi(Expression vHi) {
		AppState.vHi = vHi;
	}
	
	public static final int getNumEntries() throws ParseException {
		try {
			return (int)(Math.round(numEntries.eval()));
		} catch (NullPointerException npe) { // Occurs when text field is empty - couldn't find a more elegant solution
			throw new ParseException("number of table entries is undefined.");
		}
	}
	
	public static final void setNumEntries(Expression numEntries) {
		AppState.numEntries = numEntries;
	}
	
	public static final double getCapacitance() throws ParseException {
		try {
			return capacitance.eval();
		} catch (NullPointerException npe) { // Occurs when text field is empty - couldn't find a more elegant solution
			throw new ParseException("capacitance is undefined.");
		}
	}
	
	public static final Expression getCapacitanceEH() {
		return capacitance;
	}
	
	public static final Expression getRundurEH() {
		return rundur;
	}
	
	public static final Expression getToleranceEH() {
		return tolerance;
	}
	
	public static final Expression getNumEntriesEH() {
		return numEntries;
	}
	
	public static final Expression getvLoEH() {
		return vLo;
	}
	
	public static final Expression getvHiEH() {
		return vHi;
	}
	
	public static final void setCapatiance(Expression capacitance) {
		AppState.capacitance = capacitance;
	}
	
	public static final double getTolerance() throws ParseException {
		try {
			return tolerance.eval();
		} catch (NullPointerException npe) { // Occurs when text field is empty - couldn't find a more elegant solution
			throw new ParseException("tolerance is undefined.");
		}
	}
	
	public static final void setRundur(Expression rundur) {
		AppState.rundur = rundur;
	}
	
	public static final double getRundur() throws ParseException {
		try {
			return rundur.eval();
		} catch (NullPointerException npe) { // Occurs when text field is empty - couldn't find a more elegant solution
			throw new ParseException("run duration is undefined.");
		}
	}
	
	public static final void setTolerance(Expression tolerance) {
		AppState.tolerance = tolerance;
	}
	
	public static void setCapacitance(Expression capacitance) {
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
		out.writeBoolean(doStimulusPlots);
		out.writeBoolean(doVoltagePlots);
		out.writeObject(currentList);

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
			doStimulusPlots = in.readBoolean();
			ui.setDoStimulusPlots(doStimulusPlots);
			doVoltagePlots = in.readBoolean();
			ui.setDoVoltagePlots(doVoltagePlots);
			currentList = (ArrayList<CurrentState>)in.readObject();

			int N = currentList.size();
			for(int i=0; i<N; i++) {
				CurrentState c = currentList.get(i); 
//				if( c instanceof HHCurrentState )
				c.restore(ui);
//					ui.restoreTab(c);
//				else
//					c.setUI(null);
			}
		} catch (ClassNotFoundException e) {
			PopupHelper.fatalException("Logic error loading save file", e);
		}

		in.close();
	}
}

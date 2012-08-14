package modelView;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.AbstractButton;
import javax.swing.AbstractCellEditor;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import modelState.AppState;
import modelState.CurrentState;
import modelState.GateState;
import modelState.HHCurrentState;
import solving.ClampMode;
import solving.Solver;
import solving.SolverType;
import expressionHandling.Expression;
import expressionHandling.ExpressionListener;
import expressionHandling.NumericExpression;
import expressionHandling.SymbolExpression;
import expressionHandling.SymbolTable;
import expressionParsing.ParseException;

public class ModelDesignerView extends JFrame {
	
	private class ExpressionCellEditor extends AbstractCellEditor implements TableCellEditor  {

		private static final long serialVersionUID = 1L;

		private Expression currentExpression;
		
		@Override
		public Object getCellEditorValue() {
			return currentExpression;
		}

		@Override
		public Component getTableCellEditorComponent(JTable t, Object v,
				boolean isSelected, int row, int column) {
			currentExpression = globalExpressions.get(column).get(row);
			JTextField tf = globalTextFields.get(column).get(row); 
			return tf;
		}
	}

	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private JTabbedPane CurrentTabs;
	
	private JFileChooser modelFileChooser;
	File modelFile;
	
	private JFileChooser tableFileChooser;
	
	private JComboBox<SolverType> solver;
	private JTextField tolerance;
	private JTextField capacitance;
	private JTextField rundur;
	private JTextField tableLowVoltage;
	private JTextField tableHighVoltage;
	private JTextField tableNumberEntries;
	private ClampMode  clampMode;
	private JCheckBox  includeCurrents;
	private JCheckBox  includeGates;
	private JCheckBox  includeVoltage;
	
	private JTable globalExpressionTable;
	private ArrayList<ArrayList<JTextField>> globalTextFields;
	private ArrayList<ArrayList<Expression>> globalExpressions;
	private ExpressionCellEditor [] expressionCellEditors;
		
	public ModelDesignerView() {
		getContentPane().setPreferredSize(new Dimension(755, 800));
		initialize();
		
		modelFileChooser = new JFileChooser();
		modelFileChooser.addChoosableFileFilter(new TableToolFileFilter("wmw", "*.wmw model files"));
		modelFileChooser.setCurrentDirectory(new File("."));
		modelFile = null;
		
		tableFileChooser = new JFileChooser();
		tableFileChooser.addChoosableFileFilter(new TableToolFileFilter("tbl", "*.tbl table files"));
		tableFileChooser.setCurrentDirectory(new File("."));

		// Setup the global model parameter expressions
		AppState.setvHi(new NumericExpression(tableHighVoltage));
		tableHighVoltage.getDocument().addDocumentListener(new ExpressionListener(AppState.getvHiEH()));
		AppState.setvLo(new NumericExpression(tableLowVoltage));
		tableLowVoltage.getDocument().addDocumentListener(new ExpressionListener(AppState.getvLoEH()));
		AppState.setNumEntries(new NumericExpression(tableNumberEntries));
		tableNumberEntries.getDocument().addDocumentListener(new ExpressionListener(AppState.getNumEntriesEH()));
		AppState.setTolerance(new NumericExpression(tolerance));
		tolerance.getDocument().addDocumentListener(new ExpressionListener(AppState.getToleranceEH()));
		AppState.setCapacitance(new NumericExpression(capacitance));
		capacitance.getDocument().addDocumentListener(new ExpressionListener(AppState.getCapacitanceEH()));
		AppState.setRundur(new NumericExpression(rundur));
		rundur.getDocument().addDocumentListener(new ExpressionListener(AppState.getRundurEH()));

		initGlobalsTable();
		
		solver.setSelectedItem(SolverType.Euler);
	}
	
	private void initGlobalsTable() {
		globalTextFields = new ArrayList<ArrayList<JTextField>>();
		globalExpressions = new ArrayList<ArrayList<Expression>>();
		expressionCellEditors = new ExpressionCellEditor[2];
		
		for(int col=0; col<globalExpressionTable.getColumnCount(); col++) {
			TableColumn c = globalExpressionTable.getColumnModel().getColumn(col);
			expressionCellEditors[col] = new ExpressionCellEditor();
			c.setCellEditor(expressionCellEditors[col]);
			globalExpressions.add(col, new ArrayList<Expression>());
			
			globalTextFields.add(col, new ArrayList<JTextField>());
		
			for(int row=0; row<globalExpressionTable.getRowCount(); row++) {
				JTextField f = new JTextField();
				Expression e;
				if( col==0 ) {
					e = new SymbolExpression(f);
				} else {
					e = new NumericExpression(f);
				}
				globalExpressions.get(col).add(row, e);
				f.getDocument().addDocumentListener(new ExpressionListener(e));
				globalTextFields.get(col).add(row, f);
			}
		}
	}
	
	public void restoreGlobalsTable(SymbolTable symbolTable) {
		// Called when UI is being restored from a file
		DefaultTableModel model = (DefaultTableModel) globalExpressionTable.getModel();
		int row = 0;
		Set<?> set = symbolTable.entrySet();
		Iterator<?> i = set.iterator();
		while(i.hasNext()) { 
			Map.Entry me = (Map.Entry)i.next(); 
			String var = (String)me.getKey();
			
			// Column 0 is built from string
			JTextField f = globalTextFields.get(0).get(row);
			SymbolExpression s = new SymbolExpression(f);
			s.setString(var);
			SymbolExpression olds = (SymbolExpression) globalExpressions.get(0).get(row);
			olds = s;
			f.setText(var);
			model.setValueAt(s, row, 0);

			// Col 1 is built from an expression
			f = globalTextFields.get(1).get(row);
			NumericExpression n = (NumericExpression) me.getValue();
			NumericExpression oldn = (NumericExpression) globalExpressions.get(1).get(row);
			oldn = n;
			n.setUI(f);
			f.setText(n.getExpr());
			model.setValueAt(n, row, 1);
			
			row++;
		}
		model.fireTableDataChanged();
	}

	
	private void loadSymbolTable() {
		SymbolTable st = AppState.getSymbolTable();
		st.clear();
		for(int row=0; row<globalExpressions.get(0).size(); row++) {
			SymbolExpression s = (SymbolExpression) globalExpressions.get(0).get(row);
			NumericExpression v = (NumericExpression) globalExpressions.get(1).get(row);
			if( s==null ) continue;
			String name = s.getExpr();
			if( name==null || name.equals("") ) continue;
			st.put(name, v);
		}
	}
	
	private void clearGlobalTable() {
		DefaultTableModel model = (DefaultTableModel) globalExpressionTable.getModel();
		for(int row=0; row<globalExpressionTable.getRowCount(); row++) {
			for(int col=0; col<globalExpressionTable.getColumnCount(); col++) {
				model.setValueAt(null, row, col);
				Expression e = globalExpressions.get(col).get(row);
				e.setString(null);
			}
		}
	}
	
	private void deleteGlobalTableRow() {
		DefaultTableModel model = (DefaultTableModel) globalExpressionTable.getModel();
		
		if( model.getRowCount()==0 ) return;
		
		// If the user has a row selected delete that
		int row = globalExpressionTable.getSelectedRow();
		if( row!=-1 ) {
			deleteGlobalTableRow(row);
			return;
		}
		
		// If no row selected find the first empty row
		for(int i=0; i<model.getRowCount(); i++) {
			boolean b = true;
			for(int j=0; j<model.getColumnCount(); j++) {
				Object s = model.getValueAt(i, j);
				if( s==null || s.equals("") ) b &= true;
				else b &= false;
			}
			if( b ) {
				deleteGlobalTableRow(i);
				return;
			}
		}
		
		// Couldn't find a good candidate just delete the last row.
		deleteGlobalTableRow(model.getRowCount()-1);
	}
	
	private void deleteGlobalTableRow(int row) {
		DefaultTableModel model = (DefaultTableModel) globalExpressionTable.getModel();
		model.removeRow(row);
		model.fireTableChanged(null);
		for(int col=0; col<globalExpressionTable.getColumnCount(); col++) {
			// Editors seem to get lost after fireTableChange
			TableColumn c = globalExpressionTable.getColumnModel().getColumn(col);
			c.setCellEditor(expressionCellEditors[col]);

			globalTextFields.get(col).remove(row);
			globalExpressions.get(col).remove(row);
		}
	}
	
	public void addGlobalTableRow(String name, String value) {
		DefaultTableModel model = (DefaultTableModel) globalExpressionTable.getModel();
		model.addRow(new String[] {name, value});
		
		for(int col=0; col<globalExpressionTable.getColumnCount(); col++) {
			JTextField f = new JTextField();
			NumericExpression e = new NumericExpression(f);
			f.getDocument().addDocumentListener(new ExpressionListener(e));
			globalExpressions.get(col).add(e);
			globalTextFields.get(col).add(f);
		}
	}

	public CurrentTab addNewHHTab() {
		return new HHCurrentTab(CurrentTabs);
	}
	
	public StimCurrentTab addNewStimTab() {
		return new StimCurrentTab(CurrentTabs);
	}
	
	public void reset() {
		CurrentTabs.removeAll();
		
		AppState.reset();
		
		setSolver(SolverType.Euler);
		setClampMode(ClampMode.CURRENT_CLAMP);
		setDoCurrentPlots(true);
		setDoVoltagePlots(true);
		setDoGatePlots(true);
		
		clearGlobalTable();
	}

	public void setSolver(SolverType s) { solver.setSelectedItem(s); }
	public void setClampMode(ClampMode c) {
		for(Enumeration<AbstractButton>  e = buttonGroup.getElements(); e.hasMoreElements();) {
			JRadioButton b = (JRadioButton)e.nextElement();
			if( b.getText().equals(c.toString()) ) {
				b.setSelected(true);
				return;
			}
		}
		assert false;
	}
	public void setDoCurrentPlots(boolean s)  { includeCurrents.setSelected(s); }
	public void setDoVoltagePlots(boolean s)  { includeVoltage.setSelected(s);  }
	public void setDoGatePlots(boolean s)     { includeGates.setSelected(s);    }

	protected void toggleGatePlots(boolean b) {
		for( CurrentState c : AppState.getCurrentList() ) {
			if( !(c instanceof HHCurrentState) ) continue;
			HHCurrentState hhc = (HHCurrentState)c;
			for( GateState g : hhc.getGates() ) g.setDoPlots(b);
		}
	}

	protected void toggleCurrentPlots(boolean b) {
		for( CurrentState c : AppState.getCurrentList() ) c.setDoPlots(b);
	}

	public JTabbedPane getCurrentTabs() {
		return CurrentTabs;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModelDesignerView.class.getResource("/images/whatmeworry.jpg")));
		setTitle("TableTool");
		setBounds(100, 100, 766, 868);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reset();
			}
		});
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open ...");
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = modelFileChooser.showOpenDialog(ModelDesignerView.this);
				if (returnVal != JFileChooser.APPROVE_OPTION) {
					return;
				}
				modelFile = modelFileChooser.getSelectedFile();
				try {
					reset();
					AppState.restore(modelFile);
				} catch (IOException ex) {
					PopupHelper.errorMessage(ModelDesignerView.this, "Cannot load save file - "+ex.getMessage());
					ex.printStackTrace();
				}
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSaveCtrls = new JMenuItem("Save");
		mntmSaveCtrls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if( modelFile==null ) {
					int returnVal = modelFileChooser.showSaveDialog(ModelDesignerView.this);

					if (returnVal != JFileChooser.APPROVE_OPTION) return;
					modelFile = modelFileChooser.getSelectedFile();					
				}
				try {
					AppState.save(modelFile);
				} catch (IOException ex) {
					PopupHelper.errorMessage(ModelDesignerView.this, "Cannot save - "+ex.getMessage());
					ex.printStackTrace();
				}
			}
		});
		mntmSaveCtrls.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnFile.add(mntmSaveCtrls);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save as...");
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = modelFileChooser.showSaveDialog(ModelDesignerView.this);

				if (returnVal != JFileChooser.APPROVE_OPTION) return;
				modelFile = modelFileChooser.getSelectedFile();
				try {
					AppState.save(modelFile);
				} catch (IOException ex) {
					PopupHelper.errorMessage(ModelDesignerView.this, "Cannot save - "+ex.getMessage());
					ex.printStackTrace();
				}
			}
		});
		mnFile.add(mntmSaveAs);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnSimulate = new JMenu("Simulate");
		menuBar.add(mnSimulate);
		
		JMenuItem mntmRun = new JMenuItem("Run");
		mntmRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadSymbolTable();
				Solver s = null;
				try {
					s = new Solver();
				} catch (ParseException ex) {
					PopupHelper.errorMessage(ModelDesignerView.this, "Cannot start solver - "+ex.getMessage());
					return;
				} catch (IllegalArgumentException ex) { // Thrown during symbol lookup
					PopupHelper.errorMessage(ModelDesignerView.this, ex.getMessage());
					return;
				}
				s.go();
			}
		});
		mntmRun.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mnSimulate.add(mntmRun);
		
		JMenu mnDynamicclamp = new JMenu("DynamicClamp");
		menuBar.add(mnDynamicclamp);
		
		JMenuItem mntmGenerateTables = new JMenuItem("Generate tables");
		mntmGenerateTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = tableFileChooser.showSaveDialog(ModelDesignerView.this);

				if (returnVal != JFileChooser.APPROVE_OPTION) return;
				File tableFile = tableFileChooser.getSelectedFile();
				try {
					AppState.generateTables(tableFile);
				} catch (ParseException ex) {
					PopupHelper.errorMessage(ModelDesignerView.this, "Cannot generate tables - "+ex.getMessage());
				} catch (IOException ex) {
					PopupHelper.errorMessage(ModelDesignerView.this, "Cannot save tables - "+ex.getMessage());
				}
			}
		});
		mntmGenerateTables.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		mnDynamicclamp.add(mntmGenerateTables);
		
		JMenu mnAbout = new JMenu("Help");
		menuBar.add(mnAbout);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PopupHelper.showHelp();
			}
		});
		mnAbout.add(mntmHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PopupHelper.about(ModelDesignerView.this);
			}
		});
		mnAbout.add(mntmAbout);
		
		JPanel globalExpressionPanel = new JPanel();
		globalExpressionPanel.setBounds(518, 11, 227, 306);
		globalExpressionPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Global expressions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel mainSettingsPanel = new JPanel();
		mainSettingsPanel.setBounds(10, 11, 495, 305);
		mainSettingsPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Main settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblSolver = new JLabel("Solver");
		lblSolver.setBounds(16, 49, 48, 16);
		
		solver = new JComboBox<SolverType>();
		solver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				@SuppressWarnings("unchecked")
				JComboBox<SolverType> b = (JComboBox<SolverType>)arg0.getSource();
				SolverType odeMethod = (SolverType)b.getSelectedItem();
				AppState.setOdeMethod(odeMethod);
			}
		});
		solver.setBounds(62, 46, 152, 20);
		solver.setModel(new DefaultComboBoxModel<SolverType>(SolverType.values()));
		AppState.setOdeMethod((SolverType) solver.getSelectedItem());
		
		JLabel lblTolerancestepSize = new JLabel("Tolerance/step size");
		lblTolerancestepSize.setBounds(239, 49, 128, 16);
		
		tolerance = new JTextField();
		tolerance.setBounds(368, 47, 70, 20);
		tolerance.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JLabel lblCellCapacitance = new JLabel("Cell capacitance (pF)");
		lblCellCapacitance.setBounds(16, 87, 134, 16);
		
		capacitance = new JTextField();
		capacitance.setBounds(144, 85, 70, 20);
		capacitance.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));

		JPanel clampMode = new JPanel();
		clampMode.setBounds(16, 111, 191, 43);
		clampMode.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Clamp mode", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblRunDurationms = new JLabel("Run duration (ms)");
		lblRunDurationms.setBounds(240, 87, 127, 16);
		
		rundur = new JTextField();
		rundur.setBounds(368, 85, 70, 20);
		rundur.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JButton btnNewHHCurrent = new JButton("New HH current");
		btnNewHHCurrent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewHHTab();
			}
		});
		btnNewHHCurrent.setBounds(233, 259, 134, 26);
		
		JButton btnNewStim = new JButton("New stimulus");
		btnNewStim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewStimTab();
			}
		});
		btnNewStim.setBounds(368, 259, 118, 26);
		
		JPanel plotOptionsPanel = new JPanel();
		plotOptionsPanel.setBounds(228, 116, 258, 137);
		plotOptionsPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Plot options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel = new JPanel();
		panel.setBounds(16, 171, 191, 122);
		panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Table parameters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblLowmv = new JLabel("Low (mV)");
		lblLowmv.setBounds(16, 29, 67, 15);
		
		JLabel lblHighmv = new JLabel("High (mV)");
		lblHighmv.setBounds(16, 59, 67, 15);
		
		JLabel lblNumberOfEntries = new JLabel("Num of entries");
		lblNumberOfEntries.setBounds(12, 93, 112, 15);
		
		tableLowVoltage = new JTextField();
		tableLowVoltage.setBounds(120, 26, 60, 20);
		tableLowVoltage.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));

		tableHighVoltage = new JTextField();
		tableHighVoltage.setBounds(120, 57, 60, 20);
		tableHighVoltage.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		tableNumberEntries = new JTextField();
		tableNumberEntries.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		tableNumberEntries.setBounds(120, 90, 60, 20);
		
		includeCurrents = new JCheckBox("Currents");
		includeCurrents.setSelected(true);
		includeCurrents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppState.setDoCurrentPlots(includeCurrents.isSelected());
			}
		});
		includeCurrents.setBounds(16, 16, 89, 23);
		
		includeVoltage = new JCheckBox("Voltage");
		includeVoltage.setSelected(true);
		includeVoltage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppState.setDoVoltagePlots(includeVoltage.isSelected());
			}
		});
		includeVoltage.setBounds(139, 16, 95, 23);
		
		includeGates = new JCheckBox("Gates");
		includeGates.setSelected(true);
		includeGates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppState.setDoGatePlots(includeGates.isSelected());
			}
		});
		includeGates.setBounds(16, 42, 74, 23);
		
		JButton btnAllGatesOn = new JButton("All gates on");
		btnAllGatesOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toggleGatePlots(true);
			}
		});
		btnAllGatesOn.setBounds(12, 67, 101, 23);
		
		JButton btnAllCurrentsOn = new JButton("All currents on");
		btnAllCurrentsOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleCurrentPlots(true);
			}
		});
		btnAllCurrentsOn.setBounds(125, 67, 121, 23);
		
		JButton btnAllGatesOff = new JButton("All gates off");
		btnAllGatesOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleGatePlots(false);
			}
		});
		btnAllGatesOff.setBounds(12, 96, 101, 23);
		
		JButton btnAllCurrentsOff = new JButton("All currents off");
		btnAllCurrentsOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleCurrentPlots(false);
			}
		});
		btnAllCurrentsOff.setBounds(125, 96, 121, 23);
		
		JRadioButton rdbtnCurrent = new JRadioButton("Current");
		rdbtnCurrent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModelDesignerView.this.clampMode = ClampMode.CURRENT_CLAMP;
				AppState.setClampMode(ModelDesignerView.this.clampMode);
			}
		});
		rdbtnCurrent.setBounds(12, 16, 77, 23);
		rdbtnCurrent.setSelected(true);
		buttonGroup.add(rdbtnCurrent);
		
		JRadioButton rdbtnVoltage = new JRadioButton("Voltage");
		rdbtnVoltage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModelDesignerView.this.clampMode = ClampMode.VOLTAGE_CLAMP;
				AppState.setClampMode(ModelDesignerView.this.clampMode);
			}
		});
		rdbtnVoltage.setBounds(93, 16, 92, 23);
		buttonGroup.add(rdbtnVoltage);
		
		JButton btnNewRow = new JButton("New row");
		btnNewRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addGlobalTableRow(null, null);
			}
		});
		btnNewRow.setBounds(16, 272, 90, 23);
		
		JButton btnDeleteRow = new JButton("delete row");
		btnDeleteRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteGlobalTableRow();
			}
			
		});
		btnDeleteRow.setBounds(118, 272, 95, 23);
		
		CurrentTabs = new JTabbedPane(JTabbedPane.TOP);
		CurrentTabs.setBounds(10, 335, 735, 456);
		CurrentTabs.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Current definitions", TitledBorder.LEADING, TitledBorder.TOP, null, null));		

		plotOptionsPanel.setLayout(null);
		plotOptionsPanel.add(btnAllGatesOn);
		plotOptionsPanel.add(btnAllCurrentsOn);
		plotOptionsPanel.add(includeCurrents);
		plotOptionsPanel.add(includeGates);
		plotOptionsPanel.add(includeVoltage);
		plotOptionsPanel.add(btnAllGatesOff);
		plotOptionsPanel.add(btnAllCurrentsOff);
		panel.setLayout(null);
		panel.add(lblLowmv);
		panel.add(lblHighmv);
		panel.add(lblNumberOfEntries);
		panel.add(tableLowVoltage);
		panel.add(tableHighVoltage);
		panel.add(tableNumberEntries);
		clampMode.setLayout(null);
		clampMode.add(rdbtnCurrent);
		clampMode.add(rdbtnVoltage);
		globalExpressionPanel.setLayout(null);
		globalExpressionPanel.add(btnNewRow);
		globalExpressionPanel.add(btnDeleteRow);
		getContentPane().setLayout(null);
		mainSettingsPanel.setLayout(null);
		mainSettingsPanel.add(lblSolver);
		mainSettingsPanel.add(solver);
		mainSettingsPanel.add(lblTolerancestepSize);
		mainSettingsPanel.add(tolerance);
		mainSettingsPanel.add(lblCellCapacitance);
		mainSettingsPanel.add(capacitance);
		mainSettingsPanel.add(lblRunDurationms);
		mainSettingsPanel.add(rundur);
		mainSettingsPanel.add(clampMode);
		mainSettingsPanel.add(panel);
		mainSettingsPanel.add(plotOptionsPanel);
		
		JButton btnNewButton = new JButton("close all");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AppState.closeAllGraphs();
			}
		});
		btnNewButton.setBounds(125, 42, 121, 23);
		plotOptionsPanel.add(btnNewButton);
		mainSettingsPanel.add(btnNewHHCurrent);
		mainSettingsPanel.add(btnNewStim);
		getContentPane().add(mainSettingsPanel);
		getContentPane().add(globalExpressionPanel);		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 25, 201, 236);
		globalExpressionPanel.add(scrollPane);
		
		globalExpressionTable = new JTable();
		scrollPane.setViewportView(globalExpressionTable);
		globalExpressionTable.setRowSelectionAllowed(false);
		globalExpressionTable.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		globalExpressionTable.setModel(new DefaultTableModel(
			new String[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Name", "Value"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		getContentPane().add(CurrentTabs);
	}
}

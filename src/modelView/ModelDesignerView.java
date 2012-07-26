package modelView;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import modelState.AppState;
import expressionEvaluator.ParseException;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import solving.ClampMode;
import solving.Solver;
import solving.SolverType;

public class ModelDesignerView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTable globalExpressionTable;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private JTabbedPane CurrentTabs;
	
	private JFileChooser modelFileChooser;
	File modelFile;
	private JComboBox solver;
	private JTextField tolerance;
	private JTextField capacitance;
	private JTextField rundur;
	private JTextField tableLowVoltage;
	private JTextField tableHighVoltage;
	private JTextField tableNumberEntries;
	private ClampMode clampMode;
	private JCheckBox chckbxCurrents;
	private JCheckBox chckbxGates;
	private JCheckBox chckbxVoltage;
	private JCheckBox chckbxStimulus;
		
	public ModelDesignerView() {
		initialize();
		
		modelFileChooser = new JFileChooser();
		modelFileChooser.addChoosableFileFilter(new ModelFileFilter());
		modelFileChooser.setCurrentDirectory(new File("."));
	
		AppState.setvHi(new Expression(tableHighVoltage));
		AppState.getvHiEH().setString("100");
		AppState.setvLo(new Expression(tableLowVoltage));
		AppState.getvLoEH().setString("-100");
		AppState.setNumEntries(new Expression(tableNumberEntries));
		AppState.getNumEntriesEH().setString("16384");
		AppState.setTolerance(new Expression(tolerance));
		AppState.getToleranceEH().setString("0.01");
		AppState.setCapacitance(new Expression(capacitance));
		AppState.getCapacitanceEH().setString("2");
		AppState.setRundur(new Expression(rundur));
		AppState.getRundurEH().setString("100");
		
		solver.setSelectedItem(SolverType.RungeKutta);
	}
	
	public CurrentTab addNewHHTab() {
		return new HHCurrentTab(CurrentTabs);
	}
	
	public StepCurrentTab addNewStepTab() {
		return new StepCurrentTab(CurrentTabs);
	}
	

	public void reset() {
		CurrentTabs.removeAll();
		
		AppState.reset();
		
		setSolver(SolverType.Euler);
		setClampMode(ClampMode.CURRENT_CLAMP);
		setDoCurrentPlots(true);
		setDoVoltagePlots(true);
		setDoGatePlots(true);
		setDoStimulusPlots(true);
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
	public void setDoCurrentPlots(boolean s)  { chckbxCurrents.setSelected(s); }
	public void setDoVoltagePlots(boolean s)  { chckbxVoltage.setSelected(s);  }
	public void setDoGatePlots(boolean s)     { chckbxGates.setSelected(s);    }
	public void setDoStimulusPlots(boolean s) { chckbxStimulus.setSelected(s); }

	public JTabbedPane getCurrentTabs() {
		return CurrentTabs;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModelDesignerView.class.getResource("/images/whatmeworry.jpg")));
		setTitle("Model Designer");
		setBounds(100, 100, 766, 868);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reset();
			}
		});
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open ...");
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
				}
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSaveCtrls = new JMenuItem("Save");
		mntmSaveCtrls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AppState.save(new File("x.wmw"));
				} catch (IOException ex) {
					PopupHelper.errorMessage(ModelDesignerView.this, "Cannot save - "+ex.getMessage());
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
				}
			}
		});
		mnFile.add(mntmSaveAs);
		
		JMenuItem mntmCompile = new JMenuItem("Compile");
		mnFile.add(mntmCompile);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mnFile.add(mntmClose);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
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
				Solver s = null;
				try {
					s = new Solver();
				} catch (ParseException ex) {
					PopupHelper.errorMessage(ModelDesignerView.this, "Cannot start solver - "+ex.getMessage());
					return;
				}
				s.go();
			}
		});
		mntmRun.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mnSimulate.add(mntmRun);
		
		JMenuItem mntmStop = new JMenuItem("Stop");
		mnSimulate.add(mntmStop);
		
		JMenuItem mntmRestart = new JMenuItem("Restart");
		mnSimulate.add(mntmRestart);
		
		JPanel globalExpressionPanel = new JPanel();
		globalExpressionPanel.setBounds(518, 11, 227, 306);
		globalExpressionPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Global expressions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel mainSettingsPanel = new JPanel();
		mainSettingsPanel.setBounds(10, 11, 495, 305);
		mainSettingsPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Main settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblSolver = new JLabel("Solver");
		lblSolver.setBounds(16, 49, 36, 16);
		
		solver = new JComboBox();
		solver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox b = (JComboBox)arg0.getSource();
				SolverType odeMethod = (SolverType)b.getSelectedItem();
				AppState.setOdeMethod(odeMethod);
			}
		});
		solver.setBounds(62, 46, 152, 20);
		solver.setModel(new DefaultComboBoxModel(SolverType.values()));
		AppState.setOdeMethod((SolverType) solver.getSelectedItem());
		
		JLabel lblTolerancestepSize = new JLabel("Tolerance/step size");
		lblTolerancestepSize.setBounds(239, 49, 111, 16);
		
		tolerance = new JTextField();
		tolerance.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				AppState.getToleranceEH().textFieldHandler(e);
		}
		});
		tolerance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppState.getToleranceEH().textFieldHandler(e);
			}
		});
		tolerance.setBounds(368, 47, 70, 20);
		tolerance.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JLabel lblCellCapacitance = new JLabel("Cell capacitance (pF)");
		lblCellCapacitance.setBounds(16, 87, 118, 16);
		
		capacitance = new JTextField();
		capacitance.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				AppState.getCapacitanceEH().textFieldHandler(e);
			}
		});
		capacitance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppState.getCapacitanceEH().textFieldHandler(e);
			}
		});
		capacitance.setBounds(144, 85, 70, 20);
		capacitance.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));

		JPanel clampMode = new JPanel();
		clampMode.setBounds(16, 111, 191, 43);
		clampMode.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Clamp mode", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblRunDurationms = new JLabel("Run duration (ms)");
		lblRunDurationms.setBounds(240, 87, 101, 16);
		
		rundur = new JTextField();
		rundur.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				AppState.getRundurEH().textFieldHandler(e);
			}
		});
		rundur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppState.getRundurEH().textFieldHandler(e);
			}
		});
		rundur.setBounds(368, 85, 70, 20);
		rundur.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JButton btnNewHHCurrent = new JButton("New HH current");
		btnNewHHCurrent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewHHTab();
			}
		});
		btnNewHHCurrent.setBounds(233, 259, 123, 26);
		
		JButton btnNewStim = new JButton("New stimulus");
		btnNewStim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewStepTab();
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
		
		JLabel lblNumberOfEntries = new JLabel("Number of entries");
		lblNumberOfEntries.setBounds(12, 93, 112, 15);
		
		tableLowVoltage = new JTextField();
		tableLowVoltage.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				AppState.getvLoEH().textFieldHandler(e);
			}
		});
		tableLowVoltage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppState.getvLoEH().textFieldHandler(e);
			}
		});
		tableLowVoltage.setBounds(120, 26, 60, 20);
		tableLowVoltage.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		tableHighVoltage = new JTextField();
		tableHighVoltage.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				AppState.getvHiEH().textFieldHandler(e);
			}
		});
		tableHighVoltage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppState.getvHiEH().textFieldHandler(e);
			}
		});
		tableHighVoltage.setBounds(120, 57, 60, 20);
		tableHighVoltage.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		tableNumberEntries = new JTextField();
		tableNumberEntries.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				AppState.getNumEntriesEH().textFieldHandler(e);
			}
		});
		tableNumberEntries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppState.getNumEntriesEH().textFieldHandler(e);
			}
		});
		tableNumberEntries.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		tableNumberEntries.setBounds(120, 90, 60, 20);
		
		chckbxCurrents = new JCheckBox("Currents");
		chckbxCurrents.setSelected(true);
		chckbxCurrents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppState.setDoCurrentPlots(chckbxCurrents.isSelected());
			}
		});
		chckbxCurrents.setBounds(16, 16, 89, 23);
		
		chckbxVoltage = new JCheckBox("Voltage");
		chckbxVoltage.setSelected(true);
		chckbxVoltage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppState.setDoVoltagePlots(chckbxVoltage.isSelected());
			}
		});
		chckbxVoltage.setBounds(139, 16, 74, 23);
		
		chckbxGates = new JCheckBox("Gates");
		chckbxGates.setSelected(true);
		chckbxGates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppState.setDoGatePlots(chckbxGates.isSelected());
			}
		});
		chckbxGates.setBounds(16, 42, 74, 23);
		
		chckbxStimulus = new JCheckBox("Stimulus");
		chckbxStimulus.setSelected(true);
		chckbxStimulus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppState.setDoStimulusPlots(chckbxStimulus.isSelected());
			}
		});
		chckbxStimulus.setBounds(139, 42, 78, 23);
		
		JButton btnAllGatesOn = new JButton("All gates on");
		btnAllGatesOn.setBounds(12, 67, 101, 23);
		
		JButton btnAllCurrentsOn = new JButton("All currents on");
		btnAllCurrentsOn.setBounds(125, 67, 121, 23);
		
		JButton btnAllGatesOff = new JButton("All gates off");
		btnAllGatesOff.setBounds(12, 96, 101, 23);
		
		JButton btnAllCurrentsOff = new JButton("All currents off");
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
		rdbtnVoltage.setBounds(93, 16, 77, 23);
		buttonGroup.add(rdbtnVoltage);
		
		JButton btnNewRow = new JButton("New");
		btnNewRow.setBounds(16, 248, 90, 23);
		
		JButton btnDeleteRow = new JButton("Delete");
		btnDeleteRow.setBounds(118, 248, 95, 23);
		
		CurrentTabs = new JTabbedPane(JTabbedPane.TOP);
		CurrentTabs.setBounds(10, 335, 735, 456);
		CurrentTabs.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Current definitions", TitledBorder.LEADING, TitledBorder.TOP, null, null));		

		plotOptionsPanel.setLayout(null);
		plotOptionsPanel.add(btnAllGatesOn);
		plotOptionsPanel.add(btnAllCurrentsOn);
		plotOptionsPanel.add(chckbxCurrents);
		plotOptionsPanel.add(chckbxGates);
		plotOptionsPanel.add(chckbxStimulus);
		plotOptionsPanel.add(chckbxVoltage);
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
		mainSettingsPanel.add(btnNewHHCurrent);
		mainSettingsPanel.add(btnNewStim);
		getContentPane().add(mainSettingsPanel);
		getContentPane().add(globalExpressionPanel);		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 25, 201, 200);
		globalExpressionPanel.add(scrollPane);
		
		globalExpressionTable = new JTable();
		scrollPane.setViewportView(globalExpressionTable);
		globalExpressionTable.setRowSelectionAllowed(false);
		globalExpressionTable.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		globalExpressionTable.setModel(new DefaultTableModel(
			new Object[][] {
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
			private static final long serialVersionUID = 2245720180545019300L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		getContentPane().add(CurrentTabs);
	}

}

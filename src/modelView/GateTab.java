package modelView;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;

import expressionEvaluator.ParseException;

import plotting.GatePlotter;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import modelState.GateState;
import modelState.HHCurrentState;

public class GateTab extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private JTabbedPane GateTabPanel;
	private CurrentTab owningPanel;
	private GateState gateState;
	private Expression tau;
	private Expression inf;
	private Expression alpha;
	private Expression beta;
	private JTextPane txtpnTau;
	private JTextPane txtpnInf;
	private JTextPane txtpnAlpha;
	private JTextPane txtpnBeta;
	private HHCurrentState owningCurrent;
	private JSpinner spinner;

	public GateTab(JTabbedPane GateTabPanel, CurrentTab owningPanel, HHCurrentState owningCurrent) {
		super();
		this.GateTabPanel = GateTabPanel;
		this.owningPanel  = owningPanel;

		initialize();
		GateTabPanel.setSelectedComponent(this);		

		tau   = new Expression(txtpnTau);
		inf   = new Expression(txtpnInf);
		alpha = new Expression(txtpnAlpha);
		beta  = new Expression(txtpnBeta);

		this.owningCurrent = owningCurrent;
		gateState = new GateState(this, owningCurrent, tau, inf, alpha, beta);
		owningCurrent.addGate(gateState);
	}

	public GateTab(JTabbedPane GateTabPanel, HHCurrentTab owningPanel, GateState gateState) {
		super();
		this.GateTabPanel = GateTabPanel;
		this.owningPanel  = owningPanel;
		this.gateState    = gateState;
		
		initialize();
		
		this.gateState.setUI(this);
		
		tau = gateState.getTau();
		inf = gateState.getInf();
		alpha = gateState.getAlpha();
		beta = gateState.getBeta();
		tau.setUI(txtpnTau);
		inf.setUI(txtpnInf);
		alpha.setUI(txtpnAlpha);
		beta.setUI(txtpnBeta);
		
		owningCurrent = (HHCurrentState) owningPanel.getHHCurrentState();
	}

	public void setName(String newname) {
		super.setName(newname);
		int i = GateTabPanel.indexOfComponent(this);
		GateTabPanel.setTitleAt(i, newname);
		gateState.setName(newname);
	}
	
	public String getName() {
		int i = GateTabPanel.indexOfComponent(GateTab.this);
		return GateTabPanel.getTitleAt(i);
	}
	
	public GateState getGateState() {
		return gateState;
	}
	
	private void delete() {
		owningCurrent.removeGate(gateState);
		GateTabPanel.remove(this);
	}
	
	public void setExponent(int exponent) {
		spinner.setValue(exponent);
	}
	
	private void initialize() {
		GateTabPanel.addTab("gate", null, this, null);
		
		final JCheckBox chckbxEnabled = new JCheckBox("Enabled");
		chckbxEnabled.setBounds(8, 12, 81, 23);
		chckbxEnabled.setSelected(true);
		chckbxEnabled.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				gateState.setEnabled(chckbxEnabled.isSelected());
			}
		});
		
		spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				gateState.setExponent((Integer)spinner.getValue());
			}
		});
		spinner.setBounds(290, 78, 31, 20);
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(0), null, new Integer(1)));
		
		JLabel lblExponent = new JLabel("Exponent");
		lblExponent.setBounds(236, 81, 53, 14);
		
		txtpnTau = new JTextPane();
		txtpnTau.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				GateTab.this.tau.textFieldHandler(e);
			}
		});
		txtpnTau.setBounds(8, 195, 153, 40);
		txtpnTau.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		txtpnTau.setText("");
		
		JLabel lblTaums = new JLabel("tau (ms)");
		lblTaums.setBounds(61, 175, 57, 16);
		
		txtpnInf = new JTextPane();
		txtpnInf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				GateTab.this.inf.textFieldHandler(e);
			}
		});
		txtpnInf.setBounds(173, 195, 153, 40);
		txtpnInf.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		txtpnInf.setText("");
		
		JLabel lblInf = new JLabel("inf");
		lblInf.setBounds(236, 175, 27, 16);
		
		JLabel lblAlpha = new JLabel("alpha (1/ms)");
		lblAlpha.setBounds(49, 239, 87, 16);
		
		txtpnAlpha = new JTextPane();
		txtpnAlpha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				GateTab.this.alpha.textFieldHandler(e);
			}
		});
		txtpnAlpha.setBounds(8, 258, 153, 40);
		txtpnAlpha.setText("");
		txtpnAlpha.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JLabel lblBeta = new JLabel("beta (1/ms)");
		lblBeta.setBounds(217, 239, 64, 16);
		
		txtpnBeta = new JTextPane();
		txtpnBeta.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				GateTab.this.beta.textFieldHandler(e);
			}
		});
		txtpnBeta.setBounds(173, 258, 153, 40);
		txtpnBeta.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JCheckBox chckbxIncludeGateInPlots = new JCheckBox("Include in simulation plots");
		chckbxIncludeGateInPlots.setBounds(102, 12, 180, 23);
		chckbxIncludeGateInPlots.setSelected(true);
		
		JButton btnDeleteThisGate = new JButton("Delete");
		btnDeleteThisGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDeleteThisGate.setBounds(8, 37, 72, 23);
		
		JButton btnRenameGate = new JButton("Rename");
		btnRenameGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oldName = getName();
				String newName = PopupHelper.newName((JFrame) GateTabPanel.getTopLevelAncestor(), "Enter new gate name:", oldName);
				setName(newName);
				}
		});
		btnRenameGate.setBounds(90, 37, 81, 23);
		
		JButton btnPlot = new JButton("Plot");
		btnPlot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( !gateState.getPhysicalGate().isReady() ) {
					PopupHelper.errorMessage((JFrame) GateTabPanel.getTopLevelAncestor(), "Cannot plot - gate functions are not properly defined.");
					return;
				}
				int i = GateTabPanel.indexOfComponent(GateTab.this);
				String gateName = GateTabPanel.getTitleAt(i);
				String currentName = owningPanel.getName();
				try {
					new GatePlotter(currentName+" - "+gateName, gateState.getPhysicalGate());
				} catch (ParseException e1) {
					PopupHelper.errorMessage((JFrame) GateTabPanel.getTopLevelAncestor(), "Cannot plot - "+e1.getMessage());
				}
			}
		});
		btnPlot.setBounds(181, 37, 72, 23);
		add(btnPlot);
		
		JEditorPane textArea = new JEditorPane();
		textArea.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		textArea.setBounds(8, 101, 316, 71);

		JLabel label = new JLabel("Notes");
		label.setBounds(8, 83, 54, 15);
		
		setLayout(null);
		add(chckbxEnabled);
		add(chckbxIncludeGateInPlots);
		add(lblTaums);
		add(label);
		add(btnDeleteThisGate);
		add(btnRenameGate);
		add(lblExponent);
		add(spinner);
		add(lblInf);
		add(txtpnAlpha);
		add(txtpnTau);
		add(txtpnBeta);
		add(txtpnInf);
		add(lblAlpha);
		add(lblBeta);
		add(textArea);
	}
}

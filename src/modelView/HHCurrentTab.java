package modelView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import expressionHandling.ExpressionListener;
import expressionHandling.NumericExpression;

import modelState.AppState;
import modelState.CurrentState;
import modelState.GateState;
import modelState.HHCurrentState;

public class HHCurrentTab extends CurrentTab {
	
	private static final long serialVersionUID = 1L;
	
	private JTabbedPane gatePane;
	private JTextField txtReversal;
	private JTextField txtConductance;
	private NumericExpression reversal;
	private NumericExpression conductance;

	// Used during create from UI
	/**
	 * @wbp.parser.constructor
	 */
	public HHCurrentTab(JTabbedPane CurrentTabPanel) {
		super();
		this.CurrentTabPanel = CurrentTabPanel;
		
		initialize();
		
		reversal = new NumericExpression(txtReversal);
		conductance = new NumericExpression(txtConductance);
		currentState = new HHCurrentState(this, myName(), conductance, reversal);
		AppState.addCurrent(currentState);
		txtConductance.getDocument().addDocumentListener(new ExpressionListener(conductance));
		txtReversal.getDocument().addDocumentListener(new ExpressionListener(reversal));
	
		CurrentTabPanel.setSelectedComponent(this);
	}
	
	// Used during restore
	public HHCurrentTab(JTabbedPane currentTabPanel, HHCurrentState currentState) {
		
		super();
		this.CurrentTabPanel = currentTabPanel;
		
		initialize();
		
		this.currentState = currentState;
		currentState.setUI(this);
		reversal = currentState.getReversal();
		conductance = currentState.getConductance();
		
		conductance.setUI(txtConductance);
		reversal.setUI(txtReversal);
		txtConductance.getDocument().addDocumentListener(new ExpressionListener(conductance));
		txtReversal.getDocument().addDocumentListener(new ExpressionListener(reversal));

		currentTabPanel.setSelectedComponent(this);		
	}
	
	public GateTab addNewTab() {
		return new GateTab(gatePane, this, (HHCurrentState) currentState);
	}
	
	public GateTab restoreTab(GateState g) {
		return new GateTab(gatePane, this, g);
	}
	

	public CurrentState getHHCurrentState() { return currentState; }
	
	private void initialize() {
		CurrentTabPanel.addTab("HH current", null, this, null);
		
		gatePane = new JTabbedPane(JTabbedPane.TOP);
		gatePane.setBounds(350, 20, 351, 364);
		gatePane.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Gates", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		enabled = new JCheckBox("Enabled");
		enabled.setBounds(21, 19, 78, 23);
		enabled.setSelected(true);
		enabled.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				currentState.setEnabled(enabled.isSelected());
			}
		});
		
		JButton btnDeleteThisCurrent = new JButton("Delete");
		btnDeleteThisCurrent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDeleteThisCurrent.setBounds(21, 360, 63, 23);
		
		JLabel lblReversalPotentialmv = new JLabel("Reversal potential (mV)");
		lblReversalPotentialmv.setBounds(21, 69, 147, 16);
		
		txtReversal = new JTextField();

		txtReversal.setBounds(230, 69, 86, 20);
		txtReversal.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		txtReversal.setColumns(10);
		
		JLabel lblConductancems = new JLabel("Conductance (nS)");
		lblConductancems.setBounds(21, 106, 121, 16);
		
		txtConductance = new JTextField();

		txtConductance.setBounds(230, 105, 86, 20);
		txtConductance.setColumns(10);
		
		includeInPlots = new JCheckBox("Include in simulation plots");
		includeInPlots.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentState.setDoPlots(includeInPlots.isSelected());
			}
		});
		includeInPlots.setBounds(142, 19, 172, 23);
		includeInPlots.setSelected(true);
		
		JButton btnNewGate = new JButton("New gate");
		btnNewGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addNewTab();
			}
		});
		btnNewGate.setBounds(21, 326, 78, 23);
		
		JButton btnRenameCurrent = new JButton("Rename");
		btnRenameCurrent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oldName = myName();
				String newName = PopupHelper.newName((JFrame) CurrentTabPanel.getTopLevelAncestor(), "Enter new current name:", oldName);
				setName(newName);
			}
		});
		btnRenameCurrent.setBounds(94, 360, 71, 23);
		
		notes = new JEditorPane();
		notes.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				currentState.setNotes(notes.getText());
			}
		});
		notes.setBounds(76, 151, 238, 153);
		notes.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JLabel lblNotes = new JLabel("Notes");
		lblNotes.setBounds(21, 154, 44, 15);
		
		setLayout(null);
		add(lblConductancems);
		add(btnDeleteThisCurrent);
		add(btnRenameCurrent);
		add(lblReversalPotentialmv);
		add(txtReversal);
		add(txtConductance);
		add(btnNewGate);
		add(enabled);
		add(includeInPlots);
		add(lblNotes);
		add(notes);
		add(gatePane);
	}

}

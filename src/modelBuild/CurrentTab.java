package modelBuild;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class CurrentTab extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3360394991820161758L;
	
	private JTabbedPane gatePane;
	private JTabbedPane CurrentTabPanel;
	private JTextField txtReversal;
	private JTextField txtConductance;
	private Expression reversal;
	private Expression conductance;
	private HHCurrentState currentState;
	private JCheckBox chckbxIncludeCurrent;
	private JCheckBox chckbxCurrentEnabled;
	private JEditorPane txtrCurrentNotes;

	
	static private int cnt;

	// Used during create from UI
	public CurrentTab(JTabbedPane CurrentTabPanel) {
		super();
		this.CurrentTabPanel = CurrentTabPanel;
		
		initialize();
		
		reversal = new Expression(txtReversal);
		conductance = new Expression(txtConductance);
		currentState = new HHCurrentState(this, myName(), conductance, reversal);
		AppState.addCurrent(currentState);
		
		CurrentTabPanel.setSelectedComponent(this);

		
		String reversalTXT = null;
		String gmaxTXT = null;
		switch(cnt) {
		case 0:
			setName("Leak");
			reversalTXT = "-90";
			gmaxTXT = "2";
			break;
			
		case 1:
			setName("Na");
			reversalTXT = "47.5834";
			gmaxTXT = "4.1694e+002";
			addNewTab();
			addNewTab();
			break;
			
		case 2:
			setName("K");
			reversalTXT = "-93.1320";
			gmaxTXT = "600400e-4";
			addNewTab();
			AppState.addCurrent(new StepCurrentState(this, "stim current", 10, 90, 30));
			break;
		}

		txtReversal.setText(reversalTXT);
		txtConductance.setText(gmaxTXT);
		
		reversal.setString(reversalTXT);
		conductance.setString(gmaxTXT);
		cnt++;

	}
	
	// Used during restore
	public CurrentTab(JTabbedPane CurrentTabPanel, HHCurrentState currentState) {
		
		super();
		this.CurrentTabPanel = CurrentTabPanel;
		
		initialize();
		
		this.currentState = currentState;
		currentState.setUI(this);
		reversal = currentState.getReversal();
		conductance = currentState.getConductance();
		
		conductance.setUI(txtConductance);
		reversal.setUI(txtReversal);
		
		CurrentTabPanel.setSelectedComponent(this);		
	}
	
	public GateTab addNewTab() {
		return new GateTab(gatePane, this, currentState);
	}
	
	public GateTab restoreTab(GateState g) {
		return new GateTab(gatePane, this, g);
	}
	
	private void delete() {
		AppState.removeCurrent(currentState);
		CurrentTabPanel.remove(this);
	}
	
	private String myName() {
		int i = CurrentTabPanel.indexOfComponent(this);
		return CurrentTabPanel.getTitleAt(i);
	}
	
	public void setName(String newname) {
		super.setName(newname); // A kludge to allow gate plots access to this tab name
		int i = CurrentTabPanel.indexOfComponent(this);
		CurrentTabPanel.setTitleAt(i, newname);
		currentState.setName(newname);
	}
	
	public HHCurrentState getHHCurrentState() { return currentState; }
	
	public void setEnabled(boolean b) {
		chckbxCurrentEnabled.setSelected(b);
	}
	
	public void setDoPlots(boolean b) {
		chckbxIncludeCurrent.setSelected(b);
	}
	
	public void setNotes(String g) {
		txtrCurrentNotes.setText(g);
	}
	
	private void initialize() {
		CurrentTabPanel.addTab("HH current", null, this, null);
		
		gatePane = new JTabbedPane(JTabbedPane.TOP);
		gatePane.setBounds(350, 20, 351, 364);
		gatePane.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Gates", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		chckbxCurrentEnabled = new JCheckBox("Enabled");
		chckbxCurrentEnabled.setBounds(21, 19, 78, 23);
		chckbxCurrentEnabled.setSelected(true);
		chckbxCurrentEnabled.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				currentState.setEnabled(chckbxCurrentEnabled.isSelected());
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
		txtReversal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				conductance.textFieldHandler(arg0);
			}
		});
		txtReversal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conductance.textFieldHandler(arg0);
			}
		});
		txtReversal.setBounds(230, 69, 86, 20);
		txtReversal.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		txtReversal.setColumns(10);
		
		JLabel lblConductancems = new JLabel("Conductance (nS)");
		lblConductancems.setBounds(21, 106, 121, 16);
		
		txtConductance = new JTextField();
		txtConductance.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				reversal.textFieldHandler(e);
			}
		});
		txtConductance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reversal.textFieldHandler(e);
			}
		});
		txtConductance.setBounds(230, 105, 86, 20);
		txtConductance.setColumns(10);
		
		chckbxIncludeCurrent = new JCheckBox("Include in simulation plots");
		chckbxIncludeCurrent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentState.setDoPlots(chckbxIncludeCurrent.isSelected());
			}
		});
		chckbxIncludeCurrent.setBounds(142, 19, 172, 23);
		chckbxIncludeCurrent.setSelected(true);
		
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
		
		txtrCurrentNotes = new JEditorPane();
		txtrCurrentNotes.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				currentState.setNotes(txtrCurrentNotes.getText());
			}
		});
		txtrCurrentNotes.setBounds(76, 151, 238, 153);
		txtrCurrentNotes.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
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
		add(chckbxCurrentEnabled);
		add(chckbxIncludeCurrent);
		add(lblNotes);
		add(txtrCurrentNotes);
		add(gatePane);
	}

}

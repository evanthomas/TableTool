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
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import modelState.AppState;
import modelState.CurrentState;
import modelState.StimCurrentState;
import expressionHandling.ExpressionListener;
import expressionHandling.NumericExpression;

public class StimCurrentTab extends CurrentTab {

	private static final long serialVersionUID = 1L;
	
	private JTextArea stimTextField;
	private NumericExpression stimulus;


	/**
	 * @wbp.parser.constructor
	 */
	public StimCurrentTab(JTabbedPane CurrentTabPanel) {
		super();
		this.CurrentTabPanel = CurrentTabPanel;

		initialize();
		
		stimulus = new NumericExpression(stimTextField);
		stimTextField.getDocument().addDocumentListener(new ExpressionListener(stimulus));
		
		currentState = new StimCurrentState(this, myName(), stimulus);
		AppState.addCurrent(currentState);
		
		CurrentTabPanel.setSelectedComponent(this);
	}
	
	public StimCurrentTab(JTabbedPane currentTabPanel, StimCurrentState currentState) {
		super();
		this.CurrentTabPanel = currentTabPanel;
		
		initialize();
		
		this.currentState = currentState;
		currentState.setUI(this);
		stimulus = currentState.getStimulus();
		stimulus.setUI(stimTextField);
		stimTextField.getDocument().addDocumentListener(new ExpressionListener(stimulus));
		
		CurrentTabPanel.setSelectedComponent(this);		
	}

	public CurrentState getCurrentState() { return currentState; }

	private void initialize() {
		CurrentTabPanel.addTab("Stimulus", null, this, null);
		
		setLayout(null);
		
		enabled = new JCheckBox("Enabled");
		enabled.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentState.setEnabled(enabled.isSelected());
			}
		});
		enabled.setSelected(true);
		enabled.setBounds(20, 7, 78, 23);
		add(enabled);
		
		includeInPlots = new JCheckBox("Include in simulation plots");
		includeInPlots.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentState.setDoPlots(includeInPlots.isSelected());
			}
		});
		includeInPlots.setSelected(true);
		includeInPlots.setBounds(100, 7, 220, 23);
		add(includeInPlots);
		
		notes = new JEditorPane();
		notes.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				currentState.setNotes(notes.getText());
			}
		});
		notes.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		notes.setBounds(75, 139, 238, 153);
		add(notes);
		
		JLabel label = new JLabel("Notes");
		label.setBounds(20, 142, 44, 15);
		add(label);
		
		JButton button = new JButton("Delete");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		button.setBounds(20, 348, 63, 23);
		add(button);
		
		JButton button_1 = new JButton("Rename");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oldName = myName();
				String newName = PopupHelper.newName((JFrame) CurrentTabPanel.getTopLevelAncestor(), "Enter new current name:", oldName);
				setName(newName);
			}
		});
		button_1.setBounds(93, 348, 71, 23);
		add(button_1);
		
		stimTextField = new JTextArea();
		stimTextField.setText((String) null);
		stimTextField.setColumns(10);
		stimTextField.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		stimTextField.setBounds(436, 52, 230, 67);
		add(stimTextField);
		
		JLabel lblStartms = new JLabel("I(t) (pA)/V(t) (mV)");
		lblStartms.setBounds(334, 52, 100, 16);
		add(lblStartms);
	}
}

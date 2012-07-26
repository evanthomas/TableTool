package modelView;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import modelState.AppState;
import modelState.CurrentState;
import modelState.StepCurrentState;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StepCurrentTab extends CurrentTab {

	private static final long serialVersionUID = 1L;
	
	private Expression start;
	private Expression end;
	private Expression step;
	private JTextField startTextField;
	private JTextField endTextField;
	private JTextField durationTextField;


	public StepCurrentTab(JTabbedPane CurrentTabPanel) {
		super();
		this.CurrentTabPanel = CurrentTabPanel;

		initialize();
		
		start    = new Expression(startTextField);
		end      = new Expression(endTextField);
		step     = new Expression(durationTextField);
		
		currentState = new StepCurrentState(this, myName(), start, end, step);
		AppState.addCurrent(currentState);
		
		CurrentTabPanel.setSelectedComponent(this);
	}
	
	public StepCurrentTab(JTabbedPane currentTabPanel, StepCurrentState currentState) {
		super();
		this.CurrentTabPanel = currentTabPanel;
		
		initialize();
		
		this.currentState = currentState;
		currentState.setUI(this);
		start = currentState.getStart();
		end   = currentState.getEnd();
		step  = currentState.getStep();
		
		start.setUI(startTextField);
		end.setUI(endTextField);
		step.setUI(durationTextField);
		
		CurrentTabPanel.setSelectedComponent(this);		
	}

	public CurrentState getCurrentState() { return currentState; }

	private void initialize() {
		CurrentTabPanel.addTab("Step current", null, this, null);
		
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
		includeInPlots.setBounds(141, 7, 172, 23);
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
		
		startTextField = new JTextField();
		startTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				start.textFieldHandler(arg0);
			}
		});
		startTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				start.textFieldHandler(e);
			}
		});
		startTextField.setText((String) null);
		startTextField.setColumns(10);
		startTextField.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		startTextField.setBounds(580, 52, 86, 20);
		add(startTextField);
		
		JLabel lblStartms = new JLabel("Start (ms)");
		lblStartms.setBounds(371, 52, 147, 16);
		add(lblStartms);
		
		JLabel lblEndms = new JLabel("End (ms)");
		lblEndms.setBounds(371, 82, 121, 16);
		add(lblEndms);
		
		endTextField = new JTextField();
		endTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				end.textFieldHandler(e);
			}
		});
		endTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				end.textFieldHandler(e);
			}
		});
		endTextField.setText((String) null);
		endTextField.setColumns(10);
		endTextField.setBounds(580, 81, 86, 20);
		add(endTextField);
		
		JLabel lblDurationms = new JLabel("Step (pA)");
		lblDurationms.setBounds(371, 113, 121, 16);
		add(lblDurationms);
		
		durationTextField = new JTextField();
		durationTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				step.textFieldHandler(e);
			}
		});
		durationTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				step.textFieldHandler(e);
			}
		});
		durationTextField.setText((String) null);
		durationTextField.setColumns(10);
		durationTextField.setBounds(580, 112, 86, 20);
		add(durationTextField);
	}
}

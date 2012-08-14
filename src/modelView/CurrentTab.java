package modelView;

import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import modelState.AppState;
import modelState.CurrentState;

public class CurrentTab extends JPanel {
	public CurrentTab() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected JTabbedPane CurrentTabPanel;
	protected JCheckBox includeInPlots;
	protected JCheckBox enabled;
	protected JEditorPane notes;
	protected CurrentState currentState;

	protected String myName() {
		int i = CurrentTabPanel.indexOfComponent(this);
		return CurrentTabPanel.getTitleAt(i);
	}

	public void setName(String newname) {
		super.setName(newname); // A kludge to allow gate plots access to this tab name
		int i = CurrentTabPanel.indexOfComponent(this);
		CurrentTabPanel.setTitleAt(i, newname);
		currentState.setName(newname);
	}

	public void setEnabled(boolean b) {
		enabled.setSelected(b);
	}

	public void setDoPlots(boolean b) {
		includeInPlots.setSelected(b);
	}

	public void setNotes(String g) {
		notes.setText(g);
	}

	protected void delete() {
		AppState.removeCurrent(currentState);
		CurrentTabPanel.remove(this);
	}
}

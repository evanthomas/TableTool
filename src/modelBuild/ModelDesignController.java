package modelBuild;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ModelDesignController {

	/**
	 * Launch the window
	 */
	public static void main(String[] args) {
		// Set nice L&F
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			PopupHelper.fatalException("Failed setting look and feel", e);
		} catch (InstantiationException e) {
			PopupHelper.fatalException("Failed setting look and feel", e);
		} catch (UnsupportedLookAndFeelException e) {
			PopupHelper.fatalException("Failed setting look and feel", e);
		} catch (IllegalAccessException e) {
			PopupHelper.fatalException("Failed setting look and feel", e);
		}

		// Start main app window
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModelDesignerView window = new ModelDesignerView();
					window.setVisible(true);
					AppState.setUI(window);
				} catch (Exception e) {
					PopupHelper.fatalException("Failed opening window", e);
				}
			}
		});
	}

}

package modelView;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import modelState.AppState;

public class Main {

	/**
	 * The main main, man!
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
				try {
					AppState.restore(new File("simple muscle model.wmw"));
				} catch (IOException e) {
					PopupHelper.errorMessage(null, "model file not found.");
				}
			}
		});
	}
}

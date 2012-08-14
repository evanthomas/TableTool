package modelView;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.Enumeration;

import javax.swing.UIDefaults;
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

		fixFonts();  // Force font selection as a kludge to make it look good on smac.
		
		// Start main app window
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModelDesignerView window = new ModelDesignerView();
					window.setVisible(true);
					window.pack();
					AppState.setUI(window);
				} catch (Exception e) {
					PopupHelper.fatalException("Failed opening window", e);
				}
//				try {
//					AppState.restore(new File("sodium channel voltage clamp.wmw"));
//				} catch (IOException e) {
//					PopupHelper.errorMessage(null, "model file not found (probably).");
//				}
			}
		});
	}

	private static void fixFonts() {
		UIDefaults defaults = UIManager.getDefaults();
		Enumeration<Object> newKeys = defaults.keys();

		while (newKeys.hasMoreElements()) {
			Object obj = newKeys.nextElement();
			Object value = UIManager.get(obj);
			if (value instanceof Font) {
				UIManager.put(obj, new Font( "Tahoma", Font.PLAIN, 11));
			}
		}
	}
}
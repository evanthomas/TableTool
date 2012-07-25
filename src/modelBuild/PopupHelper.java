package modelBuild;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PopupHelper {

	public static String newName(JFrame f, String instructions, String oldName) {
		String s = (String)JOptionPane.showInputDialog(
				f,
				instructions,
				"Rename",
				JOptionPane.QUESTION_MESSAGE,
				null,
				null,
				oldName);
		
		return s==null ? oldName : s;		
	}

	public static void errorMessage(JFrame f, String message) {
		JOptionPane.showMessageDialog(f, message, "ModelBuilder error", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void fatalException(String message, Exception e) {
		if( e!= null ) {
			message = message+"\n"+e.getMessage();
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(new JFrame(), message, "ModelBuilder fatal error", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
}

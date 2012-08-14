package modelView;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

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
	
	public static void about(JFrame f) {
		ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(ModelDesignerView.class.getResource("/images/aboutIcon.jpg")), null);
		JOptionPane.showMessageDialog(
				f,
				"TableTool version 0.01\n"+
						"© Evan Thomas 2012\n"+
						"evan@evan-thomas.net",
						"About TableTool",
						JOptionPane.INFORMATION_MESSAGE,
						icon);
						
	}
	
	public static void showHelp() {
		JFrame f = new JFrame();
		f.setIconImage(Toolkit.getDefaultToolkit().getImage(ModelDesignerView.class.getResource("/images/whatmeworry.jpg")));
		f.setTitle("TableTool");
		f.setBounds(100, 100, 766, 868);
		
	    //Create an editor pane.
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        
        java.net.URL helpURL = JFrame.class.getResource("/images/help.html");
        if (helpURL != null) {
            try {
                editorPane.setPage(helpURL);
            } catch (IOException e) {
                System.err.println("Attempted to read a bad URL: " + helpURL);
            }
        } else {
            System.err.println("Couldn't find file: TextSampleDemoHelp.html");
        }
        
        // Scroll pane
        JScrollPane editorScrollPane = new JScrollPane(editorPane);
        editorScrollPane.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editorScrollPane.setPreferredSize(new Dimension(766, 868));
        editorScrollPane.setMinimumSize(new Dimension(10, 10));
        
        //
        f.add(editorScrollPane);
        f.pack();
        f.setVisible(true);
	}
}

package plotting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelState.AppState;
import modelView.PopupHelper;
import modelView.TableToolFileFilter;

import org.jfree.chart.ChartPanel;

public abstract class PlotFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected ChartPanel plotPanel;
	private JFileChooser dataFileChooser;


	/**
	 * Create the frame.
	 */
	public PlotFrame(String name) {
		super(name);
		
		dataFileChooser = new JFileChooser();
		dataFileChooser.addChoosableFileFilter(new TableToolFileFilter("txt", "*.txt data files	"));
		dataFileChooser.setCurrentDirectory(new File("."));
		
		setBounds(100, 100, 640, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnExport = new JButton("export...");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = dataFileChooser.showSaveDialog(PlotFrame.this);

				if (returnVal != JFileChooser.APPROVE_OPTION) return;
				File f = dataFileChooser.getSelectedFile();
				try {
					exportFile(f);
				} catch (IOException ex) {
					PopupHelper.errorMessage(PlotFrame.this, "Cannot export data - "+ex.getMessage());
				}
			}
		});
		btnExport.setBounds(20, 422, 89, 23);
		contentPane.add(btnExport);
		
		JButton btnImport = new JButton("compare...");
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = dataFileChooser.showOpenDialog(PlotFrame.this);
				
				if (returnVal != JFileChooser.APPROVE_OPTION) return;
				File f = dataFileChooser.getSelectedFile();
				try {
					importFile(f);
				} catch (IOException ex) {
					PopupHelper.errorMessage(PlotFrame.this, "Cannot export data - "+ex.getMessage());
				} catch( NoSuchElementException ex) {
					PopupHelper.errorMessage(PlotFrame.this, "Cannot import data - file format error");
				}
				
			}
		});
		btnImport.setBounds(119, 422, 89, 23);
		contentPane.add(btnImport);
		
		JButton btnCloseAll = new JButton("close all");
		btnCloseAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AppState.closeAllGraphs();
			}
		});
		btnCloseAll.setBounds(218, 422, 89, 23);
		contentPane.add(btnCloseAll);
	}
	
	protected abstract void exportFile(File f) throws IOException;
	protected abstract void importFile(File f) throws IOException;
}

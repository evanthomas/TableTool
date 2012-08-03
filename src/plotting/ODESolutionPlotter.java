package plotting;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.border.EtchedBorder;

import modelState.AppState;
import modelView.ModelDesignerView;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.util.DefaultShadowGenerator;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

public class ODESolutionPlotter extends PlotFrame {

	private static final long serialVersionUID = 1L;
	
	private double[] t;
	private double[][] ODEoutput;
	private String [] legend;
	private DefaultXYDataset plotDataset;

	public ODESolutionPlotter(String plotTitle, double[] t, double[][] ODEoutput, String [] legend) {
		super("Solution plot");
		
		this.t = t;
		this.ODEoutput = ODEoutput;
		this.legend = legend;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModelDesignerView.class.getResource("/images/whatmeworry.jpg")));
		
		plotDataset = new DefaultXYDataset();

		for(int i=0; i<legend.length; i++) {
			double x[][] = new double[2][t.length];
			for(int j=0; j<t.length; j++) {
				x[1][j] = t[j];
				x[0][j] = ODEoutput[i][j];
			}
			plotDataset.addSeries(legend[i], x);
		}
		
        // based on the dataset we create the chart
        JFreeChart chart = createXYPlot(plotDataset, plotTitle);
        
        // we put the chart into a panel
        plotPanel = new ChartPanel(chart);
        plotPanel.setMouseWheelEnabled(true);
		plotPanel.setBounds(10, 10, 600, 400);
		contentPane.add(plotPanel);
		plotPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
        // Show panel
        setVisible(true);
        
        AppState.registerGraph(this);
	}
	
	private JFreeChart createXYPlot(XYDataset data, String plotTitle) {
		JFreeChart chart = ChartFactory.createXYLineChart(plotTitle, 
				"", "time (ms)", // axis labels 
				data, 
				PlotOrientation.HORIZONTAL, 
				true,  // legend
				false, // tooltips
				false  // URLS
				);		
		
		XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        
		ChartUtilities.applyCurrentTheme(chart);
		
		plot.setShadowGenerator(new DefaultShadowGenerator(5, Color.black, 0.8f, 5,	-Math.PI / 4));
		
		return chart;
	}

	@Override
	protected void exportFile(File file) throws IOException {
		PrintStream f = new PrintStream(file);
		
		for(int i=0; i<t.length; i++) {
			f.print(t[i]);
			for(int j=0; j<ODEoutput.length; j++)
				f.print("  "+ODEoutput[j][i]);
			f.println();
		}
		
		f.close();
		
		if( f.checkError() ) throw new IOException();
	}

	@Override
	protected void importFile(File f) throws IOException {
		Scanner s = new Scanner(new FileInputStream(f));
		
		int nPoints = t.length;
		int nLines  = ODEoutput.length;
		double [][][] compareData = new double[nLines][2][nPoints];
		
		// No error checking just throw and exception
		for(int i=0; i<nPoints; i++) {
			double t = s.nextDouble();
			for(int j=0; j<nLines; j++) {
				compareData[j][1][i] = t;
				compareData[j][0][i] = s.nextDouble();
			}
		}
		
		for(int i=0; i<nLines; i++)
			plotDataset.addSeries(legend[i]+" - comparison", compareData[i]);
	}
}
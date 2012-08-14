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
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.util.DefaultShadowGenerator;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import physicalObjects.HHGate;
import expressionParsing.ParseException;

public class GatePlotter extends PlotFrame {

	private static final long serialVersionUID = 1L;
	
	double [] infTable;
	double [] tauTable;
	double [] v;
	DefaultXYDataset infData;
	DefaultXYDataset tauData;
	
	public GatePlotter(String plotTitle, HHGate gate) throws ParseException {
		super("Gate plot");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModelDesignerView.class.getResource("/images/whatmeworry.jpg")));

		infTable = gate.getInfTable();
		tauTable = gate.getTauTable();
		v        = gate.getVoltage();
		
		infData = createData(v, infTable, "inf", false);
		tauData = createData(v, tauTable, "tau", true);
		
        // based on the dataset we create the chart
        JFreeChart chart = createXYPlot(infData, tauData, plotTitle);
        
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
	
	private DefaultXYDataset createData(double v[], double y[], String lstr, boolean invert) {
		DefaultXYDataset data = new DefaultXYDataset();
		double [][]dataTable = new double[2][v.length];
		for(int i=0; i<v.length; i++) {
			if( invert )
				dataTable[0][i] = 1/y[i];
			else
				dataTable[0][i] = y[i];
			dataTable[1][i] = v[i];
		}
		data.addSeries(lstr, dataTable);
		return data;
	}
	
	private JFreeChart createXYPlot(XYDataset infData, XYDataset tauData, String plotTitle) {
		JFreeChart chart = ChartFactory.createXYLineChart(plotTitle, 
				"activation", "voltage (mV)", // axis labels 
				infData, 
				PlotOrientation.HORIZONTAL, 
				true,  // legend
				false, // tooltips
				false  // URLS
				);		
		
		XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        
        NumberAxis axis2 = new NumberAxis("tau (ms)");
		plot.setDomainAxis(1, axis2);
		plot.setDomainAxisLocation(1, AxisLocation.TOP_OR_RIGHT);
		plot.setDataset(1, tauData);
		plot.mapDatasetToDomainAxis(1, 1);

        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
        renderer2.setBaseShapesVisible(false);
        plot.setRenderer(1, renderer2);
      		
		ChartUtilities.applyCurrentTheme(chart);
		
		plot.setShadowGenerator(new DefaultShadowGenerator(5, Color.black, 0.8f, 5,	-Math.PI / 4));
		
		return chart;
	}

	@Override
	protected void exportFile(File file) throws IOException {
		PrintStream f = new PrintStream(file);
		
		for(int i=0; i<v.length; i++) 
			f.println(v[i]+"  "+infTable[i]+"  "+tauTable[i]);
		
		f.close();
		
		if( f.checkError() ) throw new IOException();
	}

	@Override
	protected void importFile(File f) throws IOException {
		Scanner s = new Scanner(new FileInputStream(f));
		
		int n = v.length;
		double [][] compare_infTable = new double[2][n];
		double [][] compare_tauTable = new double[2][n];
		
		// No error checking just throw and exception
		for(int i=0; i<n; i++) {
			double v = s.nextDouble();
			compare_infTable[1][i] = v;
			compare_tauTable[1][i] = v;
			compare_infTable[0][i] = s.nextDouble();
			compare_tauTable[0][i] = s.nextDouble();
		}
		
		tauData.addSeries("tau - comparison", compare_tauTable);
		infData.addSeries("inf - comparison", compare_infTable);
	}
}
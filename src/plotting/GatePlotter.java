package plotting;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;

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

import expressionEvaluator.ParseException;

import physicalObjects.HHGate;

public class GatePlotter extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GatePlotter(String plotTitle, HHGate gate) throws ParseException {
		super("Gate plot");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModelDesignerView.class.getResource("/images/whatmeworry.jpg")));

		double [] infTable = gate.getInfTable();
		double [] tauTable = gate.getTauTable();
		double [] v        = gate.getVoltage();
		
		XYDataset infData = createData(v, infTable, "inf");
		XYDataset tauData = createData(v, tauTable, "tau");
		
        // based on the dataset we create the chart
        JFreeChart chart = createXYPlot(infData, tauData, plotTitle);
        
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
        
        // add it to our application
        setContentPane(chartPanel);

        // Show panel
        pack();
        setVisible(true);
	}
	
	private XYDataset createData(double v[], double y[], String lstr) {
		DefaultXYDataset data = new DefaultXYDataset();
		double [][]dataTable = new double[2][v.length];
		for(int i=0; i<v.length; i++) {
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
}
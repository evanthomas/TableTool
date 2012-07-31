package plotting;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

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

public class ODESolutionPlotter extends JFrame {

	private static final long serialVersionUID = 1L;

	public ODESolutionPlotter(String plotTitle, double[] t, double[][] ODEoutput, String [] legend) {
		super("Solution plot");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModelDesignerView.class.getResource("/images/whatmeworry.jpg")));
		
		double x[][] = new double[2][t.length];
		DefaultXYDataset data = new DefaultXYDataset();

		for(int i=0; i<legend.length; i++) {
			for(int j=0; j<t.length; j++) {
				x[1][j] = t[j];
				x[0][j] = ODEoutput[i][j];
			}
			data.addSeries(legend[i], x);
		}
		
        // based on the dataset we create the chart
        JFreeChart chart = createXYPlot(data, plotTitle);
        
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
	
	private JFreeChart createXYPlot(XYDataset data, String plotTitle) {
		JFreeChart chart = ChartFactory.createXYLineChart(plotTitle, 
				"output", "time (ms)", // axis labels 
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
}
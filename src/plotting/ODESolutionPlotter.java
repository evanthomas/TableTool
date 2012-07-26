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

	private static final long serialVersionUID = -5442298555017545443L;

	public ODESolutionPlotter(String plotTitle, ArrayList<Double> t, double[] voltage) {
		super("Solution plot");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModelDesignerView.class.getResource("/images/whatmeworry.jpg")));
		
		XYDataset data = createData(t, voltage, "voltage");
		
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

	private XYDataset createData(ArrayList<Double> t, double[] y, String lstr) {
		DefaultXYDataset data = new DefaultXYDataset();
		double[][] dataTable = new double[2][y.length];
		for(int i=0; i<y.length; i++) {
			dataTable[0][i] = y[i];
			dataTable[1][i] = t.get(i);
		}
		data.addSeries(lstr, dataTable);
		return data;
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
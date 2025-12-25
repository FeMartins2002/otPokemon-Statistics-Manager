package br.com.cards;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import br.com.controller.DataController;
import br.com.observer.TableObserver;

public class CardGraphic extends JPanel implements TableObserver {
	private String tableName;
	private String column;
	private DefaultCategoryDataset dataset;

	public CardGraphic(String tableName, String colunm) {
		setLayout(new GridLayout(1, 1, 0, 0));

		this.column = colunm;
		this.tableName = tableName;
		this.dataset = new DefaultCategoryDataset();
		loadDataset();

		JFreeChart chart = createChart(dataset);
		configurationPlot(chart);

		ChartPanel chartPanel = new ChartPanel(chart);
		configurationChartPanel(chartPanel);
		add(chartPanel);
	}

	private void loadDataset() {
		dataset.clear();
		CategoryDataset newData = DataController.loadDataFromGraphic(tableName, column);

		for (int row = 0; row < newData.getRowCount(); row++) {
			for (int col = 0; col < newData.getColumnCount(); col++) {
				dataset.addValue(
						newData.getValue(row, col),
						newData.getRowKey(row),
						newData.getColumnKey(col));
			}
		}
	}

	private JFreeChart createChart(CategoryDataset dataset) {
		JFreeChart barChart = ChartFactory.createBarChart("", "", "", dataset, PlotOrientation.VERTICAL, false,	true, false);
		return barChart;
	}

	private void configurationPlot(JFreeChart chart) {
		CategoryPlot plot = chart.getCategoryPlot();
		CategoryAxis domainAxis = plot.getDomainAxis();

		plot.getRangeAxis().setTickMarksVisible(false);
		plot.getRangeAxis().setTickLabelsVisible(false);
		plot.setBackgroundPaint(new Color(255, 255, 255));

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
		plot.setRangeGridlineStroke(new BasicStroke(2f));

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, new Color(59, 111, 146));
		renderer.setShadowVisible(false);

		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelFont(new Font("SansSerif", Font.PLAIN, 16));
		renderer.setBaseItemLabelPaint(Color.BLACK);

		plot.getRangeAxis().setUpperMargin(0.15);

		domainAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 16));
		domainAxis.setTickLabelPaint(Color.BLACK);
	}

	private void configurationChartPanel(ChartPanel chartPanel) {
		chartPanel.setPreferredSize(new Dimension(400, 250));
		chartPanel.setMaximumSize(new Dimension(400, 250));
		chartPanel.setMinimumSize(new Dimension(400, 250));
		chartPanel.setBackground(Color.WHITE);
	}

	@Override
	public void onTableEvent(String tableName, String operation, String item) {
		if (!this.tableName.equals(tableName)) {
			return;
		}
		SwingUtilities.invokeLater(this::loadDataset);
	}
}

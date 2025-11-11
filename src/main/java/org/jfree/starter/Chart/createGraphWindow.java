package org.jfree.starter.Chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class createGraphWindow {



    createGraphWindow (dataPoints list) {



         class App extends JFrame {
            /**
             * Creates a new instance of the app.
             *
             * @param title the frame title.
             */
            public App(String title, dataPoints data) {
                setTitle(title);
                CategoryDataset dataset = createDataset(data);
                JFreeChart chart = createChart(dataset);
                ChartPanel panel = new ChartPanel(chart);
                panel.setPreferredSize(new Dimension(1024, 360));
                setLayout(new BorderLayout());
                getContentPane().add(panel, BorderLayout.CENTER);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
            }

            /**
             * Creates a sample dataset (hard-coded for the purpose of keeping the
             * demo self-contained - in practice you would normally read your data
             * from a file, database or other source).
             *
             * @return A sample dataset.
             */
            private CategoryDataset createDataset(dataPoints list) {
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                dataPoint[] arr = list.getArray();
                int listLength = list.getLength();
                String name = list.getName();

// data from Java in a Nutshell
                for (int i = 0; i < listLength; ++i) {
                    dataset.addValue((Number) arr[i].getPoint(), "name", i);
                }


                return dataset;
            }

            /**
             * Creates a sample chart.
             *
             * @param dataset a dataset.
             * @return The chart.
             */
            private JFreeChart createChart(CategoryDataset dataset) {
// create the chart...

                JFreeChart chart = ChartFactory.createLineChart(
                        "Java Standard Class Library", // chart title
                        null, // domain axis label
                        "Class Count", // range axis label
                        dataset);

                chart.removeLegend();
                CategoryPlot plot = (CategoryPlot) chart.getPlot();
                plot.setRangePannable(true);
                plot.setRangeGridlinesVisible(false);

                //valuemarker to set lines for upper and lower limit
                ValueMarker upperLimit = new ValueMarker(1.5);
                ValueMarker lowerLimit = new ValueMarker(1.8);

                upperLimit.setPaint(Color.GREEN);
                upperLimit.setStroke(new BasicStroke(2.0f)); // 2.0f for line thickness
                upperLimit.setAlpha(0.7f); // Transparency

                lowerLimit.setPaint(Color.BLUE);
                lowerLimit.setStroke(new BasicStroke(2.0f));
                lowerLimit.setAlpha(0.7f);

                plot.addRangeMarker(upperLimit);
                plot.addRangeMarker(lowerLimit);

// customise the range axis...
                NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
                rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
                ChartUtils.applyCurrentTheme(chart);

                //added for Y-axis?
                chart.addSubtitle(new TextTitle(list.getName()));

                TextTitle source = new TextTitle(
                        "SD Test 1");
                source.setFont(new Font("SansSerif", Font.PLAIN, 10));
                source.setPosition(RectangleEdge.BOTTOM);
                source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
                chart.addSubtitle(source);

// customise the renderer...
                LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
                renderer.setDefaultShapesVisible(true);
                renderer.setDrawOutlines(true);
                renderer.setUseFillPaint(true);
                renderer.setDefaultFillPaint(Color.WHITE);
                renderer.setSeriesStroke(0, new BasicStroke(3.0f));
                renderer.setSeriesOutlineStroke(0, new BasicStroke(2.0f));
                renderer.setSeriesShape(0, new Ellipse2D.Double(-5.0, -5.0, 10.0, 10.0));


                return chart;
            }
        }

        App app = new App("JFreeChart Starter App", list);

        app.pack();
        app.setVisible(true);
    }
}

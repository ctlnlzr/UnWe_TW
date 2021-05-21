package edu.tw.generate.svg;

import org.jfree.chart.ChartFactory;
import org.jfree.data.general.*;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.jfree.graphics2d.svg.SVGUtils;

import java.awt.*;
import java.io.File;


public class GenerateSvgFile {

    private static int width = 600;
    private static int height = 400;

    private static PieDataset createPieDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        return null;
    }
    public void createChart() {
        PieDataset dataset = GenerateSvgFile.createPieDataset();
        org.jfree.chart.JFreeChart chart = ChartFactory.createPieChart(
                "",
                dataset,
                true,
                true,
                false);

        SVGGraphics2D g2 = new SVGGraphics2D(width, height);
        Rectangle r = new Rectangle(0, 0, width, height);
        chart.draw(g2, r);

        try {
            SVGUtils.writeToSVG(
                    new File("C:\\Users\\strat\\Desktop\\data.svg"),
                    g2.getSVGElement());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}

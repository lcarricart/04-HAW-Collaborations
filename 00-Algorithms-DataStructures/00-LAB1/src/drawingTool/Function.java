package drawingTool;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class Function {
    
    // The raw data points for the function
    private final int[] dataXPoints = {-50, -20, 30, 80, 100};
    private final int[] dataYPoints = {-50, -10, 20, 15, 60};

    public Function() {
        
    }

    public void draw(Graphics pen, Rectangle2D.Double viewPoint, int panelWidth, int panelHeight) {
        
        int[] screenXPoints = new int[dataXPoints.length];
        int[] screenYPoints = new int[dataYPoints.length];

        // Convert each data point to a screen pixel coordinate
        for (int i = 0; i < dataXPoints.length; i++) {
            screenXPoints[i] = toScreenX(dataXPoints[i], viewPoint, panelWidth);
            screenYPoints[i] = toScreenY(dataYPoints[i], viewPoint, panelHeight);
        }
        
        pen.drawPolyline(screenXPoints, screenYPoints, dataXPoints.length);
    }

    /**
     * Converts a data X-coordinate to a screen pixel X-coordinate.
     */
    private int toScreenX(double dataX, Rectangle2D.Double viewPoint, int panelWidth) {
        double xRange = viewPoint.getWidth();
        return (int) (panelWidth * (dataX - viewPoint.getX()) / xRange);
    }

    /**
     * Converts a data Y-coordinate to a screen pixel Y-coordinate.
     * (Handles the inverted Y-axis in Swing)
     */
    private int toScreenY(double dataY, Rectangle2D.Double viewPoint, int panelHeight) {
        double yRange = viewPoint.getHeight();
        return (int) (panelHeight * (1 - ((dataY - viewPoint.getY()) / yRange)));
    }
}
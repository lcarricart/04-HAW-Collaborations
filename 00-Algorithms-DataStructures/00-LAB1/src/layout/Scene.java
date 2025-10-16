package layout;

// This is a change test for GitHub collaboration purposes

import drawingTool.Function;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import drawingTool.Drawing;

public class Scene {

    private Background background;
    private Function function;
    private int width, height; // Careful, this looks like a bad future of size control. Integrate into scale
    private int scale = 10; // The scene now manages the scale, with a default value

    private final Rectangle2D.Double initialViewPoint;
    private Rectangle2D.Double currentViewPoint;
    
    public Scene(int width, int height) {
        this.width = width;
        this.height = height;
        this.background = new Background(width, height);
        this.function = new Function();
        
        double initialRange = 300;
        // The initial view is centered on (0,0)
        this.initialViewPoint = new Rectangle2D.Double(-initialRange / 2, -initialRange / 2, initialRange, initialRange);
        this.currentViewPoint = this.initialViewPoint;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getScale() {
        return this.scale;
    }

    public void draw() {
    	background.draw(Drawing.getPen(), currentViewPoint);
    	function.draw(Drawing.getPen(), currentViewPoint, width, height);
    }
    
    public void zoomTo(Rectangle selectionArea) {
        // FIX #2: Ensure the selection has a valid size to prevent division by zero.
        if (selectionArea != null && selectionArea.width > 0 && selectionArea.height > 0) {
        	double oldX = currentViewPoint.getX();
            double oldY = currentViewPoint.getY();
            double oldWidth = currentViewPoint.getWidth();
            double oldHeight = currentViewPoint.getHeight();

            // Map pixel coordinates to data coordinates
            double newX = oldX + (selectionArea.x / (double) width) * oldWidth;
            // Y-axis is inverted in Swing (0 is at top)
            double newY = oldY + ((height - selectionArea.y - selectionArea.height) / (double) height) * oldHeight;
            double newWidth = (selectionArea.width / (double) width) * oldWidth;
            double newHeight = (selectionArea.height / (double) height) * oldHeight;

            this.currentViewPoint = new Rectangle2D.Double(newX, newY, newWidth, newHeight);
        }
    }
    
    public void sliderZoom(int sliderValue) {
        // A value of 5 is 1x zoom. A value of 50 is 10x zoom.
        double scaleFactor = sliderValue / 5.0;
        
        // Prevent scaleFactor from being zero or negative
        if (scaleFactor <= 0) { scaleFactor = 0.01; }

        double newWidth = initialViewPoint.width / scaleFactor;
        double newHeight = initialViewPoint.height / scaleFactor;
        double newX = -newWidth / 2;
        double newY = -newHeight / 2;
        
        this.currentViewPoint = new Rectangle2D.Double(newX, newY, newWidth, newHeight);
    }
    
    public void resetZoom() {
        this.currentViewPoint = this.initialViewPoint;
    }   
}
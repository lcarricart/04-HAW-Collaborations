package background;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import drawingTool.Drawing;
import drawingTool.RandomNumber;

public class GeometricShape {
	private final int SHAPE_AREA_WIDTH = 140;
	private final int SHAPE_AREA_HEIGHT = 100;
	private final int NUMBER_SHAPES = 6;
	private final int SIGNAL_WAVE_WIDTH = 120;
	private final int SIGNAL_WAVE_HEIGHT = 30;
	private final int SPACING_BUFFER = 20;
	private final Color[] SHAPE_COLORS = {
		new Color(100, 181, 246),  // Light blue
		new Color(255, 138, 101),  // Coral
		new Color(149, 117, 205),  // Purple
		new Color(129, 199, 132),  // Green
		new Color(255, 183, 77),   // Amber
		new Color(240, 98, 146)    // Pink
	};
	
	private int sceneWidth;
	private Color primaryColor;
	private Color secondaryColor;
	private Color accentColor;
	
	public GeometricShape(int sceneWidth, Color primaryColor, Color secondaryColor, Color accentColor) {
		this.sceneWidth = sceneWidth;
		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
		this.accentColor = accentColor;
	}
	
	public void draw(int betweenX, int betweenY, int showSignals) {
		int x, y;
		int i;
		List<Rectangle> placedShapes = new ArrayList<Rectangle>();
		
		for (i = 0; i < NUMBER_SHAPES; i++) {
			// Try to find a non-overlapping position
			boolean positionFound = false;
			int attempts = 0;
			int maxAttempts = 100;
			
			do {
				x = RandomNumber.between(betweenX, sceneWidth - SHAPE_AREA_WIDTH);
				y = RandomNumber.between(0, betweenY - SHAPE_AREA_HEIGHT);
				
				Rectangle currentShape = new Rectangle(x - SPACING_BUFFER, y - SPACING_BUFFER, 
											 SHAPE_AREA_WIDTH + 2 * SPACING_BUFFER, 
											 SHAPE_AREA_HEIGHT + 2 * SPACING_BUFFER);
				
				positionFound = !checkOverlap(currentShape, placedShapes);
				attempts++;
				
				if (positionFound) {
					placedShapes.add(currentShape);
				}
			} while (!positionFound && attempts < maxAttempts);
			
			if (!positionFound) {
				continue; // Skip this shape if no position found
			}
			
			// Draw minimalist geometric composition
			Color shapeColor1 = SHAPE_COLORS[i % SHAPE_COLORS.length];
			Color shapeColor2 = SHAPE_COLORS[(i + 2) % SHAPE_COLORS.length];
			Color shapeColor3 = SHAPE_COLORS[(i + 4) % SHAPE_COLORS.length];
			
			// Draw large circle
			drawCircle(x + 40, y + 30, 35, shapeColor1);
			
			// Draw square
			drawSquare(x + 85, y + 20, 28, shapeColor2);
			
			// Draw triangle
			drawTriangle(x + 25, y + 65, 25, shapeColor3);
			
			// Draw small accent circles
			drawCircle(x + 100, y + 70, 12, shapeColor1.brighter());
			drawCircle(x + 120, y + 80, 8, shapeColor2.brighter());
			
			// Draw data signal waves if enabled
			if (showSignals == 1) {
				drawSignalWave(x + SHAPE_AREA_WIDTH + 10, y + SHAPE_AREA_HEIGHT / 2);
			}
		}
	}
	
	private boolean checkOverlap(Rectangle current, List<Rectangle> placed) {
		for (Rectangle existing : placed) {
			if (current.intersects(existing)) {
				return true;
			}
		}
		return false;
	}
	
	private void drawCircle(int x, int y, int diameter, Color color) {
		// Draw filled circle
		Drawing.getPen().setColor(color);
		Drawing.getPen().fillOval(x, y, diameter, diameter);
		
		// Draw outline
		Drawing.getPen().setColor(color.darker());
		Drawing.getPen().drawOval(x, y, diameter, diameter);
	}
	
	private void drawSquare(int x, int y, int size, Color color) {
		// Draw filled square
		Drawing.getPen().setColor(color);
		Drawing.getPen().fillRect(x, y, size, size);
		
		// Draw outline
		Drawing.getPen().setColor(color.darker());
		Drawing.getPen().drawRect(x, y, size, size);
	}
	
	private void drawTriangle(int x, int y, int size, Color color) {
		// Draw filled triangle (pointing up)
		int[] xPoints = {x, x + size / 2, x + size};
		int[] yPoints = {y + size, y, y + size};
		
		Drawing.getPen().setColor(color);
		Drawing.getPen().fillPolygon(xPoints, yPoints, 3);
		
		// Draw outline
		Drawing.getPen().setColor(color.darker());
		Drawing.getPen().drawPolygon(xPoints, yPoints, 3);
	}
	
	private void drawSignalWave(int startX, int startY) {
		Drawing.getPen().setColor(accentColor);
		
		// Draw a simple sine-wave-like pattern
		int segments = 8;
		int segmentWidth = SIGNAL_WAVE_WIDTH / segments;
		
		for (int i = 0; i < segments; i++) {
			int x1 = startX + i * segmentWidth;
			int x2 = startX + (i + 1) * segmentWidth;
			int y1 = startY + (i % 2 == 0 ? -SIGNAL_WAVE_HEIGHT / 2 : SIGNAL_WAVE_HEIGHT / 2);
			int y2 = startY + (i % 2 == 0 ? SIGNAL_WAVE_HEIGHT / 2 : -SIGNAL_WAVE_HEIGHT / 2);
			
			Drawing.getPen().drawLine(x1, y1, x2, y2);
		}
	}
}

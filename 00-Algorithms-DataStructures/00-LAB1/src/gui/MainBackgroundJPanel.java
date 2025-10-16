package gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import background.Cloud;
import drawingTool.Drawing;

public class MainBackgroundJPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final Color PASTEL_BLUE = new Color(200, 220, 255);
    private final int X_MARGIN = 0;
    private final int Y_MARGIN = 1900;
    
    private int isRainy = 0;              
    private Cloud myCloud;   //aggregate

    public MainBackgroundJPanel() {
        super();
        
        initialization();
    }

    private void initialization() {
    	setBackground(PASTEL_BLUE);
    }
    
    @Override
    protected void paintComponent(Graphics pen) {
        super.paintComponent(pen);

        Drawing.setPen(pen);
        
        myCloud = new Cloud(getWidth(), Color.white);
        
        for (int i = 0; i < Y_MARGIN; i = i + 500)
        {
        	myCloud.draw(X_MARGIN, i, isRainy);
        }
    }
}
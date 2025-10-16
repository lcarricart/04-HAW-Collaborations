package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class DrawingJFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private int basicScale = 10;
    
    private DrawingJPanel drawingJPanel;
    private ConfigurationJPanel configJPanel;

    public DrawingJFrame(String title) {
        super(title);

        Dimension screen = this.getToolkit().getScreenSize();
        int frameW = screen.width;
        int frameH = screen.height;
        
        initialization(frameW, frameH);
    }
    
    private void initialization(int frameW, int frameH) {
    	setBounds(0, 0, frameW, frameH);
        getContentPane().setLayout(new BorderLayout());
        
        drawingJPanel = new DrawingJPanel(frameW, frameH, this);
        configJPanel = new ConfigurationJPanel(drawingJPanel);
        
        // Adds the drawingJPanel to the frame, where the Poodles are drawn
        getContentPane().add(drawingJPanel, BorderLayout.CENTER);
        // Adds the ConfigurationJPanel to the same frame, and therefore they're placed together and can interact in the same frame window.
        getContentPane().add(configJPanel, BorderLayout.EAST);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        configJPanel.getImportBtn().addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        	    // Add actions
        	}
        });
        
        configJPanel.getResetView().addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		drawingJPanel.getScene().resetZoom();
                configJPanel.setSliderEnabled(true);
                configJPanel.getScaleSlider().setValue(5);
                drawingJPanel.repaint();
        	}
        });
        
        configJPanel.getZoomInBtn().addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		increaseBasicScale();
        		drawingJPanel.getScene().setScale(basicScale);
        		drawingJPanel.repaint();
        	}
        });
        
        configJPanel.getZoomOutBtn().addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		decreaseBasicScale();
        		drawingJPanel.getScene().setScale(basicScale);
        		drawingJPanel.repaint();
        	}
        });
    }
    
    private void increaseBasicScale() {
        basicScale += 10;
    }

    private void decreaseBasicScale() {
        if (basicScale > 10) {
            basicScale -= 10;
        }
    }
    
    public ConfigurationJPanel getConfigurationJPanel() {
    	return configJPanel;
    }
}
package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ConfigurationJPanel extends JPanel implements ChangeListener, ActionListener {
	private static final long serialVersionUID = 1L;
    private String[] options = {"Sensor 1", "Sensor 2", "Sensor 3", "Sensor 4"};
    private String[] options2 = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
    private String[] options3 = {"Data 1", "Data 2", "Data 3"};
	
    private JButton resetView = new JButton("Reset View");
    private JButton importBtn = new JButton("Import Data Set");
    private JButton zoomInBtn = new JButton(" + ");
    private JButton zoomOutBtn = new JButton("  - ");
    private JSlider volumeSlider = new JSlider(0, 100, 10);
    private JSlider scaleSlider = new JSlider(1, 100, 5);
    private JComboBox<String> comboBox = new JComboBox<>(options);
    private JComboBox<String> comboBox2 = new JComboBox<>(options2);
    private JComboBox<String> comboBox3 = new JComboBox<>(options3);
	
	private final Font TITLE_FONT = new Font("Lucida Bright	", Font.BOLD, 18);
	private final Font BUTTON_FONT = new Font("Lucida Bright", Font.PLAIN, 14);
	
	private DrawingJPanel drawingJPanel;

	public ConfigurationJPanel(DrawingJPanel drawingJPanel) {
		super();
		this.drawingJPanel = drawingJPanel;		// Necessary for repainting inside the stateChange listener that must live in this class
		initialization();
	}
	
	// Can I use the first two for something?
	public enum EnumGlasses {
	    SENSOR1, SENSOR2, SENSOR3, SENSOR4;
	}
	public enum EnumHat {
	    ITEM1, ITEM2, ITEM3, ITEM4, ITEM5;
	}
	public enum EnumColor {
		DATA1, DATA2, DATA3;
	}

	private void initialization() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// Title of the JPanel
		add(Box.createVerticalStrut(20));
		JLabel title = new JLabel("  Sensors Toolkit  ");
		title.setFont(TITLE_FONT);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(title);
		add(Box.createVerticalStrut(15));

		// Refresh Scene button
		resetView.setFont(BUTTON_FONT);
		resetView.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(resetView);
		add(Box.createVerticalStrut(20));

		// Set Ground button
		importBtn.setFont(BUTTON_FONT);
		importBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(importBtn);
		add(Box.createVerticalStrut(20));
		
//		// Volume Slider
//		JLabel slideLabel = new JLabel("  Volume  ");
//		slideLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//		
//		volumeSlider.setPaintTrack(true);
//		volumeSlider.setPaintTicks(true);
//		volumeSlider.setPaintLabels(true);
//		volumeSlider.setMajorTickSpacing(25);
//		volumeSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
//		
//	    add(slideLabel);  
//	    add(volumeSlider); 
//	    add(Box.createVerticalStrut(20));
	    
	    // Combo Box (Glasses)
        JLabel comboLabel = new JLabel("Plot 1");
        int desiredHeight = 20; // in pixels!
        
        comboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, desiredHeight));
        comboBox.setFont(BUTTON_FONT);
        comboLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    add(comboLabel);
	    add(comboBox);
	    add(Box.createVerticalStrut(20));
	    
	    // Combo Box (Glasses)
        JLabel comboLabel2 = new JLabel("Plot 2");
        
        comboBox2.setMaximumSize(new Dimension(Integer.MAX_VALUE, desiredHeight));
        comboBox2.setFont(BUTTON_FONT);
        comboLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
	    add(comboLabel2);
	    add(comboBox2);
	    add(Box.createVerticalStrut(20));
	    
	    // Combo Box (Color)
        JLabel comboLabel3 = new JLabel("Plot 3");
        
        comboBox3.setMaximumSize(new Dimension(Integer.MAX_VALUE, desiredHeight));
        comboBox3.setFont(BUTTON_FONT);
        comboLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
	    add(comboLabel3);
	    add(comboBox3);
	    add(Box.createVerticalStrut(30));
	    
	    // Slider for zoom
	    JLabel sliderLabel = new JLabel("Zoom Slider");
	    sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scaleSlider.setPaintTrack(true);
		scaleSlider.setPaintTicks(true);
		scaleSlider.setPaintLabels(true);
		scaleSlider.setMajorTickSpacing(0);
		//unitsNumberSlider.setMinorTickSpacing(10);
		scaleSlider.addChangeListener(this);
		add(sliderLabel);
		add(scaleSlider);
		add(Box.createVerticalStrut(30));
	    
	    // Zooming buttons
		add(Box.createVerticalStrut(400));
	    JLabel zoomLabel = new JLabel("Zoom");
	    add(Box.createVerticalStrut(30));
	    add(zoomLabel);
	    zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    zoomInBtn.setFont(BUTTON_FONT);
		zoomInBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(zoomInBtn);
		add(Box.createVerticalStrut(10));
		
		zoomOutBtn.setFont(BUTTON_FONT);
		zoomOutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(zoomOutBtn);
		add(Box.createVerticalStrut(10));
	}
	
	public void setSliderEnabled(boolean isEnabled) {
        scaleSlider.setEnabled(isEnabled);
    }
	
	public JButton getImportBtn() {
		return importBtn;
	}
	
	public JButton getResetView() {
		return resetView;
	}
	
	public JSlider getVolumeSlider() {
		return volumeSlider;
	}
	
	public JSlider getScaleSlider() {
		return scaleSlider;
	}
	
	// 0.NONE	1.Sensor1	2.Sensor2 	3.Sensor3
    public int getComboBox1State() {
        return comboBox.getSelectedIndex();
     }
    
	// 0.NONE	 1.Item1	 2.Item2	3.Item3 	4.Item4
    public int getComboBox2State() {
    	return comboBox2.getSelectedIndex();
     }
    
	// 0.Data1	 1.Data2 	2.Data3
    public int getComboBox3State() {
    	return comboBox3.getSelectedIndex();
     }
	
    public JButton getZoomInBtn() {
    	return zoomInBtn;
    }
    
    public JButton getZoomOutBtn() {
    	return zoomOutBtn;
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// I'm unsure of how necessary this is. If it's not, bring the constructor back to no parameters. Also about passing the parent JFrame to DrawingJPanel
		if (e.getSource() == resetView) {
            drawingJPanel.getScene().resetZoom();
            
            setSliderEnabled(true);
            scaleSlider.setValue(5);
            
            drawingJPanel.repaint();
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// I'm unsure of how necessary this is. If it's not, bring the constructor back to no parameters
		if (scaleSlider.isEnabled()) {
            drawingJPanel.getScene().sliderZoom(scaleSlider.getValue());
            drawingJPanel.repaint();
        }
	}
}
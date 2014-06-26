package com.pseudocoloring.gui.panel;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.pseudocoloring.gui.equalizer.components.SliderFilter;

public class EqualizerPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	private ImagePanel imagePanel;
	private SliderFilter redFilter;
	private SliderFilter greenFilter;
	private SliderFilter blueFilter;
	private JLabel redLabel, blueLabel, greenLabel;
	
	public EqualizerPanel(ImagePanel imagePanel) {
		this.imagePanel = imagePanel;
		initializeUI();
		addComponents();
	}
	
	private void initializeUI(){
		this.setLayout(null);
		redLabel = new JLabel("RED");
		greenLabel = new JLabel("GREEN");
		blueLabel = new JLabel("BLUE");

		redLabel.setBounds(10, -5, 50, 30);
		greenLabel.setBounds(40, -5, 50, 30);
		blueLabel.setBounds(90, -5, 50, 30);
		this.add(redLabel);
		this.add(greenLabel);
		this.add(blueLabel);
		
		this.setVisible(true);
	}
	
	private void addComponents(){
		redFilter = new SliderFilter("RED", getImagePanel(), 0, 20);
		greenFilter = new SliderFilter("GREEN", getImagePanel(), 40, 20);
		blueFilter = new SliderFilter("BLUE", getImagePanel(), 80, 20);
		
		this.add(redFilter);
		this.add(greenFilter);
		this.add(blueFilter);
		
		this.setVisible(true);
	}

	public ImagePanel getImagePanel() {
		return imagePanel;
	}

	public void setImagePanel(ImagePanel imagePanel) {
		this.imagePanel = imagePanel;
	}
}

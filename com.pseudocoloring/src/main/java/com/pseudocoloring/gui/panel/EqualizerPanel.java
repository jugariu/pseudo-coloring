package com.pseudocoloring.gui.panel;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import com.pseudocoloring.gui.equalizer.components.SliderFilter;

public class EqualizerPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	private ImagePanel imagePanel;
	private SliderFilter redFilter;
	private SliderFilter greenFilter;
	private SliderFilter blueFilter;
	
	public EqualizerPanel(ImagePanel imagePanel) {
		this.imagePanel = imagePanel;
		initializeUI();
		addComponents();
	}
	
	private void initializeUI(){
		this.setLayout(null);
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

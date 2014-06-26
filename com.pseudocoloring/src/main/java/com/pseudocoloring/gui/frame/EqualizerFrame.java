package com.pseudocoloring.gui.frame;

import javax.swing.JFrame;

import com.pseudocoloring.gui.panel.EqualizerPanel;
import com.pseudocoloring.gui.panel.ImagePanel;

public class EqualizerFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	private ImagePanel imagePanel;
	
	public EqualizerFrame(ImagePanel imagePanel) {
		this.imagePanel = imagePanel;
		initializeUI();
		addComponents();
	}

	private void initializeUI() {
		this.setLocation(1035, 0);
		this.setSize(300, 400);
		this.setResizable(false);
		this.setTitle("Equilizer");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void addComponents(){
		this.add(new EqualizerPanel(getImagePanel()));
		this.setVisible(true);
	}

	public ImagePanel getImagePanel() {
		return imagePanel;
	}

	public void setImagePanel(ImagePanel imagePanel) {
		this.imagePanel = imagePanel;
	}
}

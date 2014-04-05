package com.pseudocoloring.gui.frame;

import java.awt.image.BufferedImage;
import javax.swing.JFrame;

import com.pseudocoloring.gui.panel.ImagePanel;

public class ImageFrame extends JFrame{

	public ImageFrame(BufferedImage bufferedImage) {
				
		this.setSize(bufferedImage.getWidth(), bufferedImage.getHeight());
		this.setResizable(false);
		this.setTitle("Image");
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		this.add(new ImagePanel(bufferedImage));
		
		this.setVisible(true);
	}
}

package com.pseudocoloring.gui.panel;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.pseudocoloring.gui.logger.ScrollableLogArea;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel label;
	private ScrollableLogArea log;
	private String imagePath;

	public ImagePanel(int xStart, int yStart, ScrollableLogArea log)
			throws IOException {
		this.log = log;
		label = new JLabel();
		log.setLoggedClass(ImagePanel.class.getName());

		initializeUI(xStart, yStart);
	}

	private void initializeUI(int xStart, int yStart) throws IOException {
		this.add(label);
		if(imagePath == null){
			ImageIcon icon = new ImageIcon(ClassLoader.class.getResource("/Images/no_image.jpg").getFile().substring(1));
			label.setIcon(icon);
		}
		this.setBounds(xStart, yStart, 500, 500);
	}

	public String getImage() {
		return imagePath;
	}

	public void setImage(BufferedImage image) {
		this.setVisible(false);
		ImageIcon icon = new ImageIcon(image);
		label.setIcon(createImageIcon(icon));
		this.setVisible(true);
	}

	public void setImage(String imagePath) {
		this.setVisible(false);
		this.imagePath = imagePath;
		ImageIcon icon = new ImageIcon(imagePath);
		label.setIcon(createImageIcon(icon));
		this.setVisible(true);
	}
	
	public void reloadImagePanel(){
		ImageIcon icon = new ImageIcon(ClassLoader.class.getResource("/Images/no_image.jpg").getFile().substring(1));
		label.setIcon(icon);
	}
	
	private ImageIcon createImageIcon(ImageIcon icon){
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance( 500, 500,  java.awt.Image.SCALE_SMOOTH ) ;  
		icon = new ImageIcon( newimg );
		
		return icon;
	}
}
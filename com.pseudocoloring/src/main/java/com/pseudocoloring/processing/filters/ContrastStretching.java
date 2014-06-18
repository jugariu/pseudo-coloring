package com.pseudocoloring.processing.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ContrastStretching {

	private String imagePath;
	private BufferedImage image;
	private int lMax;
	private int lMin;
	private static final int MP=255;
	
	public ContrastStretching(String imagePath) {
		this.imagePath = imagePath;
		File file = new File(imagePath);
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedImage getContrastStretchingImage(){
		setLimits();
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int clr = image.getRGB(x, y);
				int pixelValue = clr & 0x000000ff;
				
				float contrastStretchingValue = ((float)(pixelValue - getlMin()) / (getlMax() - getlMin()))*MP;
				Color color = new Color(Math.round(contrastStretchingValue), Math.round(contrastStretchingValue), Math.round(contrastStretchingValue));
				int rgb = color.getRGB();

				image.setRGB(x, y, rgb);
			}
		}
		
		return image;
	}
	
	private void setLimits(){
		setlMax(0);
		setlMin(255);
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int clr = image.getRGB(x, y);
				int pixelValue = clr & 0x000000ff;

				if(pixelValue>lMax)
					setlMax(pixelValue);
				if(pixelValue<lMin)
					setlMin(pixelValue);
				
			}
		}
	}

	public int getlMax() {
		return lMax;
	}

	public void setlMax(int lMax) {
		this.lMax = lMax;
	}

	public int getlMin() {
		return lMin;
	}

	public void setlMin(int lMin) {
		this.lMin = lMin;
	}
}

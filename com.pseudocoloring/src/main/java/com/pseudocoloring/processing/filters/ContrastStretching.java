package com.pseudocoloring.processing.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.pseudocoloring.gui.logger.ScrollableLogArea;

public class ContrastStretching {

	private String imagePath;
	private BufferedImage image;
	private ScrollableLogArea log;
	private int lMax;
	private int lMin;
	private static final int MP=255;
	
	public ContrastStretching(String imagePath, ScrollableLogArea log) {
		this.imagePath = imagePath;
		this.log = log;
		log.setLoggedClass(ContrastStretching.class.getName());
		File file = null;
		try {
			file = new File(imagePath);
			image = ImageIO.read(file);
			log.info("Image " + file.getPath() + " is prepared for Contrast Stretching processing.");
		} catch (NullPointerException npe) {
			log.error("No image to process. ", npe.getMessage());
			throw npe;
		} catch (IOException ioe) {
			log.error("Could not read image: " + file.getPath(), ioe.getMessage());
		}
	}
	
	public BufferedImage getContrastStretchingImage(){
		log.info("Contrast Stretching processing has started.");
		setLimits();
		log.info("The limits for Contrast Stretching: Max=" + getlMax() + " Min=" + getlMin() + ".");
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

		log.info("Contrast Stretching processing is finished.");
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

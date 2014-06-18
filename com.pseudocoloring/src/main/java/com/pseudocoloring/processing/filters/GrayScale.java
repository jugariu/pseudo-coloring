package com.pseudocoloring.processing.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.pseudocoloring.gui.logger.ScrollableLogArea;


public class GrayScale {

	private String imagePath;
	private BufferedImage image;
	private ScrollableLogArea log;

	public GrayScale(String imagePath, ScrollableLogArea log) {
		this.imagePath = imagePath;
		this.log = log;
		log.setLoggedClass(GrayScale.class.getName());
		File file = null;
		try {
			file = new File(imagePath);
			image = ImageIO.read(file);
			log.info("Image " + file.getPath() + " is prepared for Grayscale processing.");
		} catch (NullPointerException npe) {
			log.error("No image to process. ", npe.getMessage());
			throw npe;
		} catch (IOException ioe) {
			log.error("Could not read image: " + file.getPath(), ioe.getMessage());
		}
	}

	public BufferedImage getGrayScaleImage() {
		log.info("Grayscale processing has started.");
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int clr = image.getRGB(x, y);
				int red = (clr & 0x00ff0000) >> 16;
				int green = (clr & 0x0000ff00) >> 8;
				int blue = clr & 0x000000ff;

				int grayScaleValue = (red + green + blue) / 3;

				Color color = new Color(grayScaleValue, grayScaleValue, grayScaleValue);
				int rgb = color.getRGB();

				image.setRGB(x, y, rgb);
			}
		}

		log.info("Grayscale processing is finished.");
		return image;
	}
}

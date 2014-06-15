package com.pseudocoloring.processing.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class GrayScale {

	private String imagePath;
	private BufferedImage image;

	public GrayScale(String imagePath) {
		this.imagePath = imagePath;
		File file = new File(imagePath);
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BufferedImage getGrayScaleImage() {
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
		
		return image;
	}
}

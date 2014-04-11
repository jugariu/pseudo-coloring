package com.pseudocoloring.lut;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class SevenRampsLUT {

	private BufferedImage image;

	public SevenRampsLUT(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage getSevenRampsLUTImage() {
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int clr = image.getRGB(x, y);
				int grayScaleColor = (clr & 0x00ff0000) >> 16;

				if (grayScaleColor <= 72) {
					Color color = new Color(255, (grayScaleColor * 3), 0);
					int rgb = color.getRGB();
					image.setRGB(x, y, rgb);
				}
				if ((grayScaleColor > 72) && (grayScaleColor <= 108)) {
					int val = grayScaleColor - 72;
					Color color = new Color(255 - (val * 7), 255, 0);
					int rgb = color.getRGB();
					image.setRGB(x, y, rgb);
				}
				if ((grayScaleColor > 108) && (grayScaleColor <= 144)) {
					int val = grayScaleColor - 108;
					Color color = new Color(0, 255, val * 7);
					int rgb = color.getRGB();
					image.setRGB(x, y, rgb);
				}
				if ((grayScaleColor > 144) && (grayScaleColor <= 180)) {
					int val = grayScaleColor - 144;
					Color color = new Color(0, 255 - (val * 7), 255);
					int rgb = color.getRGB();
					image.setRGB(x, y, rgb);
				}
				if ((grayScaleColor > 180) && (grayScaleColor <= 216)) {
					int val = grayScaleColor - 180;
					Color color = new Color(val * 7, 0, 255);
					int rgb = color.getRGB();
					image.setRGB(x, y, rgb);
				}
				if ((grayScaleColor > 216) && (grayScaleColor <= 252)) {
					int val = grayScaleColor - 216;
					Color color = new Color(255, 0, 255 - (val * 7));
					int rgb = color.getRGB();
					image.setRGB(x, y, rgb);
				}
			}
		}

		return image;
	}
}

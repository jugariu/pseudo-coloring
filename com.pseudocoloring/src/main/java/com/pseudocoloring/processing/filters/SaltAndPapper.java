package com.pseudocoloring.processing.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class SaltAndPapper {

	private BufferedImage image;

	public SaltAndPapper(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage removeSaltAndPaperNoise() {
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {

				int left = 0, right = 0, up = 0, down = 0;

				if (x > 0){
					int clr = image.getRGB(x - 1, y);
					left = (clr & 0x00ff0000) >> 16;
				}
				if (x < image.getWidth() - 1){
					int clr = image.getRGB(x + 1, y);
					right = (clr & 0x00ff0000) >> 16;
				}
				if (y > 0){
					int clr = image.getRGB(x, y - 1);
					up = (clr & 0x00ff0000) >> 16;
				}
				if (y < image.getHeight() - 1){
					int clr = image.getRGB(x, y + 1);
					down = (clr & 0x00ff0000) >> 16;
				}

				int value = (left + right + up + down) / 4;
				Color color = new Color(value, value, value);
				int rgb = color.getRGB();
				
				image.setRGB(x, y, rgb);
			}
		}

		return image;
	}
}

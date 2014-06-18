package com.pseudocoloring.processing.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.pseudocoloring.gui.logger.ScrollableLogArea;

public class SaltAndPepper {

	private String imagePath;
	private BufferedImage image;
	private ScrollableLogArea log;

	public SaltAndPepper(String imagePath, ScrollableLogArea log) {
		this.imagePath = imagePath;
		this.log = log;
		log.setLoggedClass(SaltAndPepper.class.getName());
		File file = null;
		try {
			file = new File(imagePath);
			image = ImageIO.read(file);
			log.info("Image " + file.getPath() + " is prepared for Salt and Pepper processing.");
		} catch (NullPointerException npe) {
			log.error("No image to process. ", npe.getMessage());
			throw npe;
		} catch (IOException ioe) {
			log.error("Could not read image: " + file.getPath(), ioe.getMessage());
		}
	}

	public BufferedImage removeSaltAndPepperNoise() {
		log.info("Salt and Pepper processing has started.");
		for (int y = 1; y < image.getHeight() - 1; y++) {
			for (int x = 1; x < image.getWidth() - 1; x++) {
				int center = image.getRGB(x, y);
				int centerColor = (center & 0x00ff0000) >> 16;

				if (centerColor > 200 || centerColor < 50) {
					int left = image.getRGB(x - 1, y);
					int right = image.getRGB(x + 1, y);
					int down = image.getRGB(x, y + 1);
					int up = image.getRGB(x, y - 1);

					int leftColor = (left & 0x00ff0000) >> 16;
					int rightColor = (right & 0x00ff0000) >> 16;
					int downColor = (down & 0x00ff0000) >> 16;
					int upColor = (up & 0x00ff0000) >> 16;

					int value = (leftColor + rightColor + upColor + downColor) / 4;
					Color color = new Color(value, value, value);
					int rgb = color.getRGB();

					image.setRGB(x, y, rgb);
				} else {
					image.setRGB(x, y, center);
				}

			}
		}

		log.info("Salt and Pepper processing is finished.");
		return image;
	}
}

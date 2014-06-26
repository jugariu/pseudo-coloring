package com.pseudocoloring.processing.equalizer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import com.pseudocoloring.gui.panel.ImagePanel;

public class EqualizeProcess {

	private ImagePanel imagePanel;
	private Map<String, Integer> redMap, greenMap, blueMap;

	public EqualizeProcess(ImagePanel imagePanel) {
		this.imagePanel = imagePanel;
		createMaps();
	}

	private void createMaps() {
		redMap = new HashMap<String, Integer>();
		blueMap = new HashMap<String, Integer>();
		greenMap = new HashMap<String, Integer>();

		BufferedImage image = imagePanel.getBufferedImage();
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int clr = image.getRGB(x, y);
				int red = (clr & 0x00ff0000) >> 16;
				int green = (clr & 0x0000ff00) >> 8;
				int blue = clr & 0x000000ff;

				redMap.put(y + "#" + x, red);
				greenMap.put(y + "#" + x, green);
				blueMap.put(y + "#" + x, blue);
			}
		}
	}

	public void equalizeImageRedValue(int minValue, int maxValue) {
		BufferedImage image = imagePanel.getBufferedImage();
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int clr = image.getRGB(x, y);
				int green = (clr & 0x0000ff00) >> 8;
				int blue = clr & 0x000000ff;

				int equalizedValue = 0;
				if (redMap.get(y+"#"+x) >= minValue && redMap.get(y+"#"+x) <= maxValue) {
					equalizedValue = redMap.get(y+"#"+x);
				} 

				Color color = new Color(equalizedValue, green, blue);
				int rgb = color.getRGB();

				image.setRGB(x, y, rgb);
			}
		}
		imagePanel.setImage(image);
	}

	public void equalizeImageGreenValue(int minValue, int maxValue) {
		BufferedImage image = imagePanel.getBufferedImage();
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int clr = image.getRGB(x, y);
				int red = (clr & 0x00ff0000) >> 16;
				int blue = clr & 0x000000ff;

				int equalizedValue = 0;
				if (greenMap.get(y+"#"+x) >= minValue && greenMap.get(y+"#"+x) <= maxValue) {
					equalizedValue = greenMap.get(y+"#"+x);
				}

				Color color = new Color(red, equalizedValue, blue);
				int rgb = color.getRGB();

				image.setRGB(x, y, rgb);
			}
		}
		imagePanel.setImage(image);
	}

	public void equalizeImageBlueValue(int minValue, int maxValue) {
		BufferedImage image = imagePanel.getBufferedImage();
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int clr = image.getRGB(x, y);
				int red = (clr & 0x00ff0000) >> 16;
				int green = (clr & 0x0000ff00) >> 8;

				int equalizedValue = 0;
				if (blueMap.get(y+"#"+x) >= minValue && blueMap.get(y+"#"+x) <= maxValue) {
					equalizedValue = blueMap.get(y+"#"+x);
				}

				Color color = new Color(red, green, equalizedValue);
				int rgb = color.getRGB();

				image.setRGB(x, y, rgb);
			}
		}
		imagePanel.setImage(image);
	}
}

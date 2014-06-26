package com.pseudocoloring.fusion;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.pseudocoloring.gui.logger.ScrollableLogArea;

public class SimpleAverageImageFusion {

	private String firstImagePath;
	private String secondImagePath;
	private BufferedImage firstImage;
	private BufferedImage secondImage;
	private ScrollableLogArea log;

	public SimpleAverageImageFusion(String firstImagePath,
			BufferedImage secondImage, ScrollableLogArea log) {
		this.firstImagePath = firstImagePath;
		this.secondImage = secondImage;
		this.log = log;
		log.setLoggedClass(SimpleAverageImageFusion.class.getName());

		File file1 = null;
		try {
			file1 = new File(firstImagePath);
			firstImage = ImageIO.read(file1);
			log.info("Input images are prepared for Simple Average Image Fusion.");
		} catch (NullPointerException npe) {
			log.error("No image to process. ", npe.getMessage());
			throw npe;
		} catch (IOException ioe) {
			log.error("Could not read input images.", ioe.getMessage());
		}
	}

	public BufferedImage getSimpleAverageImageFusion() {
		log.info("Simple Average Image Fusion has started.");
		for (int y = 0; y < firstImage.getHeight(); y++) {
			for (int x = 0; x < firstImage.getWidth(); x++) {
				int clr1 = firstImage.getRGB(x, y);
				int red1 = (clr1 & 0x00ff0000) >> 16;
				int green1 = (clr1 & 0x0000ff00) >> 8;
				int blue1 = clr1 & 0x000000ff;

				int clr2 = secondImage.getRGB(x, y);
				int red2 = (clr2 & 0x00ff0000) >> 16;
				int green2 = (clr2 & 0x0000ff00) >> 8;
				int blue2 = clr2 & 0x000000ff;

				int redFusionedValue = (red1 + red2) / 2;
				int greenFusionedValue = (green1 + green2) / 2;
				int blueFusionedValue = (blue1 + blue2) / 2;
				
				Color color = new Color(redFusionedValue, greenFusionedValue, blueFusionedValue);
				int rgb = color.getRGB();

				secondImage.setRGB(x, y, rgb);
			}
		}
		
		log.info("Simple Average Image Fusion is finished.");
		return secondImage;
	}
}

package com.pseudocoloring.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.pseudocoloring.gui.frame.ImageFrame;
import com.pseudocoloring.gui.frame.MainFrame;
import com.pseudocoloring.processing.filters.GrayScale;
import com.pseudocoloring.processing.filters.SaltAndPepper;

public class Menu extends JMenuBar{

	private final static String C_PATH = "C:\\";
	private final static String FILE_CHOOSER_TITLE ="Select file";

	private final static String FILE_MENU ="File";
	private final static String OPEN ="Open";
	private final static String EXIT ="Exit";
	
	private final static String PROCESSING_MENU ="Processing";
	private final static String GRAYSCALE ="Grayscale";
	private final static String SALT_AND_PEPPER ="Salt&Pepper";
	
	private BufferedImage bufferedImage;

	public Menu() {
		JMenu fileMenu = new JMenu(FILE_MENU);
		fileMenu.add(createOpenMenuItem());
		fileMenu.addSeparator();
		fileMenu.add(createExitMenuItem());
		this.add(fileMenu);	
		
		JMenu processingMenu = new JMenu(PROCESSING_MENU);
		processingMenu.add(createGrayScaleMenu());
		processingMenu.add(createSaltAndPepperMenu());
		this.add(processingMenu);
	}
	
	private JMenuItem createExitMenuItem(){
		JMenuItem menuItem = new JMenuItem(EXIT);
		menuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		return menuItem;
	}
	
	private JMenuItem createOpenMenuItem(){
		JMenuItem menuItem = new JMenuItem(OPEN);
		menuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				openFileChooser();
			}
		});
		
		return menuItem;
	}
	
	private JMenuItem createGrayScaleMenu(){
		JMenuItem menuItem = new JMenuItem(GRAYSCALE);
		menuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				GrayScale grayscale = new GrayScale(getBufferedImage());
				BufferedImage grayscaledImage = grayscale.getGrayScaleImage();
				new ImageFrame(grayscaledImage);
			}
		});
		
		return menuItem;		
	}
	
	private JMenuItem createSaltAndPepperMenu(){
		JMenuItem menuItem = new JMenuItem(SALT_AND_PEPPER);
		menuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				SaltAndPepper saltAndPapper = new SaltAndPepper(getBufferedImage());
				BufferedImage grayscaledImage = saltAndPapper.removeSaltAndPepperNoise();
				new ImageFrame(grayscaledImage);
			}
		});
		
		return menuItem;		
	}
	
	private void openFileChooser(){
		JFileChooser fileChooser = new JFileChooser();
		FileFilter jpegFilter = new FileNameExtensionFilter("JPEG File","jpg");
		
		fileChooser.setDialogTitle(FILE_CHOOSER_TITLE);
		fileChooser.setCurrentDirectory(new File(C_PATH));
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.addChoosableFileFilter(jpegFilter);
		
		if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
			File file = new File(fileChooser.getSelectedFile().getPath());
			BufferedImage bufferedImage = null;
			try {
				bufferedImage = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			setBufferedImage(bufferedImage);
			new ImageFrame(getBufferedImage());
		}
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
}

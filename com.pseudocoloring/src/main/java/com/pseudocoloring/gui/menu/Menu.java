package com.pseudocoloring.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.pseudocoloring.fusion.SelectMaximumImageFusion;
import com.pseudocoloring.fusion.SimpleAverageImageFusion;
import com.pseudocoloring.gui.frame.EqualizerFrame;
import com.pseudocoloring.gui.logger.ScrollableLogArea;
import com.pseudocoloring.gui.panel.ImagePanel;
import com.pseudocoloring.lut.SevenRampsLUT;
import com.pseudocoloring.processing.filters.ContrastStretching;
import com.pseudocoloring.processing.filters.GrayScale;
import com.pseudocoloring.processing.filters.SaltAndPepper;

public class Menu extends JMenuBar{
	private static final long serialVersionUID = 1L;
	
	private final static String C_PATH = "C:\\";
	private final static String FILE_CHOOSER_TITLE ="Select file";

	private final static String FILE_MENU ="File";
	private final static String OPEN ="Open";
	private final static String EXIT ="Exit";
	private final static String EQUALIZER ="Equalizer";
	
	private final static String FILTERS_MENU ="Filters";
	private final static String GRAYSCALE ="Grayscale";
	private final static String SALT_AND_PEPPER ="Salt&Pepper";
	private final static String LUT_RAMPS ="7 LUT Ramps";
	private final static String CONTRAST_STRETCHING ="Contrast Stretching";

	private final static String IMAGE_FUSION ="Image Fusion";
	private final static String SIMPLE_AVERAGE ="Simple Average";
	private final static String SELECT_MAXIMUM ="Select Maximum";
	
	private ImagePanel initialImagePanel;
	private ImagePanel processedImagePanel;
	private ScrollableLogArea log;
	
	public Menu(ImagePanel initialImagePanel, ImagePanel processedImagePanel, ScrollableLogArea log) {
		super();
		this.initialImagePanel = initialImagePanel;
		this.processedImagePanel = processedImagePanel;
		this.log = log;
		
		initializeUI();
	}
	
	private void initializeUI(){
		JMenu fileMenu = new JMenu(FILE_MENU);
		fileMenu.add(createOpenMenuItem());
		fileMenu.addSeparator();
		fileMenu.add(createEqualizerMenuItem());
		fileMenu.addSeparator();
		fileMenu.add(createExitMenuItem());
		this.add(fileMenu);	
		
		JMenu processingMenu = new JMenu(FILTERS_MENU);
		processingMenu.add(createGrayScaleMenu());
		processingMenu.add(createSaltAndPepperMenu());
		processingMenu.add(createContrastStretchingMenu());
		processingMenu.add(createSevenRampsLUTMenu());
		this.add(processingMenu);
		
		JMenu imageFusionMenu = new JMenu(IMAGE_FUSION);
		imageFusionMenu.add(createSimpleAverageImageFusionMenu());
		imageFusionMenu.add(createSelectMaximumImageFusionMenu());
		this.add(imageFusionMenu);
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
	
	private JMenuItem createEqualizerMenuItem(){
		JMenuItem menuItem = new JMenuItem(EQUALIZER);
		menuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new EqualizerFrame(processedImagePanel);
			}
		});
		
		return menuItem;
	}
	
	private JMenuItem createOpenMenuItem(){
		JMenuItem menuItem = new JMenuItem(OPEN);
		menuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					openFileChooser();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		return menuItem;
	}
	
	private JMenuItem createGrayScaleMenu(){
		JMenuItem menuItem = new JMenuItem(GRAYSCALE);
		menuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				GrayScale grayscale = new GrayScale(getInitialImagePanel().getImage(), log);
				BufferedImage grayscaledImage = grayscale.getGrayScaleImage();
				getProcessedImagePanel().setImage(grayscaledImage);
			}
		});
		
		return menuItem;		
	}

	private JMenuItem createSaltAndPepperMenu(){
		JMenuItem menuItem = new JMenuItem(SALT_AND_PEPPER);
		menuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				SaltAndPepper saltAndPapper = new SaltAndPepper(getInitialImagePanel().getImage(), log);
				BufferedImage saltAndPapperImage = saltAndPapper.removeSaltAndPepperNoise();
				getProcessedImagePanel().setImage(saltAndPapperImage);
			}
		});
		
		return menuItem;		
	}

	private JMenuItem createSevenRampsLUTMenu(){
		JMenuItem menuItem = new JMenuItem(LUT_RAMPS);
		menuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				SevenRampsLUT rampsLUT = new SevenRampsLUT(getInitialImagePanel().getImage(), log);
				BufferedImage rampsLUTImage = rampsLUT.getSevenRampsLUTImage();
				getProcessedImagePanel().setImage(rampsLUTImage);
			}
		});
		
		return menuItem;		
	}

	private JMenuItem createContrastStretchingMenu(){
		JMenuItem menuItem = new JMenuItem(CONTRAST_STRETCHING);
		menuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ContrastStretching contrastStretching = new ContrastStretching(getInitialImagePanel().getImage(), log);
				BufferedImage contrastStretchingImage = contrastStretching.getContrastStretchingImage();
				getProcessedImagePanel().setImage(contrastStretchingImage);
			}
		});
		
		return menuItem;		
	}

	private JMenuItem createSimpleAverageImageFusionMenu(){
		JMenuItem menuItem = new JMenuItem(SIMPLE_AVERAGE);
		menuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				SimpleAverageImageFusion imageFusion = new SimpleAverageImageFusion(getInitialImagePanel().getImage(), getProcessedImagePanel().getBufferedImage(), log);
				BufferedImage imageFusionImg = imageFusion.getSimpleAverageImageFusion();
				getProcessedImagePanel().setImage(imageFusionImg);
			}
		});
		
		return menuItem;
	}

	private JMenuItem createSelectMaximumImageFusionMenu(){
		JMenuItem menuItem = new JMenuItem(SELECT_MAXIMUM);
		menuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				SelectMaximumImageFusion imageFusion = new SelectMaximumImageFusion(getInitialImagePanel().getImage(), getProcessedImagePanel().getBufferedImage(), log);
				BufferedImage imageFusionImg = imageFusion.getSelectMaximumImageFusion();
				getProcessedImagePanel().setImage(imageFusionImg);
			}
		});
		
		return menuItem;
	}
	
	private void openFileChooser() throws IOException{
		JFileChooser fileChooser = new JFileChooser();
		FileFilter jpegFilter = new FileNameExtensionFilter("JPEG File","jpg");
		
		fileChooser.setDialogTitle(FILE_CHOOSER_TITLE);
		fileChooser.setCurrentDirectory(new File(C_PATH));
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.addChoosableFileFilter(jpegFilter);
		
		if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
			File file = new File(fileChooser.getSelectedFile().getPath());
			getInitialImagePanel().setImage(file.getPath());
			getProcessedImagePanel().reloadImagePanel();
			log.info("Image " + file.getPath() + " was loaded.");
		}
	}
	
	public ImagePanel getProcessedImagePanel() {
		return processedImagePanel;
	}

	public void setProcessedImagePanel(ImagePanel processedImagePanel) {
		this.processedImagePanel = processedImagePanel;
	}

	public ImagePanel getInitialImagePanel() {
		return initialImagePanel;
	}

	public void setInitialImagePanel(ImagePanel initialImagePanel) {
		this.initialImagePanel = initialImagePanel;
	}
}

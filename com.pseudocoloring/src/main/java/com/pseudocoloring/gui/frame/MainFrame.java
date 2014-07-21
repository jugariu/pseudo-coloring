package com.pseudocoloring.gui.frame;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;

import com.pseudocoloring.gui.logger.ScrollableLogArea;
import com.pseudocoloring.gui.menu.Menu;
import com.pseudocoloring.gui.panel.ImagePanel;
import com.pseudocoloring.gui.panel.IntermediatePanel;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public MainFrame() throws IOException {
		initializeUI();
		addComponents();
	}

	private void initializeUI() {
		this.setSize(1265, 770);
		this.setResizable(false);
		this.setTitle("Pseudo-coloring");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setVisible(true);
	}

	private void addComponents() {
		ScrollableLogArea log = new ScrollableLogArea();
		this.add(log);
		
		try {
			ImagePanel initialImagePanel = new ImagePanel(0, 0, log);
			this.add(initialImagePanel);
			ImagePanel processedImagePanel = new ImagePanel(757, 0, log);
			this.add(processedImagePanel);
			
			IntermediatePanel intermediatePanel = new IntermediatePanel(log, processedImagePanel, initialImagePanel);
			this.add(intermediatePanel);
			
			Menu menu = new Menu(intermediatePanel, initialImagePanel, processedImagePanel, log);
			this.setJMenuBar(menu);

			this.setVisible(true);
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			File temp = new File("C:/Pseudo-coloring/"+timeStamp);
			if(!temp.exists())
				temp.mkdirs();
			File initImages = new File("C:/Pseudo-coloring/Initial_Images");
			if(!initImages.exists())
				initImages.mkdirs();
			intermediatePanel.setTempFolderPath(temp.getPath());
			
			log.info("Application succesfully started.");
		} catch (IOException ioe) {
			throw new RuntimeException();
		} 
	}
}

package com.pseudocoloring.gui.frame;

import java.io.IOException;

import javax.swing.JFrame;

import com.pseudocoloring.gui.logger.ScrollableLogArea;
import com.pseudocoloring.gui.menu.Menu;
import com.pseudocoloring.gui.panel.ImagePanel;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public MainFrame() throws IOException {
		initializeUI();
		addComponents();
	}

	private void initializeUI() {
		this.setSize(1029, 770);
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
			ImagePanel processedImagePanel = new ImagePanel(517, 0, log);
			this.add(processedImagePanel);
			new EqualizerFrame(processedImagePanel);
			
			Menu menu = new Menu(initialImagePanel, processedImagePanel, log);
			this.setJMenuBar(menu);

			this.setVisible(true);
			log.info("Application succesfully started.");
		} catch (IOException ioe) {
			throw new RuntimeException();
		} 
	}
}

package com.pseudocoloring.gui.panel;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.pseudocoloring.gui.components.IntermediateObject;
import com.pseudocoloring.gui.logger.ScrollableLogArea;

public class IntermediatePanel extends JScrollPane {
	private static final long serialVersionUID = 1L;

	private ScrollableLogArea log;
	private static JPanel panel = new JPanel();
	private ImagePanel rightImagePanel;
	private ImagePanel leftImagePanel;
	private String tempFolderPath;
	
	public IntermediatePanel(ScrollableLogArea log, ImagePanel rightImagePanel, ImagePanel leftImagePanel) {
		super(panel);
		panel.setPreferredSize(new Dimension(232,10000));
		this.log = log;
		this.rightImagePanel = rightImagePanel;
		this.leftImagePanel = leftImagePanel;
		initializeUI();
	}
	
	private void initializeUI(){
		this.setBounds(505, 0, 250, 500);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);		
		this.setVisible(true);
	}
	
	public void addIntermediate(ImageIcon icon, String processText){
		panel.add(new IntermediateObject(icon, rightImagePanel, leftImagePanel, getTempFolderPath(), processText));
		this.setVisible(true);
	}

	public String getTempFolderPath() {
		return tempFolderPath;
	}

	public void setTempFolderPath(String tempFolderPath) {
		this.tempFolderPath = tempFolderPath;
	}
}

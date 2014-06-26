package com.pseudocoloring.gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.pseudocoloring.gui.panel.ImagePanel;

public class IntermediateObject extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JButton applyLeft;
	private JButton applyRight;
	private JLabel intermediateImage;
	private JLabel processName;
	private ImageIcon icon;
	
	private String tempFolderPath;
	private String intermediateImagePath;
	private String processText;
	
	private ImagePanel rightImagePanel;
	private ImagePanel leftImagePanel;
	
	public IntermediateObject(ImageIcon icon, ImagePanel rightImagePanel, ImagePanel leftImagePanel, String tempFolderPath, String processText) {
		super();
		this.icon = icon;
		this.rightImagePanel = rightImagePanel;
		this.leftImagePanel = leftImagePanel;
		this.tempFolderPath = tempFolderPath;
		this.processText = processText;
		initializeUI();
	}
	
	private void initializeUI(){
		this.setLayout(null);
		this.setPreferredSize(new Dimension(235, 150));
		
		applyLeft = new JButton();
		applyLeft.setBounds(0, 50, 50, 50);
		applyLeft.setBorderPainted(false);
		applyLeft.setOpaque(false);
		applyLeft.setContentAreaFilled(false);
		applyLeft.setIcon(resizeIcon(new ImageIcon(ClassLoader.class.getResource("/Images/Arrow-Left-icon.png").getFile().substring(1)), 50, 50));
		applyToLeftAction();
		
		intermediateImage = new JLabel();
		intermediateImage.setBounds(51, 0, 133, 100);
		setIntermediateImage(icon);

		applyRight = new JButton();
		applyRight.setBounds(184, 50, 50, 50);
		applyRight.setBorderPainted(false);
		applyRight.setOpaque(false);
		applyRight.setContentAreaFilled(false);
		applyRight.setIcon(resizeIcon(new ImageIcon(ClassLoader.class.getResource("/Images/Arrow-Right-icon.png").getFile().substring(1)), 50, 50));
		applyToRightAction();
		
		processName = new JLabel();
		processName.setBounds(0, 101, 232, 30);
		processName.setOpaque(true);
		processName.setText(processText);
		processName.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.add(applyLeft);
		this.add(intermediateImage);
		this.add(applyRight);
		this.add(processName);
		this.setVisible(true);
	}
	
	private void applyToRightAction(){
		applyRight.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					rightImagePanel.setImage(getIntermediateImagePath());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	private void applyToLeftAction(){
		applyLeft.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					leftImagePanel.setImage(getIntermediateImagePath());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void setIntermediateImage(ImageIcon icon){
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		File file = new File(tempFolderPath+"/"+timeStamp+".jpg");
		setIntermediateImagePath(tempFolderPath+"/"+timeStamp+".jpg");
		Image image = icon.getImage();
		BufferedImage buffered = toBufferedImage(image);
		try {
			ImageIO.write(buffered, "jpg", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance( 150, 150,  java.awt.Image.SCALE_SMOOTH ) ;  
		icon = new ImageIcon( newimg );
		intermediateImage.setIcon(icon);
	}
	
	private ImageIcon resizeIcon(ImageIcon icon, int x, int y){		
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance( x, y,  java.awt.Image.SCALE_SMOOTH ) ;  
		icon = new ImageIcon( newimg );
		
		return icon;
	}
	
	private static BufferedImage toBufferedImage(Image img){  
		int width = img.getWidth(null);  
		int height = img.getHeight(null);  
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
		Graphics g = bi.getGraphics();  
		g.drawImage(img, 0,0, null);  
		g.dispose();  
		
		return bi;  
	}   
	
	public String getIntermediateImagePath() {
		return intermediateImagePath;
	}

	public void setIntermediateImagePath(String intermediateImagePath) {
		this.intermediateImagePath = intermediateImagePath;
	}
}

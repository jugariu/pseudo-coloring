package com.pseudocoloring.gui.frame;

import javax.swing.JFrame;

import com.pseudocoloring.gui.menu.Menu;

public class MainFrame extends JFrame{
	
	public MainFrame() {
		this.setSize(400, 50);
		this.setResizable(false);
		this.setTitle("Pseudo-coloring");
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		Menu menu = new Menu();		
		this.setJMenuBar(menu);
		
		this.setVisible(true);
	}
}

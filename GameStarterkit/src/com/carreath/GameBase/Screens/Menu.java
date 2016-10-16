package com.carreath.GameBase.Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {
	public void render(Graphics g){
		g.setColor(Color.white);
		Font font = new Font("arial",1,50);
		
		g.setFont(font);
		g.drawString("Menu", 140, 50);
		g.drawString("Play", 140, 150);
		g.drawString("Help", 140, 250);
		g.drawString("Quit", 140, 350);
		
		g.drawRect(	100, 100, 200, 64);
		g.drawRect(	100, 200, 200, 64);
		g.drawRect(	100, 300, 200, 64);
	}
}

package com.carreath.GameBase;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.carreath.GameBase.Game.STATE;

public class MouseInput extends MouseAdapter{
	//private Game game;
	//public MouseInput (Game game){
	//	this.game = game;
	//}
	public void mousePressed(MouseEvent e) {
		int mX = e.getX();
		int mY = e.getY();
		
		if(Game.gameState == STATE.Game){
			//BLAH BLAH BLAH
			if(mouseOver(mX,mY,100,100,100,100)){
				//More BLAH BLAH BLAH
				Game.gameState = STATE.Game;
			}
		}
	}
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mX, int mY,int x, int y, int width, int height) {
		boolean isOver = false;
		if(mX > x && mX < x + width){
			if(mY > y && mY < y + height){
				isOver = true;
			}
		}
		return isOver;
	}
}

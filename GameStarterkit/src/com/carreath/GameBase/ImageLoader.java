package com.carreath.GameBase;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	private static BufferedImage splash;
	
	private static BufferedImage[] Level1_Images = new BufferedImage[4];
	private static BufferedImage[] Level2_Images = new BufferedImage[8];
	
	public ImageLoader() {
		
	}
	public static void load() {
		try {
		    splash = ImageIO.read(new File("res/Images/FlyeyBox.png"));
		    Level1_Images[0] = ImageIO.read(new File("res/Images/Ground.png"));
		    Level1_Images[1] = ImageIO.read(new File("res/Images/TopperPillar.png"));
		    Level1_Images[2] = ImageIO.read(new File("res/Images/InvertedTopperPillar.png"));
		    Level1_Images[3] = ImageIO.read(new File("res/Images/FillerPillar.png"));

		    Level2_Images[0] = ImageIO.read(new File("res/Images/Christmas/Christmas_Ground.png"));
		    Level2_Images[1] = ImageIO.read(new File("res/Images/Christmas/Christmas_Topper.png"));
		    Level2_Images[2] = ImageIO.read(new File("res/Images/Christmas/Christmas_InvertedTopper.png"));
		    Level2_Images[3] = ImageIO.read(new File("res/Images/Christmas/Christmas_Pillar.png"));
		    Level2_Images[4] = ImageIO.read(new File("res/Images/Christmas/Christmas_Hat_1.png"));
		    Level2_Images[5] = ImageIO.read(new File("res/Images/Christmas/Christmas_Hat_2.png"));
		    Level2_Images[6] = ImageIO.read(new File("res/Images/Christmas/Christmas_Hat_3.png"));
		    Level2_Images[7] = ImageIO.read(new File("res/Images/Christmas/Christmas_BackGround.png"));
		} catch (IOException e) {
		}
	}
	public static BufferedImage getSplashImage() {
		return splash;
	}
	public static BufferedImage[] getLevel1Images() {
		return Level1_Images;
	}
	public static BufferedImage[] getLevel2Images() {
		return Level2_Images;
	}
	public static void dispose(){
		splash = null;
		Level1_Images = null;
		Level2_Images = null;
	}
}

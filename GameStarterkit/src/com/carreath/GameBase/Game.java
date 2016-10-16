package com.carreath.GameBase;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.carreath.GameBase.Screens.Menu;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 2618405428120188283L;

	public static final int WIDTH = 640, HEIGHT = WIDTH /12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private Menu menu;
	private MouseInput mouseInput;
	
	public enum STATE {
		Splash,
		Menu,
		Game;
	};	
	public static STATE gameState = STATE.Splash;
	public static STATE lastState = STATE.Splash;
	
	public Game() {
		//create instances of classes
		handler = new Handler();
		menu = new Menu();
		mouseInput = new MouseInput();
		
		//Create listeners
		this.addKeyListener(new KeyInput());
		this.addMouseListener(mouseInput);
		
		//Open Splash screen
		new Window(WIDTH,HEIGHT,"FlyeyBox",this);

		//Load all images and sounds
		ImageLoader.load();
		Sound.setupSounds();
		AudioPlayer.shuffleMusic("Music");
		AudioPlayer.shuffleMusic("Christmas");

		//Switch over to the game when loading is complete
		gameState = STATE.Menu;		
	}
	//Start of the game thread
	public synchronized void start(){
		running = true;
		thread = new Thread(this);
		thread.start();
	}	
	//End of the game thread
	public synchronized void stop() {
		try{
			running = false;
			thread.join();
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	//Game loop
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				//Tick method called "amountOfTicks" per second
				tick();
				delta--;
			}
			//Render Method called as many times as possible per second
			//Also called the frameRate FPS = renders/second
			if(running)
				render();
			frames++;
		
			//Display FPS
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		//If gameLoop stops then thread gets stopped as well
		stop();
	}
	
	//Tick Method calls Handler to handle all tick method calls
	private void tick() {
		handler.tick();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		//Checks if the game is in the splash screen loader
		if(gameState != STATE.Splash){
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			//Renders The current gameState
			if(gameState == STATE.Game)
				handler.render(g);
			else if(gameState == STATE.Menu)
				menu.render(g);
		}
		else {
			//Display Splash Screen
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.drawImage(ImageLoader.getSplashImage(), WIDTH/4, HEIGHT/2-WIDTH/4, WIDTH/2, WIDTH/2, null,null);
		}
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args){
		new Game();
	}
}

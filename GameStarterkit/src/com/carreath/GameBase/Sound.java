package com.carreath.GameBase;

import java.io.File;
import java.net.MalformedURLException;
import java.util.LinkedList;

import javax.sound.sampled.*;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.libraries.LibraryJavaSound;
import paulscode.sound.codecs.CodecJOrbis;

public class Sound {
	
	private Clip clip;
	
	// Change file name to match yours, of course
	public static LinkedList<Sound> music = new LinkedList<Sound>();//{ new Sound("/music1.wav"), new Sound("/music2.wav") };
	public static LinkedList<Sound> sound = new LinkedList<Sound>();//{ new Sound("/sound1.wav"), new Sound("/sound2.wav") };
	public static int currentSong = 0;
	
	@SuppressWarnings("deprecation")
	public static void setupSounds() {
		try
		{
		    SoundSystemConfig.addLibrary( LibraryJavaSound.class );
		    SoundSystemConfig.setCodec( "ogg", CodecJOrbis.class );
		}
		catch( SoundSystemException e )
		{
		    System.err.println("error linking with the plug-ins" );
		}
		SoundSystem mySoundSystem = new SoundSystem();
		
		String musicDir = System.getProperty("user.dir").replaceAll("\\\\", "/") + "/res/sounds/music";
		System.out.println("Res Music Directory = " + musicDir);
		File folder = new File(musicDir);
		File[] listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        System.out.println("File " + listOfFiles[i].getName());
		        //Sound.music.add(new Sound(listOfFiles[i]));

		      } else if (listOfFiles[i].isDirectory()) {
		        System.out.println("Directory " + listOfFiles[i].getName());
		      }
		    }
		    System.out.println("Res Music Path = " + listOfFiles[0].toURI());
		    try {
				mySoundSystem.quickPlay(true, listOfFiles[0].toURL(), listOfFiles[0].getName(), false, -300, 0, 0, SoundSystemConfig.ATTENUATION_ROLLOFF, SoundSystemConfig.getDefaultRolloff());
				mySoundSystem.quickPlay(true, listOfFiles[0].toURL(), listOfFiles[0].getName(), false, 50, 0, 0, SoundSystemConfig.ATTENUATION_ROLLOFF, SoundSystemConfig.getDefaultRolloff());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
	}
	
	public Sound (File file) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		try {
			if (clip != null) {
				new Thread() {
					public void run() {
						synchronized (clip) {
							clip.stop();
							clip.setFramePosition(0);
							clip.start();
						}
					}
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stop(){
		if(clip == null) return;
		clip.stop();
	}
	
	public void loop() {
		try {
			if (clip != null) {
				new Thread() {
					public void run() {
						synchronized (clip) {
							clip.stop();
							clip.setFramePosition(0);
							clip.loop(Clip.LOOP_CONTINUOUSLY);
						}
					}
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isActive(){
		return clip.isActive();
	}
}

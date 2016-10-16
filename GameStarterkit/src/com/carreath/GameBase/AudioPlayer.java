package com.carreath.GameBase;

public class AudioPlayer {
	//private static Random rand = new Random();
	
	public static void updatePlaylist(){
		if(!Sound.music.getFirst().isActive()) {
			Sound.music.addLast(Sound.music.removeFirst());
			//Sound.music.getFirst().play();
		}
	}
	public static void shuffleMusic(String key) {
		
    }
}

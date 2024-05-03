import java.io.*;
import java.util.*;
import javax.sound.sampled.*;

public class MusicManager {
	String fileName;
	File Path;
	AudioInputStream sound;
	Clip audio;
	
	public MusicManager(String fileName) {
		this.fileName = fileName;
		try{
			Path = new File(fileName);
			sound = AudioSystem.getAudioInputStream(Path);
			audio = AudioSystem.getClip();
			
			audio.open(sound);
			audio.start();
			Thread.sleep(1); //Quick Open-Close audio to pre-load
			audio.stop();
		} catch(Exception e) {};
	}
	
	public void play() {
		try{audio.setMicrosecondPosition(0); audio.start();} catch(Exception e) {}
	}
	
	public void stop() {
		try{audio.stop();} catch(Exception e) {}
	}
	
	public void close() {
		try{audio.close();} catch(Exception e) {}
	}
	
	public boolean isPlaying() {
		return audio.isActive();
	}
	
	public void playSFX() {
		try{
			File PathSFX = new File(this.fileName);
			AudioInputStream sfx = AudioSystem.getAudioInputStream(PathSFX);
			Clip SFX = AudioSystem.getClip();
			SFX.open(sfx);
			SFX.start();
		} catch(Exception e) {}
	}
	
	public void loop() {
		try{audio.loop(Clip.LOOP_CONTINUOUSLY);} catch(Exception e) {}
	}
}
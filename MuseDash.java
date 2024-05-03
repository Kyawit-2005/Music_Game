import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MuseDash extends JFrame implements KeyListener {
	public ImageIcon GameIcon = new ImageIcon("Game Assets\\Image Files\\MuseDash Resources\\KagamineRinLen Icon.png");
	public JPanel GameMenuUI, GameplayUI;
	public int ChosenTrack = 0, MusicBPM = 0;
	public int Delay_ms = 5, PixelsTravelled = 5, PixelsMoved = 0;
	public short LastUpperCount = 15, LastLowerCount = 15;
	public JLabel[] UpperCharts, LowerCharts;
	public JLabel[] RenderedUpperCharts = new JLabel[15], RenderedLowerCharts = new JLabel[15];
	public boolean isAuto = true, isHoldingA = false, isHoldingL = false, isChartMoving = false;
	public int Misses = 0;
	
	public MuseDash() {
		this.setTitle("Music Enhypen"); //lmao, Muse Dash
		this.setIconImage(GameIcon.getImage());
		this.setSize(1080, 720);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.black);
		this.addKeyListener(this);
		
		
		GameMenuUI = new JPanel();
		GameMenuUI.setBounds(0, 0, 1065, 680); //Exact Window Size
		GameMenuUI.setLayout(null);
		GameMenuUI.setOpaque(false);
		//==============================================================
		JButton ButtonMusicTrack1 = new JButton("Music 1: テレキャスタービーボーイ (feat. Kagamine Len)");
		ButtonMusicTrack1.setBounds(357, 200, 350, 35);
		ButtonMusicTrack1.setFocusable(false);
		ButtonMusicTrack1.setBorder(null);
		ButtonMusicTrack1.addActionListener(e -> {
			//Telecaster b boy [Featuring: Kagamine Len]. Target File: "Game Assets/Audio Files/Muse Dash/[Len] Telecaster B Boy.wav"  ||  182 BPM
			this.setTitle("Music Enhypen: Now playing \"テレキャスタービーボーイ [Feat. Kagamine Len]\"");
			ChosenTrack = 1;
			createInitialChart();
			GameMenuUI.setVisible(false);
			GameplayUI.setVisible(true);
		}); GameMenuUI.add(ButtonMusicTrack1);
		
		JButton ButtonMusicTrack2 = new JButton("Music 2: Bring it on (feat. Kagamine Len/Rin)");
		ButtonMusicTrack2.setBounds(357, 245, 350, 35);
		ButtonMusicTrack2.setFocusable(false);
		ButtonMusicTrack2.setBorder(null);
		ButtonMusicTrack2.addActionListener(e -> {
			//Bring it on [Featuring: Kagamine Len and Kagamine Rin]. Target File: "Game Assets/Audio Files/Muse Dash/[Rin Len] Bring it on.wav"  ||  160 BPM
			this.setTitle("Music Enhypen: Now playing \"Bring it on [Feat. Kagamine Len/Rin]\"");
			ChosenTrack = 2;
			createInitialChart();
			GameMenuUI.setVisible(false);
			GameplayUI.setVisible(true);
		}); GameMenuUI.add(ButtonMusicTrack2);
		
		JButton ButtonMusicTrack3 = new JButton("Music 3: The Servant of Evil (feat. Kagamine Len)");
		ButtonMusicTrack3.setBounds(357, 290, 350, 35);
		ButtonMusicTrack3.setFocusable(false);
		ButtonMusicTrack3.setBorder(null);
		ButtonMusicTrack3.addActionListener(e -> {
			//The Servant of Evil [Featuring: Kagamine Len]. Target File: "Game Assets/Audio Files/Muse Dash/[Len] Servant of Evil.wav"  ||  120 BPM
			this.setTitle("Music Enhypen: Now playing \"The Servant of Evil [feat. Kagamine Len]\"");
			ChosenTrack = 3;
			createInitialChart();
			GameMenuUI.setVisible(false);
			GameplayUI.setVisible(true);
		}); GameMenuUI.add(ButtonMusicTrack3);
		
		JButton GameInfo = new JButton("Info");
		GameInfo.setBounds(5, 5, 30, 30);
		GameInfo.setFocusable(false);
		GameInfo.setBorder(null);
		GameInfo.addActionListener(e -> {
			Main.GameInfo.showGameInfo("Muse Dash");
		}); GameMenuUI.add(GameInfo);
		//==============================================================
		this.add(GameMenuUI);
		
		
		GameplayUI = new JPanel();
		GameplayUI.setBounds(0, 0, 1065, 680); //Exact Window Size
		GameplayUI.setLayout(null);
		GameplayUI.setOpaque(false);
		GameplayUI.setVisible(false); //Hidden by default
		//==============================================================
		ImageIcon HitMark = new ImageIcon("Game Assets\\Image Files\\MuseDash Resources\\HitPoint.png");
		HitMark = new ImageIcon(HitMark.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
		
		JLabel UpperHitMark = new JLabel(HitMark);
		UpperHitMark.setBounds(200, 177, 75, 75);
		GameplayUI.add(UpperHitMark);
		
		JLabel LowerHitMark = new JLabel(HitMark);
		LowerHitMark.setBounds(200, 680-177-75, 75, 75);
		GameplayUI.add(LowerHitMark);
		
		
		//==============================================================
		this.add(GameplayUI);
		
		
		this.setDefaultCloseOperation(2);
		this.setLocationRelativeTo(null);
	}
	
	
	
	
//Charting System
//==============================================================================
//==============================================================================
	public void createInitialChart() { //Initial Charting. Still loads slow, AF
		int InitialBeatsCount = 15;
	
		switch(ChosenTrack) {
			case 1: MusicBPM = 182; break;
			case 2: MusicBPM = 160; break;
			case 3: MusicBPM = 120*2; break;
			default: //Nothing
		}
		
		UpperCharts = MuseDash_LevelCharts.getUpperChart(ChosenTrack);
		LowerCharts = MuseDash_LevelCharts.getLowerChart(ChosenTrack);
		
		//Initially render only "InitialBeatsCount" Beats at a time
		for(int beat = 0; beat < InitialBeatsCount; beat++) {
			try{
				RenderedUpperCharts[beat] = UpperCharts[beat];
				GameplayUI.add(RenderedUpperCharts[beat]);
			} catch(IndexOutOfBoundsException err) {
				break;
			}
		}
		
		for(int beat = 0; beat < InitialBeatsCount; beat++) {
			try{
				RenderedLowerCharts[beat] = LowerCharts[beat];
				GameplayUI.add(RenderedLowerCharts[beat]);
			} catch(IndexOutOfBoundsException err) {
				break;
			}
		}
		
		//Main.chartMusic[ChosenTrack-1].play();
		isChartMoving = true;
		Main.Runnable1_Run();
	}
	
	public void updateChart(String Pos) { //Updates the charts so that when the leftmost enemy is dead/miss, then adds another one to the rightmost
	Main.SFX[0].playSFX();
	JLabel[] prev;
	int n = 0, m=0;
		switch(Pos) {
			case "Upper":
				try{System.out.println("[DEBUG: Air Enemy Hit! ["+(RenderedUpperCharts[0].getX()-200)+"px]");} catch(Exception e) {};
				prev = RenderedUpperCharts;
				try{RenderedUpperCharts[0].setVisible(false);} catch(NullPointerException e) {System.out.println("Upper Chart ended...");}
				for(n = 0; n < 14; n++) {
					try{RenderedUpperCharts[n] = prev[n+1]; m++;}
					catch(NullPointerException err) {RenderedUpperCharts[n] = null; m = n; break;}
				} if(m >= 14) {
					try{
						RenderedUpperCharts[14] = UpperCharts[LastUpperCount]; LastUpperCount++;
						RenderedUpperCharts[14].setLocation(RenderedUpperCharts[14].getX()-PixelsMoved, RenderedUpperCharts[14].getY());
						RenderedUpperCharts[14].setVisible(true);
						GameplayUI.add(RenderedUpperCharts[14]);
					} catch(Exception e) {RenderedUpperCharts[14] = null;}
					
				} break;
				
			case "Lower":
				try{System.out.println("[DEBUG: Ground Enemy Hit! ["+(RenderedLowerCharts[0].getX()-200)+"px]");} catch(Exception e) {};
				prev = RenderedLowerCharts;
				try{RenderedLowerCharts[0].setVisible(false);} catch(NullPointerException e) {System.out.println("Lower Chart ended...");}
				for(n = 0; n < 14; n++) {
					try{RenderedLowerCharts[n] = prev[n+1]; m++;}
					catch(NullPointerException err) {RenderedLowerCharts[n] = null; m = n; break;}
				} if(m >= 14) {
					try{
						RenderedLowerCharts[14] = LowerCharts[LastLowerCount]; LastLowerCount++;
						RenderedLowerCharts[14].setLocation(RenderedLowerCharts[14].getX()-PixelsMoved, RenderedLowerCharts[14].getY());
						RenderedLowerCharts[14].setVisible(true);
						GameplayUI.add(RenderedLowerCharts[14]);
					} catch(Exception e) {RenderedUpperCharts[14] = null;}
					
				} break;
				
			default: //NONE
		}
	}
	
	public void clearChart() {
		
		for(int n = 0; n < 15; n++) {
			try{GameplayUI.remove(RenderedUpperCharts[n]);}
			catch(NullPointerException err) {break;}
		}
		for(int n = 0; n < 15; n++) {
			try{GameplayUI.remove(RenderedLowerCharts[n]);}
			catch(NullPointerException err) {break;}
		}
	}
	
	
	
	
//Keyboard Controls
//==============================================================================
//==============================================================================
	public void keyTyped(KeyEvent key) {
	
	}
	
	public void keyPressed(KeyEvent key) {
		int MarginOfError = 60;
		//System.out.print("[Pressed key: "+key.getKeyChar()+"] ");
		
		try{
			switch(key.getKeyCode()) {
				case 65: case 83:
					if(!isHoldingA) {
						isHoldingA = true;
						//if condition: Let 'x' be the time window in pixels. 140px<x<260px
						//else consition: Fails, then counts total misses
						//AIR ENEMIES
						updateChart("Upper");
					}
					break;
					
				case 76: case 75:
					if(!isHoldingL) {
						isHoldingL = true;
						//if condition: Let 'x' be the time window in pixels. 140px<x<260px
						//else consition: Fails, then counts total misses
						//GROUND ENEMIES
						updateChart("Lower");
					}
					break;
					
				case 27: //Presses "Esc", closes the "MuseDash" window
					this.setVisible(false);
					break;
				
				default: System.out.println("Unregistered");
			}
		} catch(NullPointerException err) {
			System.out.println("[NullPointerException] THE GAME HASN'T STARTED YET!!!");
		}
	}
	
	public void keyReleased(KeyEvent key) {
		switch(key.getKeyCode()) {
			case 65: case 83:
				isHoldingA = false; break;
			case 76: case 75:
				isHoldingL = false; break;
			default: //nothing
		}
	}
	
//Threads Getter
//==============================================================================
//==============================================================================
	
	public MuseDash_UpTime getThread1() {
		MuseDash_UpTime here = new MuseDash_UpTime();
		return here;
	}
	
	public ChartMove getThread2() {
		ChartMove here = new ChartMove();
		return here;
	}
	
	public ChartChecker getThread3() {
		ChartChecker here = new ChartChecker();
		return here;
	}
}















//Thread name: "[MuseDash.java] UpTime Checker"
//Checks if the MuseDash window is open. Blocks the MapGUI window from moving [KeyListener] and exiting.
//Priority Level: 1 [Lowest]
class MuseDash_UpTime extends Thread { 
	public void run() {
		while(true) {
			try{Thread.sleep(1000);} catch(Exception e) {}
			if(Main.Processes[4].isVisible()) {
				System.out.println("Opened \"Music Enhypen\"");
				while(true) {
					try{Thread.sleep(100);} catch(Exception e) {}
					Main.Processes[3].setDefaultCloseOperation(0);
					Main.MapGUI.inputEnable(false);
					if(!Main.Processes[4].isVisible()) {
						System.out.println("Closed \"Music Enhypen\"");
						Main.Processes[3].setDefaultCloseOperation(2);
						Main.MapGUI.inputEnable(true);
						Main.MD.clearChart();
						Main.MD.setTitle("Music Enhypen");
						Main.MD.GameplayUI.setVisible(false);
						Main.MD.GameMenuUI.setVisible(true);
						Main.MD.isChartMoving = false;
						
						for(MusicManager music : Main.chartMusic) {
							music.stop();
						}; break;
						
}	}	}	}	}	}




//Runnable Thread: Made "Runnable" to repeatedly call "start" after the thread died
//When the game actually started, moves the enemies by a stable pixels/sec
//Priority Level: 5 [Default]
class ChartMove implements Runnable { //Fast processing, af
	long current_MS, previous_MS, current_MS_Music, previous_MS_Music;
	long timeDif, timeDifMusicStart;
	
	public void run() {
		boolean playedMusic = false;
		JLabel[] RenderedUpperCharts = Main.MD.RenderedUpperCharts;
		JLabel[] RenderedLowerCharts = Main.MD.RenderedLowerCharts;
		double StartDelayPixels = MuseDash_LevelCharts.Pix_per_Beat*5;
		double StartDelayTime = MuseDash_LevelCharts.sec_perBPM*5;
		Main.MD.PixelsMoved -= StartDelayPixels;
		
		//Shifts the charts by certain Beats
		for(JLabel enemy : RenderedUpperCharts) {
			try{enemy.setLocation(enemy.getX()+(int) StartDelayPixels, enemy.getY());}
			catch(NullPointerException err) {break;}
		}
		for(JLabel enemy : RenderedLowerCharts) {
			try{enemy.setLocation(enemy.getX()+(int) StartDelayPixels, enemy.getY());}
			catch(NullPointerException err) {break;}
		}
		
		previous_MS = System.nanoTime(); //Check previous time, in Nanoseconds
		previous_MS_Music = System.nanoTime(); //Check previous time, in Nanoseconds
		while(Main.MD.isChartMoving) {
			try{Thread.sleep(0);} catch(Exception e) {}; //Fail Safe
			current_MS = System.nanoTime(); //Check current time, in Nanoseconds
			current_MS_Music = System.nanoTime(); //Check current time, in Nanoseconds
			timeDif = (current_MS - previous_MS);
			timeDifMusicStart = current_MS_Music - previous_MS_Music;
			
			
			if(timeDif >= Main.MD.Delay_ms*1000000) {
				//System.out.println("timeDif = "+timeDif);
				//System.out.println("timeDifMusicStart = "+timeDifMusicStart+" > "+(StartDelayTime*1000d*1000000d));
				for(JLabel enemy : RenderedUpperCharts) {
					try{enemy.setLocation(enemy.getX()-Main.MD.PixelsTravelled, enemy.getY());}
					catch(NullPointerException err) {break;}
				}
				for(JLabel enemy : RenderedLowerCharts) {
					try{enemy.setLocation(enemy.getX()-Main.MD.PixelsTravelled, enemy.getY());}
					catch(NullPointerException err) {break;}
				}
				Main.MD.PixelsMoved += Main.MD.PixelsTravelled;
				
				previous_MS = current_MS;
				timeDif -= Main.MD.Delay_ms;
			}
			
			if(!playedMusic && (timeDifMusicStart > StartDelayTime*1000d*1000000d)) {Main.chartMusic[Main.MD.ChosenTrack-1].play(); playedMusic = true;}
		}
		
		Main.MD.LastUpperCount = 15;
		Main.MD.LastLowerCount = 15;
		Main.MD.PixelsMoved = 0;
	}
	
}

//Thread name:
//Checks if the enemies went past the target area [or the so-called "Miss Judgement"]
//Priority Level: 1 [Lowest]
class ChartChecker extends Thread {
	public void run() {
		while(true) {
			if(Main.MD.isAuto) {
				//checks if the RenderedCharts[0] is at less than 200px
				//Updates the Chart
			} else {
				//checks if an enemy is missed [less than (200-MarginOfError) Pixels]
				//Misses++
			}
			
			
			
			
			
			
			
			
			
			
			
			
		}
	}
}







































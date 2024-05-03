import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Gameplay1 extends JFrame implements KeyListener {
	JLabel[] WholeMap = new JLabel[9];
	JLabel Player;
	
	public Gameplay1() {
		Player = new JLabel(new ImageIcon("Game Assets\\Image Files\\Map Resources\\Player-UP.png"));
		Player.setBounds(-66+9*63, -7+5*63, 63, 63);
		this.add(Player);
		
		MapBuilder();
		
		this.setTitle("The MAP!!!");
		this.setSize(1080, 720); //Window Actual res: 1040px - 680px
		this.setLayout(null);
		this.getContentPane().setBackground(Color.black);
		this.addKeyListener(this);
		
		
		
		this.setDefaultCloseOperation(2);
		this.setLocationRelativeTo(null);
	}
	
	public void MoveMap(int X, int Y) {
		for(int n = 0; n < 9; n++) {
			WholeMap[n].setLocation(WholeMap[n].getX()+X, WholeMap[n].getY()+Y);
		}
	}
	
	public void MapBuilder() {
		//this.add(Story1.DialogueOverlay); //Gotta work on this later
		ImageIcon MapLoc, MapPic;
		int point = 0;
		
		int[][] LabelPos = {{-1256, -697}, 	{-64, -697}, {1128, -697},
													{-1256, -7}, 		{-64, -7}, 		{1128, -7},
													{-1256, 683}, 	{-64, 683}, 	{1128, 683}};
		String[] Loc = {"UL", "U", "UR", "L", "C", "R", "BL", "B", "BR"};
		for(String loc : Loc) {
			MapLoc = new ImageIcon("Game Assets\\Image Files\\Map Layout\\Map_"+loc+".png"); //Size: 1920 - 1080
			MapPic = new ImageIcon(MapLoc.getImage().getScaledInstance(1192, 690, Image.SCALE_SMOOTH));
			
			WholeMap[point] = new JLabel(MapPic);
			WholeMap[point].setBounds(LabelPos[point][0], LabelPos[point][1], 1192, 690);
			WholeMap[point].setHorizontalAlignment(JLabel.CENTER);
			WholeMap[point].setVerticalAlignment(JLabel.CENTER);
			this.add(WholeMap[point]);
			
			point++;
		}
	}
	
	public void keyTyped(KeyEvent key) {
		
	}
	
	public void keyPressed(KeyEvent key) {
		switch(key.getKeyCode()) {
			case 38: case 87: MovePlayer("UP"); break;
			case 40: case 83: MovePlayer("DOWN"); break;
			case 37: case 65: MovePlayer("LEFT"); break;
			case 39: case 68: MovePlayer("RIGHT"); break;
			default: System.out.println("[DEBUG] Unregistered: You typed "+key.getKeyChar()+" [KeyCode "+key.getKeyCode()+"]");
		}
		
		
	}
	
	public void keyReleased(KeyEvent key) {
	
	}
	
	
	
	
	
	
//==============================================================================
//==============================================================================
	public void MovePlayer(String dir) { //Exact Block Movement: 62.72727272... [62 8/11]
		ImageIcon PlayerPic = new ImageIcon("Game Assets\\Image Files\\Map Resources\\Player-"+dir+".png");
		Player.setIcon(PlayerPic);
		MapData.MovePlayer(dir);
		
		switch(dir) {
			case "UP":
				if(MapData.canMove) {MoveMap(0, 63); MapData.setCurrentTile(0);};
				break;
			case "DOWN":
				if(MapData.canMove) {MoveMap(0, -63); MapData.setCurrentTile(0);};
				break;
			case "LEFT":
				if(MapData.canMove) {MoveMap(63, 0); MapData.setCurrentTile(0);};
				break;
			case "RIGHT":
				if(MapData.canMove) {MoveMap(-63, 0); MapData.setCurrentTile(0);};
				break;
			default:
		}
		
		//System.out.println("[DEBUG] MapData.canMove = "+MapData.canMove);
	}
	
	public void inputEnable(boolean bool) {
		if(!bool) {
			this.removeKeyListener(this);
		} else this.addKeyListener(this);
	}
}
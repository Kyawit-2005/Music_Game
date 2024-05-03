import javax.swing.*;
import java.awt.*;

public class Story1 {
	static JLabel PersonLeft, PersonRight, Dialogue;
	static JLabel PersonNameLeftPic, PersonNameRightPic, PersonNameLeft, PersonNameRight;
	static JFrame MainFrame = new JFrame();
	static int DialogueCounter = 0;
	static JPanel DialogueOverlay = new JPanel();
	static String User_Name = "";
	
	public static void StoryFrame() {
		MainFrame.setTitle("Story 1 Dialogues");
		MainFrame.setSize(1080, 720); //Window Actual res: 1040px - 680px
		MainFrame.setLayout(null);
		MainFrame.getContentPane().setBackground(Color.black);
		
		Dialogue = new JLabel(TextSize(6, "Press \"Next\" to start the Game..."));
		Dialogue.setBounds(175, 455, 690, 190);
		Dialogue.setVerticalAlignment(JLabel.TOP);
		Dialogue.setHorizontalAlignment(JLabel.CENTER);
		Dialogue.setForeground(Color.white);
		MainFrame.add(Dialogue);
		
		PersonNameLeft = new JLabel();
		PersonNameLeft.setBounds(180, 428, 145, 25);
		PersonNameLeft.setForeground(Color.white);
		PersonNameLeft.setHorizontalAlignment(JLabel.LEFT);
		MainFrame.add(PersonNameLeft);
		
		PersonNameRight = new JLabel();
		PersonNameRight.setBounds(715, 428, 145, 25);
		PersonNameRight.setForeground(Color.white);
		PersonNameRight.setHorizontalAlignment(JLabel.RIGHT);
		MainFrame.add(PersonNameRight);
		
		DialogueOverlay.setBounds(0, 0, 1065, 680); //Exact Window Size
		DialogueOverlay.setLayout(null);
		DialogueOverlay.setOpaque(false);
		//==============================================================
		//JPanel
		PersonNameLeftPic = new JLabel(new ImageIcon("Game Assets\\Image Files\\PersonNameLeft.png"));
		PersonNameLeftPic.setBounds(175, 428, 150, 25);
		PersonNameLeftPic.setVisible(false);
		DialogueOverlay.add(PersonNameLeftPic);
		
		//JPanel
		PersonNameRightPic = new JLabel(new ImageIcon("Game Assets\\Image Files\\PersonNameRight.png"));
		PersonNameRightPic.setBounds(715, 428, 150, 25);
		PersonNameRightPic.setVisible(false);
		DialogueOverlay.add(PersonNameRightPic);
		
		//JPanel
		JLabel DialogueBox = new JLabel(new ImageIcon("Game Assets\\Image Files\\DialogueBox.png"));
		DialogueBox.setBounds(170, 440, 700, 200); //Res: 700px - 200px
		DialogueOverlay.add(DialogueBox);
		
		//JPanel
		PersonLeft = new JLabel();
		PersonLeft.setBounds(-100, 80, 427, 640);
		PersonLeft.setHorizontalAlignment(JLabel.CENTER);
		DialogueOverlay.add(PersonLeft);
		
		//JPanel
		PersonRight = new JLabel();
		PersonRight.setBounds(723, 80, 427, 640);
		PersonRight.setHorizontalAlignment(JLabel.CENTER);
		DialogueOverlay.add(PersonRight);
		//==============================================================
		MainFrame.add(DialogueOverlay);
		
		JButton NextDialogue = new JButton("Next");
		NextDialogue.setBounds(170, 650, 100, 25);
		NextDialogue.setFocusable(false);
		NextDialogue.setBorder(null);
		NextDialogue.addActionListener(e -> {
			DialogueCounter++;
			DialogueList();
		}); MainFrame.add(NextDialogue);
		
		JButton SkipDialogue = new JButton("Skip Dialogue");
		SkipDialogue.setBounds(275, 650, 100, 25);
		SkipDialogue.setFocusable(false);
		SkipDialogue.setBorder(null);
		SkipDialogue.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, "Are you sure you want to skip the story?", "Placeholder", 3);
			//JOptionPane.showMessageDialog(null, "Unfortunately, it's still in the works.\nStill, Genshin could never!", "Placeholder", 0);
			DialogueCounter = -1;
			DialogueList();
		}); MainFrame.add(SkipDialogue);
		
		MainFrame.setDefaultCloseOperation(2);
		MainFrame.setLocationRelativeTo(null);
		MainFrame.setVisible(true);
	}
	
	//note: please replace all "Kagamine Len" and "Kagamine Rin" into someone, you just proided "Mc" and "Random person" respectively
	//Rin = ID
	//To do: [Might] want to add Dialogue Voices [Vocaloid], where: [this is completely unneessary, lmao]
	//  - Hajime = Len
	//  - Mari = Rin
	//  - Shino = Miku
	static void DialogueList() { //Syntax: "Character Actual Name", "Position", "Display Name", "TextBox Message"
		switch(DialogueCounter) { //																																				|| Text Limit
			case 1: Say(null, "Both", null,
									"Syncing data user..."); break;
									
			case 2: Say(null, "Both", null,
									"--ERROR--"); break;
									
			case 3: Say("Mari", "Right", "Ema Skye",
									"Oh look, the sleepy boy woke up"); break;
									
			case 4: Say("Hajime", "Left", User_Name,
									"...huh?"); break;
									
			case 5: Say("Mari", "Right", "Ema Skye",
									"Come on! Stand up, we don't have time for this, dude... We have a collection to"
					+"<br>do"); break;
					
			case 6: Say("Hajime", "Left", User_Name,
									"...collection what now?"); break;
									
			case 7: Say("Shino", "Right", "Random Girl",
									"A collection that we were assigned. Are you not feeling well today?"); break;
									
			case 8: Say("Hajime", "Left", User_Name,
									"I don't know... It's just that I got a wierd dream, and then somehow I can't"
					+"<br>remember anything..."); break;
					
			case 9: Say("Mari", "Right", "Ema Skye",
									"Hmm..."); break;
									
			case 10: Say("Shino", "Right", "Random Girl",
									"...looks like he got hit by the music randomizer"); break; //where the F did that "Music Randomizer" came from???
									
			case 11: Say("Mari", "Right", "Ema Skye",
									"Looks like it, geez... Welp, we have to find this music first-"); break;
									
			default:
				//Calls another JAVA file: Specifically for the Maze GAMEPLAY!!!
				Main.Processes[3].setVisible(true);
				MainFrame.dispose();
				//Gameplay1 play = new Gameplay1();
				//Main.Processes[3] = play;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//==============================================================================
//==============================================================================
	static void Say(String Person, String Direction, String PersonName, String Text) {
		switch(Direction) {
			case "Left":
				PersonLeft.setIcon(getPerson(Person, "Left"));
				PersonNameLeftPic.setVisible(true);
				PersonNameRightPic.setVisible(false);
				PersonNameLeft.setText(TextSize(4, PersonName));
				PersonNameRight.setText("");
				break;
			case "Right":
				PersonRight.setIcon(getPerson(Person, "Right"));
				PersonNameRightPic.setVisible(true);
				PersonNameLeftPic.setVisible(false);
				PersonNameRight.setText(TextSize(4, PersonName));
				PersonNameLeft.setText("");
				break;
			case "Both":
				ImageIcon nothing = new ImageIcon("Game Assets\\Image Files\\Standing Characters\\Null.png");
				//PersonLeft.setIcon(nothing);
				//PersonRight.setIcon(nothing);
				PersonNameLeftPic.setVisible(false);
				PersonNameRightPic.setVisible(false);
				PersonNameLeft.setText("");
				PersonNameRight.setText("");
				break;
			default: System.out.println("Something is wrong...");
		}; Dialogue.setText(TextSize(5, Text));
		Main.SFX[1].playSFX();
	}
	
	static ImageIcon getPerson(String CharName, String Direction) { //Standing Image res: 640px - 960px, Resized: 426.66px - 640px
		ImageIcon pic = new ImageIcon("Game Assets\\Image Files\\Standing Characters\\"+CharName+"_"+Direction+".png");
		ImageIcon resized_pic = new ImageIcon(pic.getImage().getScaledInstance(pic.getIconWidth()*2/3, pic.getIconHeight()*2/3, Image.SCALE_SMOOTH));
		return resized_pic;
	}
	
	static String TextSize(int size, String text) {
		return "<html><font size = '"+size+"'>"+text+"</font></html>";
	}
}
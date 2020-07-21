/**
 * Name: Quy Do
 * Date: 17/01/2018
 * 
 * Purpose: Java program will generates the game 2048.
 * The program will allow users to move the tiles in 
 * the game, restart the game, exit the game, and try 
 * playing the game again. The program use JFrame, 
 * JPanel, and rule class to generate the game.
 * 
 * 
 * Constructor(in GameWindow class):
 * GameWindow()
 * 
 * Functions(in GameWindow class):
 * main - return String
 * keyPressed - void
 * keyReleased - void
 * keyTyped - void
 * 
 * Constructor(in GamePanel class):
 * GamePanel()
 * 
 * Functions(in GamePanel class):
 * actionPerformed - void
 * tileColorUpdate - void
 * tileVisibleUpdate - void
 * tileUpdate - void
 * scoresUpdate - void 
 * gameOverScreenUpdate - void
 * restart - void
 * update - void
 * keyDisable - return boolean
 * 
 * Constructor(in GameRules class):
 * GameRules()
 * 
 * Functions(in GameRules class):
 * randomGenerator() - void
 * clearBoard - void
 * newBoard - void
 * isFull - return boolean
 * hScoreCount - void
 * win - return boolean
 * movable - return boolean 
 * lose - return boolean
 * moveAndChangeCheck - return boolean
 * getBoard - return int
 * getScore - return int
 * getHScore - return int
 */
package Game2048;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame; 

//The main class which outputs and generates the whole game and takes action when desired key is pressed 
public class GameWindow extends JFrame implements KeyListener {
	//Variable which are used to set up the size of the window of the game 
	static final int WIDTH = 550;
	static final int HEIGHT = 830;
	//Reference for the JPanel
	GamePanel gamePanel;
	
	//The default constructor of the window class 
	public GameWindow(String title){
		super(title);
		gamePanel = new GamePanel();
		Container container = getContentPane();
		container.add(gamePanel);
		container.validate();
		
		//Add KeyListener and set focus on the window
		addKeyListener(this);
		setFocusable(true);
	}
	
	//The main method which generates the window of the game
	public static void main(String args[]) { 
        GameWindow frame =  new GameWindow("2048"); 
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE); 
        
        //Set the size for the window of the game 
        frame.setSize(WIDTH, HEIGHT); 
        frame.setVisible(true); //Show the window
        frame.setResizable(false); //Make the size of the window fixed
	}
	
	//Method which takes action when a specific key is pressed
	@Override
	public void keyPressed(KeyEvent event){
		int code = event.getKeyCode();
		switch(code){
		//Move up if the up key is pressed
		case KeyEvent.VK_UP:
			if(!gamePanel.keyDisable()){
				gamePanel.update(8);
			}
			break;
		//Move down if the down is pressed
		case KeyEvent.VK_DOWN:
			if(!gamePanel.keyDisable()){
				gamePanel.update(2);
			}
			break;
		//Move to the left if the left key is pressed
		case KeyEvent.VK_LEFT:
			if(!gamePanel.keyDisable()){
				gamePanel.update(4);
			}
			break;
		//Move to the right if the right key is pressed
		case KeyEvent.VK_RIGHT:
			if(!gamePanel.keyDisable()){
				gamePanel.update(6);
			}
			break;
		//Do nothing if any key beside those 4 above is pressed
		default:
			if(!gamePanel.keyDisable()){
				gamePanel.update(0);
			}
			break;
		}
		//After user wins or loses, no action will be taken 
	}
	
	//Method which is used when a key is released or typed but we are not using it here
	@Override
	public void keyReleased(KeyEvent arg0) {
	}
	@Override
	public void keyTyped(KeyEvent arg0) {	
	}
}



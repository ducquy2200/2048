package Game2048;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JLabel; 
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

//The JLayeredPane class which takes care of the GUI of the game
public class GamePanel extends JLayeredPane implements ActionListener {
	//JLabels for the score section and the high score section of the game
	private JLabel scoreLabel, scoreNum;
	private JLabel scoreContainer;
	private JLabel hScoreLabel, hScoreNum;
	private JLabel hScoreContainer;
	//JLabels for the name of the game and the introduction of how to win the game
	private JLabel name;
	private JLabel introduction1, introduction2;
	//JLabels for the board game
	private JLabel[][] tile;
	private JLabel mainBoard;
	//JButtons for exit the game, restart, and try playing the game again
	private JButton exit, newGame, tryAgain;
	//Fonts and the color for the font
	private Font titleFont, scoreFont, normalFont;
	private Color fontColor;
	//JLabel which appears when the game is over
	private JLabel gameOver;
	//Types of tiles and colors which go with each of them
	private int[] boardNumType = {0,2,4,8,16,32,64,128,256,512,1024,2048};
	private String[] boardColorType = {"#CCC0B4","#F2E3DE","#EAE0C8","#F2B27C","#FD9163","#F97A5F","#F75D43","#F2CA72","#F1CA69","#EACB44","#ECC641","#EBC522"};
	//Reference for the rules class 
	private GameRules gameRules;
		
	//the default constructor which put in the window labels and buttons as desired
	public GamePanel(){
		//Reference for the rules class 
		gameRules = new GameRules();
		//Set up for the main window
		setLayout(null);
		setBackground(Color.decode("#FBF8F1"));
		
		//The tiles 
		tile = new JLabel[4][4];
		for (int column=0; column<4; column++){
			for (int row=0; row<4; row++){
				tile[column][row] = new JLabel(Integer.toString(gameRules.getBoard(column,row)));
				tileVisibleUpdate(column,row);
				tile[column][row].setFont(new Font("Clear Sans", Font.BOLD, 40));
				tile[column][row].setBackground(Color.decode("#CCC0B4"));
				tile[column][row].setBounds(50+(115*column), 285+(115*row), 100, 100);
				tile[column][row].setHorizontalAlignment(SwingConstants.CENTER);
				tile[column][row].setVerticalAlignment(SwingConstants.CENTER);
				tile[column][row].setOpaque(true);
				add(tile[column][row],50);
			}
		}
			
		//The name of the game
		name = new JLabel("2048");
		fontColor = Color.decode("#7F7C75");
		titleFont = new Font("Clear Sans", Font.BOLD, 75);
		name.setForeground(fontColor);
		name.setFont(titleFont);
		name.setBounds(50, 50, 175, 75);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setVerticalAlignment(SwingConstants.CENTER);
		add(name,91);
			
		//The introduction of the game
		introduction1 = new JLabel("Join the numbers and get to the");
		normalFont = new Font("Clear Sans", Font.PLAIN, 22);
		introduction1.setFont(normalFont);
		introduction1.setBounds(45, 175, 323, 30);
		add(introduction1,92);
		introduction2 = new JLabel("2048 tile!");
		introduction2.setFont(new Font("Clear Sans", Font.BOLD, 22));
		introduction2.setBounds(352, 175, 100, 30);
		add(introduction2,93);
			
		//The main board of the game
		mainBoard = new JLabel();
		mainBoard.setBackground(Color.decode("#B6AB9F"));
		mainBoard.setSize(475, 475);
		mainBoard.setLocation(35,270);
		mainBoard.setOpaque(true);
		add(mainBoard,100);
			
		//The score section
		scoreLabel = new JLabel("Score");
		scoreFont = new Font("Clear Sans", Font.BOLD, 14);
		scoreLabel.setFont(scoreFont);
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setBounds(280, 42, 100, 20);
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setVerticalAlignment(SwingConstants.CENTER);
		add(scoreLabel,94);
		scoreNum = new JLabel("0");
		scoreNum.setFont(scoreFont);
		scoreNum.setForeground(Color.WHITE);
		scoreNum.setBounds(280, 65, 100, 20);
		scoreNum.setHorizontalAlignment(SwingConstants.CENTER);
		scoreNum.setVerticalAlignment(SwingConstants.CENTER);
		add(scoreNum,95);
		scoreContainer = new JLabel();
		scoreContainer.setBackground(Color.decode("#B6AB9F"));
		scoreContainer.setSize(100,50);
		scoreContainer.setLocation(280,40);
		scoreContainer.setOpaque(true);
		add(scoreContainer,96);
		
		//The high score section
		hScoreLabel = new JLabel("Best");
		hScoreLabel.setFont(scoreFont);
		hScoreLabel.setForeground(Color.WHITE);
		hScoreLabel.setBounds(395, 42, 100, 20);
		hScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hScoreLabel.setVerticalAlignment(SwingConstants.CENTER);
		add(hScoreLabel,97);
		hScoreNum = new JLabel("0");
		hScoreNum.setFont(scoreFont);
		hScoreNum.setForeground(Color.WHITE);
		hScoreNum.setBounds(395, 65, 100, 20);
		hScoreNum.setHorizontalAlignment(SwingConstants.CENTER);
		hScoreNum.setVerticalAlignment(SwingConstants.CENTER);
		add(hScoreNum,98);
		hScoreContainer = new JLabel();
		hScoreContainer.setBackground(Color.decode("#B6AB9F"));
		hScoreContainer.setSize(100,50);
		hScoreContainer.setLocation(395,40);
		hScoreContainer.setOpaque(true);
		add(hScoreContainer,99);
		
		//The Exit, New Game and Try Again buttons
		exit = new JButton("Exit");
		exit.setFont(new Font("Clear Sans", Font.BOLD, 12));
		exit.setForeground(Color.decode("#F1F6FD"));
		exit.setBackground(Color.decode("#F39963"));
		exit.setSize(100,35);
		exit.setLocation(395, 100);
		exit.setBorder(new LineBorder(Color.decode("#F39963")));
		add(exit, 0);
		exit.addActionListener(this);
		exit.setFocusable(false);
		newGame = new JButton("New Game");
		newGame.setFont(new Font("Clear Sans", Font.BOLD, 12));
		newGame.setForeground(Color.decode("#F1F6FD"));
		newGame.setBackground(Color.decode("#F39963"));
		newGame.setSize(100,35);
		newGame.setLocation(280, 100);
		newGame.setBorder(new LineBorder(Color.decode("#F39963")));
		add(newGame, 0);
		newGame.addActionListener(this);
		newGame.setFocusable(false);
		tryAgain = new JButton("Try Again");
		tryAgain.setFont(new Font("Clear Sans", Font.BOLD, 12));
		tryAgain.setForeground(Color.decode("#F1F6FD"));
		tryAgain.setBackground(Color.decode("#907A65"));
		tryAgain.setSize(100,35);
		tryAgain.setLocation(223, 575);
		tryAgain.setBorder(new LineBorder(Color.decode("#907A65")));		
		add(tryAgain, 0);
		tryAgain.addActionListener(this);
		tryAgain.setFocusable(false);
		tryAgain.setVisible(false);
		
		//The label which appears when the game is over
		gameOver = new JLabel("");
		gameOver.setFont(titleFont);
		gameOver.setBounds(35, 270, 475, 475);
		gameOver.setHorizontalAlignment(SwingConstants.CENTER);
		gameOver.setVerticalAlignment(SwingConstants.CENTER);
		gameOver.setOpaque(true);
		gameOver.setVisible(false);
		add(gameOver,1);
		
		//Generate new board as the game starts
		gameRules.newBoard();
		tileUpdate();
	}
	
	//Method which manipulates the actions when a button is clicked
	public void actionPerformed(ActionEvent e) { 
        //Exit the game if Exit button is pressed
    	if (e.getActionCommand().equals("Exit")){
            System.exit(0); 
        }
    	
    	//Restart the game if New Game or Try Again button is pressed
    	if ((e.getActionCommand().equals("New Game"))||(e.getActionCommand().equals("Try Again"))){
    		restart();
        }            
    }
    
	//Method which updates the colors in each tile
    public void tileColorUpdate(int column, int row){
    	//The color for the background
    	for (int size=0; size<boardNumType.length; size++){
    		if(gameRules.getBoard(column,row)==boardNumType[size]){
    			tile[column][row].setBackground(Color.decode(boardColorType[size]));
    		}
    	}
    	
    	//The color for the number of the tile
    	if(gameRules.getBoard(column,row)>4){
    		tile[column][row].setForeground(Color.WHITE);
    	}else{
    		tile[column][row].setForeground(fontColor);
    	}
    }
    
    //Method which updates the visibility of the empty tiles
    public void tileVisibleUpdate(int column, int row){
    	if(gameRules.getBoard(column, row)==0){
    		tile[column][row].setText("");
    		tile[column][row].setBackground(Color.decode(boardColorType[0]));
    	}
    }
    
    //Method which updates all tiles in the board of the game
    public void tileUpdate(){
    	for (int column=0; column<4; column++){
    		for (int row=0; row<4; row++){
    			tile[column][row].setText(Integer.toString(gameRules.getBoard(column,row)));
    			tileColorUpdate(column,row);
    			tileVisibleUpdate(column,row);
    		}
    	}
    }
    
    //Method which updates the score and the high score
    public void scoresUpdate(){
    	gameRules.hScoreCount();
    	scoreNum.setText(Integer.toString(gameRules.getScore()));
    	hScoreNum.setText(Integer.toString(gameRules.getHScore()));
    }
    
    //Method which update the visibility of the label which appears when the game is over
    public void gameOverScreenUpdate(){
    	if(gameRules.win()){
    		gameOver.setText("YOU WIN!");
    		gameOver.setForeground(Color.decode("#F1F6FD"));
    		gameOver.setBackground(new Color(254, 220, 1, 100));
    		gameOver.setVisible(true);
    		tryAgain.setVisible(true);
    	}else if(gameRules.lose()){
    		gameOver.setText("YOU LOSE!");
    		gameOver.setForeground(Color.decode("#907A65"));
    		gameOver.setBackground(new Color(224, 213, 201, 100));
    		gameOver.setVisible(true);
    		tryAgain.setVisible(true);
    	}else{
    		gameOver.setText("");
    		gameOver.setVisible(false);
    		tryAgain.setVisible(false);
    	}
    }
    
    //Method which restarts the game
    public void restart(){
    	gameRules.newBoard();
    	tileUpdate();
    	scoresUpdate();
    	gameOverScreenUpdate();
    }
    
    //Method which updates the game when the user makes a move
    public void update(int move){
    	boolean change = gameRules.moveAndChangeCheck(move);
    	//Only generate a new tile if the board is changed after making a move
    	if(change){
    		gameRules.randomGenerator();
    	}
    	tileUpdate();
    	scoresUpdate();
    	gameOverScreenUpdate();
    }
    
    //Method which determines that KeyListener is disabled when the game is over 
    public boolean keyDisable(){
    	if((gameRules.win())||(gameRules.lose())){
    		return true;
    	}else{
    		return false;
    	}
    }
}

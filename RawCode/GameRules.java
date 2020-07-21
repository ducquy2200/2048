package Game2048;
import java.util.Random;

//The rules class which takes care of the manipulation of the information inside the game
public class GameRules{
	//Variables for the value of each tile
	private int[][] board;
	//Variable for the value of the score
	private int score;
	//Variable for the value of the high score
	private int hScore;
	
	//The default constructor which sets up the initial values of the variables
	public GameRules(){
		board = new int[4][4];
		for (int column=0; column<4; column++){
			for (int row=0; row<4; row++){
				board[column][row]=0;
			}
		}
		score = 0;
		hScore = 0;
	}
	
	//Method which randomly generates values for random tiles
	public void randomGenerator(){
		int randCol, randRow;
		Random rand = new Random();
		//Only generate new tile if the board is not full
		if(!isFull()){
			//Give random location of the tiles which is empty
			do{
				randCol = rand.nextInt(4);
				randRow = rand.nextInt(4);
				}while(board[randCol][randRow]!=0);
			//Determine whether a 2 or a 4 is generated
			int chance = rand.nextInt(100)+1;
			
			//2 has 90% chance of being generated and 4 has 10% chance of being generated
			if(chance<=90){
				board[randCol][randRow] = 2;
			}else{
				board[randCol][randRow] = 4;
			}
		}
	}
	
	//Method which clears all the value of the board
	public void clearBoard(){
		for (int column=0; column<4; column++){
			for (int row=0; row<4; row++){
				board[column][row] = 0;
			}
		}
	}
	
	//Method which reset the whole game back to the beginning
	public void newBoard(){
		clearBoard();
		randomGenerator();
		randomGenerator();
		score = 0;
	}
	
	//Method which determines whether the board is full of tiles or not
	public boolean isFull(){
		boolean isFull = true;
		for (int column=0; column<4; column++){
			for (int row=0; row<4;row++){
				if(board[column][row]==0){
					isFull = false;
					break;
				}
			}
			if(!isFull) break;
		}
		return isFull;
	}
	
	//Method which determines the value of the high score
	public void hScoreCount(){	
		if(hScore<score){
			hScore = score;
		}
	}
	
	//Method which determines whether the user wins or not
	public boolean win(){
		boolean win = false;
		//The user will win if there is a 2048-tile on the board
		for (int column=0; column<4; column++){
			for (int row=0; row<4; row++){
				if(board[column][row]==2048){
					win = true;
					break;
				}
			}
			if(win)break;
		}
		return win;
	}
	
	//Method which determines whether the user can still make a move on the board or not
	public boolean movable(){
		boolean movable = false;
		if(isFull()){
			//if there are any tiles which have the same value and stand next to each other, the board is immovable
			for(int column=0; column<4; column++){
				for(int row=0; row<3; row++){
					if(board[column][row]==board[column][row+1]){
						movable = true;
						break;
					}
				}
				if(movable)break;
			}
			for(int row=0; row<4; row++){
				for(int column=0; column<3; column++){
					if(board[column][row]==board[column+1][row]){
						movable = true;
						break;
					}
				}
				if(movable)break;
			}
		}else{
			//it is still movable if the board is not full
			movable = true;
		}
		return movable;
	}
	
	//Method which determines whether the user loses or not
	public boolean lose(){
		//The user loses if the board is immovable
		if(!movable()){
			return true;
		}else{
			return false;
		}
	}
	
	//Method which makes the moving action in the board and checks whether the board is change after making the move or not
	public boolean moveAndChangeCheck(int move){
		//Store the value of the initial board
		int[][] changeCheck = new int[4][4];
		boolean change = false;
		for(int column=0; column<4; column++){
			for(int row=0; row<4; row++){
				changeCheck[column][row] = board[column][row];
			}
		}
		
		int[] columnSet = new int[4];
		int[] rowSet = new int[4];
		int setCount = 0;
		int reverse = 0;
		
		//When the user chooses to move up
		if(move==8){
			//Make all the tiles to move up
			reverse = 3;
			for(int column=0; column<4; column++){
				for(int row=0; row<4; row++){
					columnSet[setCount] = board[column][row];
					setCount++;
				}
				setCount = 0;
				for (int count=0; count<4; count++){
					if(columnSet[count]!=0){
						board[column][setCount] = columnSet[count];
						setCount++;
					}else{
						board[column][reverse] = columnSet[count];
						reverse--;
					}
				}
				setCount = 0;
				reverse = 3;
			}
			
			//Merge tiles if they have the same value and count the score
			for(int column=0; column<4; column++){
				for(int row=0; row<3; row++){
					if(board[column][row]==board[column][row+1]){
						board[column][row]+=board[column][row+1];
						for(int row2=row+1; row2<3; row2++){
							board[column][row2] = board[column][row2+1];
						}
						board[column][3]=0;
						score+=board[column][row];
					}
				}
			}
		}
		
		//When the user chooses to move down 
		if(move==2){
			//Make all the tiles to move down
			for(int column=0; column<4; column++){
				for(int row=3; row>=0; row--){
					columnSet[setCount] = board[column][row];
					setCount++;
				}
				setCount = 3;
				for (int count=0; count<4; count++){
					if(columnSet[count]!=0){
						board[column][setCount] = columnSet[count];
						setCount--;
					}else{
						board[column][reverse] = columnSet[count];
						reverse++;
					}
				}
				setCount = 0;
				reverse = 0;
			}
			
			//Merge tiles if they have the same value and count the score
			for(int column=0; column<4; column++){
				for(int row=3; row>0; row--){
					if(board[column][row]==board[column][row-1]){
						board[column][row]+=board[column][row-1];
						for(int row2=row-1; row2>0; row2--){
							board[column][row2] = board[column][row2-1];
						}
						board[column][0]=0;
						score+=board[column][row];
					}
				}
			}
		}
		
		//When the user chooses to move to the left
		if(move==4){
			//Make all the tiles to move to the left
			reverse = 3;
			for(int row=0; row<4; row++){
				for(int column=0; column<4; column++){
					rowSet[setCount] = board[column][row];
					setCount++;
				}
				setCount = 0;
				for (int count=0; count<4; count++){
					if(rowSet[count]!=0){
						board[setCount][row] = rowSet[count];
						setCount++;
					}else{
						board[reverse][row] = rowSet[count];
						reverse--;
					}
				}
				setCount = 0;
				reverse = 3;
			}
			
			//Merge tiles if they have the same value and count the score
			for(int row=0; row<4; row++){
				for(int column=0; column<3; column++){
					if(board[column][row]==board[column+1][row]){
						board[column][row]+=board[column+1][row];
						for(int column2=column+1; column2<3; column2++){
							board[column2][row] = board[column2+1][row];
						}
						board[3][row]=0;
						score+=board[column][row];
					}
				}
			}
		}
		
		//When the user chooses to move to the right
		if(move==6){
			//Make all the tiles to move to the right
			for(int row=0; row<4; row++){
				for(int column=3; column>=0; column--){
					rowSet[setCount] = board[column][row];
					setCount++;
				}
				setCount = 3;
				for (int count=0; count<4; count++){
					if(rowSet[count]!=0){
						board[setCount][row] = rowSet[count];
						setCount--;
					}else{
						board[reverse][row] = rowSet[count];
						reverse++;
					}
				}
				setCount = 0;
				reverse = 0;
			}
			
			//Merge tiles if they have the same value and count the score
			for(int row=0; row<4; row++){
				for(int column=3; column>0; column--){
					if(board[column][row]==board[column-1][row]){
						board[column][row]+=board[column-1][row];
						for(int column2=column-1; column2>0; column2--){
							board[column2][row] = board[column2-1][row];
						}
						board[0][row]=0;
						score+=board[column][row];
					}
				}
			}
		}
		
		//When the user pressed the wrong key
		if(move==0){
		}//Do nothing
		
		//Determine whether the board is changed or not by comparing the initial board with the board is diplayed after a move is made 
		for(int column=0; column<4; column++){
			for(int row=0; row<4; row++){
				if (changeCheck[column][row]!=board[column][row]){
					change = true;
					break;
				}
			}
			if(change)break;
		}
		return change;
	}
	
	//The getter for the value of a specific tile
	public int getBoard(int column, int row){
		return board[column][row];
	}
	
	//The getter for the score
	public int getScore(){
		return score;
	}
	
	//The getter for the high score
	public int getHScore(){
		return hScore;
	}
}


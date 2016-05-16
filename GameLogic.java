import java.util.Random;

public class GameLogic {
	private int currentRound;
	private int currentSquare;
	private char gameBoard[] = new char[8];
	private int scoreBoard[];
	private int SecretSquare;
	private boolean PickedSecretSquare;
	private String PrizesGiven[] = new String[2];
	private boolean CurrentPlayer;
	private QuestionBank myQuestionBank;
	private TicTacToeGui Display;
	private boolean Multiplayer;
	private boolean CorrectAnswer;
	private int roundsWonP1;
	private int roundsWonP2;
	private Thread Timer;
	private User myUserClass=new User(args[1]); 
	//A>dont know if i can add args[1] here, if yes we can leave it, if no need to 
	//document User(string in) changes to User() and i will hardcode the text file into the code and document that, regardless
	//need to document we never made new variable of user class in gamelogic class- Tyler

	public GameLogic(TicTacToeGui GUI){
		Display=GUI;
	}
	
	// Purpose: end turn of the current player (act as a wrapper method)
		private void endTurn() {
			
		}
	//Purpose: set numbers of player
	public void setMultiplayer(boolean m) {
		Multiplayer = m;
	}
	//Purpose: act as a wrapper class to start the game
	private void StartGame() {
		UpdateGameboard();//initialize the gameboard
	}
	
	//Purpose: act as a wrapper class to start the game
	private void StartRounds() {
		UpdateRounds();
	}

	// Purpose: Update Round number
	private void UpdateRounds() {
		currentRound++;
	}

	// Purpose: Set up Secret Square location randomly
	private void SecretSquareSetup() {
		Random rand;
		SecretSquare = rand.nextInt((8 - 0) + 1) + 0;
	}

	// Purpose: Get Question from question Bank
	// Changes: return type to string
	public String GetQuesiton() {
		return questionBank.GetQuestion();

	}

	// Purpose: get current round
	public int GetRoundNumber() {
		return currentRound;
	}

	// Purpose: to get current player play item on board
	// Changes: return type to char
	public char getShape() {
		if (CurrentPlayer = true)
			return 'O'; // player 1
		else
			return 'X'; // player 2
	}

	// Purpose: Get a prize from prize bank
	// Changes:Change return type to string
	public String getPrize() {
		int i = 0;
		String Prize;
		while (PrizesGiven[i] != null) {
			Prize = PrizesGiven[i];
			PrizesGiven[i] = null;
			i++;
			break;
		}
		return Prize;
	}

	// Purpose: switch current player value
	// Changes: remove parameter
	private void SwitchPlayer() {
		CurrentPlayer = !CurrentPlayer;
	}

	// Purpose: Start turn (act as a wrapper method)
	private void PlayerTurnStart() {

	}

	// Purpose: Start Timer
	private void StartTimer() {
		this.Timer=new Thread();
		Timer.start();
		Timer.sleep(100000);
		TimeUp();
	}

	//Purpose: to end the turn
	private void TimeUp() {
		endTurn();
	}

	private void KillTimer() {
		Timer.interrupt();
	}

	// Purpose: Start turn (act as a wrapper method)
	private void ContinueTurn() {
		
	}

	// Purpose: player pick square (act as a wrapper method)
	public boolean PickSquare(int square) {
		currentSquare=square;
	}

	// Purpose: to check if the square is valid to pick
	private boolean CheckSquareEmpty(int square) {
		if (gameBoard[square] == 'o' || gameBoard[square] == 'x')
			return true;
		else
			return false;
	}

	//Purpose: to update the scoreBoard with whoever won
	private void UpdateScoreBoard() {
		
	}
	
	
	//Purpose: to check the gameBoard if the round is over:return winner player icon otherwise n
	//Changes: change return type to char 
	//thoughts: might need to fill the entire board intially with 'n'
	private char RoundWonCheck() {
		if(checkWinner(0,1,2))return gameBoard[0];
		if(checkWinner(3,4,5))return gameBoard[3];
		if(checkWinner(6,7,8))return gameBoard[6];
		if(checkWinner(0,3,6))return gameBoard[0];
		if(checkWinner(1,4,7))return gameBoard[1];
		if(checkWinner(2,5,8))return gameBoard[2];
		if(checkWinner(0,4,8))return gameBoard[0];
		if(checkWinner(2,4,6))return gameBoard[2];
		return 'n';
				}
	
	//Purpose: give answer 
	private boolean AnswerQuestion(Boolean a) {
		KillTimer();
	}

	// Purpose: update the gameBoard square with the shape picked
	private void SetSquare(char shape) {
		gameBoard[currentSquare] = shape;
	}

	
	// Purpose:fill the entire board with n in the beginning
	private void UpdateGameboard() {
		for(int i=0;i<9;i++){
			gameBoard[i]='n';
		}
	}

	// Purpose: update GUI accordingly with the player picked choice (act as a
	// wrapper method)
	// Needs: In GUI, GUI.updateBoardView()  doesn't have this method not sure how the general program overflow will implement this
	private void UpdateBoardBasedOnAnswer() {
		GUI.updateBoardView(); //update GUI board display
	}

	// Purpose: check Winner of the line (will be called by RoundWoncheck)
	//changes: changed return type and parameter 
	private boolean checkWinner(int square1,int square2,int square3) {
		if(gameBoard[square1]==gameBoard[square2]){
			if(gameBoard[square2]==gameBoard[square3])return true;
			else return false;
		}
		else return false;
	}

	// Purpose: check who win the game return 1 if p1 wins, return 2 if p2 wins ,return 0 if nobody wins
	private int GameWonCheck() {
		if (roundsWonP1 == 2) {
			return 1;
		} else if (roundsWonP2 == 2) {
			return 2;
		} else {
			return 0;
		}
	}

}

// end GameLogic

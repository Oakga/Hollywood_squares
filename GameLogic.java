import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;

public class GameLogic {
	private int currentRound;
	private int currentSquare;
	private char gameBoard[];
	private int scoreBoard[];
	private int SecretSquare;
	private boolean PickedSecretSquare;
	private String PrizesGiven[] = new String[2];
	private boolean CurrentPlayer;
	private QuestionBank myQuestionBank;
	private TicTacToeGui Display;
	private boolean Multiplayer;
	private Boolean CorrectAnswer;// Changed back to String
	private int roundsWonP1;
	private int roundsWonP2;
	private Timer TimerObject; //Flipped the order of the words since it is meant to be of type Timer
	public Player player1;// new
	public Player player2;// new
	// private User myUserClass=new User(args[1]);
	// A>dont know if i can add args[1] here, if yes we can leave it, if no need
	// to
	// document User(string in) changes to User() and i will hardcode the text
	// file into the code and document that, regardless
	// need to document we never made new variable of user class in gamelogic
	// class- Tyler

	public GameLogic(TicTacToeGui GUI) {
		currentRound = 1;
		currentSquare = 1;
		gameBoard = new char[8];
		scoreBoard = new int[2];
		SecretSquareSetup();
		myQuestionBank = new QuestionBank("QuestionDB");
		Display = GUI;
	}

	// Purpose: act as a wrapper class to start the game
	//Changes make public
	public void StartGame() {
		// login
		// choose mutiplayer
		// start round
	}

	// Purpose: act as a wrapper class to start the round
	private void StartRounds() {
		
		UpdateGameboard();// initialize the gameboard
		PlayerTurnStart();
	}

	// Purpose: Start turn (act as a wrapper method)
	private void PlayerTurnStart() {
		//player pick a square
		// get question
		// celebrity answer
		//Start timer
		// return control to the gui for player to answer
	}

	// Purpose: gui call this method after player give answer
	private void ContinueTurn(String answer) {
		//end timer
		// player answer
		// make consequences according to the answer
	}

	// Purpose: end turn of the current player (act as a wrapper method)
	private void endTurn(int Rounds) {
		int playerWon=GameWonCheck();
		UpdateScoreBoard(playerWon);
		//update in the database
		UpdateRounds();
		if(Rounds>3){} //end game
	}

	// Purpose: to update the scoreBoard with whoever won
	// Changes added a parameter
	private void UpdateScoreBoard(int player) {
		scoreBoard[player]++;
	}

	// Purpose: set numbers of player
	public void setMultiplayer(boolean m) {
		Multiplayer = m;
	}

	// Purpose: Update Round number
	private void UpdateRounds() {
		currentRound++;
	}// done

	// Purpose: Set up Secret Square location randomly
	private void SecretSquareSetup() {
		Random rand = null;
		SecretSquare = rand.nextInt((8 - 0) + 1) + 0;
	}// done

	// Purpose: get current round
	public int GetRoundNumber() {
		return currentRound;
	}// done

	// Purpose: to get current player play item on board
	// Changes: return type to char
	public char getShape() {
		if (CurrentPlayer = true)
			return 'O'; // player 1
		else
			return 'X'; // player 2
	}// done

	// Purpose: Get a prize from prize bank
	// Changes:Change return type to string
	public String getPrize() {
		int i = 0;
		String Prize = null;
		while (PrizesGiven[i] != null) {
			Prize = PrizesGiven[i];
			PrizesGiven[i] = null;
			i++;
			break;
		}
		return Prize;
	}// done

	// Purpose: switch current player value
	// Changes: remove parameter
	private void SwitchPlayer() {
		CurrentPlayer = !CurrentPlayer;
	}// done

	// Purpose: Start Timer
	private void StartTimer() {
		this.Timer = new Thread();
		Timer.start();
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TimeUp();
	}// done

	// Purpose: to end the turn
	private void TimeUp() {
		int i;
		if(CurrentPlayer=true){i=1;}
		else i=2;
		endTurn(i);//end turn for player i
	}// done

	private void KillTimer() {
		Timer.interrupt();
	}// done

	// Purpose: player pick square if it is empty(act as a wrapper method)
	public boolean PickSquare(int square) {
		if (CheckSquareEmpty(square)) {
			currentSquare = square;
			return true;
		} else
			return false;
	}// done

	// Purpose: to check if the square is valid to pick
	private boolean CheckSquareEmpty(int square) {
		if (gameBoard[square] == 'o' || gameBoard[square] == 'x')
			return true;
		else
			return false;
	}// done

	// Purpose: to check the gameBoard if the round is over:return winner player
	// icon otherwise n
	// Changes: change return type to char
	// thoughts: might need to fill the entire board intially with 'n'
	private char RoundWonCheck() {
		if (checkWinner(0, 1, 2))
			return gameBoard[0];
		if (checkWinner(3, 4, 5))
			return gameBoard[3];
		if (checkWinner(6, 7, 8))
			return gameBoard[6];
		if (checkWinner(0, 3, 6))
			return gameBoard[0];
		if (checkWinner(1, 4, 7))
			return gameBoard[1];
		if (checkWinner(2, 5, 8))
			return gameBoard[2];
		if (checkWinner(0, 4, 8))
			return gameBoard[0];
		if (checkWinner(2, 4, 6))
			return gameBoard[2];
		return 'n';
	}// done

	// Purpose: give answer if parameter is boolean
	// Changes: return type to boolean
	private boolean AnswerQuestion(Boolean answer) {
		String answerInString;
		if (answer = true)
			answerInString = "agree";
		else
			answerInString = "disagree";
		KillTimer();
		if (CorrectAnswer == answerInString) {
			return true;
		} else
			return false;

	}// done

	// Purpose: Get Question from question Bank
	// Changes: return type to string
	public String GetQuesiton() {
		LinkedList QuestionString = myQuestionBank.getAquestion();
		String Question = (String) QuestionString.getFirst();
		CorrectAnswer = (String) QuestionString.getLast();
		return Question;
	}// done

	// Purpose: update the gameBoard square with the shape picked
	private void SetSquare(char shape) {
		gameBoard[currentSquare] = shape;
	}// done

	// Purpose:fill the entire board with n in the beginning
	private void UpdateGameboard() {
		for (int i = 0; i < 9; i++) {
			gameBoard[i] = 'n';
		}
	}// done

	// Purpose: update GUI accordingly with the player picked choice (act as a
	// wrapper method)
	// Needs: In GUI, GUI.updateBoardView() doesn't have this method not sure
	// how the general program overflow will implement this
	private void UpdateBoardBasedOnAnswer() {
		GUI.updateBoardView(); // update GUI board display
	}

	// Purpose: check Winner of the line (will be called by RoundWoncheck)
	// changes: changed return type and parameter
	private boolean checkWinner(int square1, int square2, int square3) {
		if (gameBoard[square1] == gameBoard[square2]) {
			if (gameBoard[square2] == gameBoard[square3])
				return true;
			else
				return false;
		} else
			return false;
	}// done

	// Purpose: check who win the game return 1 if p1 wins, return 2 if p2 wins
	// ,return 0 if nobody wins
	private int GameWonCheck() {
		if (roundsWonP1 == 2) {
			return 1;
		} else if (roundsWonP2 == 2) {
			return 2;
		} else {
			return 0;
		}
	}// done

}

// end GameLogic

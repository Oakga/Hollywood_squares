import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
	private Boolean CorrectAnswer;
	private int roundsWonP1;
	private int roundsWonP2;
	private Timer TimerObject;

	public GameLogic() {
		currentRound = 1;
		currentSquare = 1;
		gameBoard = new char[9];
		scoreBoard = new int[2];
		SecretSquareSetup();
		myQuestionBank = new QuestionBank("QuestionDB");		
	}


	void StartGame() {
		currentRound = 1;
		gameBoard = new char[9];
		scoreBoard = new int[2];
		StartRounds();
	}
	private void StartRounds() {
		UpdateGameboard();
		SecretSquareSetup();
		PlayerTurnStart();
	}


	public void PlayerTurnStart() {
		
		
		if (!Multiplayer) {
			Random rand = new Random();
			int square = rand.nextInt(8);// select one of the 9 squares from
											// 0->8
			while (!CheckSquareEmpty(square)) {
				Random rand1 = new Random();
				square = rand1.nextInt(8);// select one of the 9 squares from
											// 0->8
			}
			switch (square) {
			case (0):
				Display.topLeftButton.doClick();
				break;
			case (1):
				Display.topMiddleButton.doClick();
				break;
			case (2):
				Display.topRightButton.doClick();
				break;
			case (3):
				Display.middleLeftButton.doClick();
				break;
			case (4):
				Display.middleRightButton.doClick();
				break;
			case (5):
				Display.middleMiddleButton.doClick();
				break;
			case (6):
				Display.bottomMiddleButton.doClick();
				break;
			case (7):
				Display.bottomLeftButton.doClick();
				break;
			case (8):
				Display.bottomRightButton.doClick();
				break;
			}
		}
		Display.ToQuestionFrame();
	}

	private void ContinueTurn() {
		if (!Multiplayer) {
			Random rand = new Random();
			int answer = rand.nextInt(1); // 0 is agree 1 is disagree
			boolean response = true;
			if (answer == 1)
				response = false;
			AnswerQuestion(response);
		}
	}

	private void endTurn() {
		UpdateBoardBasedOnAnswer();
		int winner = RoundWonCheck();
		if (winner == -1) {
			SwitchPlayer(true);
			//PlayerTurnStart();
		} else {
			// winner = CurrentPlayer;
			UpdateScoreBoard();// only you can win on your turn
			int gameWinner = GameWonCheck();
			if (gameWinner != -1) {
				System.out.println("Round over");
				// Display.ToRoundOverFrame(CurrentPlayer, scoreBoard); //show
				// round stats
			} else {
				System.out.println("Game over");
				// Display.togameoverframe(scoreBoard); //show game stats
			}
		}
		// check if there is a winner or board is full
		// if a player won or it is full, check the round number and either
		// display the final score or the round score
		// otherwise switchplayers(GUI must then start the turn )
		// checkIfBoard is full

		/*
		 * int playerWon=GameWonCheck(); UpdateScoreBoard(playerWon); //update
		 * in the database UpdateRounds(); if(Rounds>3){} //end game
		 */
	}

	// Purpose: to update the scoreBoard with whoever won
	// Changes added a parameter
	private void UpdateScoreBoard() {
		int player = 0;
		if (CurrentPlayer == false)
			player = 1;
		scoreBoard[player]++;
		UpdateRounds();
	}

	// Purpose: set numbers of player
	public void setMultiplayer(boolean m) {
		Multiplayer = m;
		StartGame();
	}

	// Purpose: Update Round number
	private void UpdateRounds() {
		currentRound++;
	}// done

	// Purpose: Set up Secret Square location randomly
	private void SecretSquareSetup() {
		// THIS SHOULD ONLY BE ON SOME ROUNDS, THIS REQUIRES A CHECK
		if (!PickedSecretSquare) {
			Random rand = new Random();
			SecretSquare = rand.nextInt((8 - 0) + 1) + 0;
		}
		SecretSquare = -1;

	}//

	// Purpose: get current round
	public int GetRoundNumber() {
		return currentRound;
	}// done

	// Purpose: to get current player play item on board
	// Changes: return type to char
	public char getShape() {
		char s;
		if (CurrentPlayer = true)
			s = 'O';
		else
			s = 'X';
		return s; // player 2
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


	private void SwitchPlayer(Boolean Player) {
		CurrentPlayer = !CurrentPlayer;
	}
	
	public boolean PickSquare(int square) {
		if (CheckSquareEmpty(square)) {
			currentSquare = square;
			return true;
		}
		return false;
		// if the return of this is true then the gui should call the
		// getQuestion method, which will continue the turn as well
	}


	private boolean CheckSquareEmpty(int square) {
		if (gameBoard[square] == 'o' || gameBoard[square] == 'x')
			return true;
		else
			return false;
	}// no changes

	// return 1 if player 1 win 'O'
	// return 2 if player 2 win 'X'
	// return 0 if game is not done yet
	// return -1 if game is tie
	// return 3 when there is an error and previous cases doesn't match
	private int RoundWonCheck() {
		char winnerSign = 0;
		if (checkWinner(0, 1, 2))
			winnerSign = gameBoard[0];
		else if (checkWinner(3, 4, 5))
			winnerSign = gameBoard[3];
		else if (checkWinner(6, 7, 8))
			winnerSign = gameBoard[6];
		else if (checkWinner(0, 3, 6))
			winnerSign = gameBoard[0];
		else if (checkWinner(1, 4, 7))
			winnerSign = gameBoard[1];
		else if (checkWinner(2, 5, 8))
			winnerSign = gameBoard[2];
		else if (checkWinner(0, 4, 8))
			winnerSign = gameBoard[0];
		else if (checkWinner(2, 4, 6))
			winnerSign = gameBoard[2];

		if (winnerSign == 'O') {
			return 1;
		} else if (winnerSign == 'X') {
			return 2; 
		} else {
			for (int i = 0; i < 9; i++) {
				if (CheckSquareEmpty(i)) {
					return 0;
				}
				return -1;
			}
		}
		return 3;
	}


	public void AnswerQuestion(Boolean answer) {

		if (answer == CorrectAnswer) {
			SetSquare(currentSquare);
			if(SecretSquare== currentSquare){
				PrizesGiven[0]="A new Car";//test
			}
		} else{
			CurrentPlayer = !CurrentPlayer;
			SetSquare(currentSquare);
			if(RoundWonCheck()!=1 || RoundWonCheck()!=2){
				Display.buttonsLeft[currentSquare].setEnabled(false);
				if(SecretSquare== currentSquare){
					PrizesGiven[0]="A new Car";//test
				}
			}
			resetSquare(currentSquare);
			CurrentPlayer = !CurrentPlayer;
		}
		endTurn();
	}

	public String GetQuestion() {
		LinkedList<String> QuestionString = myQuestionBank.getAquestion();
		String Question = (String) QuestionString.getFirst();
		String correctAnswerString = (String) QuestionString.getLast();
		if (correctAnswerString == "agree") {
			CorrectAnswer = true;
		} else {
			CorrectAnswer = false;
		}
		return Question;
	}

	private void SetSquare(int squarenum) {
		if(CurrentPlayer==true) gameBoard[squarenum]='O';
		else gameBoard[squarenum]='X';
	}
	private void resetSquare(int squarenum) {
		if(CurrentPlayer==true) gameBoard[squarenum]='n';
		else gameBoard[squarenum]='n';
	}

	private void UpdateGameboard() {
		for (int i = 0; i < 9; i++) {
			gameBoard[i] = 'n';
		}
	}

	private void UpdateBoardBasedOnAnswer() {
		//Display.updateGameBoard(); //added GUI METHOD
	}

	private boolean checkWinner(int square1, int square2, int square3) {
		if (gameBoard[square1] == gameBoard[square2]) {
			if (gameBoard[square2] == gameBoard[square3])
				return true;
			else
				return false;
		} else
			return false;
	}// done

	private int GameWonCheck() {
		if (scoreBoard[0] == 2) {
			return 0;
		} else if (scoreBoard[1] == 2) {
			return 1;
		} else {
			return -1;
		}
	}

}

// end GameLogic

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
	// public boolean turn =true;

	public GameLogic() {
		currentRound = 1;
		currentSquare = 1;
		gameBoard = new char[9];
		scoreBoard = new int[2];
		SecretSquareSetup();
		String args[] = new String[1];
		args[0] = new String("QuestionDB.txt");
		myQuestionBank = new QuestionBank(args);
		Display = new TicTacToeGui(this);
		Display.setVisible(true);
	}

	private void StartGame() {
		currentRound = 1;
		gameBoard = new char[9];
		scoreBoard = new int[2];
		StartRounds();
	}

	private void StartRounds() {
		UpdateGameboard();// initialize the gameboard
		SecretSquareSetup();
		PlayerTurnStart();
	}


	public void PlayerTurnStart() {
		// Display.changeToGameMode(getShape());
		if (!Multiplayer  && !CurrentPlayer) {
			Random rand = new Random();
			int square = rand.nextInt(8);// select one of the 9 squares from
											// 0->8
			while (!CheckSquareEmpty(square)) {
				System.out.println("syck");
				Random rand1 = new Random();
				square = rand1.nextInt(8);// select one of the 9 squares from // 0->8				
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
		// a human player actually picks a square on the gui which should
		// trigger
		// get question and should also change the gui to be allow the user to
		// select agree or disagre
	}

	// Purpose: gui call this method after player give answer
	private void ContinueTurn() {
		// StartTimer();
		if (!Multiplayer && CurrentPlayer) {
			/*
			 * throws InterruptedException { Thread.sleep(400); }
			 */
			Random rand = new Random();
			int answer = rand.nextInt(1); // 0 is agree 1 is disagree
			boolean response = true;
			if (answer == 1)
				response = false;
			AnswerQuestion(response);
			// the computer randomly selects agree or diagree.- that function
			// will do checks and end turn
		// A human will click a button, or the timer will run out
		}
	}

	// Purpose: end turn of the current player (act as a wrapper method)
	private void endTurn() {
		int winner = RoundWonCheck();
		// System.out.println(winner)
		if (winner == 0) {
			SwitchPlayer(true);
			PlayerTurnStart();
		} else {
			// winner = CurrentPlayer;
			UpdateScoreBoard();// only you can win on your turn
			int gameWinner = GameWonCheck();
			if (gameWinner == -1) {
				System.out.println("Round over");
				Display.ToRoundOverFrame(CurrentPlayer, scoreBoard); //show
				StartRounds();
				// round stats
			} else {
				System.out.println("Game over");
				Display.changeToPlayGameorCheckScoreMode(scoreBoard); //show game stats
			}
		}
		UpdateBoardBasedOnAnswer();
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
		if (CurrentPlayer == true)
			s = 'X';
		else
			s = 'O';
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
		String p = "player2";
		if(CurrentPlayer==true){
			p = "player1";
		}
		System.out.println("old player is " + p );
		CurrentPlayer = !CurrentPlayer;
		p = "player2";
		if(CurrentPlayer==true){
			p = "player1";
		}
		System.out.println("Current player is " + p );
		
	}
	
	public boolean PickSquare(int square) {
		if (CheckSquareEmpty(square)) {
			currentSquare = square;
			return true;
		}
		return false;
	}
	// Purpose: Start Timer
	private void StartTimer() {
		// This function needs to be updated to use the timers properly. 
		TimerObject = new Timer();
		//offically schedule is meant to take in a timertask, but it should hopeuflly work like this, otherwise there is a lamda way to do it i presume
		// FIX THIS
		// TimerObject.schedule(TimeUp(),30*1000);// double check that this is 30 seconds

		/*this.Timer = new Thread();
		Timer.start();
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		// TimeUp();
	}// done

	// Purpose: to end the turn
	private Boolean TimeUp() {
		// this was never used in the activity diagram and was not clear in the class diagram, but i assume it is
		// meant to control what happens when the timer countdown is complete
		return AnswerQuestion(false); // answer the question saying the player disagrees
/*		int i;
		if(CurrentPlayer=true){i=1;}
		else i=2;
		endTurn(i);//end turn for player i*/
	}// done

	private void KillTimer() {
		// NEEDS TO BE REWRITTEN
		//Timer.interrupt();
		TimerObject.cancel();
		TimerObject.purge();
	}// 

	private boolean CheckSquareEmpty(int square) {
		if (gameBoard[square] == 'o' || gameBoard[square] == 'x')
			return false;
		else
			return true;	
	}// no changes

	// return 1 if player 1 win 'O'
	// return 2 if player 2 win 'X'
	// return 0 if game is not done yet
	// return -1 if game is tie
	// return 3 when there is an error and previous cases doesn't match
	private int RoundWonCheck() {
		char winnerSign = 0;
		int[] majorityCounter = new int[2];
		for (int i = 0; i < 9; i++) {
			if (gameBoard[i]=='x'){
				majorityCounter[0]+=1;
			}
			else if(gameBoard[i]=='0'){
				majorityCounter[1]+=1;
			}
		}
		if(majorityCounter[0]>=5){
			winnerSign='x';
		}
		else if(majorityCounter[1]>=5){
			winnerSign='o';
		}
		else if (checkWinner(0, 1, 2))
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

	// Purpose: give answer if parameter is boolean
	// Changes: return type to boolean
	// WE SHOULD MENTION THAT WE FORGOT THIS NODE IN THE ACTIVITY DIAGRAM, it goes before checkANSWER, 
	// ALSO IN THE ACTIVTY DIAGRAM WE HAVE A CHECKANSWER() method that doesn't seem to exist in the class diagrams, 
	// Rather that function exists only in the questionbank
	public boolean AnswerQuestion(Boolean answer) {
		// Sysetm.out.println("you answered");
		// KillTimer();
		boolean returnVal;
		/*String answerInString;
		if (answer = true)
			answerInString = "agree";
		else
			answerInString = "disagree";*/
		
		// THERE IS NO NEED TO CALL THE CHECK ANSWER OF QUESTIONBANK BECAUSE IT DOES NOT STORE THE ANSWER, SO THAT FUNCTION IS BASICALLY USELESS, 
		// WE COULD USE THE FUNCTION TO FOLLOW DOCUMENTATION, BUT THAT MEANS THAT WE WILL NEED TO UNCONVER EVERYTHING WE DID WITH CORRECTANSWER, 
		// SO THAT THE CHECK ANSWER FUNCTION CAN CONVERT IT BACK TO A BOOLEAN, WHICH WE MAY AS WELL JUST DO HERE. 
		// return myQuestionBank.checkAnswer(answerInString);

		// CORRECT ANSWWER IS BEING REPURPOSED TO STORE IF THE ANSWER THE USER CHOSE IS THE CORRECT ANSWER, AS OPPOSED TO PREVIOUSLY, 
		// WHERE IT HELD WHAT THE CORRECT ANSWER WAS.
		if (answer == CorrectAnswer) {
			//CorrectAnswer = true;
			System.out.println("right Answer");
			returnVal = true;
			SetSquare(getShape());// .charAt(0) 
			if(SecretSquare== currentSquare){
				PickedSecretSquare = true;
				PrizesGiven[0]="A new Car";
			}
			// now we need to set the square to the current players value
		} else{
			//CorrectAnswer = false;
			System.out.println("wrong Answer");
			returnVal = false;
			char tempGameBoard[] = new char[9];
			tempGameBoard = gameBoard;
			// switch the current player temporarily so the next functions work properly
			CurrentPlayer = !CurrentPlayer;
			SetSquare(getShape()); // .charAt(0)
			int currentPlayerint = 0;
			if(CurrentPlayer){
				currentPlayerint = 1;
			}
			if(RoundWonCheck()==currentPlayerint){

				gameBoard = tempGameBoard;
			}
			CurrentPlayer = !CurrentPlayer;
			// now we need to see if the opponent can get this position without winning
		}
		endTurn();
		// WE don't actually care about the return value at all, since we need something to end the turn and that function is private we are calling it from here
		return returnVal;
	}// done

	/*// Purpose: Get Question from question Bank
=======



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
				if(SecretSquare== currentSquare){
					PrizesGiven[0]="A new Car";//test
				}
			}
			resetSquare(currentSquare);
			CurrentPlayer = !CurrentPlayer;
		}
		endTurn();
	}*/

	public void GetQuesiton() {
		LinkedList<String> QuestionString = myQuestionBank.getAquestion();
		String Question = (String) QuestionString.getFirst();
		String correctAnswerString = (String) QuestionString.getLast();
		if (correctAnswerString == "agree") {
			CorrectAnswer = true;
		} else {
			CorrectAnswer = false;
		}
		Display.ToQuestionFrame(Question);
		ContinueTurn();
	}

	private void SetSquare(char c){
		gameBoard[currentSquare]=c;
	}
	/*private void SetSquare(int squarenum) {//changed
		if(CurrentPlayer==true){
			//Display.buttonsLeft[squarenum].setText("O");
			gameBoard[squarenum]='O';
		}
		else{
			gameBoard[squarenum]='X';
			//Display.buttonsLeft[squarenum].setText("X");
		}
	}
	private void resetSquare(int squarenum) {//changed
		if(CurrentPlayer==true){
			Display.buttonsLeft[squarenum].setEnabled(true);
			gameBoard[squarenum]='n';
		}
		else{
			Display.buttonsLeft[squarenum].setEnabled(true);
			gameBoard[squarenum]='n';
		}
	}*/

	private void UpdateGameboard() {
		for (int i = 0; i < 9; i++) {
			gameBoard[i] = 'n';
		}
	}

/*	// Purpose: check Winner of the line (will be called by RoundWoncheck)
	private int checkWinner() {
		// it is unclear what this was really meant to do based of class diagram2 and the activty diagram. 
		// the activity diagram uses it for something that is not its reponsibility... so we didn't end up using it really. 
		// in some ways it seems to be the exact same thing as roundWonCheck, in other ways it seems to be the same as GameWonCheck so I just never used it
		return 0;
	}*/
	private boolean checkWinner(int square1, int square2, int square3) {
		if (gameBoard[square1] == gameBoard[square2]) {
			if (gameBoard[square2] == gameBoard[square3])
				return true;
			else
				return false;
		} else
			return false;
	}// done

	private void UpdateBoardBasedOnAnswer() {
		Display.setButtons(gameBoard);
		Display.changeToGameMode(getShape());

	}

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

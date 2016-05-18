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
	private Boolean CorrectAnswer;// Changed back to Boolean from string
	private int roundsWonP1; // this is useless as we have the scoreboard
	private int roundsWonP2; // this is useless as we have the scoreboard
	private Timer TimerObject; // DOCUMENT CHANGE! -Flipped the order of the words since it is meant to be of type Timer
	// JASON: I don't believe we need the next two variables and therefore we should try and remove them
//	public Player player1;// new
//	public Player player2;// new
	// private User myUserClass=new User(args[1]);
	// A>dont know if i can add args[1] here, if yes we can leave it, if no need
	// to
	// document User(string in) changes to User() and i will hardcode the text
	// file into the code and document that, regardless
	// need to document we never made new variable of user class in gamelogic
	// class- Tyler

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
		// Display = new TicTacToeGui();
		// .setVisible(true);
	}

	// Purpose: act as a wrapper class to start the game
	//Changes make public
	public void StartGame() {
		//set rounds back to zero and refresh scoreboard
		currentRound = 1;
		gameboard = new char[9];
		scoreBoard = new int[2];

		StartRounds();
		// login
		// choose mutiplayer
		// start round
	}

	// Purpose: act as a wrapper class to start the round
	private void StartRounds() {
		UpdateGameboard();// initialize the gameboard
		SecretSquareSetup();
		PlayerTurnStart();
	}

	// Purpose: Start turn (act as a wrapper method)
	private void PlayerTurnStart() {
		Display.changeToPlayerSelect();
		if(!Multiplayer){
			throws InterruptedException {
				Thread.sleep(400);
			}
			Random rand = new Random();
			square = rand.nextInt(8);// select one of the 9 squares from 0->8
			while(!CheckSquareEmpty(square)){
				Random rand = new Random();
				square = rand.nextInt(8);// select one of the 9 squares from 0->8
			}
			switch(square){
				case (0):
					Display.topLeftButton === doClick();
					break;
				case (1):
					Display.topMiddleButton === doClick();
					break;
				case (2):
					Display.topRightButton === doClick();
					break;
				case (3):
					Display.middleLeftButton === doClick();
					break;
				case (4):
					Display.middleRightButton === doClick();
					break;
				case (5):
					Display.middleMiddleButton === doClick();
					break;
				case (6):
					Display.bottomMiddleButton === doClick();
					break;
				case (7):
					Display.bottomLeftButton === doClick();
					break;
				case (8):
					Display.bottomRightButton === doClick();
					break;
			}
		}
		//a human player actually picks a square on the gui which should trigger
		// get question and should also change the gui to be allow the user to select agree or disagre
	}

	// Purpose: gui call this method after player give answer
	private void ContinueTurn() {
		StartTimer();
		if(!Multiplayer){
			throws InterruptedException {
				Thread.sleep(400);
			}
			Random rand = new Random();
			answer = rand.nextInt(1); // 0 is agree 1 is disagree
			AnswerQuestion(answer);
			// the computer randomly selects agree or diagree.- that function will do checks and end turn
		}
		// A human will click a button, or the timer will run out
	}

	// Purpose: end turn of the current player (act as a wrapper method)
	private void endTurn() {
		UpdateBoardBasedOnAnswer();
		int winner = RoundWonCheck();
		if(winner== -1){
			switchplayers();
		}
		else{
			//winner = CurrentPlayer; 
			UpdateScoreBoard()// only you can win on your turn
			int gameWinner = GameWonCheck();
			if(gameWinner!=-1){
				Display.toroundoverframe(CurrentPlayer, scoreboard) //show round stats
			}
			else{
				Display.togameoverframe(scoreboard) //show game stats
			}
		}
		//check if there is a winner or board is full
		// if a player won or it is full, check the round number and either display the final score or the round score
		// otherwise switchplayers and startTurn 
		//checkIfBoard is full

		/*int playerWon=GameWonCheck();
		UpdateScoreBoard(playerWon);
		//update in the database
		UpdateRounds();
		if(Rounds>3){} //end game*/
	}

	// Purpose: to update the scoreBoard with whoever won
	// Changes added a parameter
	private void UpdateScoreBoard() {
		scoreBoard[CurrentPlayer]++;
		UpdateRounds();
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
		// THIS SHOULD ONLY BE ON SOME ROUNDS, THIS REQUIRES A CHECK
		Random rand = new Random();
		SecretSquare = rand.nextInt((8 - 0) + 1) + 0;
	}// 

	// Purpose: get current round
	public int GetRoundNumber() {
		return currentRound;
	}// done

	// Purpose: to get current player play item on board
	// Changes: return type to char
	public String getShape() {
		String s = new String();
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

	// Purpose: switch current player value
	// Changes: remove parameter
	private void SwitchPlayer() {
		CurrentPlayer = !CurrentPlayer;
	}// done

	// Purpose: Start Timer
	private void StartTimer() {
		// This function needs to be updated to use the timers properly. 
		TimerObject = new Timer;
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
	private void TimeUp() {
		int i;
		if(CurrentPlayer=true){i=1;}
		else i=2;
		endTurn(i);//end turn for player i
	}// done

	private void KillTimer() {
		// NEEDS TO BE REWRITTEN
		//Timer.interrupt();
	}// 

	// Purpose: player pick square if it is empty(act as a wrapper method)
	public boolean PickSquare(int square) {
		if (CheckSquareEmpty(square)) {
			currentSquare = square;
			return true;
		}
		return false;
		// if the return of this is true then the gui should call the getQuestion method, which will continue the turn as well
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
	// Jason: I believe this should be changed back to int
	private int RoundWonCheck() {
		String winnerSign = new String("n");
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

		if (winnerSign == getShape()){
			return CurrentPlayer;
		}
		else if(winnerSign != "n"){
			return ((CurrentPlayer+1)%2);
		}
		else{
			for(int i=0;i<9;i++){
				if(CheckSquareEmpty(i)){
					return -1;
					break;
			}
			return 2; // there is a tie since all pieces are non empty
		}
		
	}// done

	// Purpose: give answer if parameter is boolean
	// Changes: return type to boolean
	// WE SHOULD MENTION THAT WE FORGOT THIS NODE IN THE ACTIVITY DIAGRAM, it goes before checkANSWER, 
	// ALSO IN THE ACTIVTY DIAGRAM WE HAVE A CHECKANSWER() method that doesn't seem to exist in the class diagrams, 
	// Rather that function exists only in the questionbank
	public boolean AnswerQuestion(Boolean answer) {
		KillTimer();
		Boolean returnVal;
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
			returnVal = true;
			SetSquare(getShape().charAt(0));
			// now we need to set the square to the current players value
		} else{
			//CorrectAnswer = false;
			returnVal = false;
			char tempGameBoard[] = new char[9];
			// switch the current player temporarily so the next functions work properly
			CurrentPlayer = !CurrentPlayer;
			SetSquare(getShape().charAt(0));
			if(RoundWonCheck()==CurrentPlayer){
				gameBoard = tempGameBoard;
			}
			CurrentPlayer = !CurrentPlayer;
			// now we need to see if the opponent can get this position without winning
		}
		endTurn();
		// WE don't actually care about the return value at all, since we need something to end the turn and that function is private we are calling it from here
		return returnVal;
	}// done

	// Purpose: Get Question from question Bank
	// Changes: return type to string
	public void GetQuesiton() {
		// celebrity answer
		//Start timer
		LinkedList<String> QuestionString = myQuestionBank.getAquestion();
		String Question = (String) QuestionString.getFirst();
		String correctAnswerString = (String) QuestionString.getLast();
		if (correctAnswerString == "agree"){
			CorrectAnswer = true;
		}
		else{
			CorrectAnswer = false;
		}
		Display.ToQuestionFrame(Question); // this may requre other parameters as well
		ContinueTurn();
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
	// THIS FUNCTION WAS NEVER CLEARY SHOWED IN THE ACTIVITY DIAGRAM
	private void UpdateBoardBasedOnAnswer() {
		Display.updateBoardView(scoreboard); // update GUI board display
	}

	// Purpose: check Winner of the line (will be called by RoundWoncheck)
	private int checkWinner() {
		// it is unclear what this was really meant to do based of class diagram...
		return 0;
	}//

	// Purpose: check who win the game return 1 if p1 wins, return 2 if p2 wins
	// ,return 0 if nobody wins
	private int GameWonCheck(int u) {
		if (scoreBoard[0] == 2) {
			return 0;
		} else if (scoreBoard[1] == 2) {
			return 1;
		} else {
			return -1;
		}
	}// done

}

// end GameLogic

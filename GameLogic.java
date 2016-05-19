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
		Display = new TicTacToeGui(this);
		Display.setVisible(true);
	}

	private void StartGame() {
		PickedSecretSquare = false;
		PrizesGiven = new String[2];

		CurrentPlayer = true;
		currentRound = 1;
		currentSquare = -1;
		gameBoard = new char[9];
		scoreBoard = new int[2];
		SecretSquareSetup();
		String args[] = new String[1];
		args[0] = new String("QuestionDB.txt");
		myQuestionBank = new QuestionBank(args);

		UpdateBoardBasedOnAnswer(currentSquare);
		Display.changeToGameMode(getShape());
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
		StartTimer();
		if (!Multiplayer && !CurrentPlayer) {
			/*
			 * throws InterruptedException { Thread.sleep(400); }
			 */
			Random randAnswer = new Random();
			Random randTime = new Random();
			

			Display.questionButtonEnabler(false);
			try{
				Thread.sleep((long)(randTime.nextInt((45 - 2)+1) + 2 * 1000));
			}
			catch (InterruptedException e){

			}

			int answer = randAnswer.nextInt(1); // 0 is agree 1 is disagree
			boolean response = true;
			if (answer == 1)
				response = false;
			Display.machineMessage(AnswerQuestion(response));
			Display.questionButtonEnabler(true);
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
			UpdateBoardBasedOnAnswer(currentSquare);
			PlayerTurnStart();
		} else {
			SwitchPlayer(true);
			UpdateBoardBasedOnAnswer(currentSquare); // we want it to send the info to the gui pretending it is already the next turn
			SwitchPlayer(true);
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
		TimerTask timerup = new TimerTask() { public void run() { TimeUp(); }};
		int seconds = 5;
		TimerObject.schedule(timerup,seconds*1000);

		TimerTask updateTimer = new TimerTask() {
   			int i = 0;
   			public void run() { i++; Display.setTimer("Time Left: " + (seconds-i) + " seconds"); }
   		};
		TimerObject.schedule(updateTimer,0, 1000);

	/*	public TimerTask schedule(final Runnable r, long delay) {
		    final TimerTask task = new TimerTask() { public void run() { r.run(); }};
		    t.schedule(task, delay);
		    return task;
		}*/

		


	/*	public TimerTask schedule(final Runnable r, long delay) {
		    final 
		    t.schedule(task, delay);
		    return task;
		}
*/
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
		System.out.println("THIS WAS GENERATED BASED OFF OF A TIMER");
		 // answer the question saying the player disagrees
		boolean answerCorrect = AnswerQuestion(false);
		Display.timerRanOutMessage(answerCorrect);
		return answerCorrect;
/*		int i;
		if(CurrentPlayer=true){i=1;}
		else i=2;
		endTurn(i);//end turn for player i*/
	}// done

	private void KillTimer() {
		// NEEDS TO BE REWRITTEN
		//Timer.interrupt();
		System.out.println("TIMER IS CANCLED");
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
		if(checkWinner()==true)
		{
			if(CurrentPlayer==true){
				return 2;
			}
			else return 1;
		}
		return 0;
	}

	// Purpose: give answer if parameter is boolean
	// Changes: return type to boolean
	// WE SHOULD MENTION THAT WE FORGOT THIS NODE IN THE ACTIVITY DIAGRAM, it goes before checkANSWER, 
	// ALSO IN THE ACTIVTY DIAGRAM WE HAVE A CHECKANSWER() method that doesn't seem to exist in the class diagrams, 
	// Rather that function exists only in the questionbank
	public boolean AnswerQuestion(Boolean answer) {
		// Sysetm.out.println("you answered");
		KillTimer();
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
		if (answer.equals(CorrectAnswer) ) {
			//CorrectAnswer = true;
			System.out.println("right Answer");
			returnVal = true;
			Display.displayQuestionResult(1, CurrentPlayer);
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
			for(int i=0; i<gameBoard.length; i++){
				tempGameBoard[i] = gameBoard[i];
			}
			// switch the current player temporarily so the next functions work properly
			CurrentPlayer = !CurrentPlayer;
			SetSquare(getShape()); // .charAt(0)
			int currentPlayerint = 1;
			if(CurrentPlayer){
				currentPlayerint = 2;
			}
			if(RoundWonCheck()==currentPlayerint){
				System.out.println("player " + currentPlayerint + " would win on the other player's disagree");
				for(int i=0; i<gameBoard.length; i++){
					gameBoard[i] = tempGameBoard[i];
				}
				Display.displayQuestionResult(2, !CurrentPlayer);
			}
			else{
				Display.displayQuestionResult(3, !CurrentPlayer);
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
		if (correctAnswerString.equals("agree") ) {
			CorrectAnswer = true;
		} else {
			CorrectAnswer = false;
		}
		Display.ToQuestionFrame(Question+"\n"+correctAnswerString);
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
	private boolean checkWinner() {
			char[][] positions = new char[3][3];
			int x=0;
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					positions[i][j]=gameBoard[x];
					x++;
				}
			}
			// this checks if you have 5 pieces on the board
			int gameoverx=0;
			int gameovero=0;
			for(int i=0;i<9;i++)
			{
				if(gameBoard[i]=='X'){
					gameoverx++;
				}
				if(gameBoard[i]=='O'){
					gameovero++;
				}
			}
			if(gameovero>=5 || gameoverx>=5)return true;
			
			// this does the rows
			for(int i=0;i<3;i++){
				gameoverx=0;
				gameovero=0;
				for(int j=0;j<3;j++){
					if(positions[i][j]=='X')gameoverx++;
					if(positions[i][j]=='O')gameovero++;
					if(gameoverx==3){
						return true;	 
					}
					if(gameovero==3){
						return true;			
					}
				}
			}
			//this does the columns 
			for(int i=0;i<3;i++){
				gameoverx=0;
				gameovero=0;
				for(int j=0;j<3;j++)
				{
					if(positions[j][i]=='X')gameoverx++;
					if(positions[j][i]=='O')gameovero++;
					if(gameoverx==3)
					{
						return true;			
					}
					if(gameovero==3)
					{
						return true;			
					}
				}
			}
			// this does the top left to bottom right diagonal1 test  
			gameoverx=0;
			gameovero=0;
			for(int i=0;i<3;i++)
			{
				if(positions[i][i]=='X')gameoverx++;
				if(positions[i][i]=='O')gameovero++;
				if(gameoverx==3)
				{
					return true;	
				}							
				if(gameovero==3)
				{
					return true;		
				}
			}
						// this is the diagonal test for the top right to bottom left 
			gameoverx=0;
			gameovero=0;
			for(int i=2;i>-1;i--)
			{
				
				if(positions[2-i][i]=='X')gameoverx++;
				if(positions[2-i][i]=='O')gameovero++;
				if(gameoverx==3)
				{
					return true;			
				}
				if(gameovero==3)
				{
					return true;			
				}
			}

			return false;
	}

	private void UpdateBoardBasedOnAnswer(int currentSquare) {
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

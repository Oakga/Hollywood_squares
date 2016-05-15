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

	public GameLogic(TicTacToeGui){
		Display
		
		
	}

	public void setMultiplayer(boolean m) {
		Multiplayer = m;
	}

	private void StartGame() {

	}

	private void StartRounds() {

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

	}

	private void TimeUp() {

	}

	private void KillTimer() {

	}

	// Purpose: Start turn (act as a wrapper method)
	private void ContinueTurn() {

	}

	// Purpose: player pick square (act as a wrapper method)
	public boolean PickSquare(int square) {

	}

	// Purpose: to check if the square is valid to pick
	private boolean CheckSquareEmpty(int square) {
		if (gameBoard[square] == 'o' || gameBoard[square] == 'x')
			return true;
		else
			return false;
	}

	private void UpdateScoreBoard() {

	}
	//
	private int RoundWonCheck() {
		if(gameBoard[0]=gameBoard[1]=gameBoard[2]||	//across top
				   gameBoard[3]=gameBoard[4]=gameBoard[5]||	//across middle
				   gameBoard[6]=gameBoard[7]=gameBoard[8]||	//across bottom
				   gameBoard[0]=gameBoard[3]=gameBoard[6]||	//down left
				   gameBoard[1]=gameBoard[4]=gameBoard[7]||	//down middle
				   gameBoard[2]=gameBoard[5]=gameBoard[8]||	//down right
				   gameBoard[0]=gameBoard[4]=gameBoard[8]||	//diagonal L>R
				   gameBoard[2]=gameBoard[4]=gameBoard[6]){	//diagonal R>L
				   	return true;
				   }//if
				   else{
				   	return false;
				   }//else
				}//RoundWonCheck
	

	private boolean AnswerQuestion(Boolean a) {

	}

	// Purpose: update the gameBoard square with the shape picked
	private void SetSquare(char shape) {
		gameBoard[currentSquare] = shape;
	}

	// Purpose: Unnecessary and might be removing this in future changes
	// Changes: might remove this method
	private void UpdateGameboard() {

	}

	// Purpose: update GUI accordingly with the player picked choice (act as a
	// wrapper method)
	// Needs: In GUI, GUI.updateBoardView()  doesn't have this method not sure how the general program overflow will implement this
	private void UpdateBoardBasedOnAnswer() {
		GUI.updateBoardView(); //update GUI board display

	}

	// Purpose: end turn of the current player (act as a wrapper method)
	private void endTurn() {

	}

	// Purpose: check Winner
	private int checkWinner() {

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

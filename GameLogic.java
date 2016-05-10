public class GameLogic{
	private int currentRound
	private int currentSquare
	private Char gameBoard[]
	private int scoreBoard[2]
	private int SecretSquare
	private boolean PickedSecretSquare
	private String PrizesGiven[]
	private boolean CurrentPlayer
	private QuestionBank myQuestionBank
	private TicTacToeGui Display
	private boolean Multiplayer
	private boolean CorrectAnswer
	private int roundsWonP1
	private int roundsWonP2
	private TimerObject Timer
	private User myUserClass=new User(args[1]); 
	//A>dont know if i can add args[1] here, if yes we can leave it, if no need to 
	//document User(string in) changes to User() and i will hardcode the text file into the code and document that, regardless
	//need to document we never made new variable of user class in gamelogic class- Tyler
	public GameLogic(){

	}

	public setMultiplayer(boolean m){

	}

	// 99% SURE THIS MUST BE CHANGED TO PUBLIC TO WORK
	private StartGame(){

	}

	private StartRounds(){

	}

	private UpdateRounds(){

	}

	private SecretSquareSetup(){

	}
	// THIS IS CLEARLY A PROBLEM SINCE IT IS RETURN TYPE VOID FOR A GETTER
	public GetQuesiton(){

	}

	public int GetRoundNumber(){

	}

	public String getShape(){

	}
	// THIS IS CLEARLY A PROBLEM SINCE IT IS RETURN TYPE VOID FOR A GETTER
	public getPrize(){

	}

	private SwitchPlayer(Player: boolean){

	}

	private PlayerTurnStart(){

	}

	private StartTimer(){

	}

	private TimeUp(){

	}

	private KillTimer(){

	}

	private ContinueTurn(){

	}

	public boolean PickSquare(int square){

	}
	
	private boolean CheckSquareEmpty(int square){
	if(gameBoard[square]=='x'||gameBoard[square]=='o'){
		return false;
	}//if
	else{
		return true;
	}//else
	}//CheckSquareEmpty

	private UpdateScoreBoard(){

	}

	private int RoundWonCheck(){
	if(gameBoard[0]==gameBoard[1]==gameBoard[2]||	//across top
	   gameBoard[3]==gameBoard[4]==gameBoard[5]||	//across middle
	   gameBoard[6]==gameBoard[7]==gameBoard[8]||	//across bottom
	   gameBoard[0]==gameBoard[3]==gameBoard[6]||	//down left
	   gameBoard[1]==gameBoard[4]==gameBoard[7]||	//down middle
	   gameBoard[2]==gameBoard[5]==gameBoard[8]||	//down right
	   gameBoard[0]==gameBoard[4]==gameBoard[8]||	//diagonal L>R
	   gameBoard[2]==gameBoard[4]==gameBoard[6]){	//diagonal R>L
	   	return true;
	   }//if
	   else{
	   	return false;
	   }//else
	}//RoundWonCheck

	private boolean AnswerQuestion(Boolean a){

	}

	private SetSquare(char c){

	}

	private UpdateGameboard(int CurrentSquare){

	}

	private UpdateBoardBasedOnAnswer(){

	}

	private endTurn(){

	}

	private int checkWinner(){
	
	}
	//not sure what int i is but id assume this is how its supposed to look- Tyler
	private int GameWonCheck(int i){
	if(roundsWonP1==2){
		return 1;
	}//if
	else if (roundsWonP1==2){
		return 1;
	}//else if
	else{
		return -1;
	}//else
	}

	




}// end GameLogic


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicTacToeGui GUI = null;
		GameLogic game=new GameLogic(GUI);
		GUI=new TicTacToeGui(game);
		game.StartGame();
	}

}

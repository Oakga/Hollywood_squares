<<<<<<< HEAD
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicTacToeGui GUI = null;
		GameLogic game=new GameLogic(GUI);
		GUI=new TicTacToeGui(game);
		game.StartGame();
=======
public class Main{
  	public static void main(String[] args)
	{
	GameLogic y = new GameLogic();
	TicTacToeGui x = new TicTacToeGui(y);
	x.setVisible(true);
>>>>>>> 2771762049edde73d3ea7ecd9e0761ccab76d406
	}
}

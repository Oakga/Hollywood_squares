public class Main{
  	public static void main(String[] args)
	{
	GameLogic y = new GameLogic();
	TicTacToeGui x = new TicTacToeGui(y);
	x.setVisible(true);
	}
}

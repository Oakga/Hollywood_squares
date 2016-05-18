import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


/*
 * I'm 95% percent sure we dont need any of these variables but im keeping them here for now because they are in the documentation we had 
 * 
 * also, I changed the arguments for the methods because we are going to do different things depending on the state of the game, and we need the GameLogic to do those checks
 * To make those checks work, who ever knows the GameLogic, please put it into the actionLisnter method for each button that does something
 * 
 * VERY IMPORTNAT: the agree button on the question frame will automatically end the round and send you to the round over frame, this was so we could test that frame,
 * please change it back once we have both the agree and disagree button working
 * 
 * 
 * the create account button as of now does not do ANYTHING, but it needs to save whats in the username and password text field ( use .getText() on both those JTextfields ) 
 * and create a user. ( you can also use the JOption pane to tell the user the account was made)
 * 
 * the login button doesn't check for an account it will automatically send you to a blank stats frame, so use the GameLogic to check for a user and then update the stats frame to 
 * show that users stats
 * 
 * the game board buttons only turn off, but dont display X's or O's, once we know a winner, someone add either buttonleft[i].setText("X") or "O". 
 * the buttons also need to be re-enabled if the user gets the answer wrong and the other player does not get the square
 * 
 * 
 * the single player and two player buttons both just send you to the game board frame, so somebody update the GameLogic, so we know what button was pressed.
 * 
 * also the board only says "player x please go", the game logic should be checked so we know who's turn to say it is ( its a simple if statement)
 * 
 * the timer needs to be updated, because right now its blank. ( game logic should just use the .updateText() method every second to it will count down from 60) 
 * 
 * if the timer if still going it should be able to disagree and go back to the game board
 * 
 * thats all folks.
 * 
 * with love, elliot and michael 
 */
@SuppressWarnings("serial")
public class TicTacToeGui extends JFrame  {
	
	private JLabel gamesWon;
	private JLabel gamesPlayed;
	private JLabel turnMessage;
	private JButton exitButton;
	private JTextField loginTextField;
	private JButton topLeftButton;
	private JButton topMiddleButton;
	private JButton topRightButton;
	private JButton middleLeftButton;
	private JButton middleRightButton;
	private JButton middleMiddleButton;
	private JButton bottomMiddleButton;
	private JButton bottomLeftButton;
	private JButton bottomRightButton;
	private JButton agreeButton;
	private JButton disagreeButton;
	private JButton PVPButton;
	private JButton PVEButton;
	private JLabel timerMessage;
	private JLabel questionMessage;
	private JButton checkScoreButton;
	
	/*
	 * these five items are extra, we will need too add them to the documentation 
	 */
	private GameLogic gl;
	private JPanel masterPane;
	public JButton[] buttonsLeft = new JButton[9];
	private CardLayout mainFrame;
	private User loginAccount;
	private String userName;
	
	
	public TicTacToeGui(GameLogic gameLogic)
	{	
		gl= new GameLogic();
		try {
			loginAccount = new User("UserDB.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mainFrame= new CardLayout(0,0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 336);
	    masterPane = new JPanel();
		masterPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		masterPane.setLayout(mainFrame);
		setContentPane(masterPane);
		topLeftButton = new JButton("");
		topMiddleButton= new JButton("");
		topRightButton= new JButton("");
		middleLeftButton= new JButton("");
		middleRightButton= new JButton("");
		middleMiddleButton= new JButton("");
		bottomMiddleButton= new JButton("");
		bottomLeftButton= new JButton("");
		bottomRightButton= new JButton("");
		buttonsLeft[0] = topLeftButton;
		buttonsLeft[1] = topMiddleButton;
		buttonsLeft[2] = topRightButton;
		buttonsLeft[3] = middleLeftButton;
		buttonsLeft[4] = middleRightButton;
		buttonsLeft[5] = middleMiddleButton;
		buttonsLeft[6] = bottomMiddleButton;
		buttonsLeft[7] = bottomLeftButton;
		buttonsLeft[8] = bottomRightButton;
		changeToLoginScreen();
		setVisible(true);
	}
	
	public void changeToGameMode()
	{
		JPanel gameModePanel = new JPanel();
		gameModePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		gameModePanel.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(5, 30, 424, 238);
		gameModePanel.add(panel);
		panel.setLayout(new GridLayout(3,3));
		for(int i =0; i<9; i++)
		{
			panel.add(buttonsLeft[i]);
		}
		    topLeftButton.addActionListener(e ->{
		    	topLeftButton.setEnabled(false);
		    	gl.PickSquare(0);
		    	ToQuestionFrame();
		    });
			topMiddleButton.addActionListener(e ->{
				topMiddleButton.setEnabled(false);
				gl.PickSquare(1);
				ToQuestionFrame();
		    });
			topRightButton.addActionListener(e ->{
				gl.PickSquare(2);
				topRightButton.setEnabled(false);
				ToQuestionFrame();
		    });
			middleLeftButton.addActionListener(e ->{
				gl.PickSquare(3);
				middleLeftButton.setEnabled(false);
				ToQuestionFrame();
		    });
			middleMiddleButton.addActionListener(e ->{
				gl.PickSquare(4);
				middleMiddleButton.setEnabled(false);
				ToQuestionFrame();
		    });
			middleRightButton.addActionListener(e ->{
				gl.PickSquare(5);
				middleRightButton.setEnabled(false);
				ToQuestionFrame();
		    });
			bottomLeftButton.addActionListener(e ->{
				gl.PickSquare(6);
				bottomLeftButton.setEnabled(false);
				ToQuestionFrame();
		    });
			bottomMiddleButton.addActionListener(e ->{
				gl.PickSquare(7);
				bottomMiddleButton.setEnabled(false);
				ToQuestionFrame();
		    });
			bottomRightButton.addActionListener(e ->{
				gl.PickSquare(8);
				bottomRightButton.setEnabled(false);
				ToQuestionFrame();
		    });
		// this should be changeable to whatever player went last, so well need to keep track of that at some point
		 turnMessage = new JLabel("Player X please go");
		 turnMessage.setBounds(5, 5, 424, 14);
		gameModePanel.add(turnMessage);
		gameModePanel.add(panel);
		masterPane.add(gameModePanel);
		mainFrame.addLayoutComponent(gameModePanel, "gameModePanel");
		mainFrame.show(masterPane, "gameModePanel");
	}
	
    public void changeToPlayerSelect()
	{
		JPanel playerSelectPanel = new JPanel();
		playerSelectPanel.setLayout(null);
		
		 PVPButton = new JButton("Single Player");
		 PVPButton.addActionListener(e ->{
			gl.StartGame();
			gl.setMultiplayer(false);
			changeToGameMode();
		});
		 PVPButton.setBounds(10, 101, 121, 53);
		playerSelectPanel.add(PVPButton);
		
		 PVEButton = new JButton("Two Players");
		 PVEButton.setBounds(313, 101, 121, 53);
		 PVEButton.addActionListener(e ->{
			 	gl.StartGame();
				gl.setMultiplayer(true);
			changeToGameMode();
		});
		playerSelectPanel.add(PVEButton);
		
		JLabel lblNewLabel = new JLabel("Choose what game mode you want");
		lblNewLabel.setBounds(134, 22, 250, 79);
		playerSelectPanel.add(lblNewLabel);
		masterPane.add(playerSelectPanel);
		mainFrame.addLayoutComponent(playerSelectPanel, "playerSelectPanel");
		mainFrame.show(masterPane, "playerSelectPanel");
	}
	
	

	public void ToQuestionFrame()
	{
		JPanel questionPanel = new JPanel();
		questionPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		questionPanel.setLayout(new BorderLayout(0, 0));
		
		 timerMessage = new JLabel("TIMER");
		 timerMessage.setFont(new Font("Tahoma", Font.BOLD, 21));
		questionPanel.add(timerMessage, BorderLayout.NORTH);
		
		disagreeButton = new JButton("Disagree");
		questionPanel.add(disagreeButton, BorderLayout.WEST);
		
		 agreeButton = new JButton("  Agree ");
		questionPanel.add(agreeButton, BorderLayout.EAST);

		String Question = gl.GetQuesiton();
		JTextArea questionPlace = new JTextArea();
		//questionMessage = new JLabel(Question);
		questionPlace.setText(Question);
		questionPlace.setLineWrap(true);
		questionPlace.setToolTipText("");
		questionPlace.setForeground(Color.BLACK);
		questionPlace.setBackground(Color.WHITE);
		questionPanel.add(questionPlace, BorderLayout.CENTER);
		
		disagreeButton.addActionListener(e ->{
			changeToGameMode();
		});
		agreeButton.addActionListener(e ->{
			//changeToGameMode( gl,questionPanel);
			ToRoundOverFrame();
		});
		masterPane.add(questionPanel);
		mainFrame.addLayoutComponent(questionPanel, "questionPanel");
		mainFrame.show(masterPane, "questionPanel");
		
	}
	public void ToRoundOverFrame()
	{
		JPanel roundOverPanel = new JPanel();
		roundOverPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		roundOverPanel.setLayout(null);
		
		// need to check the gl to see what message to print out and what kind of frame this is going to be
		JLabel lblNewLabel = new JLabel("Says who just won the latest round and if that player also won the whole game");
		lblNewLabel.setBounds(10, 24, 414, 35);
		roundOverPanel.add(lblNewLabel);
		
		JButton contineButton = new JButton("continue");
		contineButton.setBounds(335, 194, 89, 57);
		roundOverPanel.add(contineButton);
		// there are going to be two action listeners one to continue, and one to play again
		contineButton.addActionListener(e ->{
			for(int i=0; i<9; i++){
				buttonsLeft[i].setEnabled(true);
				buttonsLeft[i].setText("");
			}
			changeToPlayGameorCheckScoreMode();
		});
	
		exitButton.setBounds(10, 194, 89, 57);
		roundOverPanel.add(exitButton);
		// need namelogic check here to see what kind of message gets printed
		JLabel lblNewLabel_1 = new JLabel("Says who won the secret square,\r\n or says nobody won it");
		lblNewLabel_1.setBounds(10, 82, 414, 57);
		roundOverPanel.add(lblNewLabel_1);
		masterPane.add(roundOverPanel);
		mainFrame.addLayoutComponent(roundOverPanel, "roundOverPanel");
		mainFrame.show(masterPane, "roundOverPanel");
	}
	
	public void changeToLoginScreen(){
		JPanel loginPanel = new JPanel();
		loginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		loginPanel.setLayout(null);
		
		exitButton = new JButton("EXIT");
		exitButton.addActionListener(e ->{
			System.exit(0);
		});
		
		JTextField textField = new JTextField();
		textField.setBounds(10, 47, 414, 20);
		loginPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(10, 22, 81, 14);
		loginPanel.add(lblUserName);
		
		JButton btnNewButton = new JButton("Create Account");
		btnNewButton.setBounds(10, 196, 130, 55);
		loginPanel.add(btnNewButton);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(317, 196, 107, 55);
		loginPanel.add(btnLogin);
		
		// this is the action listener for the login button, somebody please link this to the login checks
		btnLogin.addActionListener(e ->
		{
			userName = textField.getText();
			if(loginAccount.CheckUser(1,userName)==true)
			{
				ToStatsFrame();
			}
			else{
				JOptionPane.showMessageDialog(null, "User not found");
			}
		});
		
		// this is the action listener for the create account button, somebody please add in the functionality to create a new account
		btnNewButton.addActionListener(e ->
		{
			userName = textField.getText();
			if(loginAccount.CheckUser(1,userName)==true)
			{
				JOptionPane.showMessageDialog(null, "Account already exists");
				try {
					loginAccount = new User("UserDB.txt");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(userName.isEmpty()==true){
				JOptionPane.showMessageDialog(null, "requires user name");
				try {
					loginAccount = new User("UserDB.txt");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(userName.isEmpty()!=true){
				
				loginAccount.makeNewUser(userName);
				try {
					loginAccount.updateDB();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Account "+userName+" has been made");
				try {
					loginAccount = new User("UserDB.txt");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		masterPane.add(loginPanel);
		mainFrame.addLayoutComponent(loginPanel, "loginPanel");
		mainFrame.show(masterPane, "loginPanel");
	}
	public void updateBoardView(){
		// the filler method
	}
	
	public void setTimer(String x){
		timerMessage.setText(x);
	}
	public void changeToPlayGameorCheckScoreMode(){
		JPanel PGOCSM_Panel = new JPanel();
		PGOCSM_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		PGOCSM_Panel.setLayout(null);
		
		JButton playAgainButton = new JButton("Play Again?");
		playAgainButton.addActionListener(e -> {
				changeToPlayerSelect();			
	
		});
		playAgainButton.setBounds(303, 172, 121, 79);
		PGOCSM_Panel.add(playAgainButton);
		
		JLabel lblNewLabel = new JLabel("says who won the latest game");
		lblNewLabel.setBounds(134, 22, 184, 79);
		PGOCSM_Panel.add(lblNewLabel);
		
		JButton toStatsButton = new JButton("Check Stats");
		toStatsButton.setBounds(10, 172, 121, 79);
		PGOCSM_Panel.add(toStatsButton);
		toStatsButton.addActionListener(e ->{
			ToStatsFrame();
		});
		masterPane.add(PGOCSM_Panel);
		mainFrame.addLayoutComponent(PGOCSM_Panel, "PGOCSM_Panel");
		mainFrame.show(masterPane, "PGOCSM_Panel");
	}
	public void ToStatsFrame()
	{
		JPanel statsPanel = new JPanel();
		statsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		statsPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Wins:");
		lblNewLabel.setBounds(10, 45, 89, 14);
		statsPanel.add(lblNewLabel);
		
		int winNumber = loginAccount.P1GP;
		int totalGames = loginAccount.P1GW;
		int losses = totalGames-winNumber;
		gamesWon = new JLabel(Integer.toString(winNumber));
		gamesWon.setBounds(258, 45, 166, 14);
		statsPanel.add(gamesWon);
		
		JLabel lblNewLabel_2 = new JLabel("User name");
		lblNewLabel_2.setBounds(10, 11, 414, 14);
		statsPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Loses:");
		lblNewLabel_3.setBounds(10, 70, 89, 14);
		statsPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(Integer.toString(losses));
		lblNewLabel_4.setBounds(258, 70, 166, 14);
		statsPanel.add(lblNewLabel_4);
		
	    gamesPlayed = new JLabel("Total games played");
	    gamesPlayed.setBounds(10, 95, 188, 14);
	    statsPanel.add(gamesPlayed);
		
		JLabel lblNewLabel_6 = new JLabel(Integer.toString(totalGames));
		lblNewLabel_6.setBounds(258, 95, 166, 14);
		statsPanel.add(lblNewLabel_6);
		
		//JButton btnNewButton = new JButton("EXIT");
		exitButton.setBounds(335, 184, 99, 78);
		statsPanel.add(exitButton);
		
		JButton btnNewButton_1 = new JButton("Play Game");
		btnNewButton_1.setBounds(0, 184, 99, 78);
		statsPanel.add(btnNewButton_1);
		statsPanel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(e ->
		{
			changeToPlayerSelect();
		});
		masterPane.add(statsPanel);
		mainFrame.addLayoutComponent(statsPanel, "statsPanel");
		mainFrame.show(masterPane,"statsPanel" );
		
	}
	
}


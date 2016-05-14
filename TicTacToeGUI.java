import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


/*
 * I'm 95% percent sure we dont need any of these variables but im keeping them here for now because they are in the documentation we had 
 * 
 * 
 */
@SuppressWarnings("serial")
public class TicTacToeGui extends JFrame  {
	private JLabel gamesWon;
	private JLabel gamesPlayed;
	private JLabel turnMessage;
	private JLabel timerMessage;
	private JLabel questionMessage;
	private JButton exitButton;
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
	private JTextField loginTextField;
	
	// I change the arguments of this method, please add to the documentation
	public void changeToGameMode( JButton buttonsLeft[], JFrame lastBoard)
	{
		JFrame lastFrame = lastBoard;
		lastFrame.setVisible(false);
		lastFrame.setEnabled(false);
		lastFrame.dispose();
		lastFrame.removeAll();
		JFrame TTTBoard = new JFrame();
		TTTBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TTTBoard.setBounds(100, 100, 450, 317);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		TTTBoard.setResizable(false);
		JPanel panel = new JPanel();
		panel.setBounds(5, 30, 424, 238);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(3,3));
		for(int i =0; i<9; i++)
		{
			panel.add(buttonsLeft[i]);
		}
		    topLeftButton.addActionListener(e ->{
		    	topLeftButton.setEnabled(false);
		    	ToQuestionFrame(TTTBoard,buttonsLeft,0);
		    });
			topMiddleButton.addActionListener(e ->{
				topMiddleButton.setEnabled(false);
		    	ToQuestionFrame(TTTBoard,buttonsLeft,1);
		    });
			topRightButton.addActionListener(e ->{
				topRightButton.setEnabled(false);
		    	ToQuestionFrame(TTTBoard,buttonsLeft,2);
		    });
			middleLeftButton.addActionListener(e ->{
				middleLeftButton.setEnabled(false);
		    	ToQuestionFrame(TTTBoard,buttonsLeft,3);
		    });
			middleRightButton.addActionListener(e ->{
				middleRightButton.setEnabled(false);
		    	ToQuestionFrame(TTTBoard,buttonsLeft,4);
		    });
			middleMiddleButton.addActionListener(e ->{
				middleMiddleButton.setEnabled(false);
		    	ToQuestionFrame(TTTBoard,buttonsLeft,5);
		    });
			bottomMiddleButton.addActionListener(e ->{
				bottomMiddleButton.setEnabled(false);
		    	ToQuestionFrame(TTTBoard,buttonsLeft,6);
		    });
			bottomLeftButton.addActionListener(e ->{
				bottomLeftButton.setEnabled(false);
		    	ToQuestionFrame(TTTBoard,buttonsLeft,7);
		    });
			bottomRightButton.addActionListener(e ->{
				bottomRightButton.setEnabled(false);
		    	ToQuestionFrame(TTTBoard,buttonsLeft,8);
		    });
		// this should be changeable to whatever player went last, so well need to keep track of that at some point
		JLabel lblNewLabel = new JLabel("Player X please go");
		lblNewLabel.setBounds(5, 5, 424, 14);
		contentPane.add(lblNewLabel);
		contentPane.add(panel);
		TTTBoard.add(contentPane);
		TTTBoard.setVisible(true);
	}
	// I changed the input of this method----------- add that to the documentation
	public void ToQuestionFrame(JFrame lastBoard, JButton[] buttonsLeft, int buttonPressed)
	{
		JFrame lastFrame = lastBoard;
		lastFrame.removeAll();
		lastFrame.dispose();
		lastFrame.setVisible(false);
		lastFrame.setEnabled(false);
		JFrame QuestionFrame = new JFrame();
		QuestionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		QuestionFrame.setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("TIMER");
		label.setFont(new Font("Tahoma", Font.BOLD, 21));
		contentPane.add(label, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Disagree");
		contentPane.add(btnNewButton, BorderLayout.WEST);
		
		JButton btnNewButton_1 = new JButton("  Agree ");
		contentPane.add(btnNewButton_1, BorderLayout.EAST);
		
		JLabel lblQuestiom = new JLabel("               Question");
		lblQuestiom.setToolTipText("");
		lblQuestiom.setForeground(Color.BLACK);
		lblQuestiom.setBackground(Color.WHITE);
		contentPane.add(lblQuestiom, BorderLayout.CENTER);
		QuestionFrame.add(contentPane);
		
		btnNewButton_1.addActionListener(e ->{
			
			changeToGameMode( buttonsLeft,QuestionFrame);
			lastFrame.setEnabled(false);
			lastFrame.dispose();
			lastFrame.removeAll();
			lastFrame.setVisible(false);
		});
		btnNewButton.addActionListener(e ->{
			changeToGameMode( buttonsLeft,QuestionFrame);
			lastFrame.setEnabled(false);
			lastFrame.dispose();
			lastFrame.removeAll();
			lastFrame.setVisible(false);
		});
		QuestionFrame.setVisible(true);
		
	}
	public void ToRoundOverFrame(){
		
	}
	public void ToStatsFrame()
	{
		setVisible(false);
		JFrame StatsFrame = new JFrame();
		StatsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		StatsFrame.setBounds(100, 100, 450, 300);
	
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Wins:");
		lblNewLabel.setBounds(10, 45, 89, 14);
		contentPane.add(lblNewLabel);
		
		 gamesWon = new JLabel("Number of wins");
		 gamesWon.setBounds(258, 45, 166, 14);
		contentPane.add(gamesWon);
		
		JLabel lblNewLabel_2 = new JLabel("User name");
		lblNewLabel_2.setBounds(10, 11, 414, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Loses:");
		lblNewLabel_3.setBounds(10, 70, 89, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Number of loses");
		lblNewLabel_4.setBounds(258, 70, 166, 14);
		contentPane.add(lblNewLabel_4);
		
	    gamesPlayed = new JLabel("Total games played");
	    gamesPlayed.setBounds(10, 95, 188, 14);
		contentPane.add(gamesPlayed);
		
		JLabel lblNewLabel_6 = new JLabel("Number of games played");
		lblNewLabel_6.setBounds(258, 95, 166, 14);
		contentPane.add(lblNewLabel_6);
		
		//JButton btnNewButton = new JButton("EXIT");
		exitButton.setBounds(335, 184, 99, 78);
		contentPane.add(exitButton);
		
		
		JButton btnNewButton_1 = new JButton("Play Game");
		btnNewButton_1.setBounds(0, 184, 99, 78);
		contentPane.add(btnNewButton_1);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(e ->
		{
			JButton buttonsLeft[] = new JButton[9];
			topLeftButton = new JButton("New button");
			topMiddleButton= new JButton("New button");
			topRightButton= new JButton("New button");
			middleLeftButton= new JButton("New button");
			middleRightButton= new JButton("New button");
			middleMiddleButton= new JButton("New button");
			bottomMiddleButton= new JButton("New button");
			bottomLeftButton= new JButton("New button");
			bottomRightButton= new JButton("New button");
			buttonsLeft[0] = topLeftButton;
			buttonsLeft[1] = topMiddleButton;
			buttonsLeft[2] = topRightButton;
			buttonsLeft[3] = middleLeftButton;
			buttonsLeft[4] = middleRightButton;
			buttonsLeft[5] = middleMiddleButton;
			buttonsLeft[6] = bottomMiddleButton;
			buttonsLeft[7] = bottomLeftButton;
			buttonsLeft[8] = bottomRightButton;
			changeToGameMode(buttonsLeft,StatsFrame);
		});
		
		StatsFrame.add(contentPane);
		StatsFrame.setVisible(true);
	}
	public TicTacToeGui(GameLogic gl)
	{	
		exitButton = new JButton("EXIT");
		exitButton.addActionListener(e ->
		{
			System.exit(0);
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextField textField = new JTextField();
		textField.setBounds(10, 47, 414, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(10, 22, 81, 14);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 78, 81, 14);
		contentPane.add(lblPassword);
		
		loginTextField = new JTextField();
		loginTextField.setColumns(10);
		loginTextField.setBounds(10, 103, 414, 20);
		contentPane.add(loginTextField);
		
		JButton btnNewButton = new JButton("Create Account");
		btnNewButton.setBounds(10, 196, 130, 55);
		contentPane.add(btnNewButton);
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(317, 196, 107, 55);
		contentPane.add(btnLogin);
		
		// this is the action listener for the login button, somebody please link this to the login checks
		btnLogin.addActionListener(e ->
		{
			ToStatsFrame();
		});
		
		// this is the action listener for the create account button, somebody please add in the functionality to create a new account
		btnNewButton.addActionListener(e ->
		{
			JOptionPane.showMessageDialog(null, "This does nothing for now, but will create an account later and message you about it");
		});
	}
}


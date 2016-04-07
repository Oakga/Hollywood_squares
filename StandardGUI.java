import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.*;

import java.awt.*;

public class StandardGUI extends JFrame{
   JFrame frame= new JFrame();
   //Container pane;
   //TextArea myTextArea, myTextArea2;
   /**
   * method standardGUI
   *
   * sets name,calls initialize, sets text areas and visible
   */
   
   public StandardGUI() {
      super("Game");
      initialize();
      //pane= getContentPane();
      //myTextArea = new TextArea();
      //myTextArea2 = new TextArea();
      pack();
      setVisible(true);
   }
   
   /**
   * method initialize
   *
   * sets standard things in gui
   */
   public void initialize(){
      // setSize(1000,1000);
      // setLocation(500,500);
      
      final String opening="Welcome to the Computer Science Department. "
              + "\n" +"Please select an option from the File Tab";
      
      setDefaultCloseOperation(StandardGUI.EXIT_ON_CLOSE);
      JPanel front = new JPanel();
      getContentPane().add(front);
      front.add(new JTextArea(opening,6,10));
      
      //setLayout (new GridLayout(1,2));
      createFileMenu();
      pack();
      setVisible(true);
   }
   
   /**
   * method creatFileMenu
   * adds menubar with file that has open quit and save
   */
   private void createFileMenu( ) {
      JMenuItem   item;
      JMenuBar    menuBar  = new JMenuBar();
      JMenu       fileMenu = new JMenu("File");
      FileMenuHandler fmh  = new FileMenuHandler(this);
      
      item= new JMenuItem("New Game");
      item .addActionListener(fmh);
      fileMenu.add(item,0);
      
      item = new JMenuItem("Open");    //Open...
      item.addActionListener( fmh );
      fileMenu.add( item ,1);
      
      fileMenu.addSeparator();           //add a horizontal separator line
      
      item = new JMenuItem("Quit");       //Quit
      item.addActionListener( fmh );
      fileMenu.add( item );
      setJMenuBar(menuBar);
      menuBar.add(fileMenu);
    
             
      item= new JMenuItem("Save");
      item.addActionListener(fmh);
      fileMenu.add(item,2);
      menuBar.add(fileMenu);
      setJMenuBar(menuBar);
   }

}

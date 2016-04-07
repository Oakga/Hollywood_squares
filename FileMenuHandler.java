import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileMenuHandler implements ActionListener {
   static JFrame jframe;
   //creation of static variables to be used throughout whole class file
   

   static String Name;
   static JTextField tField;
 //  static String[] progress=new String[5];
   static Double gpa = 4.0;
   static int[] progress=new int[5];
   static JLabel jlabel;
   static JPanel tfPanel;


   public FileMenuHandler (JFrame jf) {
      jframe = jf;
   }
   
   public FileMenuHandler() {
	// TODO Auto-generated constructor stub
}

/**
   * tests which menuName was clicked, calls on method
   *
   *  actionPerformed method
   *
   *  @param event menuName that user clicked on
   */
   public void actionPerformed(ActionEvent event) {
      String  menuName;
      menuName = event.getActionCommand();
      if (menuName.equals("Open"))
        try {
            openFile( );
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    else if (menuName.equals("Quit"))
         System.exit(0);
      else if (menuName.equals("Save"))
        try {
            Save();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    else if (menuName.equals("New Game"))
          NewGame();
   } //actionPerformed
   
   private static void NewGame() {    

       //JPanel tfPanel = new JPanel();
       //tfPanel.setLayout(new BorderLayout());
    JPanel tfPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    
       jlabel = new JLabel("Enter Character Name");
       
    JButton submitButton= new JButton("Submit");
    submitButton.setEnabled(true);
    
    tField = new JTextField(10);
      
    gbc.gridx= 0;
    gbc.gridy= 0;
    gbc.anchor = GridBagConstraints.NORTH;
    
    tfPanel.add(jlabel,gbc);
    gbc.gridy++;
    tfPanel.add(tField,gbc);
    gbc.gridy++;
    tfPanel.add(submitButton,gbc);
          //tfPanel.add(tField,BorderLayout.CENTER);
          //tfPanel.add(jlabel,BorderLayout.NORTH);
          //tfPanel.add(submitButton,BorderLayout.SOUTH);
          submitButton.addActionListener(new setNameListener());
          jframe.setContentPane(tfPanel);
          jframe.setVisible(true);
       

    
   }
   
   private static class setNameListener implements ActionListener {
       public void actionPerformed(ActionEvent event){
          setName();
          if(Name.isEmpty()==false){
          
          classPage classP = new classPage(jframe,progress);
          }
       }
       private static void setName(){
           Name = tField.getText();
           if (Name.isEmpty()){
               JOptionPane.showMessageDialog(null, "No Name Was Inputted", "ERROR", JOptionPane.ERROR_MESSAGE);
           NewGame();
           }
       }
       

   }



 
     
          
/**
   * openFile method
   *
   * opens file, calls on readSource method after.
 * @throws FileNotFoundException
   */
   private void openFile( ) throws FileNotFoundException {
      JFileChooser chooser;
      int status;
      chooser = new JFileChooser( );
      status = chooser.showOpenDialog(null);
      
      if (status == JFileChooser.APPROVE_OPTION)
         readSource(chooser.getSelectedFile());
   } //openFile
   
   /**
   * readsource method
   * reads file and changes values for the game
   *
   * @param chosenFile selected file from JFileChooser
 * @throws FileNotFoundException
   */
   private void readSource(File chosenFile)  {
       try {
       Scanner scanner = new Scanner(chosenFile);
    
          Name=scanner.nextLine();    //reads name
          System.out.println(Name);
          gpa=Double.parseDouble(scanner.nextLine());    //reads gpa
          System.out.println(gpa);
          String line = scanner.nextLine();
          String delims=" ";
            //progress=line.split(delims);
            for (int i=0;i<5;i++){
            //    progress[i]="2";
            }
            
           scanner.close();
       }//try
  //  Scanner scanner = new Scanner(chosenFileName);
          catch (FileNotFoundException e) {
              e.printStackTrace();
           }//catch
 
      
   } //insert method
   
   /**
   * method Save
   * saves current data in game
 * @throws IOException
   *
   */
   private void Save() throws IOException{

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("/home/me/Documents"));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
              
                 // try {
                        PrintWriter writer = new PrintWriter(chooser.getSelectedFile()+".txt");
                       
                        writer.println(Name+"\r"); //also keeps track of who's turn it was
                      writer.println(gpa+"\r"); //to print
                      for (int i=0;i<5;i++){
                          writer.print(progress[i]+" ");
                      }
                        writer.close();
                    }
                   
         }
   }

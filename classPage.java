import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class classPage extends Container implements ActionListener {
     private static JButton[] courses;
     private static JPanel cPanel= new JPanel(new GridBagLayout());
     private static GridBagConstraints gbc = new GridBagConstraints();
     private final static int[] array = {111,211,212,220,240,313,320,343,323,331,370,316,340};
     private final static String[] strArray= {"Intro to Algorithmic Problem Solving","OOP in C++", "OOP in Java",
         "Discrete Structures","Computer Organization & Assembly Language", "Data Structures", "Theory of Computation",
         "Computer Architecture","Data & Analysis of Algorithms","Database","Software Engineering","Principle of"
                 + " Programming Languages","Operating Systems"}; 
    classPage(){
        
    }
    
    classPage(JFrame jf,int[] progress){
        gbc.gridx=0;
        gbc.gridy=0;        
        JLabel jlabel = new JLabel("Welcome! Pick a Class to Enroll");
         cPanel.add(jlabel,gbc);
         gbc.gridy++;
        cPanel.add(makeButtons(progress),gbc);
        jf.getContentPane().removeAll();
        jf.setContentPane(cPanel);
        jf.setVisible(true);
        /**
        JFrame frame = jf;
        cPanel.add(makeButtons(progress),BorderLayout.SOUTH);
    
         JLabel jlabel = new JLabel("Pick Classes");
         cPanel.add(jlabel,BorderLayout.NORTH);
         jf.getContentPane().removeAll();
        jf.setContentPane(cPanel);
         jf.setVisible(true);
         **/
         
    }
    
    private Component makeButtons(int[] pArray){

        JPanel mbPanel= new JPanel();
        mbPanel.setLayout(new GridLayout(3,4));
        courses = new JButton[12];
        
        for(int i=0; i<12; i++){
            courses[i] = new JButton("<html>" + Integer.toString(array[i]) +"<br>"+ strArray[i] + "</html>");
            //courses[i]= new JButton(Integer.toString(i));
            //when mouse hovers over course, displays prer-requisite
            if(i==0){
                courses[i].setToolTipText("Pre-Requisite: CSCI"+ array[i]);
            }
            else courses[i].setToolTipText("Pre-Requisite: CSCI"+ array[i-1]);
            
            courses[i].addActionListener(this);
            mbPanel.add(courses[i]); 
            courses[i].setEnabled(true);
        }
        //goes through the progress bar
        /** ignore for now to set up buttonlistener for each class
        for (int i=0; i<5; i++){
            //goes through the course button array
            for(int j=0;j<12;j++ ){
                if(pArray[i]==j){
                    courses[j].setEnabled(true);
                }
            }
        }
        **/
   
        return mbPanel;
    }
    
    
    public void actionPerformed(ActionEvent a){
        for(int i=0;i<12;i++){
            if(a.getSource()==courses[i]){
                
            }
        }
        
        //OR 
        
        if (a.getSource()==courses[0]){
            Class111 c111 = new Class111();
        }
        
        if (a.getSource()==courses[1]){
            //Class211 c211 = new Class211();
        }
        if(a.getSource() == courses[2]){
            Class212 c212= new Class212(0);
        }
        if (a.getSource()==courses[3]){
            //Class111 c111 = new Class111();
        }
        
        if (a.getSource()==courses[4]){
            //Class211 c211 = new Class211();
        }
        if(a.getSource() == courses[5]){
            
        }
        if(a.getSource() == courses[6]){
            Class313 c313=new Class313(0);
        }
        
    }
   
    
    
}
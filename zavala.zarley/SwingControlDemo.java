import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.lang.*;
import java.util.Scanner;
import animal.carnivore.*;
import animal.herbivore.*;
import animal.*;
import java.util.ArrayList;
 
public class SwingControlDemo {
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   private JTextField giraffe;
   private JTextField lion;

   String fileEntryPoint;
   String fileExitPoint;
   int giraffeNum;
   int lionNum;

   public SwingControlDemo(){
      prepareGUI();
   }
   public static void main(String[] args){
      SwingControlDemo  swingControlDemo = new SwingControlDemo();      
      swingControlDemo.showFileChooserDemo();
   }
   private void prepareGUI(){
      mainFrame = new JFrame("Java Swing Examples");
      mainFrame.setSize(700,400);
      mainFrame.setLayout(new GridLayout(7, 1));
      
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      headerLabel = new JLabel("", JLabel.CENTER);        
      statusLabel = new JLabel("",JLabel.CENTER);    
      statusLabel.setSize(300,100);
      giraffe = new JTextField("Enter # of Giraffes Here");
      lion = new JTextField("Enter # of Lions Here");
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(giraffe);
      mainFrame.add(lion);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }
   private void showFileChooserDemo(){
      headerLabel.setText("Input how many Giraffes in the first field, then how many Lions in the second field, then select a file."); 
      final JFileChooser  fileDialog = new JFileChooser();
      JButton showFileDialogButton = new JButton("Open File");
      
      showFileDialogButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            int returnVal = fileDialog.showOpenDialog(mainFrame);
            
            if (returnVal == JFileChooser.APPROVE_OPTION) {
               java.io.File file = fileDialog.getSelectedFile();
               statusLabel.setText("File Selected :" + file.getName());
               fileEntryPoint = file.getPath();
               giraffeNum = Integer.valueOf(giraffe.getText());
               lionNum = Integer.valueOf(lion.getText());
            } else {
               statusLabel.setText("Open command cancelled by user." );           
            }      
         }
      });
      controlPanel.add(showFileDialogButton);
      mainFrame.setVisible(true);  

		MyThread mt1 = MyThread.createAndStart("Child #1", fileEntryPoint, "C:\\Users\\Zarley\\Desktop\\Java Workspace\\CIS-18B\\zavala.zarley\\output1.txt", giraffeNum, lionNum);
		MyThread mt2 = MyThread.createAndStart("Child #2", fileEntryPoint, "C:\\Users\\Zarley\\Desktop\\Java Workspace\\CIS-18B\\zavala.zarley\\output2.txt", giraffeNum, lionNum);

		try {
			mt1.thrd.join();
			System.out.println("Child #1 joined.");
			mt2.thrd.join();
			System.out.println("Child #2 joined.");
		}
		catch(InterruptedException exc) {
			System.out.println("Main thread interrupted.");
   }
}
}
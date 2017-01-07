import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/*

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

*/

public class App {

	public static void main(String[]args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				JFrame frame = new MainFrame("Title of this window");
				frame.setSize(500,400);
				
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}//end run
			
			
		}); //runnable close
	}//end main

}//end class

/*************************************************************
         * @file: TextPanel.java 
	 * @source: adapted from Horstmann and Cornell, Core Java
	 * @history: Visualization Course, Spring 2003, Chee Yap
	 *************************************************************/

	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	
	/*************************************************************
         *	TextPanel Class (with main method)
	 *************************************************************/
	
	class TextPanel extends JPanel {
	  // override the paintComponent method
	  // THE MAIN DEMO OF THIS EXAMPLE:
	
	  public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Font f = new Font("SansSerif", Font.BOLD, 14);
	    Font fi = new Font("SansSerif", Font.BOLD + Font.ITALIC, 14);
	    FontMetrics fm = g.getFontMetrics(f);
	    FontMetrics fim = g.getFontMetrics(fi);
	    int cx = 75; int cy = 100;
	    g.setFont(f);
	    g.drawString("Hello, ", cx, cy);
	    cx += fm.stringWidth("Hello, ");
	    g.setFont(fi);
	    g.drawString("World!", cx, cy);
	  } //paintComponent

	  //=============================================
	  ///////////// main ////////////////////////////

	  public static void main(String[] args) {
	    JFrame f = new MyFrame("My Hello World Frame");
	    f.show();
	  } //main
	
	} //class TextPanel
	
	/*************************************************************
         	MyFrame Class
	 *************************************************************/

	class MyFrame extends JFrame {
	  public MyFrame(String s) {
		// Frame Parameters
		setTitle(s);
		setSize(300,200); // default size is 0,0
		setLocation(10,200); // default is 0,0 (top left corner)
	
		// Window Listeners
		addWindowListener(new WindowAdapter() {
		  public void windowClosing(WindowEvent e) {
			System.exit(0);
		  } //windowClosing
		}); //addWindowLister
	
		// Add Panels
		Container contentPane = getContentPane();
		contentPane.add(new TextPanel());
	 
	  } //constructor MyFrame
	} //class MyFrame
	
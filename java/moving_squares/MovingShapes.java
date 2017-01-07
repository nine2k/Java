/***
 * Emily Bao
 * The user gain points by clicking on the moving square:
 * 1 point for destroying a green shape, 
 * 2 points for a blue shape,
 * 3 points for a red shape, 
 * and 4 points for a grey shape. It deducts 1 point when the user clicks on a black shape.
 * (penalized for letting a shape get too old)
 * 
 * The program ends when the time limit (counter ==2000)
 * 
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Iterator;



public class MovingShapes extends JFrame implements ActionListener {


	// A timer to "tick" every 20 milliseconds (or as close to that as the system can manage).
	// If you want the program to run slower while you're debugging, just increase the 20 to
	// a larger number.
	private Timer timer = new Timer(10, this);


	// Number of ticks between creation of new squares.  If squares created more or less often,
	// you can change this number.
	private static final int CREATION_INTERVAL = 300; 

	// A list of the squares showing in the window.  We'll be talking about the ArrayList class soon;
	// it's like a Python list.
	private ArrayList<Square> squareList = new ArrayList<Square>();

	// The inner area of the window -- the part that can contain squares (doesn't include
	// title, menu bar, and borders)
	private MovingSquarePanel innerPanel = new MovingSquarePanel();

	// Initial dimensions of the inner panel.  The user can change the size of the frame while
	// the program is running.
	private static final int INITIAL_PANEL_WIDTH = 600;
	private static final int INITIAL_PANEL_HEIGHT = 400;
	
	
	// Count of number of ticks until it's time to create a new square
	private int creationCountdown = 0;

	// initial size for squares
	private int INITIAL_SQUARE_SIZE = 40;
	// Number of pixels a square grows after each collision
	final int SIZE_INCREMENT = 10; 

	//assume game is running at this point (for pause/resume button)
	boolean checkPause = false;

	//Initial name string as blank
	public String name1=("");
	
	//keep track of score
	private int score=0;
	
	//Set Font, background and foreground colors
	Font font = new Font("Serif", Font.BOLD, 15);
	public Color background_Color = new Color (249,189,189);
	public Color foreground_Color = new Color (74,9,117); 
	
	//Counter for program time limit
	private int counter=0;
	

	// Sequence of colors for squares -- they start at the first color
	// and move to the next after each collision until they reach the last
	private static final Color squareColors[] = {Color.GREEN, Color.BLUE, Color.RED, Color.GRAY, Color.BLACK};
	private static final Color INITIAL_COLOR = squareColors[0];
	private static final Color LAST_COLOR = squareColors[squareColors.length-1];

	// Pointer to the main frame of the program (for referencing from inside inner classes)
	private JFrame thisFrame = this;
	
	//Buttons
	JButton exitButton = new JButton("Exit");
	JButton pauseButton = new JButton("Pause");
	JButton stopButton = new JButton("Stop");
	JLabel scoreText = new JLabel ("Score: ");
	JLabel name = new JLabel ("Name: ");
	JTextField addField = new JTextField(10);
	JButton addPlayer = new JButton ("Ok");

	// Constructor to set up the window for the program

	public MovingShapes() {
		// set position of window in screen (better for demo videos)
		setLocation(new Point(600,100));

		setTitle("Moving Squares"); 
		// Make sure program cleans itself up when the user closes the window
		setDefaultCloseOperation(EXIT_ON_CLOSE); 

		// Make the inner panel part of the window and specify its initial size
		add(innerPanel);
		

		// Specify initial size for the inner panel
		innerPanel.setPreferredSize(new Dimension(INITIAL_PANEL_WIDTH, INITIAL_PANEL_HEIGHT));
		// Add a "listener" to react every time the size of the window is changed.
		innerPanel.addComponentListener(new Resizer());


		//add name label, text field and ok button
		innerPanel.add(name);
		innerPanel.add(addField);
		innerPanel.add(addPlayer);
	    
		//Action Listener for pause button
		pauseButton.addActionListener(this);
		
		//Add score
		innerPanel.add(scoreText);
		
		//Add pause button
		innerPanel.add(pauseButton);

		//add the stop button
		innerPanel.add(stopButton);
		
		//set inner panel background to the color background_Color holds
		innerPanel.setBackground(background_Color);
		
		//Changing font of name and color of score display
		name.setFont(font);
		scoreText.setForeground(foreground_Color);
		
		

		// Specify that the actionPerformed method will be called each time the timer ticks
		timer.addActionListener(this);

		innerPanel.addMouseListener(new MouseAdapter() {// empty implementation of all
			// MouseListener`s methods
			public void mousePressed(MouseEvent e) {
				//gets the x, y coordinates when the mouse is pressed
				int x=e.getX();
				int y=e.getY();

				Iterator<Square> ite = squareList.iterator(); 
				while(ite.hasNext()){
					Square s = ite.next();
					if (s.inside(x,y)==true){
						
						//records score points when a shape is destroyed
						
						Color myColor = s.getColor(); 
						
						
						ite.remove();
						
						if (myColor==Color.GREEN){
							
							score=score+1;

						}
						
						else if (myColor==Color.BLUE){
							
							score=score+2;
						}
						
						else if (myColor==Color.RED){
							
							score=score+3;
						}
						
						else if (myColor==Color.GRAY){
							
							score=score+4;
						}
						
						else if (myColor==Color.BLACK){
							
							score=score-1;
						}
						
						scoreText.setText(name1 + " " + score);
						
					}
				}

			}
		});
		


		//add the exit button 
		innerPanel.add(exitButton);
		
		// Now that everything's set up, show the window on the screen and start the timer
		pack(); 
		setVisible(true); 
		timer.start();

		

	} // end MovingShapes



	// This method is called each time the timer "ticks".  It updates the position of every
	// square, creates a new square if it's time, and handles collisions.

	public void actionPerformed(ActionEvent e) {
		/***** Make each square move.  *****/
		// The square itself will know what direction it needs to move and what to do if it 
		// hits the boundary of the panel.  All we need to do here is tell it to move.


		if (e.getSource() == pauseButton){

			//check to see if the program is running
			//if it is, stop
			//change text on pauseButton to resume
			//set checkPause to true (tell program that it has stopped running)
			
			if (checkPause == false){
				timer.stop();
				pauseButton.setText("Resume");
				checkPause = true;
			}
			
			//other case: if program has already been paused
			
			else if (checkPause == true){
				timer.start();
				pauseButton.setText("Pause");
				checkPause = false;
			}

		}

		//Timer for program
		counter++;
		if (counter==30000){
			JOptionPane.showMessageDialog(thisFrame, "Time is up, Your score is "+ score);
			System.exit(0);
		}

		for (int i = 0; i < squareList.size(); i++)
			squareList.get(i).move(); 

		// Search for collisions.  In Assignment 2, when two squares collided one of them
		// went away.  In this version, when two squares collide the should "bounce" off
		// each other.
		for (int i = 0; i < squareList.size(); i++) {
			Square squareA = squareList.get(i);
			for (int j = i+1; j < squareList.size(); j++) {
				Square squareB = squareList.get(j);

				/* see if the two squares are have collided */
				if (Square.overlap(squareA,squareB)) {
					// add code here to make squares "bounce"
					if (squareA.getDirection() == Square.LEFT){
						squareA.setDirection(Square.RIGHT);
						if (squareB.getDirection() == Square.RIGHT)
							squareB.setDirection(Square.LEFT);
						else if (squareB.getDirection() == Square.UP)
							squareB.setDirection(Square.DOWN);
						else if (squareB.getDirection() == Square.DOWN)
							squareB.setDirection(Square.UP);
					}
					else if (squareA.getDirection() == Square.RIGHT){
						squareA.setDirection(Square.LEFT);
						if (squareB.getDirection() == Square.LEFT)
							squareB.setDirection(Square.RIGHT);
						else if (squareB.getDirection() == Square.UP)
							squareB.setDirection(Square.DOWN);
						else if (squareB.getDirection() == Square.DOWN)
							squareB.setDirection(Square.UP);

					}
					else if (squareA.getDirection() == Square.UP){
						squareA.setDirection(Square.DOWN);
						if (squareB.getDirection() == Square.RIGHT)
							squareB.setDirection(Square.LEFT);
						else if (squareB.getDirection() == Square.LEFT)
							squareB.setDirection(Square.RIGHT);
						else if (squareB.getDirection() == Square.DOWN)
							squareB.setDirection(Square.UP);
					}
					else if (squareA.getDirection() == Square.DOWN){
						squareA.setDirection(Square.UP);
						if (squareB.getDirection() == Square.RIGHT)
							squareB.setDirection(Square.LEFT);
						else if (squareB.getDirection() == Square.LEFT)
							squareB.setDirection(Square.RIGHT);
						else if (squareB.getDirection() == Square.UP)
							squareB.setDirection(Square.DOWN);
					}


				} // end if
			} // end for

		} // end for

		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.exit(0);

			}
		});
		
		
		//add button response
		
		addPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name1 = addField.getText();
				scoreText.setText(name1+" "+score);
				

			}
		});

		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int result = JOptionPane.showConfirmDialog(thisFrame, "Do you want to exit?");

				if (result==JOptionPane.YES_OPTION)
					System.exit(0);

				if (result==JOptionPane.NO_OPTION)
					remove(result);

				if (result==JOptionPane.CANCEL_OPTION)
					remove(result);

			}
		});



		// If it's time to create a new square, do that, but make sure it doesn't appear
		// on top of an existing square.  If the screen is so full that this can't be done
		// after the maximum number of tries, the program ends.
		final int MAX_TRIES = 100; // number of times we try to create a new square before giving up
		if (creationCountdown == 0) {
			int tries = 0; // number of times we try to create a new square in a place
			boolean success = false; // becomes true when we've successfully created a new square that doesn't overlap with an older one
			Square newSquare = null; 
			while (!success && tries < MAX_TRIES) {
				newSquare = new Square(INITIAL_SQUARE_SIZE, INITIAL_COLOR); // square constructor picks a random direction and position
				// See if the new square overlaps any of the others
				boolean hasOverlap = false; // true if the new square overlaps with an existing one
				for (int i = 0; i < squareList.size() && !hasOverlap; i++) {
					if (Square.overlap(squareList.get(i),newSquare)) {
						hasOverlap = true;
					} // end if
				} // end for
				if (!hasOverlap) {
					success = true;
					break;
				}
				else {
					tries++;
				} // end if
			} // end while



			if (success) {
				squareList.add(newSquare); 
				creationCountdown = CREATION_INTERVAL; // re-start count until time to add another shape
			}
			else {
				// Could not create a new square without overlapping with another: end program.
				JOptionPane.showMessageDialog(thisFrame, "SCREEN IS TOO FULL; PROGRAM ENDING");
				System.exit(0);
			} // end if
		}
		else {
			creationCountdown--;
		} // end if

		// Tell the inner panel to re-display its contents according to the 
		// updated list of squares
		innerPanel.repaint();

	} // end actionPerformed



	// This is an inner class that specifies what should happen if the window is resized.
	// Its componentResized method will be called at the start of the program and then
	// each time the user re-sizes the window.
	// It's responsible for informing the square class of its new limits.  
	private class Resizer extends ComponentAdapter {
		public void componentResized(ComponentEvent e) {
			// Get the new dimensions of the inner panel
			int panelWidth = innerPanel.getWidth();
			int panelHeight = innerPanel.getHeight();

			// Tell the Square class that the size of its enclosing panel has changed 
			Square.setPanelDimensions(panelWidth, panelHeight);

		} // end componentResized
	} // end Resizer



	// This class describes the inner panel.  It adds knowledge about how to "paint" the
	// contents of the panel to the standard JPanel class.
	private class MovingSquarePanel extends JPanel {

		// Constructor: adds a border around the panel
		public MovingSquarePanel() {
			setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		} // end constructor

		// This method describes how to "paint" the squares inside the panel
		public void paintComponent(Graphics gc) {
			super.paintComponent(gc); // default panel drawing (borders, nothing inside yet)
			// draw each square in the panel
			for (Square square: squareList) {
				square.paint(gc); // tell the square to display itself in the current graphics context
			} // end for
		} // end paintComponent  
	} // end class MovingSquarePanel


	public static void main(String args[]) {
		// create an instance of this class and let it run
		new MovingShapes();

	} // end main

} // end class MovingShapes
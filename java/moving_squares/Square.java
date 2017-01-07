/*
 * CISC-124 Assignment 5
 * Emily Bao
 * 10103388
 * 
 * The user gain points by clicking on the moving square:
 * 1 point for destroying a green shape, 
 * 2 points for a blue shape,
 * 3 points for a red shape, 
 * and 4 points for a grey shape. It deducts 1 point when the user clicks on a black shape.
 * (penalized for letting a shape get too old)
 * 
 */
import java.awt.*;
import java.awt.geom.Area;

import javax.swing.JOptionPane;





public class Square {

    // the four directions in which the square can move
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    
    // INSTANCE VARIABLES
    
    // true if the square is in motion, false if has stopped
    private boolean moving;
    
    // the current color of the square
    private Color color;

    // The size of each side of the square in pixels.  Must stay between MIN_SIZE and MAX_SIZE
    private int size;
    
    public static int age;
    
    // The position of the upper left-hand corner of the square as (x,y) coordinates inside the game's window.
    //(can i change this to public static?)
    private int pos_x;
    private int pos_y;
    
    // The direction in which the shape is moving
    private int direction = LEFT;
    
    // CLASS VARIABLES: dimensions of the panel in which the square is moving, defaulting to 600x400 pixels.
    // The Square class has to know this to make sure the square will "bounce" off the edges of its panel.
    private static int panelWidth = 600;
    private static int panelHeight = 400;
    
    // CONSTRUCTOR: Creates a new moving square with a specified size and color.  Puts the square in
    // a randomly-chosen location that is completely inside the panel and picks a random direction
    // in which the square will move.
    public Square(int initialSize, Color initialColor) {
        size = initialSize;
        color = initialColor;
        age=0;
        
        
        moving = true;
        direction = (int) (4*Math.random()); // random integer between 0 and 4
        pos_x = (int) ((panelWidth-size) * Math.random()); // random horizontal position inside panel
        pos_y = (int) ((panelHeight-size) * Math.random()); // random vertical position inside panel        
    } // end Square
    
    
    // INSTANCE METHODS
   
    // Moves the square one pixel in its direction of motion (if it's moving).
    // If it hits the edge of the panel, it "bounces" back the other way.
    // Makes sure that it remains inside the boundaries of the panel, even if the panel
    // has decreased in size since the last call.
    
    //check if point is inside the square
    public boolean inside (int x, int y){
    	
    	// The position of the upper left-hand corner of the square as (x,y) coordinates inside the game's window.
        int leftX = pos_x;
        int UpperY = pos_y; 
        
        int RightX = pos_x + size;
        int BottomY= pos_y + size ; 
       

        
    	if ((x >=leftX  && x <= RightX) && (y <= BottomY && y >=UpperY)){
    		return true;
    	}
    	else{
    		return false;
    	}
    	   	
    }
    
    
    
    public void move() {
        if (moving) {
        	
        	age++;
        	
        	if (age==200){
        		
        		color=Color.BLUE;
        	}
        	
        	if (age==400){
        		
        		color=Color.RED;
        	}
        	
        	if (age==600){
        		
        		color=Color.GRAY;
        	}
        	
        	if (age==800){
        		
        		color=Color.BLACK;
        	}
        	

        	
            if (direction == LEFT)
                pos_x--;
            
            else if (direction == RIGHT) 
                pos_x++;
           
            else if (direction == UP) 
                pos_y--;
            else // direction == DOWN
                pos_y++;
        } // end if 
            
        // If the square is now partly or completely outside the panel, move it back in and
        // reverse its direction if necessary to make sure it's not heading back out again.
        if (pos_x < 0) {
            pos_x = 0;
            if (direction == LEFT)
                direction = RIGHT;
        }
       
        
        
        else if (pos_x > panelWidth-size) {
            pos_x = panelWidth-size;
            if (direction == RIGHT)
                direction = LEFT;
        } // end if
            
        if (pos_y < 0) {
            pos_y = 0;
            if (direction == UP)
                direction = DOWN;
        }
        else if (pos_y > panelHeight-size) {
            pos_y = panelHeight-size;
            if (direction == DOWN)
                direction = UP;
        }
   
    	

        
        
        
    } // end move
    
    public static boolean testIntersection(Shape shapeA, Shape shapeB) {
    	   Area areaA = new Area(shapeA);
    	   areaA.intersect(new Area(shapeB));
    	   return !areaA.isEmpty();
    	}
   
    
    // makes the shape stop moving
    public void stop() {
        moving = false;
    } // end stop
    
    // Increases the size of the square by the parameter amount.
   public void grow(int increase) {
        size += increase;
    } // end changeSize
    
    // Returns true if the square is currently moving
    public boolean isMoving()   {
        return moving;
    } // end get isMovi
    
    // Returns the current horizontal position of the left side of the square
    public int getXposition() {
        return pos_x;
    } // end getXposition
    
    // Returns the current vertical position of the top of the square
    public int getYposition() {
        return pos_y;
    } // end getYposition
    
    // Returns the current size of the square
    public int getSize() {
        return size;
    } // end getSize
    
    // Returns the current color of the square
    public Color getColor() {
        return color;
    } // end getColor
    
    // Changes the current color of the square
    public void setColor(Color newColor) {
        color = newColor;
    } // end setColor    
    
    public int getDirection(){
    	return direction;
    }
    public void setDirection(int newDirection){
    	direction= newDirection;
    }
    
    // CLASS METHODS
    
    // Records the dimensions of the panel in which this square is moving. The
    // GUI class will call this method when the panel size changes.
    public static void setPanelDimensions(int newWidth, int newHeight) {
    panelWidth = newWidth;
        panelHeight = newHeight;
    } // end setPanelDimensions
    
    // Returns true if the two parameter squares touch -- in other words, if
    // there are any pixels that are in both squares.  This includes pixels that
    // are on the boundary of both squares.  The square must overlap in both the
    // horizontal and vertical dimensions
    public static boolean overlap(Square square1, Square square2) {
        // left and right boundaries of both squares
        int left1 = square1.pos_x;
        int right1 = left1 + square1.size -1;
        int left2 = square2.pos_x;
        int right2 = left2 + square2.size -1;
        boolean horizOverlap = (left1 <= left2 && left2 <= right1) || (left2 <= left1 && left1 <= right2);
        
        if (!horizOverlap)
            return false; // no need to check for vertical overlap
  
    
        // top and bottom boundaries of both squares
        int top1 = square1.pos_y;
        int bot1 = top1 + square1.size -1;
        int top2 = square2.pos_y;
        int bot2 = top2 + square2.size -1;
        boolean vertOverlap = (top1 <= top2 && top2 <= bot1) || (top2 <= top1 && top1 <= bot2);
        

        return vertOverlap;  
        
    } // end overlap
    
   
    
    
    // "Paints" the square in a graphics context -- added for Assignment 5
    public void paint(Graphics gc) {
        gc.setColor(color);
        gc.fillRect(pos_x, pos_y, size, size);
    } // end paint
    
    
    
    
} // end class Square
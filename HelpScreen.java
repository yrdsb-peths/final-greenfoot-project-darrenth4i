import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HelpScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HelpScreen extends World
{
    int colour;
    int icon;
    int highScore;
    
    GreenfootImage[] bg = new GreenfootImage[4];
    
    //boolean to keep track of mouse click
    boolean pressed;
    
    //Integer to keep track of current page
    int current;
    
    /**
     * Constructor for objects of class HelpScreen.
     * 
     */
    public HelpScreen(int col, int ic, int hiScore)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        colour = col;
        icon = ic;
        highScore = hiScore;
        
        //Create array with four images, each image showing new instructions
        for(int i = 0; i<4; i++){
            bg[i] = new GreenfootImage("images/help_screen/help" + i + ".png");
        }
        //Show first image
        setBackground(bg[current]);
    }
    
    public void act(){
        clicked();
    }
    
    /**
     * Method to switch image to new image when mouse clicked
     */
    public void clicked(){
        //Check if user has clicked anywhere on screen
        if(Greenfoot.mouseClicked(null) && !pressed){
            pressed = true;
            current++;
        }
        else{
            pressed = false;
        }
        
        //Prevent index out of bounds error by resetting to first page
        //after fourth page is clicked
        if(current > 3){
            current = 0;
        }
        
        if(pressed){
            setBackground(bg[current]);
        }
    }
}

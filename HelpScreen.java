import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * HelpScreen helps the user understand different objects in the game
 * and the controls as well as the objective of the game
 * 
 * @author Darren
 * @version 17 January 2024
 */
public class HelpScreen extends World
{
    int colour;
    int icon;
    int highScore;
    
    GreenfootImage[] bg = new GreenfootImage[5];
    
    //boolean to keep track of mouse click
    boolean pressed;
    
    //Integer to keep track of current page
    int current;
    
    //Button to exit to title screen
    Button exit;
    
    //Song playing during help screen
    GreenfootSound helpSong = new GreenfootSound("help.mp3");
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
        for(int i = 0; i<5; i++){
            bg[i] = new GreenfootImage("images/help_screen/help" + i + ".png");
        }
        //Show first image
        setBackground(bg[current]);
        
        exit = new Button("exit", 8);
        addObject(exit, 25, 25);
        
        helpSong.setVolume(60);
        helpSong.playLoop();
    }
    
    public void act(){
        clicked(4, bg);
    }
    
    /**
     * Method to switch image to new image when mouse clicked
     */
    public void clicked(int limit, GreenfootImage[] bg){
        //Move back to title screen
        if(Greenfoot.mouseClicked(exit) || Greenfoot.isKeyDown("escape")){
            TitleScreen titleWorld = new TitleScreen(colour, icon, highScore);
            Greenfoot.setWorld(titleWorld);
            Greenfoot.playSound("exitClick.mp3");
            helpSong.pause();
        }
        
        //Check if user has clicked anywhere on screen to move to next image
        if(Greenfoot.mouseClicked(null) && !pressed){
            pressed = true;
            current++;
        }
        else{
            pressed = false;
        }
        
        //Prevent index out of bounds error by resetting to first page
        //after fourth page is clicked
        if(current > limit){
            current = 0;
        }
        
        if(pressed){
            setBackground(bg[current]);
        }
    }
}

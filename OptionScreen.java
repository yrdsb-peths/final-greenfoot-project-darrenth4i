import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class OptionScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OptionScreen extends World
{

    /**
     * Constructor for objects of class OptionScreen.
     * 
     */
    Button exit;
    public OptionScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        exit = new Button("exit", 8);
        addObject(exit, 25, 25);
        
        //Create buttons for changing colour of wave trail
        Button blueTrail = new Button("blue", 2);
        addObject(blueTrail, 135, 295);
        
        Button blackTrail = new Button("black", 2);
        addObject(blackTrail, 195, 295);
        
        Button redTrail = new Button("red", 2);
        addObject(redTrail, 255, 295);
    }
    
    public void act(){
        pressed();
    }
    
    public void pressed(){
        //Exit to title screen from the options screen
        if(Greenfoot.mouseClicked(exit)){
			TitleScreen titleWorld = new TitleScreen();
            Greenfoot.setWorld(titleWorld);
        }
    }
}

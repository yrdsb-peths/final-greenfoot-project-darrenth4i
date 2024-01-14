import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Title screen to introduce player to the game
 * 
 * @author Darren
 * @version 12/31/23
 */
public class TitleScreen extends World
{
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    int colour = 0;
    Button play;
    Button options;
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        play = new Button("play", 4);
        addObject(play, 250, 250);
        
        options = new Button("options", 3);
        addObject(options, 350, 250);
    }
    
    public void act(){
        pressed();
    }
    
    MyWorld gameWorld = new MyWorld();
    OptionScreen optionWorld = new OptionScreen();
    /**
     * Method that transitions to another world based on the
     * image/button that is pressed
     */
    public void pressed(){
        //Change to specified world when the specific button is clicked
        //E.g. play button is clicked so it switches to game world
        if(Greenfoot.mouseClicked(play)){
            Greenfoot.setWorld(gameWorld);
        }
        //Go to options screen from title screen
        else if(Greenfoot.mouseClicked(options)){
            Greenfoot.setWorld(optionWorld);
        }
    }
    
    
}

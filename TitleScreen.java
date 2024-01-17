import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Title screen to introduce player to the game
 * 
 * @author Darren
 * @version 15 January 2024
 */
public class TitleScreen extends World
{
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    Button play;
    Button options;
    
    //acts as offset to wave trail image index to change colour
    //0 = black, 3 = blue, 6 = red
    int colour = 0;
    //offset but for wave icon
    //0 = default, 3 = kite icon, 6 = taser icon
    int icon = 0;
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        play = new Button("play", 4);
        addObject(play, 250, 250);
        
        options = new Button("options", 3);
        addObject(options, 350, 250);
    }
    
    /**
     * Overloaded constructor that takes in colour input from
     * user changing colours/icons in option screen
     */
    public TitleScreen(int col, int iconOffset)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        colour = col;
        icon = iconOffset;
        
        play = new Button("play", 4);
        addObject(play, 250, 250);
        
        options = new Button("options", 3);
        addObject(options, 350, 250);
    }
    
    public void act(){
        pressed();
    }
    
    /**
     * Method that transitions to another world based on the
     * image/button that is pressed
     */
    public void pressed(){
        //Change to specified world when the specific button is clicked
        //E.g. play button is clicked so it switches to game world
        if(Greenfoot.mouseClicked(play)){
            //Pass user chosen colour to game world 
            MyWorld gameWorld = new MyWorld(colour, icon);
            Greenfoot.setWorld(gameWorld);
        }
        //Go to options screen from title screen
        else if(Greenfoot.mouseClicked(options)){
            //Pass user chosen colour to option world 
            OptionScreen optionWorld = new OptionScreen(colour, icon);
            Greenfoot.setWorld(optionWorld);
        }
    }
    
}

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
    //button object to exit options screen 
    Button exit;
    //button objects to change wave trail colour
    Button blueTrail;
    Button blackTrail;
    Button redTrail;
    public OptionScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        exit = new Button("exit", 8);
        addObject(exit, 25, 25);
        
        //Create buttons for changing colour of wave trail
        blueTrail = new Button("blue", 2);
        addObject(blueTrail, 135, 295);
        
        blackTrail = new Button("blackCheck", 2);
        addObject(blackTrail, 195, 295);
        
        redTrail = new Button("red", 2);
        addObject(redTrail, 255, 295);
    }
    
    public void act(){
        pressed();
    }
    
    /**
     * Method that transitions to another world based on the
     * image/button that is pressed
     */
    int colour;
    public void pressed(){
        //Exit to title screen from the options screen
        if(Greenfoot.mouseClicked(exit)){
            TitleScreen titleWorld = new TitleScreen(colour);
            Greenfoot.setWorld(titleWorld);
        }
        //Since there are three different wave trails
        //as animation, the colour variable offsets image index by 0/3/6
        //0 = colour black, 3 = blue, 6 = red
        else if(Greenfoot.mouseClicked(blackTrail)){
            colour = 0;
            //Change red colour image to add checkmark and remove it from
            //The other selected colours
            blackTrail.createImage("blackCheck", 2);
            resetChecked(redTrail, "red", blueTrail, "blue");
        }
        else if(Greenfoot.mouseClicked(blueTrail)){
            colour = 3;
            //Change red colour image to add checkmark and remove it from
            //The other selected colours
            blueTrail.createImage("blueCheck", 2);
            resetChecked(blackTrail, "black", redTrail, "red");
        }
        else if(Greenfoot.mouseClicked(redTrail)){
            colour = 6;
            //Change red colour image to add checkmark and remove it from
            //The other selected colours
            redTrail.createImage("redCheck", 2);
            resetChecked(blackTrail, "black", blueTrail, "blue");
        }
    }
    
    /**
     * Method to switch image back to unchecked version if it
     * has not been selected
     */
    public void resetChecked(Button colOne, String one, Button colTwo, String two){
        colOne.createImage(one, 2);
        colTwo.createImage(two, 2);
    }
}

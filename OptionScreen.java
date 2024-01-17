import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * the options screen allows a user to customize their wave trail
 * asa well as their wave icon
 * 
 * @author Darren
 * @version 15 Jan 2024
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
    //button objects to change wave's icon
    Button defaultWave;
    Button kiteWave;
    Button taserWave;
    
    int colour;
    int icon;
    
    //col keeps track of selected colour trail/icon
    public OptionScreen(int col, int iconOffset)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        colour = col;
        icon = iconOffset;
        
        createButtons();
        
        //Use colour variable from previous world to display the correct
        //colour user had previously chosen
        if(colour == 0){
            blackTrail.createImage("blackCheck", 2);
        }
        else if(colour == 3){
            blueTrail.createImage("blueCheck", 2);
        }
        else{
            redTrail.createImage("redCheck", 2);
        }
        
        //Use icon variable from previous world to display the correct
        //wave icon user had previously chosen
        if(icon == 0){
            defaultWave.createImage("defaultCheck", 4);
        }
        else if(icon == 3){
            kiteWave.createImage("kiteCheck", 4);
        }
        else{
            taserWave.createImage("taserCheck", 4);
        }
    }
    
    public void act(){
        pressed();
    }
    
    /**
     * Method that transitions to another world based on the
     * image/button that is pressed
     */
    public void pressed(){
        //Each if statement checks if mouse clicks on the button's image
        //Exit to title screen from the options screen
        if(Greenfoot.mouseClicked(exit)){
            TitleScreen titleWorld = new TitleScreen(colour, icon);
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
            resetChecked(redTrail, "red", blueTrail, "blue", 2);
        }
        else if(Greenfoot.mouseClicked(blueTrail)){
            colour = 3;
            //Change red colour image to add checkmark and remove it from
            //The other selected colours
            blueTrail.createImage("blueCheck", 2);
            resetChecked(blackTrail, "black", redTrail, "red", 2);
        }
        else if(Greenfoot.mouseClicked(redTrail)){
            colour = 6;
            //Change red colour image to add checkmark and remove it from
            //The other selected colours
            redTrail.createImage("redCheck", 2);
            resetChecked(blackTrail, "black", blueTrail, "blue", 2);
        }
        //Since there are three different wave icons
        //as animation, the icon variable offsets image index by 0/3/6
        //0 = colour default, 3 = kite, 6 = taser
        else if(Greenfoot.mouseClicked(defaultWave)){
            icon = 0;
            defaultWave.createImage("defaultCheck", 4);
            resetChecked(kiteWave, "kiteIcon", taserWave, "taserIcon", 4);
        }
        else if(Greenfoot.mouseClicked(kiteWave)){
            icon = 3;
            kiteWave.createImage("kiteCheck", 4);
            resetChecked(defaultWave, "defaultIcon", taserWave, "taserIcon", 4);
        }
        else if(Greenfoot.mouseClicked(taserWave)){
            icon = 6;
            taserWave.createImage("taserCheck", 4);
            resetChecked(kiteWave, "kiteIcon", defaultWave, "defaultIcon", 4);
        }
    }
    
    /**
     * Method to switch image back to unchecked version if it
     * has not been selected
     */
    public void resetChecked(Button colOne, String one, Button colTwo, String two, int scale){
        colOne.createImage(one, scale);
        colTwo.createImage(two, scale);
    }
    
    /**
     * Method to create wave trail colour + icon images/buttons
     */
    public void createButtons(){
        exit = new Button("exit", 8);
        addObject(exit, 25, 25);
        
        //Create buttons for changing colour of wave trail
        blueTrail = new Button("blue", 2);
        addObject(blueTrail, 135, 295);
        
        //Black trail is selected by default
        blackTrail = new Button("black", 2);
        addObject(blackTrail, 195, 295);
        
        redTrail = new Button("red", 2);
        addObject(redTrail, 255, 295);
        
        //default wave icon is selected by default
        defaultWave = new Button("defaultIcon", 4);
        addObject(defaultWave, 355, 295);
        
        kiteWave = new Button("kiteIcon", 4);
        addObject(kiteWave, 415, 295);
        
        taserWave = new Button("taserIcon", 4);
        addObject(taserWave, 475, 295);
    }
}

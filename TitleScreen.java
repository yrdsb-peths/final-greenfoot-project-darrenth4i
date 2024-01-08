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
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        Button play = new Button("play");
        addObject(play, 400, 400);
    }
    
    MyWorld gameWorld = new MyWorld();
    /**
     * Main world's act loop
     */
    public void act(){
        //Start game once player presses space bar or clicks mouse
        if(Greenfoot.isKeyDown("space") || Greenfoot.mousePressed(gameWorld)){
            Greenfoot.setWorld(gameWorld);
        }
    }
}

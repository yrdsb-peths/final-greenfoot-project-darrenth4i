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
    }
}

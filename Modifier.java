import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Modifiers change wave gravity, obstacle speed, and other
 * aspects of the gameplay
 * 
 * @author Darren 
 * @version 16 January 2024
 */
public class Modifier extends Button
{
    /**
     * Act - do whatever the Modifier wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    /**
     * Modifier constructor to find image path based on specified 
     * imgPath and create scaled image
     */
    public Modifier(String imgPath, int scale){
        super(imgPath, scale);
        super.createImage(imgPath, scale);
    }
}

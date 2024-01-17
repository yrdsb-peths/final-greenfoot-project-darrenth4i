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
    //string to hold imgPath
    String name;
    /**
     * Act - do whatever the Modifier wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        scroll();
        remove();
    }
    
    /**
     * Modifier constructor to find image path based on specified 
     * imgPath and create scaled image
     */
    public Modifier(String imgPath, int scale){
        super(imgPath, scale);
        super.createImage(imgPath, scale);
        name = imgPath;
    }
    
    
    /**
     * Method to let the modifier image scroll to the left until it 
     * goes out of screen
     */
    public void scroll(){
        setLocation(getX() - 5, getY()); //Moves 5 units to the left 
    }
    
    /**
     * Method to remove the modifier image once it goes off of the screen
     */
    public void remove(){
        //Remove block object when it is offscreen and create new block
        if(getX() < 0){
            MyWorld world = (MyWorld) getWorld();
            world.removeObject(this);
        }
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Trail follows the Wave as a visual effect of past movements
 * 
 * @author Darren
 * @version 12/20/2023
 */
public class Trail extends Actor
{
    /**
     * Act - do whatever the Trail wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        scroll();
        remove();
    }
    
    //Array for the wave trail images
    GreenfootImage[] trailAnimation = new GreenfootImage[6];
    public Trail(int imageIndex){
        for(int i = 0; i < trailAnimation.length; i++){
            trailAnimation[i] = new GreenfootImage("images/wave_trail/trail" + i  + ".png");
            trailAnimation[i].scale(trailAnimation[i].getWidth() / 5, trailAnimation[i].getHeight() / 5);
        }
        for(int i = 3; i < trailAnimation.length; i++){
            trailAnimation[i] = new GreenfootImage("images/wave_trail/trail" + i  + "blue.png");
            trailAnimation[i].scale(trailAnimation[i].getWidth() / 5, trailAnimation[i].getHeight() / 5);
        }
        setImage(trailAnimation[imageIndex]);
    }
    
    /**
     * Method to let the wave trail scroll to the left until it 
     * goes out of screen
     */
    public void scroll(){
        setLocation(getX() - 5, getY()); //Moves 5 units to the left 
    }
    
    /**
     * Method to remove the spike once it goes off of the screen
     */
    public void remove(){
        //Remove wave trail object when it is offscreen
        if(getX() < 0){
            MyWorld world = (MyWorld) getWorld();
            world.removeObject(this);
        }
    }
}

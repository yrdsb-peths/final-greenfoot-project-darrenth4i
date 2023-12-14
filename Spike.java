import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The spike is an obstacle that the player must avoid,
 * touching it will result in the death of the player
 * 
 * @author Darren
 * @version 12/12/2023
 */
public class Spike extends Actor
{
    static GreenfootImage[] spikeImages = new GreenfootImage[4];
    static String[] spikeSuffix = {"Up", "Down", "Left", "Right"}; 
    
    /**
     * Spike constructor to scale images to smaller size
     */
    public Spike(int num){
        //Construct an array of 4 images of spike and scale down in size
        for(int i = 0; i < spikeImages.length; i++){
            spikeImages[i] = new GreenfootImage("images/spike/spike" + spikeSuffix[i]  + ".png");
            spikeImages[i].scale(spikeImages[i].getWidth() / 4, spikeImages[i].getHeight() / 4);
            setImage(spikeImages[num]); //Random spike image
        }
    }
    
    /**
     * Act - do whatever the Spike wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        scroll();
    }
    
    /**
     * Method to let the spike scroll to the left until it 
     * goes out of screen
     */
    public void scroll(){
        setLocation(getX() - 5, getY()); //Moves 5 units to the left 
        
        //Remove spike object when it is offscreen and create new spike
        if(getX() < 0){
            MyWorld world = (MyWorld) getWorld();
            world.removeObject(this);
            world.limitSpikes++;
            world.createSpike();
            world.increaseScore();
        }
    }
}

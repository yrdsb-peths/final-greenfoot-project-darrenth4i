import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false);
        
        Wave wave = new Wave();
        addObject(wave, 200, 300);
        
        Hitbox hitbox = new Hitbox();
        addObject(hitbox, 200, 300);

        createSpike();
    }
    
    //keep a list of past y-values to prevent overlapping spikes
    int[] prevSpikeLocations = new int[3];
    int prevY;
    /**
     * Create a spike in a random y-value on the ground
     */
    public void createSpike(){
        //Remove all previous instances of spike so it doesn't stack infinitely
        removeObjects(getObjects(Spike.class)); 
        //Set the amount of spikes that can appear at once
        for(int i = 0; i<3; i++){
            Spike spike = new Spike(Greenfoot.getRandomNumber(4));
            int x = 600;
            int y = Greenfoot.getRandomNumber(400);

            addObject(spike, x, y);
        }
    }
}

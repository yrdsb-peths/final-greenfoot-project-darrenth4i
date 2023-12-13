import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A shrunken hitbox of the wave icon to
 * make the gameplay less difficult
 * 
 * @author Darren
 * @version 12/13/2023
 */
public class Hitbox extends Actor
{
    /**
     * Act - do whatever the Hitbox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        followWave();
        hitSpike();
    }
    
    /**
     * Constructor for hitbox
     */
    public Hitbox(){
        GreenfootImage hitbox = new GreenfootImage("images/waveHitbox.png");
        hitbox.scale(hitbox.getWidth() / 10, hitbox.getHeight() / 15);
        setImage(hitbox);
    }
    
    /**
     * Method to make sure the hitbox follows the wave
     */
    public void followWave(){
        //Create an object for the hitbox that touches the wave
        Wave wave = (Wave)getOneIntersectingObject(Wave.class);
        //Since Hitbox spawns with Wave, it touches at beginning of program
        if(isTouching(Wave.class)){
            //Update location to follow wave
            setLocation(wave.getX(), wave.getY());
        }
    }
    
    /**
     * Method that occurs when wave touches a spike)
     */
    public void hitSpike(){
        //Create an object for the spike that touches the wave
        Spike spike = (Spike)getOneIntersectingObject(Spike.class);
        //Remove spike object when wave touches it
        if(isTouching(Spike.class)){ 
            removeTouching(Spike.class);
            MyWorld world = (MyWorld) getWorld();
            world.createSpike();
        }
    }
}

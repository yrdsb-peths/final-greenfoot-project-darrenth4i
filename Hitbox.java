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
    GreenfootImage hitbox = new GreenfootImage("images/waveHitbox.png");
    //Wave object that hitbox follows
    Wave wave;
    
    GreenfootSound zoom = new GreenfootSound("zoom.mp3");
    GreenfootSound slow = new GreenfootSound("slow.mp3");
    GreenfootSound gravity = new GreenfootSound("gravity.mp3");
    
    /**
     * Act - do whatever the Hitbox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        followWave();
        hitSpike();
        toggleHitbox();
        hitModifier();
    }
    
    /**
     * Constructor for hitbox
     */
    public Hitbox(){
        hitbox.scale(hitbox.getWidth() / 10, hitbox.getHeight() / 15);
        hitbox.setTransparency(0); //make hitbox invisible
        setImage(hitbox);
        
        zoom.setVolume(45);
        slow.setVolume(30);
        gravity.setVolume(45);
    }
    
    /**
     * Method to make sure the hitbox follows the wave
     */
    public void followWave(){
        //Create an object for the hitbox that touches the wave
        wave = (Wave)getOneIntersectingObject(Wave.class);
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
            
            Greenfoot.playSound("death.mp3");
            
            //Update high score
            if(world.score > world.highScore){
                world.highScore = world.score;
                world.highScoreLabel.setValue("Best: " + world.score); //update score
            }
            
            //Remove objects and reset variables like score/spawn cooldown
            world.removeObjects(world.getObjects(Spike.class));
            world.removeObjects(world.getObjects(Modifier.class));
            world.score = 0;
            world.spawnCD = 200;
            world.scoreLabel.setValue("Score: " + world.score); //update score
            world.limitSpikes = 3;
            world.currentSpikes = 0;
            world.limitBlocks = 3;
            world.currentBlocks = 0;
            world.gravityCounter = 0;
            world.wave.gravity = 1;
            //reset static speed to 0
            Spike.speed = 0;
            Modifier.speed = 0;
            
        }
    }
    
    /**
     * Method to detect when wave touches a modifier and switch
     * physics accordingly
     */
    public void hitModifier(){
        if(isTouching(Modifier.class)){
            //get the specific modifier portal that touches wave
            Modifier modifier = (Modifier)getOneIntersectingObject(Modifier.class);
            //Change gravity based on portal touched
            if(modifier.name.equals("/modifier/reversePortal")){
                wave.gravity = -1;
                gravity.play();
            }
            else if(modifier.name.equals("/modifier/normalPortal")){
                wave.gravity = 1;
                gravity.play();
            }    
            //Change speed of block towers based on modifier touched
            if(modifier.name.equals("/modifier/speedUp")){
                //Speed up obstacles
                Spike.speed = 2;
                Modifier.speed = 2;
                zoom.play();
            } 
            else if(modifier.name.equals("/modifier/speedNormal")){
                //Slow down to normal speed
                Spike.speed = 0;  
                Modifier.speed = 0;
                slow.play();
            } 
        }
    }
    
    /**
     * Method to toggle hitbox by pressing Shift
     */
    public void toggleHitbox(){
        if(Greenfoot.isKeyDown("Shift")){
            hitbox.setTransparency(255);
        }
        else{
            hitbox.setTransparency(0);
        }
    }
}

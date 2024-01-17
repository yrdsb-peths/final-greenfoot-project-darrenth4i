import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The game world in which the user has to dodge blocks, spikes
 * and other obstacles to get the highest score
 * 
 * @author Darren
 * @version 15 January 2024
 */
public class MyWorld extends World
{
    Label scoreLabel; //Score label object
    int waveStartPos = 100;
    Wave wave;
    //variable to switch to different trail colour values 
    //-> black = 0 -> blue = 3 -> red = 6
    int colour; 
    
    Background bg1 = new Background();
    Background bg2 = new Background();
    /**
     * Constructor for objects of class MyWorld
     */
    public MyWorld(int col, int icon)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false);
        
        //set trail index offset
        colour = col;
        
        //Create two Background objects that will loop infinitely
        bg1.setOtherBackground(bg2);
        bg2.setOtherBackground(bg1);

        addObject(bg1, 0, 200);
        addObject(bg2, 924, 200);
        
        //create wave object with specified icon image
        wave = new Wave(icon);
        addObject(wave, waveStartPos, 300);
        
        //hitbox that follows wave icon and is checked for collision
        Hitbox hitbox = new Hitbox();
        addObject(hitbox, waveStartPos, 300);
        
        //Create inital block tower obstacles
        createBlock();
        
        //Label to show the score
        scoreLabel = new Label("Score: " + 0, 40);
        addObject(scoreLabel, 85, 30);
        
        //Trail will always spawn behind wave, looks better
        setPaintOrder(Hitbox.class, Wave.class, Trail.class);
    }
    
    SimpleTimer spawnBlockTimer = new SimpleTimer();
    SimpleTimer spawnTrailTimer = new SimpleTimer();
    int spawnCD = 200; //default block spawning cooldown
    /**
     * Method to spawn in obstacles constantly
     */
    public void act(){
        trailTimer();
        if(spawnBlockTimer.millisElapsed() < spawnCD){
            return;
        }
        spawnBlockTimer.mark();
        //Only create a block tower once spawn cooldown is over
        createBlock();
    }
    
    /**
     * Method to spawn in a trail object behind wave
     * every 25 ms
     */
    public void trailTimer(){
        if(spawnTrailTimer.millisElapsed() < 25){
            return;
        }
        spawnTrailTimer.mark();
        createTrail(colour);
    }
    
    /**
     * Create a spike in a random y-value on the ground
     */
    int limitSpikes = 3; //maximum amount of spikes spawning on screen
    int currentSpikes = 0; //number of spikes on screen
    public void createSpike(){
        //Set the amount of spikes that can appear at once
        if(currentSpikes < limitSpikes){
            Spike spike = new Spike();
            int x = Greenfoot.getRandomNumber(200);
            int y = Greenfoot.getRandomNumber(400);
    
            addObject(spike, x + 600, y);
        }
        currentSpikes++;
    }
    
    /**
     * Overloaded method to create a spike at a specific coordinate
     */
    public void createSpike(int x, int y, boolean facingDown){
        //Set the amount of spikes that can appear at once
        Spike spike = new Spike(1);
        if(!facingDown){
            spike = new Spike(0);
        }
        addObject(spike, x, y);
        
        currentSpikes++;
    }    
    
    /**
     * Method to create trail to follow user as they click wave
     */
    public void createTrail(int colour){
        //Variable to determine when Wave is touching ground or ceiling
        boolean touchingGroundOrCeiling = wave.wavePosY > 390 || wave.wavePosY < 10;
        //Remove spike object when it is offscreen and create new spike
        if(wave.pressed && !touchingGroundOrCeiling){
            Trail trail = new Trail(2 + colour);
            addObject(trail, wave.wavePosX, wave.wavePosY);
        }
        if(!wave.pressed && !touchingGroundOrCeiling){
            Trail trail = new Trail(0 + colour);
            addObject(trail, wave.wavePosX, wave.wavePosY);
        }
        
        if(touchingGroundOrCeiling){
            Trail trail = new Trail(1 + colour);
            addObject(trail, wave.wavePosX, wave.wavePosY);
        }
    }
    
    /**
     * Method to create a block tower from either the ceiling or floor
     */
    int limitBlocks = 3; //maximum amount of block towers spawning on screen
    int currentBlocks = 0; //number of block towers on screen
    int gravityCounter = 0; //keep track of the last gravity portal's colour
    public void createBlock(){
        if(currentBlocks < limitBlocks){
            int y = Greenfoot.getRandomNumber(2); //Choose either 0 or 1
            int towerHeight = Greenfoot.getRandomNumber(5) + 1; //Num from 1-5
            
            //0 * 400 = ceiling or 1 * 400 = ground
            int ySpawn = y * 400;
            
            Block block = new Block(towerHeight);
            
            //Image height
            int blockHeight = block.getImage().getHeight();
            //Block spawn comes from middle of block so this offset fixes
            //the block spawning past the ceiling/ground, depending on + or -
            int offset = blockHeight/2;
            
            //Spawning on ceiling
            if(ySpawn == 0){
                addObject(block, 600, ySpawn + offset);
                //Create a spike on below the block tower
                createSpike(600, ySpawn + blockHeight + 15, true);
                //Spawn portal below spike
                gravityCounter = createPortal(ySpawn + blockHeight + 15, 75, gravityCounter);
            }
            //Spawning on ground
            else{
                addObject(block, 600, ySpawn - offset);
                //Create a spike on top the block tower
                createSpike(600, ySpawn - blockHeight - 15, false);
                //Spawn portal above spike
                gravityCounter = createPortal(ySpawn - blockHeight - 15, -75, gravityCounter);
            }
            
            currentBlocks++;
        }
    }
    
    /**
     * Method to spawn gravity changing portal at random
     */
    Modifier portal;
    public int createPortal(int y, int yOffset, int reverseGravity){
        //Every 10 score, spawn a portal 50% of the time
        if(score > 0 && score % 10 == 0 && Greenfoot.getRandomNumber(2) == 1){
            //Change the portal colours every time
            //symbolizing different gravities
            portal = new Modifier("/modifier/reversePortal", 3);
            if(reverseGravity == 0){
                reverseGravity = 1;
            }
            else{
                portal = new Modifier("/modifier/normalPortal", 3);
                reverseGravity = 0;
            }
            
            //Create a portal above spike tower + specified offset so
            //it doesnt overlap with the spike tower
            addObject(portal, 600, y + yOffset);
        }
        
        //Keep same value if a portal wasnt created
        return reverseGravity;
    }
    
    int score;
    /**
     * Method to increase the score by 1
     */
    public void increaseScore(){
        score++;
        scoreLabel.setValue("Score: " + score); //update score
        
        //Increase amount of spikes every 10 points and reduce
        //block spawning cooldown by 10 ms
        if(limitSpikes < 13 && score % 10 == 0)
        {
            limitSpikes++;
            limitBlocks++;
            spawnCD -= 10;
        }
    }
}

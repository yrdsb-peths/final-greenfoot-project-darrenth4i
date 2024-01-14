import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    Label scoreLabel; //Score label object
    int waveStartPos = 100;
    Wave wave;
    //variable to switch to different imageIndex values 
    //-> black = 0 -> blue = 3 -> red = 6
    int colour; 
    /**
     * Constructor for objects of class MyWorld
     */
    public MyWorld(int col)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false);
        
        colour = col;
        System.out.println(colour);
        
        wave = new Wave();
        addObject(wave, waveStartPos, 300);
        
        Hitbox hitbox = new Hitbox();
        addObject(hitbox, waveStartPos, 300);

        createBlock();
        
        //Label to show the score
        scoreLabel = new Label("Score: " + 0, 40);
        addObject(scoreLabel, 85, 30);
        
        //Trail will always spawn behind wave, looks better
        setPaintOrder(Hitbox.class, Wave.class, Trail.class);
    }
    
    SimpleTimer spawnTimer = new SimpleTimer();
    int spawnCD = 200; //default block spawning cooldown
    /**
     * Method to spawn in obstacles constantly
     */
    public void act(){
        createTrail(colour);
        if(spawnTimer.millisElapsed() < spawnCD){
            return;
        }
        spawnTimer.mark();
        //Only create a block tower once spawn cooldown is over
        createBlock();
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
    public void createBlock(){
        if(currentBlocks < limitBlocks){
            int x = Greenfoot.getRandomNumber(200);
            int y = Greenfoot.getRandomNumber(2); //Choose either 0 or 1
            int towerHeight = Greenfoot.getRandomNumber(5) + 1;
            
            //0 * 400 = ceiling or 1 * 400 = ground
            int ySpawn = y * 400;
            Block block = new Block();
            //Image height
            int blockHeight = block.getImage().getHeight();
            //Block spawn comes from middle of block so this offset fixes
            //the block spawning past the ceiling/ground, depending on + or -
            int offset = blockHeight / 2; 
            
            //Block tower creation
            for(int i = 0; i<towerHeight; i++){
                block = new Block();
                //Spawning on ceiling
                if(ySpawn == 0){
                    addObject(block, 600, ySpawn + offset + (i * blockHeight));
                }
                //Spawning on ground
                else{
                    addObject(block, 600, ySpawn - offset - (i * blockHeight));
                }
            }
            //Create a spike on top/below the block tower
            if(ySpawn == 0){
                createSpike(600, ySpawn + offset + (towerHeight * blockHeight), true);
            }
            else{
                createSpike(600, ySpawn - offset - (towerHeight * blockHeight), false);
            }
            currentBlocks++;
        }
    }
    
    /**
     * Method to display score
     */
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
    
    public void setColour(int col){
        colour = col;
    }
}

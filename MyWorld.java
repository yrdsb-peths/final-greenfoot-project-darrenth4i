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
    /**
     * Constructor for objects of class MyWorld
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false);
        
        Wave wave = new Wave();
        addObject(wave, waveStartPos, 300);
        
        Hitbox hitbox = new Hitbox();
        addObject(hitbox, waveStartPos, 300);

        createSpike();
        createBlock();
        
        //Label to show the score
        scoreLabel = new Label("Score: " + 0, 40);
        addObject(scoreLabel, 85, 30);
    }
    
    SimpleTimer spawnTimer = new SimpleTimer();
    int spawnCD = 500; //default block spawning cooldown
    /**
     * Method to spawn in obstacles constantly
     */
    public void act(){
        createSpike();
        //Only create a block tower once spawn cooldown is over
        if(spawnTimer.millisElapsed() < spawnCD){
            return;
        }
        spawnTimer.mark();
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

            addObject(spike, x + 400, y);
            currentSpikes++;
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
            
            //Block tower creation
            for(int i = 0; i<towerHeight; i++){
                Block block = new Block();
                //Image height
                int blockHeight = block.getImage().getHeight();
                //Block spawn comes from middle of block so this offset fixes
                //the block spawning past the ceiling/ground, depending on + or -
                int offset = blockHeight / 2; 
                
                //Spawning on ceiling
                if(ySpawn == 0){
                    addObject(block, x + 400, ySpawn + offset + (i * blockHeight));
                }
                //Spawning on ground
                else{
                    addObject(block, x + 400, ySpawn - offset - (i * blockHeight));
                }
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
        
        //Increase amount of spikes every 5 points and reduce
        //block spawning cooldown by 10 ms
        if(limitSpikes < 10 && score % 5 == 0)
        {
            limitSpikes++;
            limitBlocks++;
            spawnCD -= 10;
        }
    }
}

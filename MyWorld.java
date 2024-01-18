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
    Label highScoreLabel; //Highscore label object
    int waveStartPos = 100;
    Wave wave;
    //variable to switch to different trail colour values 
    //-> black = 0 -> blue = 3 -> red = 6
    int colour;
    //variable to switch to different wave icon values 
    //-> default = 0 -> kite = 3 -> taser = 6
    int icon;
    
    Background bg1 = new Background();
    Background bg2 = new Background();
    
    int limitBlocks = 3; //maximum amount of block towers spawning on screen
    int currentBlocks = 0; //number of block towers on screen
    int gravityCounter = 0; //keep track of the last gravity portal's colour
    int speedCounter = 0; //keep track of the last speed portal's speed
    
    SimpleTimer spawnBlockTimer = new SimpleTimer();
    SimpleTimer spawnTrailTimer = new SimpleTimer();
    int spawnCD = 200; //default block spawning cooldown
    
    int limitSpikes = 3; //maximum amount of spikes spawning on screen
    int currentSpikes = 0; //number of spikes on screen
    
    Modifier portal;
    Modifier speed;
    
    int score;
    int highScore;
    
    //Boolean to check if portal has already been spawned when score % 10 == 0 
    boolean spawnedPortal;
    
    //Button to exit back to title screen
    Button exit;
    /**
     * Constructor for objects of class MyWorld
     */
    public MyWorld(int col, int ic, int hiScore)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false);
        
        //set trail index offset
        colour = col;
        icon = ic;
        highScore = hiScore;
        
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
        
        //Label to show the highscore
        highScoreLabel = new Label("Best: " + highScore, 40);
        addObject(highScoreLabel, 75, 70);
        
        //Trail will always spawn behind wave, looks better
        setPaintOrder(Hitbox.class, Wave.class, Trail.class);
        
        //Button to exit back to title screen        
        exit = new Button("exit", 8);
        addObject(exit, 575, 25);
    }
    
    /**
     * Method to spawn in obstacles constantly
     */
    public void act(){
        buttonPressed();
        trailTimer();
        if(spawnBlockTimer.millisElapsed() < spawnCD){
            return;
        }
        spawnBlockTimer.mark();
        //Only create a block tower once spawn cooldown is over
        createBlock();
    }
    
    /**
     * Method to exit to title screen while keeping
     * track of important variables like customizations/high score
     */
    public void buttonPressed(){
        //Exit to title screen from the options screen
        if(Greenfoot.mouseClicked(exit) || Greenfoot.isKeyDown("escape")){
            TitleScreen titleWorld = new TitleScreen(colour, icon, highScore);
            Greenfoot.setWorld(titleWorld);
            Greenfoot.playSound("exitClick.mp3");
        }
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
            //Change trail if gravity is upside down
            if(wave.gravity == -1){
                trail = new Trail(0 + colour);
            }
            addObject(trail, wave.wavePosX, wave.wavePosY);
        }
        if(!wave.pressed && !touchingGroundOrCeiling){
            Trail trail = new Trail(0 + colour);
            if(wave.gravity == -1){
                trail = new Trail(2 + colour);
            }
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
                //Spawn speed portal below spike
                speedCounter = createSpeed(ySpawn + blockHeight + 15, 75, speedCounter);
            }
            //Spawning on ground
            else{
                addObject(block, 600, ySpawn - offset);
                //Create a spike on top the block tower
                createSpike(600, ySpawn - blockHeight - 15, false);
                //Spawn portal above spike
                gravityCounter = createPortal(ySpawn - blockHeight - 15, -75, gravityCounter);
                //Spawn portal above spike
                speedCounter = createSpeed(ySpawn - blockHeight - 15, -75, speedCounter);
            }
            
            currentBlocks++;
        }
    }
    
    /**
     * Method to spawn gravity changing portal at random
     */
    public int createPortal(int y, int yOffset, int reverseGravity){
        //Every 10 score, spawn a portal 50% of the time
        if(!spawnedPortal && score > 0 && score % 10 == 0 && Greenfoot.getRandomNumber(2) == 1){
            //Change the portal colours every time
            //symbolizing different gravities
            portal = new Modifier("/modifier/reversePortal", 3);
            
            //spawn portal only once
            spawnedPortal = true;
            
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
        else if(score % 10 != 0){
            spawnedPortal = false;
        }
        
        //Keep same value if a portal wasnt created
        return reverseGravity;
    }
    
    /**
     * Method to spawn speed changing portal at random
     */
    public int createSpeed(int y, int yOffset, int speedType){
        //Every 13 score, spawn a portal 75% of the time
        if(score > 0 && score % 13 == 0 && Greenfoot.getRandomNumber(4) < 3){
            //Change the portal colours every time
            //symbolizing different gravities
            speed = new Modifier("/modifier/speedUp", 3);
            if(speedType == 0){
                speedType = 1;
            }
            else{
                speed = new Modifier("/modifier/speedNormal", 3);
                speedType = 0;
            }
            
            //Create a speed portal above spike tower + specified offset so
            //it doesnt overlap with the spike tower
            addObject(speed, 600, y + yOffset);
        }
        
        //Keep same value if a portal wasnt created
        return speedType;
    }
    
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
            spawnCD -= 7;
        }
    }
}

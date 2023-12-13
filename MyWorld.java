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
    /**
     * Constructor for objects of class MyWorld
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
        
        //Label to show the score
        scoreLabel = new Label("Score: " + 0, 40);
        addObject(scoreLabel, 85, 30);
    }
    
    /**
     * Create a spike in a random y-value on the ground
     */
    int maxSpikes; //maximum amount of spikes spawning on screen
    int limitSpikes = maxSpikes + 3;
    public void createSpike(){
        //Set the amount of spikes that can appear at once
        for(int i = 0; i<limitSpikes; i++){
            Spike spike = new Spike(Greenfoot.getRandomNumber(4));
            int x = Greenfoot.getRandomNumber(200);
            int y = Greenfoot.getRandomNumber(400);

            addObject(spike, x + 400, y);
            limitSpikes--;
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
        scoreLabel.setValue("Score: " + score);
        
        //Increase amount of spikes or speed of apple falling every 5 points
        if(score % 5 == 0)
        {
            limitSpikes++;
        }
    }
}

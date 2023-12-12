import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The wave is a character the player controls
 * holding mouse click makes the wave go up and
 * letting go makes it go down again.
 * 
 * @author Darren 
 * @version 12/12/2023
 */
public class Wave extends Actor
{
    /**
     * Act - do whatever the Wave wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        click();
    }
    
    //Array for the wave images
    GreenfootImage[] waveAnimation = new GreenfootImage[3];
    /**
     * Wave constructor to scale images to smaller size
     */
    public Wave(){
        //Construct an array of 3 images of wave
        for(int i = 0; i < waveAnimation.length; i++){
            waveAnimation[i] = new GreenfootImage("images/wave_idle/wave" + i  + ".png");
            waveAnimation[i].scale(waveAnimation[i].getWidth() / 4, waveAnimation[i].getHeight() / 4);
            setImage(waveAnimation[1]); //Default wave icon image
        }
    }
    
    /**
     * Method to change the image and location of the wave icon based on
     * the Y-coordinate and if mouse is being pressed
     */
    public void click(){
        MyWorld world = new MyWorld();
        //When player is clicking, wave moves up
        if(Greenfoot.mousePressed(world)){
            setImage(waveAnimation[2]); //Wave icon up image
        } 
        //When player lets go, wave moves down
        else if(Greenfoot.mouseClicked(world)){
            setImage(waveAnimation[1]); //Wave icon down image
        }
    }
}

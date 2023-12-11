import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wave here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
    
    GreenfootImage[] waveAnimation = new GreenfootImage[3];
    public Wave(){
        //Construct an array of 4 images of spike
        for(int i = 0; i < waveAnimation.length; i++){
            waveAnimation[i] = new GreenfootImage("images/wave_idle/wave" + i  + ".png");
            waveAnimation[i].scale(waveAnimation[i].getWidth() / 4, waveAnimation[i].getHeight() / 4);
            setImage(waveAnimation[1]);
        }
    }
    
    public void click(){
        MyWorld world = new MyWorld();
        if(Greenfoot.mousePressed(world)){
            setImage(waveAnimation[2]);
        }
        else if(Greenfoot.mouseClicked(world)){
            setImage(waveAnimation[1]);
        }
    }
}

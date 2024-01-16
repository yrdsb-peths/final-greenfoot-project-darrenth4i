import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Background class will create a scrolling background effect
 * when the game is being played. This adds to the illusion
 * of the wave moving
 * 
 * @author Darren
 * @version 15 Jan 2024
 */
public class Background extends Actor
{
    private int imageWidth;
    private int offset = 100;
    private Background otherBackground;
    private int speed = 4;
    
    /**
     * Act - do whatever the Background wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Background()
    {
        //get image width
        imageWidth = getImage().getWidth(); 
    }
    
    //Setter method to set other background object for scrolling bg
    public void setOtherBackground(Background otherBackground) {
        this.otherBackground = otherBackground;
    }
    
    public void increaseSpeed() {
        speed += 1;
    }
    
    public void act()
    {
        //move image once it goes off screen
        if(getX() < -imageWidth + offset) {
            int newX = otherBackground.getX() + imageWidth;
            setLocation(newX, getY());
        }
        //move to get scrolling effect
        move(-speed);
    }
}

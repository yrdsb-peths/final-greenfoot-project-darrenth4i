import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Background here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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

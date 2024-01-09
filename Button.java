import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An image that the user clicks to go to another World object
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    String imageLink;
    public Button(String imgPath, int scale){
        imageLink = "images/button/" + imgPath + ".png";
        GreenfootImage image = new GreenfootImage(imageLink);
        image.scale(image.getWidth() / scale, image.getHeight() / scale);
        setImage(image);
    }
    
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        pressed();
    }
    
    MyWorld gameWorld = new MyWorld();
    public void pressed(){
        if(Greenfoot.mouseClicked(this)){
            Greenfoot.setWorld(gameWorld);
        }
    }
}

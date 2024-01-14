import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An image that the user clicks to go to another World object
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    //Image path string
    String imageLink;  
    //To tell what colour was chosen for wave trail
    int colour;
    GreenfootImage image;
    /**
     * Button constructor to find image path based on specified 
     * imgPath and create scaled image
     */
    public Button(String imgPath, int scale){
        createImage(imgPath, scale);
    }
    
    /**
     * Method to set the image of an object and scale it
     */
    public void createImage(String img, int scale){
        image = new GreenfootImage("images/button/" + img + ".png");
        image.scale(image.getWidth() / scale, image.getHeight() / scale);
        setImage(image);
    }
}

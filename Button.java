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
    //To differentiate between different buttons
    String buttonType;
    //To tell what colour was chosen for wave trail
    int colour;
    /**
     * Button constructor to find image path based on specified 
     * imgPath and create scaled image
     */
    public Button(String imgPath, int scale){
        buttonType = imgPath;
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
    }

    /**
     * Function to change to another world when user clicks on the button
     */ 
    public void pressed(){
        
    }
}

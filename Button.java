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
        pressed();
    }

    /**
     * Function to change to another world when user clicks on the button
     */
    public void pressed(){
        //Change to specified world when the specific button is clicked
        //E.g. play button is clicked so it switches to game world
        if(Greenfoot.mouseClicked(this) && buttonType.equals("play")){
            MyWorld gameWorld = new MyWorld();
            Greenfoot.setWorld(gameWorld);
        }
        //Go to options screen from title screen
        else if(Greenfoot.mouseClicked(this) && buttonType.equals("options")){
            OptionScreen optionsWorld = new OptionScreen();
            Greenfoot.setWorld(optionsWorld);
        }
        //Exit to title screen from the options screen
        else if(Greenfoot.mouseClicked(this) && buttonType.equals("exit")){
            TitleScreen titleWorld = new TitleScreen();
            Greenfoot.setWorld(titleWorld);
        }
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AchievementScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AchievementScreen extends HelpScreen
{
    GreenfootImage[] bg = new GreenfootImage[2];
    /**
     * Constructor for objects of class AchievementScreen.
     * 
     */
    public AchievementScreen(int colour, int icon, int highScore)
    {
        super(colour, icon, highScore);
        //Create array with four images, each image showing new instructions
        for(int i = 0; i<2; i++){
            bg[i] = new GreenfootImage("images/achievement_screen/achievement" + i + ".png");
        }
        //Show first image
        setBackground(bg[current]);
    }
    
    public void act(){
        super.clicked(1, bg);
    }
}

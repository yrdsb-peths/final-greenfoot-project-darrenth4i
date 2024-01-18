import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * AchievementScreen motivates the player to reach new high scores
 * by rewarding them with achievements and funny pictures
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AchievementScreen extends HelpScreen
{
    GreenfootImage[] bg = new GreenfootImage[2];
    int colour;
    int icon;
    int highScore;
    
    //Button objects for all 6 achievements
    Button ach1;
    Button ach2;
    Button ach3;
    /**
     * Constructor for objects of class AchievementScreen.
     * 
     */
    public AchievementScreen(int col, int ic, int hiScore)
    {
        super(col, ic, hiScore);
        
        colour = col;
        icon = ic;
        highScore = hiScore;
        
        //Create array with four images, each image showing new instructions
        for(int i = 0; i<2; i++){
            bg[i] = new GreenfootImage("images/achievement_screen/achievement" + i + ".png");
        }
        //Show first image
        setBackground(bg[current]);
        
        ach1 = new Button("question", 8);
        addObject(ach1, 150, 150);
        
        ach2 = new Button("question", 8);
        addObject(ach2, 150, 240);
        
        ach3 = new Button("question", 8);
        addObject(ach3, 150, 330);
        
    }
    
    public void act(){
        super.clicked(1, bg);
    }
    
    public void checkScore(){
        
    }
    
}

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
        cheat();
        checkScore();
    }
    
    public void checkScore(){
        if(super.current == 0){
            if(super.highScore >= 50){
                ach1.createImage("face0", 8);
                ach2.createImage("face1", 9);
                ach3.createImage("face2", 8);
            }
            else if(super.highScore >= 10){
                ach1.createImage("face0", 8);
                ach2.createImage("face1", 9);
            }
            else if(super.highScore >= 1){
                ach1.createImage("face0", 8);
            }
        }
        else{
            if(super.highScore >= 312){
                ach1.createImage("face3", 5);
                ach2.createImage("face4", 11);
                ach3.createImage("face5", 8);
            }
            else if(super.highScore >= 250){
                ach1.createImage("face3", 5);
                ach2.createImage("face4", 11);
            }
            else if(super.highScore >= 100){
                ach1.createImage("face3", 5);
            }
        }
    }
    
    public void cheat(){
        if(Greenfoot.isKeyDown("o") && Greenfoot.isKeyDown("p")){
            super.highScore = 1000;
        }
    }
    
}

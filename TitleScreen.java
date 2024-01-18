import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Title screen to introduce player to the game
 * 
 * @author Darren
 * @version 15 January 2024
 */
public class TitleScreen extends World
{
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    Button play;
    Button options;
    Button question;
    Button achievement;
    
    //acts as offset to wave trail image index to change colour
    //0 = black, 3 = blue, 6 = red
    int colour = 0;
    //offset but for wave icon
    //0 = default, 3 = kite icon, 6 = taser icon
    int icon = 0;
    //Highscore value
    int highScore = 0;
    
    GreenfootSound song = new GreenfootSound("themeSong.mp3");

    //Variable to make sure music only plays when run is clicked
    static boolean started;
    
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        play = new Button("play", 4);
        addObject(play, 250, 220);
        
        options = new Button("options", 3);
        addObject(options, 350, 220);
        
        question = new Button("question", 7);
        addObject(question, 250, 310);
        
        achievement = new Button("achievement", 4);
        addObject(achievement, 350, 310);
        
        if(started){
            song.setVolume(50);
            song.play();
        }
            
    }
    
    /**
     * Overloaded constructor that takes in colour input from
     * user changing colours/icons in option screen
     */
    public TitleScreen(int col, int iconOffset, int hiScore)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        colour = col;
        icon = iconOffset;
        highScore = hiScore;
        
        play = new Button("play", 4);
        addObject(play, 250, 220);
        
        options = new Button("options", 3);
        addObject(options, 350, 220);
        
        question = new Button("question", 7);
        addObject(question, 250, 310);
        
        achievement = new Button("achievement", 4);
        addObject(achievement, 350, 310);
        
        if(started){
            song.setVolume(50);
            song.play();
        }
    }
    
    public void started(){
        started = true;
        song.setVolume(50);
        song.play();
    }
    
    public void act(){
        pressed();
    }
    
    /**
     * Method that transitions to another world based on the
     * image/button that is pressed
     */
    public void pressed(){
        //Change to specified world when the specific button is clicked
        //E.g. play button is clicked so it switches to game world
        if(Greenfoot.mouseClicked(play)){
            //Pass user chosen colour, icon and highscore to game world 
            MyWorld gameWorld = new MyWorld(colour, icon, highScore);
            Greenfoot.setWorld(gameWorld);
            //Play button click sfx
            Greenfoot.playSound("buttonClick.mp3");
            //prevent song from playing multiple times in different worlds
            song.pause();
        }
        //Go to options screen from title screen
        else if(Greenfoot.mouseClicked(options)){
            //Pass user chosen colour, icon and highscore to option world 
            OptionScreen optionWorld = new OptionScreen(colour, icon, highScore);
            Greenfoot.setWorld(optionWorld);
            Greenfoot.playSound("buttonClick.mp3");
            song.pause();
        }
        else if(Greenfoot.mouseClicked(question)){
            //Pass user chosen colour, icon and highscore to help world 
            HelpScreen helpWorld = new HelpScreen(colour, icon, highScore);
            Greenfoot.setWorld(helpWorld);
            Greenfoot.playSound("buttonClick.mp3");
            song.pause();
        }
        else if(Greenfoot.mouseClicked(achievement)){
            //Pass user chosen colour, icon and highscore to achievement world 
            AchievementScreen achievementWorld = new AchievementScreen(colour, icon, highScore);
            Greenfoot.setWorld(achievementWorld);
            Greenfoot.playSound("buttonClick.mp3");
            song.pause();
        }
    }
    
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Another type of obstacle the player must avoid. Square shape
 * instead of a triangular spike and only spawns on the ground
 * or ceiling
 * 
 * @author Darren
 * @version 12/15/2023
 */
public class Block extends Spike
{
    //Create array with 5 image size 
    GreenfootImage[] blockTower = new GreenfootImage[5];
    /**
     * Act - do whatever the Block wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        super.scroll();
        remove();
    }
    
    public Block(int height){
        //Construct block image and scale it down
        for(int i = 0; i < 5; i++){
            blockTower[i] = new GreenfootImage("images/spike/" + "block" + i + ".png");
            blockTower[i].scale(blockTower[i].getWidth() / 4, blockTower[i].getHeight() / 4);
        }
        setImage(blockTower[height - 1]);
    }
    
    /**
     * Overloaded method to remove the block once it goes off of the screen
     */
    public void remove(){
        //Remove block object when it is offscreen and create new block
        if(getX() < 0){
            MyWorld world = (MyWorld) getWorld();
            world.currentBlocks--;
            world.removeObject(this);
        }
    }
}

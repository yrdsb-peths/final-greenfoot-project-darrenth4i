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
    
    public Block(){
        //Construct block image and scale it down
        GreenfootImage blockImage = new GreenfootImage("images/block.png");
        blockImage.scale(blockImage.getWidth() / 4, blockImage.getHeight() / 4);
        setImage(blockImage); 
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

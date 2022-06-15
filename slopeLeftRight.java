import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class diagonal_block here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class slopeLeftRight extends Slopes
{   
    private boolean isOnSlope;
    //Creates construtor with custom or default image using overlooading
    public slopeLeftRight (World world, int x, int y, int xCoord,int yCoord)
    {
        GreenfootImage image = new GreenfootImage("images/Grass terrain/slopeLeftRight.png");
        image.scale(x, y);
        setImage(image);
    
        Block blockRight = new Block(x / 2 - 10, y);
        world.addObject(blockRight, xCoord - getImage().getWidth() / 4 - 5, yCoord);
    }
    
    public slopeLeftRight (World world, int x, int y, int xCoord,int yCoord, GreenfootImage terrainType)
    {
        GreenfootImage image = new GreenfootImage(terrainType);
        image.scale(x, y);
        setImage(image);
    
        Block blockRight = new Block(x / 2 - 10, y);
        world.addObject(blockRight, xCoord - getImage().getWidth() / 4 - 5, yCoord);
    }
    //Creates and sets detection system
    public void act()
    {
        if(onSlopeLeft() == true)
        {
            getWorld().getObjects(SquatKing.class).get(0).onSlopeLeft(onSlopeLeft());
        }
    }
    
    public boolean onSlopeLeft()
    {
        isOnSlope = false;
        if (!getNeighbours(getImage().getWidth() - 10, false, SquatKing.class).isEmpty())
        {
            isOnSlope = true;
        }
        return isOnSlope;
    }
}

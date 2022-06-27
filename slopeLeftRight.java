import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * slopeLeftRight actor class; Diagonal is from top left to bottom right
 * 
 * 
 * Joey & Anson
 * June 17, 2022
 */
public class slopeLeftRight extends Slopes
{   
    private boolean isOnSlope;
    /**
     * Creates construtor with custom or default image using overloading
     * 
     * @param world sets which world to place the actors
     * @param x sets width
     * @param y sets height
     * @param xCoord determines side bounds of the slope
     * @param yCoord determines top and bottom bounds of the slope
     */
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
    /**
     * Returns a boolean indicating if SquatKing.class is touching the slope using diagonal bounds
     * 
     * No parameters 
     * 
     * @return true if touching SquatKing.class, else return false
     */
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

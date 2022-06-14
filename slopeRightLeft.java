import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class diagonal_block here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class slopeRightLeft extends Slopes
{
    /**
     * Act - do whatever the diagonal_block wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean isOnSlope;
    public slopeRightLeft (World world, int x, int y, int xCoord,int yCoord)
    {
        GreenfootImage image = new GreenfootImage("images/Grass terrain/slopeLeftRight.png");
        image.scale(x, y);
        setImage(image);
        
        Block blockLeft = new Block(x / 2 - 10, y);
        world.addObject(blockLeft, xCoord + getImage().getWidth() / 4 + 5, yCoord);
    }
    
    public slopeRightLeft (World world, int x, int y, int xCoord,int yCoord, GreenfootImage terrainType)
    {
        GreenfootImage image = new GreenfootImage(terrainType);
        image.scale(x, y);
        setImage(image);
        
        Block blockLeft = new Block(x / 2 - 10, y);
        world.addObject(blockLeft, xCoord + getImage().getWidth() / 4 + 5, yCoord);
    }
    
    public void act()
    {
        if(onSlopeRight() == true)
        {
            getWorld().getObjects(SquatKing.class).get(0).onSlopeRight(onSlopeRight());
        }
    }
    
    public boolean onSlopeRight()
    {
        isOnSlope = false;
        if (!getNeighbours(getImage().getWidth() - 10, false, SquatKing.class).isEmpty())
        {
            isOnSlope = true;
        }
        return isOnSlope;
    }
}

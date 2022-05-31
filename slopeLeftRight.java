import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class diagonal_block here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class slopeLeftRight extends ScrollObjects
{
    /**
     * Act - do whatever the diagonal_block wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean isOnSlope;
    public slopeLeftRight (World world, int x, int y, int xCoord,int yCoord)
    {
        
        GreenfootImage image = getImage();
        image.scale(x, y);
        setImage(image);
        
        Block blockLeft = new Block(x / 2, y);
        world.addObject(blockLeft, xCoord - getImage().getWidth() / 4 , yCoord);
        Block blockBottom = new Block(x, y / 2);
        world.addObject(blockBottom, xCoord, yCoord + getImage().getWidth() / 4);
    }
    
    public void act()
    {
        if(onSlope() == true)
        {
            getWorld().getObjects(bluejay.class).get(0).onSlopeLeft(onSlope());
        }
    }
    
    public boolean onSlope()
    {
        isOnSlope = false;
        if (!getNeighbours(getImage().getWidth() - 2, false, bluejay.class).isEmpty())
        {
            isOnSlope = true;
        }
        return isOnSlope;
    }
}

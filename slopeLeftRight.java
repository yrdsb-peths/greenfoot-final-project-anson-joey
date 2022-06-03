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
    
        Block blockLeft = new Block(x / 2 - 10, y);
        world.addObject(blockLeft, xCoord - getImage().getWidth() / 4 - 5, yCoord);

    }
    
    public void act()
    {
        if(onSlopeLeft() == true)
        {
            getWorld().getObjects(bluejay.class).get(0).onSlopeLeft(onSlopeLeft());
        }
    }
    
    public boolean onSlopeLeft()
    {
        isOnSlope = false;
        if (!getNeighbours(getImage().getWidth() - 3, false, bluejay.class).isEmpty())
        {
            isOnSlope = true;
        }
        return isOnSlope;
    }
}

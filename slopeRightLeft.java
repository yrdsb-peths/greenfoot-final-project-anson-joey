import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * slopeRightLeft actor class; Diagonal is from top right to bottom left
 * 
 * 
 * Joey & Anson
 * June 17, 2022
 */
public class slopeRightLeft extends Slopes
{
    private boolean isOnSlope;
    //Creates construtor with custom or default image using overlooading
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
    //Creates and sets detection system
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

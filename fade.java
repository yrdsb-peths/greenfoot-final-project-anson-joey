import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * 
 * Joey & Anson
 * June 17, 2022
 */
public class fade extends gameAssets
{
    private int transVal;
    private int direction;
    World nextWorld;
 
    public fade(World inWorld)
    {
        nextWorld = inWorld;
        if (nextWorld == null) 
        {
            direction = -4;  
        }
        else 
        {
            direction = 4;
        }
    }
    
    public void addedToWorld(World world)
    {
        setImage(new GreenfootImage(world.getWidth(), world.getHeight()));
        getImage().fill(); // black is default color for new GreenfootImages
    }
 
    public void act()
    {
        transVal = (transVal+direction+256)%256; // update value
        if (transVal == 0) // check value to see if we are done yet
        {
            if (nextWorld == null) getWorld().removeObject(this); // for new world
            else Greenfoot.setWorld(nextWorld); // for old world
        }
        else getImage().setTransparency(transVal); // for when not done yet
    }
}

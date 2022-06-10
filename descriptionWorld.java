import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class descriptionWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class descriptionWorld extends World
{

    /**
     * Constructor for objects of class descriptionWorld.
     * 
     */
    public descriptionWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1); 
        addObject(new fade(null), getWidth()/2, getHeight()/2);
    }
    
    public void act()
    {
        if(Greenfoot.isKeyDown("space"))
        {
            MyWorld world = new MyWorld();
            fade fadeout = new fade(world);
            addObject(fadeout, getWidth() / 2, getHeight() / 2);
        }
    }
}

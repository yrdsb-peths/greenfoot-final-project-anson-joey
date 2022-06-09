import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class titleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class titleScreen extends World
{
    private int transVal;
    /**
     * Constructor for objects of class titleScreen.
     * 
     */
    public titleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1); 
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

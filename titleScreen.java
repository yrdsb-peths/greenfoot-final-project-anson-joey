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
    public static GreenfootSound menuTheme = new GreenfootSound("menuTheme.mp3");
    public titleScreen()
    {    
        super(1000, 800, 1);
    }
    
    public void act()
    {
        menuTheme.playLoop();
        if(Greenfoot.isKeyDown("space"))
        {
            descriptionWorld world = new descriptionWorld();
            fade fadeout = new fade(world);
            addObject(fadeout, getWidth() / 2, getHeight() / 2);
        }
    }
}

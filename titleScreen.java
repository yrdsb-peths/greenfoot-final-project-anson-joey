import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * titleScreen world class; It's a title screen.
 * Contains a title and some music. Switches to the descriptionWorld world when space is pressed.
 * 
 * Joey & Anson
 * June 17, 2022
 */
public class titleScreen extends World
{
    public static GreenfootSound menuMusic = new GreenfootSound("menuMusic.mp3");
    private boolean nextWorldToggle = false;
    
    public titleScreen()
    {    
        super(1000, 800, 1);
    }
    
    public void act()
    {
        menuMusic.playLoop();
        if(Greenfoot.isKeyDown("space") && nextWorldToggle == false)
        {
            descriptionWorld world = new descriptionWorld();
            fade fadeout = new fade(world);
            addObject(fadeout, getWidth() / 2, getHeight() / 2);
            nextWorldToggle = true;
        }
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class descriptionWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class descriptionWorld extends World
{
    titleScreen world = new titleScreen();
    GreenfootSound startSound = new GreenfootSound("startSound.mp3");
    private boolean nextWorldToggle = false;
    public descriptionWorld()
    {    
        super(1000, 800, 1); 
        addObject(new fade(null), getWidth()/2, getHeight()/2);
    }
    
    public void act()
    {
        if(Greenfoot.isKeyDown("space") && nextWorldToggle == false)
        {
            world.menuMusic.stop();
            startSound.setVolume(100);
            startSound.play();
            MyWorld world = new MyWorld();
            fade fadeout = new fade(world);
            addObject(fadeout, getWidth() / 2, getHeight() / 2);
            nextWorldToggle = true;
        }
    }
}

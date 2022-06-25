import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * descriptionWorld world class; It displays game instructions.
 * Contains instruction text and switches to the MyWorld world when space is pressed.
 * Stops menu music and plays a little jingle when switching to MyWorld.
 * 
 * Joey & Anson
 * June 17, 2022
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

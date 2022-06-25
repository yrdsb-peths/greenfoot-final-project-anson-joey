import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BBQScene world class; It's an endscreen image.
 * Contains the ending background image and plays some music.
 * Switches to the statsScreen world when prompted.
 * 
 * Joey & Anson
 * June 17, 2022
 */
public class BBQScene extends World
{
    public GreenfootSound endMusic = new GreenfootSound("endMusic.mp3");
    SimpleTimer nextWorldTimer = new SimpleTimer();
    private boolean nextWorldToggle = false;
    
    /**
     * Constructor for BBQScene world
     * 
     * No parameters or returns
     */
    public BBQScene()
    {    
        super(1000, 800, 1); 
        endMusic.play();
        //Sets the background image to scale with the world size
        GreenfootImage image = new GreenfootImage("BBQScene.jpg");
        image.scale(getWidth(), getHeight());
        setBackground(image);
        //Fades in
        addObject(new fade(null), getWidth()/2, getHeight()/2);
        nextWorldTimer.mark();
    }
    
    /**
     * This act loop changes the world to the statsScreen world
     * when you press space, or after 10,000 milliseconds (10 seconds).
     */
    public void act()
    {
        if(Greenfoot.isKeyDown("space") && nextWorldToggle == false)
        {
            statsScreen stats = new statsScreen(Utils.getStats());
            fade fadeout = new fade(stats);
            addObject(fadeout, getWidth() / 2, getHeight() / 2);
            nextWorldToggle = true;
        }
        if(nextWorldTimer.millisElapsed() > 10000 && nextWorldToggle == false)
        {
            statsScreen stats = new statsScreen(Utils.getStats());
            fade fadeout = new fade(stats);
            addObject(fadeout, getWidth() / 2, getHeight() / 2);
            nextWorldToggle = true;
        }
    }
}

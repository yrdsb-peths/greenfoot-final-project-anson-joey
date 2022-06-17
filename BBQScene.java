import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BBQScene here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BBQScene extends World
{
    public GreenfootSound endMusic = new GreenfootSound("endMusic.mp3");
    SimpleTimer nextWorldTimer = new SimpleTimer();
    private boolean nextWorldToggle = false;
    public BBQScene()
    {    
        super(1000, 800, 1); 
        endMusic.play();
        GreenfootImage image = new GreenfootImage("BBQScene.jpg");
        image.scale(getWidth(), getHeight());
        setBackground(image);
        addObject(new fade(null), getWidth()/2, getHeight()/2);
        nextWorldTimer.mark();
    }
    
    public void act()
    {
        if(Greenfoot.isKeyDown("space") && nextWorldToggle == false)
        {
            endMusic.stop();
            statsScreen stats = new statsScreen(Utils.getStats());
            fade fadeout = new fade(stats);
            addObject(fadeout, getWidth() / 2, getHeight() / 2);
            nextWorldToggle = true;
        }
        if(nextWorldTimer.millisElapsed() > 10000 && nextWorldToggle == false)
        {
            endMusic.stop();
            statsScreen stats = new statsScreen(Utils.getStats());
            fade fadeout = new fade(stats);
            addObject(fadeout, getWidth() / 2, getHeight() / 2);
            nextWorldToggle = true;
        }
    }
}

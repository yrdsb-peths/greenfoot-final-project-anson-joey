import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class changeWorldButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class button extends gameAssets
{
    private int level;
    boolean mouseDown;
    GreenfootImage pressedImage;
    public button(int x, int y, int level, GreenfootImage unpressedButtonImage, GreenfootImage pressedButtonImage)
    {
        this.level = level;
        mouseDown = false;
        
        GreenfootImage unpressedImage = new GreenfootImage(unpressedButtonImage); 
        pressedImage = new GreenfootImage(pressedButtonImage); 
        
        unpressedImage.scale(x, y);
        pressedImage.scale(x, y);
        setImage(unpressedImage);
    }
    
    /**
     * Act - do whatever the changeWorldButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(!mouseDown && Greenfoot.mousePressed(this))
        {
            setImage(pressedImage);
            mouseDown = true;
        }
        
        if(mouseDown && Greenfoot.mouseClicked(this))
        {
            ((MyWorld)getWorld()).teleportWorld(level);
        }
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * 
 * Joey & Anson
 * June 17, 2022
 */
public class button extends gameAssets
{
    private int level;
    boolean mouseDown;
    GreenfootImage pressedImage;
    /**
     * Creates construtor to create button
     * 
     * @param x determines width
     * @param y determines height
     * @param unpressedButtonImage determines default button image
     * @param pressedButtonImage determines image to display when button is pressed
     */
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
     * Changes the image and world SquatKing.class is in if button actor is pressed
     * 
     * No returns or parameters
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

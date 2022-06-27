import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Wind actor class;
 * Scrolling display image to indicate that a level is windy
 * 
 * Joey & Anson
 * June 17, 2022
 */
public abstract class wind extends gameAssets
{
    private int scrollSpeed = 7;
    /**
     * Creates wind scrolling animation to either side
     * 
     * @param direction determines scrolling direction
     */
    public void shiftX(String direction)
    {
        if(direction.equals("right"))
        {
            setLocation(getX() + scrollSpeed, getY());
            if(getX() >= 1500)
            {
                setLocation(-500, getY());
            }
        }
        if(direction.equals("left"))
        {
            setLocation(getX() - scrollSpeed, getY());
            if(getX() <= -500)
            {
                setLocation(1500, getY());
            }
        }
    }
}

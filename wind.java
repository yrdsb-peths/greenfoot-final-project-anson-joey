import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class wind here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class wind extends gameAssets
{
    private int scrollSpeed = 7;
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

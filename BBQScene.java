import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BBQScene here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BBQScene extends World
{
    public BBQScene()
    {    
        super(1000, 800, 1); 
        addObject(new fade(null), getWidth()/2, getHeight()/2);
    }
}

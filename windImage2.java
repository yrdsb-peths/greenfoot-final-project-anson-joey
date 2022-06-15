import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class windImage1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class windImage2 extends wind
{
    private String direction;
    public windImage2(String direction)
    {
        this.direction = direction;
    }
    /**
     * Act - do whatever the windImage1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        shiftX(direction);
    }
}

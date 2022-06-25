import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * windImage2 actor class;
 * Scrolling display image to indicate that a level is windy
 * 
 * Joey & Anson
 * June 17, 2022
 */
public class windImage2 extends wind
{
    private String direction;
    public windImage2(String direction)
    {
        this.direction = direction;
    }
    public void act()
    {
        shiftX(direction);
    }
}

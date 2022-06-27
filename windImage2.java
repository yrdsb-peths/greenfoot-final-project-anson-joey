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
    /**
     * Creates second wind image that scrolls and resets to create a cohesive image
     * 
     * @param direction sets direction to scroll
     */
    public windImage2(String direction)
    {
        this.direction = direction;
    }
    public void act()
    {
        shiftX(direction);
    }
}

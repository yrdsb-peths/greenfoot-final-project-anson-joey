import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * windImage1 actor class;
 * Scrolling display image to indicate that a level is windy
 * 
 * Joey & Anson
 * June 17, 2022
 */
public class windImage1 extends wind
{
    private String direction;
    /**
     * Creates first wind image that scrolls and resets to create a cohesive image
     * 
     * @param world sets which world to create the wind image
     * @param direction sets direction to scroll
     */
    public windImage1(MyWorld world, String direction)
    {
        this.direction = direction;
        
        windImage2 wind2 = new windImage2(direction);
        world.addObject(wind2, -500, 400);
    }
    public void act()
    {
        shiftX(direction);
    }
}

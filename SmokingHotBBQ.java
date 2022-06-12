import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SmokingHotBBQ here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SmokingHotBBQ extends Terrain
{
    public GreenfootSound endMusic = new GreenfootSound("endMusic.mp3");
    public SmokingHotBBQ(int x, int y)
    {
        GreenfootImage image = getImage();
        image.scale(x, y);
    }
    
    public void act()
    {
        if(isTouching(SquatKing.class))
        {
            endMusic.playLoop();
        }
    }
}

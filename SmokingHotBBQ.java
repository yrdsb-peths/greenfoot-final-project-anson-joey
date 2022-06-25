import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * SmokingHotBBQ actor class;
 * It's the win-condition. Touch it to win
 * 
 * Joey & Anson
 * June 17, 2022
 */
public class SmokingHotBBQ extends gameAssets
{
     /** 
     * Constructor for SmokingHotBBQ actor
     * 
     * @param x determines image width
     * @param y determines image height
     * @return none
     */
    public SmokingHotBBQ(int x, int y)
    {
        GreenfootImage image = getImage();
        image.scale(x, y);
    }
}

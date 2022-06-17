import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class iceBlock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class iceBlock extends Terrain
{
    GreenfootImage snow = new GreenfootImage("images/Snow terrain/tile002.png");
    //Creates construtor with custom or default image using overlooading
    public iceBlock(int x, int y, GreenfootImage terrainType)
    {
        GreenfootImage image = new GreenfootImage(terrainType);
        image.scale(x, y);
        setImage(image);
    }
    
    public iceBlock(int x, int y)
    {
        GreenfootImage image = snow;
        image.scale(x, y);
        setImage(image);
    }
}

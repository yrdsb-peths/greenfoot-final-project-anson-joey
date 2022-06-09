import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class iceBlock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class iceBlock extends Terrain
{
    GreenfootImage grass = new GreenfootImage("images/Grass terrain/tile002.png");
    
    public iceBlock(int x, int y, GreenfootImage terrainType)
    {
        GreenfootImage image = terrainType;
        image.scale(x, y);
        setImage(image);
    }
    
    public iceBlock(int x, int y)
    {
        GreenfootImage image = grass;
        image.scale(x, y);
        setImage(image);
    }
}

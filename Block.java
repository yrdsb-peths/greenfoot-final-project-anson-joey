import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class platform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Block extends Terrain
{  
    GreenfootImage grass = new GreenfootImage("tile001.png");
    
    public Block(int x, int y, GreenfootImage terrainType)
    {
        GreenfootImage image = terrainType;
        image.scale(x, y);
        setImage(image);
    }
    
    public Block(int x, int y)
    {
        GreenfootImage image = grass;
        image.scale(x, y);
        setImage(image);
    }
}

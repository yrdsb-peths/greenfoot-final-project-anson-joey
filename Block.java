import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class platform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Block extends Terrain
{  
    public GreenfootImage grass = new GreenfootImage("images/Grass terrain/tile002.png");
    //Creates construtor with custom or default image using overlooading
    public Block(int x, int y, GreenfootImage terrainType)
    {
        GreenfootImage image = new GreenfootImage(terrainType); 
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

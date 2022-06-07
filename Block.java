import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class platform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Block extends Terrain
{  
    /**
     * Act - do whatever the platform wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Block(int x, int y)
    {
        GreenfootImage image = new GreenfootImage("images/Snow terrain/tile001.png");
        image.scale(x, y);
        setImage(image);
    }
}

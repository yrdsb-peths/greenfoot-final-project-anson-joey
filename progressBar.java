import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ProgressBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class progressBar extends gameAssets
{
    /**
     * Act - do whatever the ProgressBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public progressBar(int x, int y, GreenfootImage progressImage)
    {
        GreenfootImage image = new GreenfootImage(progressImage); 
        image.scale(x, y);
        setImage(image);    
    }
}

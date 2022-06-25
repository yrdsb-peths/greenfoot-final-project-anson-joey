import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * progressBar actor class;
 * Image to indicate your progress to the top
 * 
 * Joey & Anson
 * June 17, 2022
 */
public class progressBar extends gameAssets
{
    /** 
     * Constructor for progressBar actor
     * 
     * @param x determines image width
     * @param y determines image height
     * @param progressImage determines which image to use based on which level you are on
     * @return none
     */
    public progressBar(int x, int y, GreenfootImage progressImage)
    {
        GreenfootImage image = new GreenfootImage(progressImage); 
        image.scale(x, y);
        setImage(image);    
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * iceBlock actor class;
 * Used to create the game map. Interacts with SquatKing actor
 * 
 * Joey & Anson
 * June 17, 2022
 */
public class iceBlock extends Terrain
{
     /** 
     * Constructor for iceBlock actor
     * 
     * @param x determines image width
     * @param y determines image height
     * @param terrainType determines image used
     * @return none
     */
    public iceBlock(int x, int y, GreenfootImage terrainType)
    {
        GreenfootImage image = new GreenfootImage(terrainType);
        image.scale(x, y);
        setImage(image);
    }
}

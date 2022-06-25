import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Block actor class;
 * Used to create the game map. Interacts with SquatKing actor
 * 
 * Joey & Anson
 * June 17, 2022
 */
public class Block extends Terrain
{  
    public GreenfootImage grass = new GreenfootImage("images/Grass terrain/tile002.png");
    /** 
     * Constructor for Block actor
     * 
     * @param x determines image width
     * @param y determines image height
     * @param terrainType determines image used
     * @return none
     */
    public Block(int x, int y, GreenfootImage terrainType)
    {
        GreenfootImage image = new GreenfootImage(terrainType); 
        image.scale(x, y);
        setImage(image);
    }
    /** 
     * Constructor for Block actor
     * Method overloading allowing for a default block.
     * 
     * @param x determines image width
     * @param y determines image height
     * @return none
     */
    public Block(int x, int y)
    {
        GreenfootImage image = grass; 
        image.scale(x, y);
        setImage(image);
    }
}

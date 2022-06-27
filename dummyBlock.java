import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creates a fake block that can be walked through 
 * 
 * Joey & Anson
 * June 17, 2022
 */
public class dummyBlock extends gameAssets
{   
    public GreenfootImage grass = new GreenfootImage("images/Grass terrain/tile002.png");
    SquatKing actor;
    /**
     * Creates construtor with custom or default images using overloading
     * 
     * @param x determines width
     * @param y determines height
     * @param terrainType determines image of actor
     */
    public dummyBlock(int x, int y, GreenfootImage terrainType)
    {
        GreenfootImage image = new GreenfootImage(terrainType); 
        image.scale(x, y);
        setImage(image);
    }
    
    public dummyBlock(int x, int y)
    {
        GreenfootImage image = grass;
        image.scale(x, y);
        setImage(image);
    }
    /**
     * Changes the transparency of the actor if touching SquatKing.class
     * 
     * No returns or parameters
     */
    public void act()
    {
        actor = ((MyWorld)getWorld()).getObjects(SquatKing.class).get(0);
        if(isTouching(SquatKing.class))
        {
            getImage().setTransparency(100);
        }
        else
        {
            getImage().setTransparency(255);
        }
    }
}

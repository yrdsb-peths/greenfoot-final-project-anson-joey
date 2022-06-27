import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creates a class to keep track of statistics using class variables
 * 
 * Joey & Anson
 * June 17, 2022
 */
public class Utils extends Actor
{
    private static int jumps = 0, lvlsFall = 0, time = 0;
    private static int stats[] = new int[3];
    
    public void act()
    {
        stats[0] = jumps;
        stats[1] = lvlsFall;
        stats[2] = time;
    }
    
    /** 
     * Increases jumps by 1 
     * 
     * No parameters or returns
     */
    public static void setJumps()
    {
        jumps++;
    }
    
    /** 
     * Increases levels fallen by 1
     * 
     * No parameters or returns
     */
    public static void setlvlsFall()
    {
        lvlsFall++;
    }
    /**
     * Sets the total time the player has played the game
     * 
     * No parameters or returns
     */
    public static void setTime(int gameTime)
    {
        time = gameTime;
    }
    /**
     * getter methods to retrieve all stats using an array
     * 
     * @return getStats returns all stats in a array
     */
    public static int[] getStats()
    {
        return stats;
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creates a class to keep track of statistics using class variables
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
    
    public static void setJumps()
    {
        jumps++;
    }
    public static void setlvlsFall()
    {
        lvlsFall++;
    }
    public static void setTime(int gameTime)
    {
        time = gameTime;
    }
    
    public static int[] getStats()
    {
        return stats;
    }
}

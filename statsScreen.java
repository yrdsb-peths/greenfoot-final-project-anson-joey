import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * statsScreen world class; It displays your gameplay stats.
 * Displays your time, jump #, and levels fallen # taken to complete the game.
 * Grabs these stats from the Utils class that keeps track of them.
 * 
 * Joey & Anson
 * June 17, 2022
 */
public class statsScreen extends World
{
    Label time = new Label(100, 50);
    Label jumps = new Label(100, 50);
    Label intFall = new Label(100, 50);
    int hours = 0, mins = 0, seconds = 0, totalSeconds;
    int stats[];
    /**
     * Constructor for objects of class statsScreen.
     * 
     * @param stats[] array holding 
     */
    public statsScreen(int stats[])
    {    
        super(1000, 800, 1); 
        this.stats = stats;
        addObject(time, 500, 220);
        addObject(jumps, 500, 400);
        addObject(intFall, 500, 600);
        addObject(new fade(null), getWidth()/2, getHeight()/2);
    }
    /** 
     * Sets stats variables locally in statsScreen class for display.
     * Calculates formats time from milliseconds to hours, minutes, and seconds.
     * 
     * No parameters or returns
     */
    public void act()
    {
        int seconds = (stats[2] / 1000) % 60 ;
        if(totalSeconds >= 60)
        {
            int mins = ((stats[2] / (1000*60)) % 60);
        }
        if(totalSeconds >= 3600)
        {
            int hours = ((stats[2] / (1000*60*60)) % 24);
        }
        jumps.setValue(stats[0]);
        intFall.setValue(stats[1]);
        time.setValue(((hours < 10)? "0"+ hours : hours) + " : " + ((mins < 10)? "0"+ mins : mins) + " : " + ((seconds < 10)? "0"+ seconds : seconds));
    }
}

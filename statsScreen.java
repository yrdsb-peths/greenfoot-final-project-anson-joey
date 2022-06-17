import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class statsScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
     */
    public statsScreen(int stats[])
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1); 
        this.stats = stats;
        addObject(time, 500, 220);
        addObject(jumps, 500, 400);
        addObject(intFall, 500, 600);
        addObject(new fade(null), getWidth()/2, getHeight()/2);
    }
    
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

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ScrollObjects here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class ScrollObjects extends Actor
{
    private int yMoveInt;

    public void moveYUp()
    {
        Actor who = getOneIntersectingObject(bluejay.class);
        yMoveInt = ((MyWorld)getWorld()).getYCoord();
        setLocation(getX(), getY() + ((MyWorld)getWorld()).getSpeed());
        yMoveInt -= 1;
        ((MyWorld)getWorld()).setYCoord(yMoveInt);
    }
    
    public void moveYDown()
    {
        yMoveInt = ((MyWorld)getWorld()).getYCoord();
        setLocation(getX(), getY() - ((MyWorld)getWorld()).getSpeed());
        yMoveInt += 1;
        ((MyWorld)getWorld()).setYCoord(yMoveInt);
    }
}

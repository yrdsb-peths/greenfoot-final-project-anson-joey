import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BoundDetection here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class BoundDetection extends Actor
{
    private boolean onSlopeLeft, onSlopeRight;
    private int SPEED = 5;
    public void act()
    {
        // Add your action code here.
    }
    
        public boolean onGround()
    {
        boolean isOnGround = false;
        if (getOneObjectAtOffset(getImage().getWidth()/2, getImage().getHeight()/2, Block.class) != null ||
            getOneObjectAtOffset(getImage().getWidth()/-2, getImage().getHeight()/2, Block.class) != null)
        {
            isOnGround = true;
        }
        return isOnGround;
    }
    
    public boolean bumpedHead()
    {
        boolean didBumpHead = false;
        if (getOneObjectAtOffset(getImage().getWidth()/2, getImage().getHeight()/-2, Block.class) != null ||
            getOneObjectAtOffset(getImage().getWidth()/-2, getImage().getHeight()/-2, Block.class) != null)
        {
            didBumpHead = true;
        }
        return didBumpHead;
    }
    
    public boolean canMoveLeftSlope()
    {
        boolean canMoveLS = true;
        if(getOneObjectAtOffset(getImage().getWidth()/-2-SPEED - 1, getImage().getHeight()/-2, slopeLeftRight.class) != null || 
            getOneObjectAtOffset(getImage().getWidth()/-2-SPEED - 1, getImage().getHeight()/2-1, slopeLeftRight.class) != null)
            {
                canMoveLS = false;
            }
        return canMoveLS;
    }
    
    public boolean canMoveRightSlope()
    {
        boolean canMoveRS = true;
        if(getOneObjectAtOffset(getImage().getWidth()/2 + SPEED, getImage().getHeight()/-2, slopeRightLeft.class) != null ||
            getOneObjectAtOffset(getImage().getWidth()/2 + SPEED, getImage().getHeight()/2-1, slopeRightLeft.class) != null)
            {
                canMoveRS = false;
            }
        return canMoveRS;
    }
    
    public boolean canMoveLeft()
    {
        boolean canMoveL = true;
        if (getOneObjectAtOffset(getImage().getWidth()/-2-SPEED - 1, getImage().getHeight()/-2, Block.class) != null ||
            getOneObjectAtOffset(getImage().getWidth()/-2-SPEED - 1, getImage().getHeight()/2-1, Block.class) != null)
            {
                canMoveL = false;
            }
        return canMoveL;
    }
    
    public boolean canMoveRight()
    {
        boolean canMoveR = true;
        if (getOneObjectAtOffset(getImage().getWidth()/2 + SPEED, getImage().getHeight()/-2, Block.class) != null ||
            getOneObjectAtOffset(getImage().getWidth()/2 + SPEED, getImage().getHeight()/2-1, Block.class) != null)
            {
                canMoveR = false;
            }
        return canMoveR;
    }
    
    public int map(int number, int givenSmallNum, int givenBigNum, int desiredSmallNum, int desiredBigNum)
    {
        return (number - givenSmallNum) * (desiredBigNum - desiredSmallNum) / (givenBigNum - givenSmallNum) + desiredSmallNum;
    }
}

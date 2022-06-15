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
    //Checks if actor is on ground
    public boolean onGround()
    {
        boolean isOnGround = false;
        if (getOneObjectAtOffset(getImage().getWidth()/2 - 10, getImage().getHeight()/2, Block.class) != null ||
            getOneObjectAtOffset(getImage().getWidth()/-2 + 10, getImage().getHeight()/2, Block.class) != null)
        {
            isOnGround = true;
        }
        return isOnGround;
    }
    //Check if actor is on ice ground
    public boolean onIceGround()
    {
        boolean isOnIceGround = false;
        if (getOneObjectAtOffset(getImage().getWidth()/2 - 10, getImage().getHeight()/2, iceBlock.class) != null ||
            getOneObjectAtOffset(getImage().getWidth()/-2 + 10, getImage().getHeight()/2, iceBlock.class) != null)
        {
            isOnIceGround = true;
        }
        return isOnIceGround;
    }
    //Check if actor hit their head
    public boolean bumpedHead()
    {
        boolean didBumpHead = false;
        if (getOneObjectAtOffset(getImage().getWidth()/2-10, getImage().getHeight()/-2, Terrain.class) != null || //top right
            getOneObjectAtOffset(getImage().getWidth()/-2+10, getImage().getHeight()/-2, Terrain.class) != null) //top left
        {
            didBumpHead = true;
        }
        return didBumpHead;
    }
    //Checks if actor can move left (checks for slopesLeftRight class only)
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
    //Checks if actor can move right (checks for slopeRightLeft class only)
    public boolean canMoveRightSlope()
    {
        boolean canMoveRS = true;
        if(getOneObjectAtOffset(getImage().getWidth()/2 + SPEED + 1, getImage().getHeight()/-2, slopeRightLeft.class) != null ||
            getOneObjectAtOffset(getImage().getWidth()/2 + SPEED + 1, getImage().getHeight()/2-1, slopeRightLeft.class) != null)
            {
                canMoveRS = false;
            }
        return canMoveRS;
    }
    //Check if actor can move left (checks for terrain class only)
    public boolean canMoveLeft()
    {
        boolean canMoveL = true;
        if (getOneObjectAtOffset(getImage().getWidth()/-2-SPEED - 1, getImage().getHeight()/-2, Terrain.class) != null ||
            getOneObjectAtOffset(getImage().getWidth()/-2-SPEED - 1, getImage().getHeight()/2-1, Terrain.class) != null)
            {
                canMoveL = false;
            }
        return canMoveL;
    }
    //Check if actor can move right (checks for terrain class only)
    public boolean canMoveRight()
    {
        boolean canMoveR = true;
        if (getOneObjectAtOffset(getImage().getWidth()/2 + SPEED + 1, getImage().getHeight()/-2, Terrain.class) != null ||
            getOneObjectAtOffset(getImage().getWidth()/2 + SPEED + 1, getImage().getHeight()/2-1, Terrain.class) != null)
            {
                canMoveR = false;
            }
        return canMoveR;
    }
    //Check if actor is tounching a BBQ actor
    public boolean isTouchingBBQ()
    {
        boolean touchingBBQ = false;
        if(isTouching(SmokingHotBBQ.class))
        {
            touchingBBQ = true;
        }
        return touchingBBQ;
    }
    //Re-maps a number from one range to another
    public int map(int number, int givenSmallNum, int givenBigNum, int desiredSmallNum, int desiredBigNum)
    {
        return (number - givenSmallNum) * (desiredBigNum - desiredSmallNum) / (givenBigNum - givenSmallNum) + desiredSmallNum;
    }
}

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
        if(getOneObjectAtOffset(getImage().getWidth()/2 + SPEED + 1, getImage().getHeight()/-2, slopeRightLeft.class) != null ||
            getOneObjectAtOffset(getImage().getWidth()/2 + SPEED + 1, getImage().getHeight()/2-1, slopeRightLeft.class) != null)
            {
                canMoveRS = false;
            }
        return canMoveRS;
    }
    
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
    
    public boolean isTouchingBBQ()
    {
        boolean touchingBBQ = false;
        if(isTouching(SmokingHotBBQ.class))
        {
            touchingBBQ = true;
        }
        return touchingBBQ;
    }
    
    public int map(int number, int givenSmallNum, int givenBigNum, int desiredSmallNum, int desiredBigNum)
    {
        return (number - givenSmallNum) * (desiredBigNum - desiredSmallNum) / (givenBigNum - givenSmallNum) + desiredSmallNum;
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Super class for the SquatKing player actor holding
 * most of the methods for detecting collisions.
 * 
 * Joey & Anson
 * June 17, 2022
 */
public abstract class BoundDetection extends Actor
{
    private boolean onSlopeLeft, onSlopeRight;
    private int SPEED = 5;

    /**
     * Checks if actor is on a ground block
     * 
     * @return true if on a ground block, false if not on a ground block
     */
    public boolean onGround()
    {
        boolean isOnGround = false;
        if (getOneObjectAtOffset(getImage().getWidth()/2 - 10, getImage().getHeight()/2, Block.class) != null || //Checks bottom right 
        getOneObjectAtOffset(getImage().getWidth()/-2 + 10, getImage().getHeight()/2, Block.class) != null) //Checks bottom left
        {
            isOnGround = true;
        }
        return isOnGround;
    }

    /**
     * Checks if actor is on a ice ground block
     * 
     * @return true if on a ice ground block, false if not on a ice ground block
     */
    public boolean onIceGround()
    {
        boolean isOnIceGround = false;
        if (getOneObjectAtOffset(getImage().getWidth()/2 - 10, getImage().getHeight()/2, iceBlock.class) != null || //Checks bootm right
        getOneObjectAtOffset(getImage().getWidth()/-2 + 10, getImage().getHeight()/2, iceBlock.class) != null) //Checks bottom left
        {
            isOnIceGround = true;
        }
        return isOnIceGround;
    }

    /**
     * Checks if actor bumped their head on a block
     * 
     * @return true if bumped, false if not bumped
     */
    public boolean bumpedHead()
    {
        boolean didBumpHead = false;
        if (getOneObjectAtOffset(getImage().getWidth()/2-10, getImage().getHeight()/-2, Terrain.class) != null || //Checks top right
        getOneObjectAtOffset(getImage().getWidth()/-2+10, getImage().getHeight()/-2, Terrain.class) != null) //Checks top left
        {
            didBumpHead = true;
        }
        return didBumpHead;
    }

    /**
     * Checks if actor can move left (checks for slopeLeftRight only)
     * 
     * @return true if there is no slopeLeftRight block on the left , false if there is
     */
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

    /**
     * Checks if actor can move right (checks for slopeRightLeft only)
     * 
     * @return true if there is no slopeRightLeft block on the right, false if there is 
     */
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

    /**
     * Checks if actor can move left (checks for terrain class only)
     * 
     * @return true if there is no block on the left, false if there is
     */
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

    /**
     * Checks if actor can move right (checks for terrain class only)
     * 
     * @return true if there is no block on the right, false if there is
     */
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

    /**
     * Checks if actor is touching a BBQ class actor
     * 
     * @return true if is, false if not
     */
    public boolean isTouchingBBQ()
    {
        boolean touchingBBQ = false;
        if(isTouching(SmokingHotBBQ.class))
        {
            touchingBBQ = true;
        }
        return touchingBBQ;
    }
    
    /**
     * Maps a number from a range of values to another
     * 
     * @param number the number being mapped
     * @param givenSmallNum the original min range
     * @param givenBigNum the original max range
     * @param desiredSmallNum the mapped min range
     * @param desiredBigNum the mapped max range
     * 
     * @return number mapped from given to desired range
     */
    public int map(int number, int givenSmallNum, int givenBigNum, int desiredSmallNum, int desiredBigNum)
    {
        return (number - givenSmallNum) * (desiredBigNum - desiredSmallNum) / (givenBigNum - givenSmallNum) + desiredSmallNum;
    }
}

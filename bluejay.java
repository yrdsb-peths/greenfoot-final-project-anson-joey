import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;

public class bluejay extends Actor
{
    private static int gravity = 1;
    private String direction;
    private int velocityY, velocityX;
    private boolean onSlope;
    private int speed = 5;
    private int chargeTime;
    SimpleTimer timer = new SimpleTimer();
    public bluejay(int x, int y)
    {
        GreenfootImage image = getImage();
        image.scale(x, y);
        setImage(image);
    }

    public void act()
    {
        fallPhysics();
        if(Greenfoot.isKeyDown("up") && onGround())
        {
            jumpTimer(); 
        }
        walkMovement();
    }
    
    public void setVelocity(int velocityY)
    {
        this.velocityY = velocityY;
    }

    public void fallPhysics()
    {   
        if(onSlope)
        {   
            setLocation(getX() + 8, getY() + 8);
            velocityY = 0;
        }  
        
        setLocation(getX(), getY() + velocityY);
        if(onGround() || onSlope)
        {
            velocityY = 0;
            velocityX = 0;
            while(onGround())
            {
                setLocation(getX(), getY()-1);
            }
            setLocation(getX(), getY()+1);
        }
        else if (velocityY < 0 && bumpedHead())
        {
            velocityY = 0;
            
            while(bumpedHead())
            {
                setLocation(getX(), getY()+1);
            }
        }
        else
        {
            velocityY += gravity; 
        }
        
        setLocation(getX() + velocityX, getY());
        if(!canMoveRight() || !canMoveLeft())
        {
            velocityX = velocityX * -1;
        }
        onSlope = false;
    }

    public void jumpTimer()
    {
        timer.mark();
        while(Greenfoot.isKeyDown("up") && onGround())
        {
            chargeTime = timer.millisElapsed();
        }
        if(chargeTime > 1000)
        {
            chargeTime = 1000;
        }
        
        velocityY = map(chargeTime, 0, 1000, 0 , 20) * -1;
        velocityX = map(chargeTime, 0, 1000, 0, 10);
        if(direction.equals("null"))
        {
            velocityX = 0;
        }
        else if(direction.equals("left"))
        {
            velocityX = Math.abs(velocityX) * -1;
        }
        else if(direction.equals("right"))
        {
            velocityX = Math.abs(velocityX);
        }   
    }
        
    public void walkMovement()
    {
        if(!Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right"))
        {
            direction = "null";
        }
        
        if((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) && canMoveLeft()){
            direction = "left";
            setLocation(getX() - speed, getY());
        }
        
        if((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) && canMoveRight()){
            direction = "right";
            setLocation(getX() + speed, getY());
        }
    }

    public void setvelocityY(int velocityY)
    {
        this.velocityY = velocityY;
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
    
    public boolean canMoveLeft()
    {
        boolean canMoveL = true;
        if (getOneObjectAtOffset(getImage().getWidth()/-2-speed - 1, getImage().getHeight()/-2, Block.class) != null ||
            getOneObjectAtOffset(getImage().getWidth()/-2-speed - 1, getImage().getHeight()/2-1, Block.class) != null)
            //getOneObjectAtOffset(getImage().getWidth()/-2-speed - 1, getImage().getHeight()/-2, slopeLeftRight.class) != null || 
            //getOneObjectAtOffset(getImage().getWidth()/-2-speed - 1, getImage().getHeight()/2-1, slopeLeftRight.class) != null )
            {
                canMoveL = false;
            }
        return canMoveL;
    }
    
    public boolean canMoveRight()
    {
        boolean canMoveR = true;
        if (getOneObjectAtOffset(getImage().getWidth()/2 + speed, getImage().getHeight()/-2, Block.class) != null ||
            getOneObjectAtOffset(getImage().getWidth()/2 + speed, getImage().getHeight()/2-1, Block.class) != null)
            {
                canMoveR = false;
            }
        return canMoveR;
    }
    
    public void onSlope(boolean onSlope)
    {
        this.onSlope = onSlope;
    }
    
    public int map(int number, int givenSmallNum, int givenBigNum, int desiredSmallNum, int desiredBigNum)
    {
        return (number - givenSmallNum) * (desiredBigNum - desiredSmallNum) / (givenBigNum - givenSmallNum) + desiredSmallNum;
    }
}

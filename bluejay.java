import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;

public class bluejay extends BoundDetection
{
    private static int GRAVITY = 1;
    private int SPEED = 5;
    private int velocityY, velocityX;
    private String direction;
    private boolean onSlopeLeft, onSlopeRight;
    private int chargeTime;
    
    private SimpleTimer timer = new SimpleTimer();
    
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
        if(onGround())
        {
            walkMovement();
        }
    }
    
    public void setVelocity(int velocityY)
    {
        this.velocityY = velocityY;
    }
    
    public void setvelocityY(int velocityY)
    {
        this.velocityY = velocityY;
    }
    
    public void fallPhysics()
    {   
        if(onSlopeLeft)
        {   
            setLocation(getX() + 8, getY() + 8);
            velocityY = 0;
        }
        if(onSlopeRight)
        {
            setLocation(getX() - 8, getY() + 8);
            velocityY = 0;
        }
        
        setLocation(getX(), getY() + velocityY);
        if(onGround() || onSlopeLeft || onSlopeRight)
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
            velocityY += GRAVITY; 
        }

        if(velocityX > 0 && canMoveRight())
        {
            setLocation(getX() + velocityX, getY());
        }
        if(velocityX < 0 && canMoveLeft())
        {
            setLocation(getX() + velocityX, getY());
        }
        if(!canMoveRight() || !canMoveLeft())
        {
            velocityX = velocityX * -1;
        }
        onSlopeLeft = false;
        onSlopeRight = false;
    }

    public void jumpTimer()
    {
        timer.mark();

        while(Greenfoot.isKeyDown("up") && onGround())
        {
            chargeTime = timer.millisElapsed();
            if(!Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right"))
            {
                direction = "null";
            }
            if(Greenfoot.isKeyDown("left"))
            {
                direction = "left";
            }
            if(Greenfoot.isKeyDown("right"))
            {
                direction = "right";
            }
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
        if((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) && canMoveLeft() && canMoveLeftSlope()){
            setLocation(getX() - SPEED, getY());
        }
        if((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) && canMoveRight() && canMoveRightSlope()){
            setLocation(getX() + SPEED, getY());
        }
    }
    
    public void onSlopeLeft(boolean onSlopeLeft)
    {
        this.onSlopeLeft = onSlopeLeft;
    }
    
    public void onSlopeRight(boolean onSlopeRight)
    {
        this.onSlopeRight = onSlopeRight;
    }
}

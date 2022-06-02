import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;

public class bluejay extends BoundDetection
{
    private static int GRAVITY = 1;
    private int SPEED = 5, imageIndex = 0;
    private int velocityY, velocityX;
    private String direction = "left";
    private boolean onSlopeLeft, onSlopeRight;
    private int chargeTime;
    
    private SimpleTimer timer = new SimpleTimer();
    private SimpleTimer animationTimer = new SimpleTimer();
    
    GreenfootImage walk[] = new GreenfootImage[4];
    GreenfootImage leftWalk[] = new GreenfootImage[4];
    public bluejay(int x, int y)
    {
        for(int i = 0; i < walk.length; i++)
        {
            GreenfootImage image = new GreenfootImage("walk" + i + ".png");
            image.scale(x, y);
            walk[i] = image;
        }
        
        for(int i = 0; i < leftWalk.length; i++)
        {
            leftWalk[i] = new GreenfootImage("walk" + i + ".png");
            leftWalk[i].scale(x, y);
            leftWalk[i].mirrorHorizontally();
        }
        setImage(walk[0]);
    }

    public void act()
    {
        animateWalk();
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
    
    public void animateWalk()
    {
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        animationTimer.mark();
        if(direction.equals("right"))
        {
            setImage(walk[imageIndex]);
            imageIndex = (imageIndex + 1) % walk.length;
        }
        if(direction.equals("left")) 
        {
            setImage(leftWalk[imageIndex]);
            imageIndex = (imageIndex + 1) % leftWalk.length;
        }
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
            direction = "left";
        }
        if((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) && canMoveRight() && canMoveRightSlope()){
            setLocation(getX() + SPEED, getY());
            direction = "right";
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

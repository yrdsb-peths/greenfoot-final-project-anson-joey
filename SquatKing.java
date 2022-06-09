import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;

public class SquatKing extends BoundDetection
{
    private static int GRAVITY = 1;
    private int SPEED = 5, imageIndex = 0;
    private int velocityY, velocityX;
    private String direction = "left", facing = "right";
    private boolean onSlopeLeft, onSlopeRight;
    private int chargeTime;
    
    private SimpleTimer timer = new SimpleTimer();
    private SimpleTimer animationTimer = new SimpleTimer();
    
    GreenfootImage walk[] = new GreenfootImage[4];
    GreenfootImage leftWalk[] = new GreenfootImage[4];
    GreenfootImage idle[] = new GreenfootImage[3];
    GreenfootImage leftIdle[] = new GreenfootImage[3];
    GreenfootImage jump[] = new GreenfootImage[3];
    GreenfootImage leftJump[] = new GreenfootImage[3];
    GreenfootImage rightSlopeImage, leftSlopeImage;

    public SquatKing(int x, int y)
    {
        for(int i = 0; i < walk.length; i++)
        {
            walk[i] = new GreenfootImage("walk" + i + ".png");
            walk[i].scale(x, y);
            
            leftWalk[i] = new GreenfootImage("walk" + i + ".png");
            leftWalk[i].scale(x, y);
            leftWalk[i].mirrorHorizontally();
        }
        for(int i = 0; i < idle.length; i++)
        {
            idle[i] = new GreenfootImage("idle" + i + ".png");
            idle[i].scale(x, y);
            
            leftIdle[i] = new GreenfootImage("idle" + i + ".png");
            leftIdle[i].scale(x, y);
            leftIdle[i].mirrorHorizontally();
        }
        for(int i = 0; i < jump.length; i++)
        {
            jump[i] = new GreenfootImage("jump" + i + ".png");
            jump[i].scale(x, y);
            
            leftJump[i] = new GreenfootImage("jump" + i + ".png");
            leftJump[i].scale(x, y);
            leftJump[i].mirrorHorizontally();
        }
        
        rightSlopeImage = new GreenfootImage("jumpWall.png");
        rightSlopeImage.scale(x, y);
        
        leftSlopeImage = new GreenfootImage("jumpWall.png");
        leftSlopeImage.scale(x, y);
        leftSlopeImage.mirrorHorizontally();

        setImage(idle[0]);
    }

    public void act()
    {
        changeArea();
        animate();
        fallPhysics();
        if(Greenfoot.isKeyDown("up") && onGround())
        {
            jumpTimer(); 
        }
        walkMovement();
    }
    
    public void changeArea()
    {
        if(getY() == 0)//going up
        {
            ((MyWorld)getWorld()).nextLevel();
            setLocation(getX(), 795);
        }
        if(getY() == 799) //going down
        {
            ((MyWorld)getWorld()).previousLevel();
            setLocation(getX(), 0);
        }
    }
    
    public void animate()
    {
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        animationTimer.mark();
        if(facing.equals("left") && !Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right"))
        {
            imageIndex = (imageIndex + 1) % idle.length;
            setImage(leftIdle[imageIndex]);
        }
        if(facing.equals("right") && !Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right"))
        {
            imageIndex = (imageIndex + 1) % leftIdle.length;
            setImage(idle[imageIndex]);
        }
        if(Greenfoot.isKeyDown("right"))
        {
            setImage(walk[imageIndex]);
            imageIndex = (imageIndex + 1) % walk.length;
        }
        if(Greenfoot.isKeyDown("left")) 
        {
            setImage(leftWalk[imageIndex]);
            imageIndex = (imageIndex + 1) % leftWalk.length;
        }
        if(velocityY < 0 && facing.equals("right"))
        {
            setImage(jump[1]);
        }
        if(velocityY < 0 && facing.equals("left"))
        {
            setImage(leftJump[1]);
        }
        if(velocityY > 0 && facing.equals("right"))
        {
            setImage(jump[2]);
        }
        if(velocityY > 0 && facing.equals("left"))
        {
            setImage(leftJump[2]);
        }
    }
    
    public void fallPhysics()
    {   
        if(onSlopeLeft)
        {   
            setImage(rightSlopeImage);
            setLocation(getX() + 5, getY() + 5);
            velocityY = 0;
            velocityX = 0;
        }
        if(onSlopeRight)
        {
            setImage(leftSlopeImage);
            setLocation(getX() - 5, getY() + 5);
            velocityY = 0;
            velocityX = 0;
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
        setImage(jump[0]);
        timer.mark();
        while(Greenfoot.isKeyDown("up") && onGround())
        {
            getWorld().repaint();
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
        velocityY = map(chargeTime, 0, 1000, 0 , 30) * -1;
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
        if(onGround())
        {
            if((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) && canMoveLeft() && canMoveLeftSlope()){
                setLocation(getX() - SPEED, getY());
                facing = "left";
            }
            if((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) && canMoveRight() && canMoveRightSlope()){
                setLocation(getX() + SPEED, getY());
                facing = "right";
            }
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
 import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;

public class SquatKing extends BoundDetection
{
    private static int GRAVITY = 1;
    private int SPEED = 3, imageIndex = 0, slideSpeed = 5, frames = 0, jumpFrames = 0;
    private int velocityY, velocityX, slideVelocity;
    private int chargeTime;
    private String direction = "left", facing = "right",  windDirection;
    private boolean onSlopeLeft, onSlopeRight, isWindyLvl;
    
    private boolean landed = true; //keeps track of when landing sfx should be played

    private SimpleTimer timer = new SimpleTimer();
    private SimpleTimer animationTimer = new SimpleTimer();

    GreenfootImage walk[] = new GreenfootImage[4];
    GreenfootImage leftWalk[] = new GreenfootImage[4];
    GreenfootImage idle[] = new GreenfootImage[3];
    GreenfootImage leftIdle[] = new GreenfootImage[3];
    GreenfootImage jump[] = new GreenfootImage[3];
    GreenfootImage leftJump[] = new GreenfootImage[3];
    GreenfootImage rightSlopeImage, leftSlopeImage;
    
    windImage1 wind1Actor;
    windImage2 wind2Actor;

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
        frames++;
        changeArea();
        animate();
        fallPhysics();
        wind();
        if(Greenfoot.isKeyDown("up") && (onGround() || onIceGround()))
        {
            Utils.setJumps();
            jumpTimer(); 
        }
        walkMovement();
    }

    public void wind()
    {
        if(isWindyLvl == true)
        {
            if(onGround())
            {
                if(windDirection == "right" && canMoveRight())
                {
                    setLocation(getX() + 1, getY());
                }
                if(windDirection == "left" && canMoveLeft())
                {       
                    setLocation(getX() - 1, getY());
                }
            }
            else
            {
                if(windDirection == "right" && canMoveRight())
                {
                    setLocation(getX() + 3, getY());
                }
                if(windDirection == "left" && canMoveLeft())
                {       
                    setLocation(getX() - 3, getY());
                }
            }
        }
    }

    public void changeArea()
    {
        if(getY() < 0)//going up
        {
            ((MyWorld)getWorld()).nextLevel();
            setLocation(getX(), 795);
        }
        if(getY() > 800) //going down
        {
            ((MyWorld)getWorld()).previousLevel();
            setLocation(getX(), 0);
            Utils.setlvlsFall();
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

        if(velocityY == map(chargeTime, 0, 1000, 5, 25) * -1) // plays sound when jumping
        {
            Greenfoot.playSound("jumpSound.mp3");
            landed = false;
        }

        setLocation(getX(), getY() + velocityY);
        if(onIceGround())
        {
            while(onIceGround())
            {
                setLocation(getX(), getY()-1);
            }
            setLocation(getX(), getY()+1);
            velocityY = 0;

            if(landed == false) // plays sound when landing
            {
                Greenfoot.playSound("iceLandSound.mp3");
                landed = true;
            }

            if(velocityX > 0)
            {
                slideVelocity = map(velocityX, 1, 10, 2, 5);
                velocityX = 0;
            }
            else if(velocityX < 0)
            {
                slideVelocity = (map(velocityX * -1, 1, 10, 2, 5)) * -1;
                velocityX = 0;
            }

            if(slideVelocity > 0)
            {
                if(frames%5 == 0)
                {
                    slideVelocity--;
                }
            }
            else if(slideVelocity < 0)
            {
                if(frames%5 == 0)
                {
                    slideVelocity++;    
                }
            }

        }
        else if(onGround())
        {
            velocityY = 0;
            velocityX = 0;
            slideVelocity = 0;
            if(landed == false) // plays sound when landing
            {
                Greenfoot.playSound("landSound.mp3");
                landed = true;
            }
            while(onGround())
            {
                setLocation(getX(), getY()-1);
            }
            setLocation(getX(), getY()+1);
        }
        else
        {
            velocityY += GRAVITY; 
        }
        
        if(bumpedHead()) // bouncing off ceilings
        {
            velocityY = 0;
            while(bumpedHead())
            {
                setLocation(getX(), getY()+1);
            }
            Greenfoot.playSound("bumpSound.mp3"); 
        }
        if(!canJumpRight() || !canJumpLeft()) // bouncing off walls
        {
            velocityX = velocityX * -1;
            if(velocityX != 0) //so that it doesn't play when standing next to wall
            {
                Greenfoot.playSound("bumpSound.mp3");   
            }
        }
        
        if(velocityX > 0 && canJumpRight() || (slideVelocity > 0 && canJumpRight()))
        {
            setLocation(getX() + velocityX, getY());
            setLocation(getX() + slideVelocity, getY());
        }
        if((velocityX < 0 && canJumpLeft()) || (slideVelocity < 0 && canJumpLeft()))
        {
            setLocation(getX() + velocityX, getY());
            setLocation(getX() + slideVelocity, getY());
        }
        
        onSlopeLeft = false;
        onSlopeRight = false;
    }

    public void jumpTimer()
    {
        setImage(jump[0]);
        timer.mark();
        
        while(Greenfoot.isKeyDown("up") && (onGround() || onIceGround()))
        {
            getWorld().repaint();
            jumpFrames++;
            if(isWindyLvl && jumpFrames % 2 == 0)
            {
                wind1Actor = ((MyWorld)getWorld()).getObjects(windImage1.class).get(0);
                wind2Actor = ((MyWorld)getWorld()).getObjects(windImage2.class).get(0);
                wind1Actor.shiftX(windDirection);
                wind2Actor.shiftX(windDirection);
            }
            
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
        velocityY = map(chargeTime, 0, 1000, 5, 25) * -1;
        velocityX = map(chargeTime, 0, 1000, 5, 10);
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
        if(onIceGround())
        {
            if((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) && canMoveLeft() && canMoveLeftSlope()){
                setLocation(getX() - SPEED, getY());
                facing = "left";
                slideVelocity = -SPEED-1;
            }
            if((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) && canMoveRight() && canMoveRightSlope()){
                setLocation(getX() + SPEED, getY());
                facing = "right";
                slideVelocity = SPEED-1;
            }
        }
    }

    public boolean canJumpLeft()
    {
        boolean canJumpL = true;
        if (getOneObjectAtOffset(getImage().getWidth()/-2+velocityX, getImage().getHeight()/-2+1, Terrain.class) != null || //top left
            getOneObjectAtOffset(getImage().getWidth()/-2+velocityX, getImage().getHeight()/2-1, Terrain.class) != null) //bottom left
            {
                canJumpL = false;
            }
        return canJumpL;
    }

    public boolean canJumpRight()
    {
        boolean canJumpR = true;
        if (getOneObjectAtOffset(getImage().getWidth()/2+velocityX, getImage().getHeight()/-2+1, Terrain.class) != null || //top right
            getOneObjectAtOffset(getImage().getWidth()/2+velocityX, getImage().getHeight()/2-1, Terrain.class) != null) //bottom right
            {
                canJumpR = false;
            }
        return canJumpR;
    }
    
    public void onSlopeLeft(boolean onSlopeLeft)
    {
        this.onSlopeLeft = onSlopeLeft;
    }

    public void onSlopeRight(boolean onSlopeRight)
    {
        this.onSlopeRight = onSlopeRight;
    }

    public void windyLvl(boolean isWindyLvl, String windDirection)
    {
        this.isWindyLvl = isWindyLvl;
        this.windDirection = windDirection;
    }
    
    public void windyLvl(boolean isWindyLvl)
    {
        this.isWindyLvl = isWindyLvl;
    }
}

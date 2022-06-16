import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SquatKing extends BoundDetection
{
    //Declares all variables needed for game mechanics and logic
    private static int GRAVITY = 1;
    private int SPEED = 3, imageIndex = 0,slideSpeed = 5, frames = 0, jumpFrames = 0;
    private int velocityY, velocityX, slideVelocity;
    private int chargeTime;
    private String direction = "left", facing = "right",  windDirection;
    private boolean onSlopeLeft, onSlopeRight, isWindyLvl, landed = true;
    //Declares timers
    private SimpleTimer timer = new SimpleTimer();
    private SimpleTimer animationTimer = new SimpleTimer();
    //Declares arrays for animations
    GreenfootImage rightWalk[] = new GreenfootImage[4];
    GreenfootImage leftWalk[] = new GreenfootImage[4];
    GreenfootImage rightIdle[] = new GreenfootImage[3];
    GreenfootImage leftIdle[] = new GreenfootImage[3];
    GreenfootImage rightJump[] = new GreenfootImage[3];
    GreenfootImage leftJump[] = new GreenfootImage[3];
    GreenfootImage rightSlopeImage, leftSlopeImage;
    
    windImage1 wind1Actor;
    windImage2 wind2Actor;

    public SquatKing(int x, int y)
    {
        //Appeneds to lists and/or assigns variables with images
        for(int i = 0; i < rightWalk.length; i++)
        {
            rightWalk[i] = new GreenfootImage("walk" + i + ".png");
            rightWalk[i].scale(x, y);

            leftWalk[i] = new GreenfootImage("walk" + i + ".png");
            leftWalk[i].scale(x, y);
            leftWalk[i].mirrorHorizontally();
        }
        for(int i = 0; i < rightIdle.length; i++)
        {
            rightIdle[i] = new GreenfootImage("idle" + i + ".png");
            rightIdle[i].scale(x, y);

            leftIdle[i] = new GreenfootImage("idle" + i + ".png");
            leftIdle[i].scale(x, y);
            leftIdle[i].mirrorHorizontally();
        }
        for(int i = 0; i < rightJump.length; i++)
        {
            rightJump[i] = new GreenfootImage("jump" + i + ".png");
            rightJump[i].scale(x, y);

            leftJump[i] = new GreenfootImage("jump" + i + ".png");
            leftJump[i].scale(x, y);
            leftJump[i].mirrorHorizontally();
        }

        rightSlopeImage = new GreenfootImage("jumpWall.png");
        rightSlopeImage.scale(x, y);

        leftSlopeImage = new GreenfootImage("jumpWall.png");
        leftSlopeImage.scale(x, y);
        leftSlopeImage.mirrorHorizontally();

        setImage(rightIdle[0]);
    }

    public void act()
    {
        frames++;
        animate();
        verticlePhysics();
        horizontalPhysics();
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
        //Creates logic when at windy level
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

    public void animate()
    {
        //Sets animation timer
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        animationTimer.mark();
        //Changes actor's image depending on conditions to create animation
        if(facing.equals("left") && !Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right"))
        {
            imageIndex = (imageIndex + 1) % rightIdle.length;
            setImage(leftIdle[imageIndex]);
        }
        if(facing.equals("right") && !Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right"))
        {
            imageIndex = (imageIndex + 1) % leftIdle.length;
            setImage(rightIdle[imageIndex]);
        }
        if(Greenfoot.isKeyDown("right"))
        {
            setImage(rightWalk[imageIndex]);
            imageIndex = (imageIndex + 1) % rightWalk.length;
        }
        if(Greenfoot.isKeyDown("left")) 
        {
            setImage(leftWalk[imageIndex]);
            imageIndex = (imageIndex + 1) % leftWalk.length;
        }
        if(velocityY < 0 && facing.equals("right"))
        {
            setImage(rightJump[1]);
        }
        if(velocityY < 0 && facing.equals("left"))
        {
            setImage(leftJump[1]);
        }
        if(velocityY > 0 && facing.equals("right"))
        {
            setImage(rightJump[2]);
        }
        if(velocityY > 0 && facing.equals("left"))
        {
            setImage(leftJump[2]);
        }
    }
    //Creates and controls all movements involving the Y axis
    public void verticlePhysics()
    {   
        //Creates slope movement logic
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
        //Plays sound when jumping
        if(velocityY == map(chargeTime, 0, 1000, 5, 25) * -1) 
        {
            Greenfoot.playSound("jumpSound.mp3");
            landed = false;
        }
        //Updates actor Y coordinate
        setLocation(getX(), getY() + velocityY); 
        //Creates movement logic for when on ground block        
        if(onGround())
        {
            velocityY = 0;
            velocityX = 0;
            slideVelocity = 0;
            // plays sound when landing
            if(landed == false) 
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
            if(velocityY < 15)
            {
                velocityY += GRAVITY; 
            }
        }
        //Creates movement logic to bounce off ceilings
        if(bumpedHead())
        {
            velocityY = 0;
            while(bumpedHead())
            {
                setLocation(getX(), getY()+1);
            }
            Greenfoot.playSound("bumpSound.mp3"); 
        }
        
        onSlopeLeft = false;
        onSlopeRight = false;
    }
    //Creates and controls all movement that involves the X axis
    public void horizontalPhysics()
    {
        //Creates movement logic for when on ice block
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
                slideVelocity = map(velocityX, 5, 10, 2, 5);
                velocityX = 0;
            }
            else if(velocityX < 0)
            {
                slideVelocity = (map(velocityX * -1, 5, 10, 2, 5)) * -1;
                velocityX = 0;
            }

            if(slideVelocity > 0)
            {
                if(frames%7 == 0)
                {
                    slideVelocity--;
                }
            }
            else if(slideVelocity < 0)
            {
                if(frames%7 == 0)
                {
                    slideVelocity++;    
                }
            }
        }
        //Creates movement logic to bounce off walls
        if(!canJumpRight() || !canJumpLeft())
        {
            velocityX = velocityX * -1;
            //Plays sound when bouncing off wall
            if(velocityX != 0) 
            {
                Greenfoot.playSound("bumpSound.mp3");   
            }
        }
        //Creates logic for carrying current x velocity or sliding velocity
        if(velocityX > 0 && canJumpRight())
        {
            setLocation(getX() + velocityX, getY());
        }
        if(velocityX < 0 && canJumpLeft())
        {
            setLocation(getX() + velocityX, getY());
        }
        if(onIceGround() && slideVelocity > 0 && canJumpRight())
        {
            setLocation(getX() + slideVelocity, getY());
        }
        if(onIceGround() && slideVelocity < 0 && canJumpLeft())
        {
            setLocation(getX() + slideVelocity, getY());
        }
    }
    //Creates all logic for jump mechanic
    public void jumpTimer()
    {
        setImage(rightJump[0]);
        timer.mark();
        //Creates function to check how long player has pressed jump button
        while(Greenfoot.isKeyDown("up") && (onGround() || onIceGround()))
        {
            getWorld().repaint();
            jumpFrames++;
            //Allows the world to repaint wind while in "while" loop
            if(isWindyLvl && jumpFrames % 2 == 0)
            {
                wind1Actor = ((MyWorld)getWorld()).getObjects(windImage1.class).get(0);
                wind2Actor = ((MyWorld)getWorld()).getObjects(windImage2.class).get(0);
                wind1Actor.shiftX(windDirection);
                wind2Actor.shiftX(windDirection);
            }
            
            chargeTime = timer.millisElapsed();
            //Sets jump direction
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
        //Creates max jump height
        if(chargeTime > 1000)
        {
            chargeTime = 1000;
        }
        //Remaps the chargetime to other values for y velocity and x velocity
        velocityY = map(chargeTime, 0, 1000, 5, 25) * -1;
        velocityX = map(chargeTime, 0, 1000, 5, 10);
        //Checks which direction to set x velocity
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
    //Creates all walk movement logic
    public void walkMovement()
    {
        //Creates walk movement for when on ground block
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
        //Creates walk movement for when on ice block
        if(onIceGround())
        {
            if((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) && canMoveLeft() && canMoveLeftSlope()){
                setLocation(getX() - SPEED, getY());
                facing = "left";
                slideVelocity = -SPEED;
            }
            if((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) && canMoveRight() && canMoveRightSlope()){
                setLocation(getX() + SPEED, getY());
                facing = "right";
                slideVelocity = SPEED;
            }
        }
    }
    //Checks if actor can jump left (checks for terrain class only)
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
    //Checks if actor can jump right (checks for terrain class only)
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
    //Setter methods for slope detection and windy level 
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

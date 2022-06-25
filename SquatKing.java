import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * SquatKing player class; Contains logic for
 * controls, sfx, animations, movement, and physics.
 * 
 * Joey & Anson
 * June 17, 2022
 */
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

    /** 
     * Constructor for SquatKing actor
     * 
     * @param x determines image width
     * @param y determines image height
     * @return none
     */
    public SquatKing(int x, int y)
    {
        //Appends to lists and/or assigns variables with images
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

    /** 
     * Runs the other methods that contain game logic
     * 
     * No parameters or returns
     */
    public void act()
    {
        frames++; // used for timing/delay purposes
        animate();
        verticlePhysics();
        horizontalPhysics();
        wind();
        if(Greenfoot.isKeyDown("up") && (onGround() || onIceGround()))
        {
            Utils.setJumps(); // keeps track of total amount of jumps
            jumpTimer(); 
        }
        walkMovement();
    }

    /** 
     * Moves the player left or right constantly if the level is a windy one.
     * A level can only have wind blowing from one direction.
     * Amount of movement is higher when not on the ground.
     * 
     * No parameters or returns
     */
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

    /** 
     * Does all of the animating of the player image
     * 
     * No parameters or returns
     */
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

    /** 
     * Controls all of the player movement involving the Y-Axis
     * 
     * No parameters or returns
     */
    public void verticlePhysics()
    {   
        //Slides down a slope when on one
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
        //Stops vertical and jumping movement when on a ground block        
        if(onGround())
        {
            velocityY = 0;
            velocityX = 0;
            slideVelocity = 0;
            //Plays sound when landing
            if(landed == false) 
            {
                Greenfoot.playSound("landSound.mp3");
                landed = true;
            }
            //Moves player out of blocks if they fall in from the top
            while(onGround()) 
            {
                setLocation(getX(), getY()-1);
            }
            setLocation(getX(), getY()+1);
        }
        else
        {   
            //Accelerates player downwards by force of gravity. Terminal velocity of 20 pixels per frame
            if(velocityY < 20)
            {
                velocityY += GRAVITY; 
            }
        }
        //Stops upward velocity when you bump your head
        if(bumpedHead())
        {
            velocityY = 0;
            //Moves player out of blocks if they jump in from the bottom
            while(bumpedHead()) 
            {
                setLocation(getX(), getY()+1);
            }
            Greenfoot.playSound("bumpSound.mp3"); 
        }

        onSlopeLeft = false;
        onSlopeRight = false;
    }

    /** 
     * Controls all of the player movement involving the X-axis
     * 
     * No parameters or returns
     */
    public void horizontalPhysics()
    {
        //Slides player on ice blocks depending on how large velocityX was when landing
        if(onIceGround())
        {
            //Moves player out of ice blocks if they fall in from the top
            while(onIceGround())
            {
                setLocation(getX(), getY()-1);
            }
            setLocation(getX(), getY()+1);
            velocityY = 0;
            
            //Plays sound when landing
            if(landed == false) 
            {
                Greenfoot.playSound("iceLandSound.mp3");
                landed = true;
            }
            //Sets slide direction and slide magnitude
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
            //Sets amount of frames it takes to stop sliding
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
        //Does horizontal player movement when sliding and jumping
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
    /** 
     * Handles the player input of jumping. 
     * Controls magnitude of velocity X and Y based on amount of time up is held.
     * 
     * No parameters or returns
     */
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
        //Maps velocity X and Y to a range of values based on chargeTime
        velocityY = map(chargeTime, 0, 1000, 5, 25) * -1;
        velocityX = map(chargeTime, 0, 1000, 5, 10);
        //Sets direction of velocityX based on the direction variable in the while loop
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
    
    /** 
     * Handles the player input of walking. 
     * Moves left and right based on SPEED constant
     * when left or right arrow keys are pressed while on a ground type block.
     * 
     * No parameters or returns
     */
    public void walkMovement()
    {
        //Creates walk movement for when on ground block
        if(onGround())
        {
            if(Greenfoot.isKeyDown("left") && canMoveLeft() && canMoveLeftSlope()){
                setLocation(getX() - SPEED, getY());
                facing = "left";
            }
            if(Greenfoot.isKeyDown("right") && canMoveRight() && canMoveRightSlope()){
                setLocation(getX() + SPEED, getY());
                facing = "right";
            }
        }
        //Creates walk movement for when on ice block
        if(onIceGround())
        {
            if(Greenfoot.isKeyDown("left") && canMoveLeft() && canMoveLeftSlope()){
                setLocation(getX() - SPEED, getY());
                facing = "left";
                slideVelocity = -SPEED;
            }
            if(Greenfoot.isKeyDown("right") && canMoveRight() && canMoveRightSlope()){
                setLocation(getX() + SPEED, getY());
                facing = "right";
                slideVelocity = SPEED;
            }
        }
    }
    
    /** 
     * Checks if actor can jump left without clipping into a block (checks for terrain class only)
     * 
     * No parameters
     * @return true if there is no terrain, false if there is terrain
     */
    public boolean canJumpLeft()
    {
        boolean canJumpL = true;
        if (getOneObjectAtOffset(getImage().getWidth()/-2+velocityX, getImage().getHeight()/-2+1, Terrain.class) != null || //top left of player sprite
        getOneObjectAtOffset(getImage().getWidth()/-2+velocityX, getImage().getHeight()/2-1, Terrain.class) != null) //bottom left of player sprite
        {
            canJumpL = false;
        }
        return canJumpL;
    }
    
    /** 
     * Checks if actor can jump right without clipping into a block (checks for terrain class only)
     * 
     * No parameters
     * @return true if there is no terrain, false if there is terrain
     */
    public boolean canJumpRight()
    {
        boolean canJumpR = true;
        if (getOneObjectAtOffset(getImage().getWidth()/2+velocityX, getImage().getHeight()/-2+1, Terrain.class) != null || //top right of player sprite
        getOneObjectAtOffset(getImage().getWidth()/2+velocityX, getImage().getHeight()/2-1, Terrain.class) != null) //bottom right of player sprite
        {
            canJumpR = false;
        }
        return canJumpR;
    }
    
    /**
     * Sets if SquatKing is on a slopeLeftRight block from the slopeLeftRight class.
     * 
     * @param onSlopeLeft whether or not player is on slopeLeftRight block
     */
    public void onSlopeLeft(boolean onSlopeLeft)
    {
        this.onSlopeLeft = onSlopeLeft;
    }
    
    /**
     * Sets if SquatKing is on a slopeRightLeft block from the slopeRightLeft class.
     * 
     * @param onSlopeRight whether or not player is on slopeRightLeft block
     */
    public void onSlopeRight(boolean onSlopeRight)
    {
        this.onSlopeRight = onSlopeRight;
    }

    /**
     * Sets if SquatKing is in a windy level from the game world class. (true case)
     * 
     * @param isWindyLvl whether or not the level is windy
     * @param windDirection the direction the wind is blowing in
     */
    public void windyLvl(boolean isWindyLvl, String windDirection)
    {
        this.isWindyLvl = isWindyLvl;
        this.windDirection = windDirection;
    }

    /**
     * Sets if SquatKing is in a windy level from the game world class. (false case)
     * Overrides the previous method since if there's no wind, there's no wind direction either.
     * 
     * @param isWindyLvl whether or not the level is windy
     */
    public void windyLvl(boolean isWindyLvl)
    {
        this.isWindyLvl = isWindyLvl;
    }
}

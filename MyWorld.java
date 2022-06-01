import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    GreenfootImage background = new GreenfootImage("background.png");
    private int bgYCoord;
    private int speed = 5;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1, true); 
        prepare();
    }
    //Scrolls the world down, sets global y coordinate += 1
    public void yScrollDown()
    {
        GreenfootImage bg = new GreenfootImage(getBackground());
        getBackground().drawImage(bg, 0, speed);
        getBackground().drawImage(bg, 0, (getHeight() * -1) + speed);
        bgYCoord += 1;
    }
    //Scrolls the world up, sets the global y coordinate -= 1
    public void yScrollUp()
    {
        GreenfootImage bg = new GreenfootImage(getBackground());
        getBackground().drawImage(bg, 0, speed * -1);
        getBackground().drawImage(bg, 0, getHeight() - speed);
        bgYCoord -= 1;
    }
    
    private void prepare()
    {
        //GreenfootImage bg = new GreenfootImage("GrassBg.png");
        //bg.scale(getWidth(), getHeight());
        //setBackground(bg);
        
        //Block platform = new Block(150, 150);
        //addObject(platform, 417, 600);
        bluejay bluejay = new bluejay(48,48);
        addObject(bluejay,272,224); 
        
        slopeLeftRight diagonal_block1 = new slopeLeftRight(this,96, 96, 48*4, 550);
        addObject(diagonal_block1,48*4,550);
        slopeLeftRight diagonal_block = new slopeLeftRight(this, 96,96, 48*3,500);
        addObject(diagonal_block,48*3,500);
        /*
        slopeRightLeft diagonal_block3 = new slopeRightLeft(this, 100, 100, 600, 500);
        addObject(diagonal_block3,600,500);
        slopeRightLeft diagonal_block4 = new slopeRightLeft(this, 100, 100, 650, 450);
        addObject(diagonal_block4,650,450);
        slopeRightLeft diagonal_block5 = new slopeRightLeft(this, 100, 100, 700, 400);
        addObject(diagonal_block5,700,400);
        */
        Block platform2 = new Block(96, 96);
        addObject(platform2, 475,600);
        Block block8 = new Block(96, 96);
        addObject(block8,48,500);
        Block block9 = new Block(96, 96);
        addObject(block9,500,596);
        Block block10 = new Block(96, 96);
        addObject(block10,400,596);
    }
    //Setter method for global y coordniate to allow communication between the objects and the world class
    public void setYCoord(int bgYCoord)
    {
        this.bgYCoord = bgYCoord;
    }
    //Getter method for global y coordniate to allow communication between the objects and the world class
    public int getYCoord()
    {
        return bgYCoord;
    }
    //Getter method for global speed variable
    public int getSpeed()
    {
        return speed;
    }

}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    //GreenfootImage background = new GreenfootImage("background.png");
    private int bgYCoord;
    private int speed = 5;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1, true); 
        prepare();
    }
    
    public void act()
    {
        repaint();
    }
    
    public void prepare()
    {
        GreenfootImage bg = new GreenfootImage("GrassBg.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);

        bluejay bluejay = new bluejay(48,48);
        addObject(bluejay,272,224); 

        slopeLeftRight slopeLR0 = new slopeLeftRight(this, 96,96, 48*3,500);
        addObject(slopeLR0,48*3,500);
        slopeLeftRight slopeLR1 = new slopeLeftRight(this,96, 96, 48*4, 548);
        addObject(slopeLR1,48*4,548);        
        slopeRightLeft slopeRL1 = new slopeRightLeft(this, 96, 96, 48*13, 500);
        addObject(slopeRL1,48*13,500);
        slopeRightLeft slopeRL0 = new slopeRightLeft(this, 96, 96, 48*12, 548);
        addObject(slopeRL0,48*12,548);

        Block block1 = new Block(96, 96);
        addObject(block1, 48, 500);
        Block block2 = new Block(96, 96);
        addObject(block2, 480, 596);
        Block block3 = new Block(96, 96);
        addObject(block3, 384, 596);
        Block block4 = new Block(96, 96);
        addObject(block4, 288, 596);
        Block block5 = new Block(96, 96);
        addObject(block5, 576, 596);
        Block block6 = new Block(96, 96);
        addObject(block6, 192, 596);
        Block block7 = new Block(96, 96);
        addObject(block7, 672, 500);
    }
    //Getter method for global speed variable
    public int getSpeed()
    {
        return speed;
    }

}

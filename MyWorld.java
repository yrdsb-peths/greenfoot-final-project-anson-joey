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
    private int level = 1;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1, true); 
        prepareActor();
        prepare();
    }
    
    public void act()
    {
        repaint();
    }
    
    private void prepareActor()
    {
        bluejay bluejay = new bluejay(48,48);
        addObject(bluejay,272,100); 
    }
    
    private void prepare()
    {
        switch(level)
        {
            case 1:
                GreenfootImage bg = new GreenfootImage("GrassBg.png");
                bg.scale(getWidth(), getHeight());
                setBackground(bg);

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
                Block block8 = new Block(96, 96);
                addObject(block8, 272, 200);
                break;
            case 2:
                Block platform12 = new Block(150, 150);
                addObject(platform12, 600,650);
                break;
        }
    }
    public void nextLevel()
    {
        removeObjects(getObjects(Block.class));
        removeObjects(getObjects(slopeLeftRight.class));
        removeObjects(getObjects(slopeRightLeft.class));//remove all objects from the world 
        
        level++;
        prepare();
    }
    public void previousLevel()
    {
        removeObjects(getObjects(Block.class));
        removeObjects(getObjects(slopeLeftRight.class));
        removeObjects(getObjects(slopeRightLeft.class));//remove all objects from the world 
        
        level--;
        prepare();
    }

    //Getter method for global speed variable
    public int getSpeed()
    {
        return speed;
    }

}

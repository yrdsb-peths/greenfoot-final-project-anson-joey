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
        super(850, 735, 1, false); 
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
        //Block platform = new Block(150, 150);
        //addObject(platform, 417, 600);
        bluejay bluejay = new bluejay(38, 58);
        addObject(bluejay,300,200); 
        Block platform2 = new Block(150, 150);
        addObject(platform2, 500,575);
        //Block block3 = new Block(150, 150);
        //addObject(block3, 250, 398);

        slopeLeftRight diagonal_block = new slopeLeftRight(this, 100, 100, 300, 400);
        addObject(diagonal_block,300,400);

        slopeLeftRight diagonal_block1 = new slopeLeftRight(this, 100, 100, 350, 450);
        addObject(diagonal_block1,350,450);

        slopeLeftRight diagonal_block2 = new slopeLeftRight(this, 100, 100, 400, 500);
        addObject(diagonal_block2,400,500);

        Block block8 = new Block(200, 200);
        addObject(block8,6,400);
        Block block9 = new Block(200, 200);
        addObject(block9,142,564);
        bluejay.setLocation(272,224);
        block9.setLocation(775,421);
        block9.setLocation(472,436);
        block9.setLocation(671,448);
        Block block10 = new Block(200, 200);
        addObject(block10,172,509);
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

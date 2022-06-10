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
    private boolean touchingbbq = false;
    
    GreenfootImage grass = new GreenfootImage("images/Grass terrain/tile002.png");
    GreenfootImage grassL = new GreenfootImage("images/Grass terrain/tile002.png");
    GreenfootImage grassR = new GreenfootImage("images/Grass terrain/tile002.png");
    
    GreenfootImage dirt = new GreenfootImage("images/Grass terrain/tile013.png");
    GreenfootImage snow = new GreenfootImage("images/Snow terrain/tile002.png");
    
    GreenfootImage grassBg = new GreenfootImage("images/Grass terrain/GrassBg.png");
    GreenfootImage snowBg = new GreenfootImage("images/Snow terrain/SnowBg.png");           
    
    SquatKing actor;
    SmokingHotBBQ BBQ;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1, true); 
        addObject(new fade(null), getWidth()/2, getHeight()/2);
        
        grassL.rotate(270);
        grassR.rotate(90);
        
        setPaintOrder(fade.class, SquatKing.class, Terrain.class);
        prepareActor();
        prepare();
    }
    
    public void act()
    {
        actor = getObjects(SquatKing.class).get(0);
        if(actor.isTouchingBBQ() == true && touchingbbq == false)
        {
            changeWorld();
        }
        if(touchingbbq == false)
        {
            repaint();
        }
    }
    
    private void prepareActor()
    {
        SquatKing squattyboi = new SquatKing(48,48);
        addObject(squattyboi,500,740); 
    }
    
    private void changeWorld()
    {
        touchingbbq = true;
        BBQScene world1 = new BBQScene();
        fade fadeout1 = new fade(world1);
        addObject(fadeout1, getWidth() / 2, getHeight() / 2);
    }
    
    private void prepare()
    {
        switch(level)
        {
            case 1:
                grassBg.scale(getWidth(), getHeight());
                setBackground(grassBg);
                
                Block block1 = new Block(96, 96, grass); // spawning block
                addObject(block1, 500, 800);
                
                Block block2 = new Block(96, 96, grass);
                addObject(block2, 404, 800);
                
                Block block3 = new Block(96, 96, grass);
                addObject(block3, 596, 800);
                
                
                slopeRightLeft slopeRL1 = new slopeRightLeft(this, 96, 96, 692, 550); // right side
                addObject(slopeRL1, 692, 550);
                Block block4 = new Block(96, 96, dirt);
                addObject(block4, 692, 800);
                Block block5 = new Block(96, 96, dirt);
                addObject(block5, 692, 704);
                Block block6 = new Block(96, 96, dirt);
                addObject(block6, 692, 608);
                
                slopeLeftRight slopeLR0 = new slopeLeftRight(this, 96,96, 308,550); // left side
                addObject(slopeLR0,308,550);
                Block block7 = new Block(96, 96, dirt);
                addObject(block7, 308, 800);
                Block block8 = new Block(96, 96, dirt);
                addObject(block8, 308, 704);
                Block block9 = new Block(96, 96, dirt);
                addObject(block9, 308, 608);
                
                Block block10 = new Block(96, 96, grass);
                addObject(block10, 212, 550);
                Block block11 = new Block(96, 96, grass);
                addObject(block11, 116, 550);
                Block block12 = new Block(96, 96, grass);
                addObject(block12, 20, 550);

                Block block13 = new Block(96, 96, grass);
                addObject(block13, 788, 550);
                Block block14 = new Block(96, 96, grass);
                addObject(block14, 884, 550);
                Block block15 = new Block(96, 96, grass);
                addObject(block15, 980, 550);
                
                Block block16 = new Block(96, 96, grass);
                addObject(block16, 500, 300);
                Block block17 = new Block(96, 96, grass);
                addObject(block17, 596, 300);
                Block block18 = new Block(96, 96, grass);
                addObject(block18, 404, 300);
                
                break;
            case 2:
                snowBg.scale(getWidth(), getHeight());
                setBackground(snowBg);

                Block platform12 = new Block(96, 96, snow);
                addObject(platform12, 500,500);

                Block platform13 = new Block(96, 96, snow);
                addObject(platform13, 500,500);
                
                break;
        }
        }

    public void nextLevel()
    {
        removeObjects(getObjects(Terrain.class)); //Removes all objects
        
        level++;
        prepare();
    }
    public void previousLevel()
    {
        removeObjects(getObjects(Terrain.class)); //Removes all objects 
        
        level--;
        prepare();
    }

    //Getter method for global speed variable
    public int getSpeed()
    {
        return speed;
    }

}

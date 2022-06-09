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
    GreenfootImage snow = new GreenfootImage("images/Snow terrain/tile002.png");
    
    GreenfootImage grassBg = new GreenfootImage("images/Grass terrain/GrassBg.png");
    GreenfootImage snowBg = new GreenfootImage("images/Snow terrain/SnowBg.png");           
    
    SquatKing actor;
    SmokingHotBBQ BBQ;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1, true); 
        prepareActor();
        prepare();
        addObject(new fade(null), getWidth()/2, getHeight()/2);
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
        addObject(squattyboi,272,100); 
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

                slopeLeftRight slopeLR0 = new slopeLeftRight(this, 96,96, 48*3,500);
                addObject(slopeLR0,48*3,500);
                slopeLeftRight slopeLR1 = new slopeLeftRight(this,96, 96, 48*4, 548);
                addObject(slopeLR1,48*4,548);        
                slopeRightLeft slopeRL1 = new slopeRightLeft(this, 96, 96, 48*13, 500);
                addObject(slopeRL1,48*13,500);
                slopeRightLeft slopeRL0 = new slopeRightLeft(this, 96, 96, 48*12, 548);
                addObject(slopeRL0,48*12,548);

                Block block1 = new Block(96, 96, grass);
                addObject(block1, 48, 500);
                iceBlock block2 = new iceBlock(96, 96, grass);
                addObject(block2, 480, 596);
                iceBlock block3 = new iceBlock(96, 96, grass);
                addObject(block3, 384, 596);
                iceBlock block4 = new iceBlock(96, 96, grass);
                addObject(block4, 288, 596);
                iceBlock block5 = new iceBlock(96, 96, grass);
                addObject(block5, 576, 596);
                iceBlock block6 = new iceBlock(96, 96, grass);
                addObject(block6, 192, 596);
                iceBlock block7 = new iceBlock(96, 96, grass);
                addObject(block7, 672, 500);

                Block block8 = new Block(96, 96, grass); //spawning location
                addObject(block8, 272, 200);

                Block block9 = new Block(96, 96, grass);
                addObject(block9, 768, 500);
                Block block10 = new Block(96, 96, grass);
                addObject(block10, 864, 500);
                Block block11 = new Block(96, 96, grass);
                addObject(block11, 960, 500);
                
                
                Block block16 = new Block(96, 96);
                addObject(block16,420,333);
                
                SmokingHotBBQ bbq = new SmokingHotBBQ(96,96);
                addObject(bbq, 800, 410);
                
                break;
            case 2:
                snowBg.scale(getWidth(), getHeight());
                setBackground(snowBg);

                Block platform12 = new Block(150, 150, snow);
                addObject(platform12, 600,650);

                Block platform13 = new Block(150, 150, snow);
                addObject(platform13, 850,500);
                
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

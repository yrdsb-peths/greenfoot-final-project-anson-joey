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
    GreenfootImage cloudsBg = new GreenfootImage("images/Grass terrain/CloudsBg.png");
    GreenfootImage snowBg = new GreenfootImage("images/Snow terrain/SnowBg.png");           
    
    GreenfootSound bgMusic = new GreenfootSound("bgMusic.mp3");
    
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
                
                slopeRightLeft slopeRL2 = new slopeRightLeft(this, 96, 96, 740, 512); 
                addObject(slopeRL2, 740, 512);
                slopeRightLeft slopeRL1 = new slopeRightLeft(this, 96, 96, 692, 560); //right side
                addObject(slopeRL1, 692, 560);
                
                
                Block block4 = new Block(96, 96, dirt);
                addObject(block4, 692, 800);
                Block block5 = new Block(96, 96, dirt);
                addObject(block5, 692, 704);
                Block block6 = new Block(96, 96, dirt);
                addObject(block6, 692, 608);
                
                slopeLeftRight slopeLR2 = new slopeLeftRight(this, 96,96, 260,512); 
                addObject(slopeLR2,260,512);
                slopeLeftRight slopeLR1 = new slopeLeftRight(this, 96,96, 308,560); //left side
                addObject(slopeLR1,308,560);
                
                
                Block block7 = new Block(96, 96, dirt);
                addObject(block7, 308, 800);
                Block block8 = new Block(96, 96, dirt);
                addObject(block8, 308, 704);
                Block block9 = new Block(96, 96, dirt);
                addObject(block9, 308, 608);
                
                
                Block block10 = new Block(96, 96, grass);
                addObject(block10, 212, 512);
                Block block11 = new Block(96, 96, grass);
                addObject(block11, 116, 512);
                Block block12 = new Block(96, 96, grass);
                addObject(block12, 20, 512);

                Block block13 = new Block(96, 96, grass);
                addObject(block13, 788, 512);
                Block block14 = new Block(96, 96, grass);
                addObject(block14, 884, 512);
                Block block15 = new Block(96, 96, grass);
                addObject(block15, 980, 512);
                
                
                Block block16 = new Block(96, 96, grass);
                addObject(block16, 500, 300);
                Block block17 = new Block(96, 96, grass);
                addObject(block17, 596, 300);
                Block block18 = new Block(96, 96, grass);
                addObject(block18, 404, 300);
                
                
                
               
                break;
            case 2:
                cloudsBg.scale(getWidth(), getHeight());
                setBackground(cloudsBg);

                Block platform12 = new Block(96, 96, grass);
                addObject(platform12, 500,500);

                Block platform13 = new Block(96, 96, grass);
                addObject(platform13, 500,500);
                
                bgMusic.setVolume(70);
                bgMusic.playLoop();
                
                break;
                
            case 3:
                
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

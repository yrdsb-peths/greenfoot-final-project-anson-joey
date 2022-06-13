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
    GreenfootImage bridge = new GreenfootImage("images/Grass terrain/tile106.png");
    
    GreenfootImage dirt = new GreenfootImage("images/Grass terrain/tile013.png");
    GreenfootImage sideTile = new GreenfootImage("images/Grass terrain/tile073.png");
    GreenfootImage snow = new GreenfootImage("images/Snow terrain/tile002.png");
    
    GreenfootImage grassRL = new GreenfootImage("images/Grass terrain/slopeRightLeft.png");
    
    GreenfootImage grassBg = new GreenfootImage("images/Grass terrain/GrassBg.png");
    GreenfootImage cloudsBg = new GreenfootImage("images/Grass terrain/CloudsBg.png");
    GreenfootImage snowBg = new GreenfootImage("images/Snow terrain/SnowBg.png");           
    
    SquatKing actor;
    SmokingHotBBQ BBQ;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1, true); 
        //addObject(new fade(null), getWidth()/2, getHeight()/2);
        
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
        addObject(squattyboi,100,600); 
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
            case 1://grass
                grassBg.scale(getWidth(), getHeight());
                setBackground(grassBg);
                
                Block borderR = new Block(96, 800, sideTile);
                addObject(borderR, 1000,400);
                Block borderL = new Block(96, 800, sideTile);
                addObject(borderL, 0,400);
                
                slopeLeftRight sLR4 = new slopeLeftRight(this, 96, 96, 144, 272);
                addObject(sLR4, 144, 272);
                slopeLeftRight sLR3 = new slopeLeftRight(this, 96, 96, 192, 320);
                addObject(sLR3, 192, 320);
                slopeLeftRight sLR2 = new slopeLeftRight(this, 96, 96, 240, 368);
                addObject(sLR2, 240, 368);
                slopeLeftRight sLR1 = new slopeLeftRight(this, 96, 96, 288, 416);
                addObject(sLR1, 288, 416);
                
                Block b9 = new Block(96, 96, grass);
                addObject(b9, 96,272);
                Block bl = new Block(96, 96, dirt);
                addObject(bl, 96,368);
                Block bl1 = new Block(96, 96, grass);
                addObject(bl1, 192,368);
                
                Block b3 = new Block(96, 96, grass);
                addObject(b3, 96,708);
                Block b4 = new Block(96, 96, grass);
                addObject(b4, 192,708);
                Block b5 = new Block(96, 96, grass);
                addObject(b5, 288,708);
                
                
                Block b6 = new Block(96, 96, dirt);
                addObject(b6, 96,464);
                Block b7 = new Block(96, 96, dirt);
                addObject(b7, 192,464);
                Block b8 = new Block(96, 96, dirt);
                addObject(b8, 288,464);
                

                
                slopeRightLeft sRL2 = new slopeRightLeft(this, 96, 96, 856, 320, grassRL);
                addObject(sRL2, 856, 320);
                slopeRightLeft sRL1 = new slopeRightLeft(this, 96, 96, 808, 368, grassRL);
                addObject(sRL1, 808, 368);
                Block b13 = new Block(96, 96, grass);
                addObject(b13, 904,320);
                
                Block b10 = new Block(96, 96, dirt);
                addObject(b10, 904,416);
                Block b11 = new Block(96, 96, grass);
                addObject(b11, 808,416);
                Block b12 = new Block(96, 48, grass);
                addObject(b12, 712,440);
                
                slopeRightLeft sRL3 = new slopeRightLeft(this, 96, 96, 432, 181, grassRL);
                addObject(sRL3, 432, 181);
                slopeLeftRight sLR5 = new slopeLeftRight(this, 96, 96, 528, 181);
                addObject(sLR5, 528, 181);
                Block bl2 = new Block(96, 96, grass);
                addObject(bl2, 480,176);
                
                break;
                
            case 2://grass
                cloudsBg.scale(getWidth(), getHeight());
                setBackground(cloudsBg);
                
                slopeRightLeft sRL4 = new slopeRightLeft(this, 96, 96, 952, 752, grassRL);
                addObject(sRL4, 952, 752);
                slopeRightLeft sRL5 = new slopeRightLeft(this, 96, 96, 952, 800, grassRL);
                addObject(sRL5, 904, 800);
                
                Block borderR1 = new Block(96, 800, sideTile);
                addObject(borderR1, 1000,400);
                Block borderL1 = new Block(96, 800, sideTile);
                addObject(borderL1, 0,400);
                

                Block b15 = new Block(96, 96, grass);
                addObject(b15, 96,704);
                
                Block b14 = new Block(48, 96, grass);
                addObject(b14, 480,608);
                
                Block b16 = new Block(48, 96, grass);
                addObject(b16, 624,608);
                
                Block b17 = new Block(96, 96, grass);
                addObject(b17, 816,608);
                
                Block b18 = new Block(96, 96, grass);
                addObject(b18, 768,344);
                
                
                Block b21 = new Block(96, 96, bridge);
                addObject(b21, 528,200);
                Block b23 = new Block(96, 96, bridge);
                addObject(b23, 432,200);
                Block b24 = new Block(96, 96, bridge);
                addObject(b24, 336,200);
                Block b25 = new Block(96, 96, bridge);
                addObject(b25, 240,200);
                
                break;
                
            case 3://ice
                
                
                
                
            case 4://ice
                
                
                
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

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
    
    //Dungeon biome images
    GreenfootImage dnGround = new GreenfootImage("images/Dungeon terrain/tile001.png");
    GreenfootImage dnSlopeRL = new GreenfootImage("images/Dungeon terrain/slopeRightLeft.png");
    GreenfootImage dnSlopeLR = new GreenfootImage("images/Dungeon terrain/slopeLeftRight.png");
    GreenfootImage dnStone = new GreenfootImage("images/Dungeon terrain/tile027.png");
    GreenfootImage dnStones = new GreenfootImage("images/Dungeon terrain/tile050.png");
    //Stronghold biome images
    GreenfootImage shGround = new GreenfootImage("images/Stronghold terrain/tile002.png");
    GreenfootImage shSlopeRL = new GreenfootImage("images/Stronghold terrain/slopeRightLeft.png");
    GreenfootImage shSlopeLR = new GreenfootImage("images/Stronghold terrain/slopeLeftRight.png");
    //Grass biome images
    GreenfootImage grass = new GreenfootImage("images/Grass terrain/tile002.png");
    GreenfootImage grassRL = new GreenfootImage("images/Grass terrain/slopeRightLeft.png");
    GreenfootImage bridge = new GreenfootImage("images/Grass terrain/tile106.png");
    GreenfootImage dirt = new GreenfootImage("images/Grass terrain/tile013.png");
    GreenfootImage sideTile = new GreenfootImage("images/Grass terrain/tile073.png");
    //Snow biome images
    GreenfootImage snow = new GreenfootImage("images/Snow terrain/tile002.png");
    GreenfootImage snowSlopeRL = new GreenfootImage("images/Snow terrain/slopeRightLeft.png");
    GreenfootImage snowSlopeLR = new GreenfootImage("images/Snow terrain/slopeLeftRight.png");
    //Background images
    GreenfootImage dungeonBg1 = new GreenfootImage("images/Dungeon terrain/DungeonBg1.png");
    GreenfootImage dungeonBg2 = new GreenfootImage("images/Dungeon terrain/DungeonBg2.png");
    GreenfootImage strongholdBg1 = new GreenfootImage("images/Stronghold terrain/Strongholdbg1.png");
    GreenfootImage strongholdBg2 = new GreenfootImage("images/Stronghold terrain/Strongholdbg2.png");
    GreenfootImage grassBg = new GreenfootImage("images/Grass terrain/GrassBg.png");
    GreenfootImage cloudsBg = new GreenfootImage("images/Grass terrain/CloudsBg.png");
    GreenfootImage snowBg = new GreenfootImage("images/Snow terrain/SnowBg.png");           
    //Background music
    GreenfootSound bgmFallenKing = new GreenfootSound("bgmFallenKing.mp3");
    GreenfootSound bgmSkyBlue = new GreenfootSound("bgmSkyBlue.mp3");
    
    SimpleTimer musicTimer = new SimpleTimer();
    
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
        playMusic();
    }
    
    public void playMusic()
    {
        if(musicTimer.millisElapsed() >= 10000 && (level >=1 && level <= 1))
        {
            bgmFallenKing.playLoop();
        }
        if(musicTimer.millisElapsed() >= 10000 && (level >=2 && level <= 2))
        {
            bgmSkyBlue.playLoop();
        }
    }
    
    private void prepareActor()
    {
        SquatKing squattyboi = new SquatKing(48,48);
        addObject(squattyboi,100,0); 
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
        actor = getObjects(SquatKing.class).get(0);
        switch(level)
        {
            case 1://dungeon
                musicTimer.mark();
                
                //sets background
                dungeonBg1.scale(getWidth(), getHeight());
                setBackground(dungeonBg1);
                //creates middle platforms
                Block block1 = new Block(96, 96, dnGround);
                addObject(block1, 500, 752);
                Block block2 = new Block(96, 96, dnGround);
                addObject(block2, 404, 752);
                Block block3 = new Block(96, 96, dnGround);
                addObject(block3, 596, 752);
                Block block12 = new Block(96, 48, dnGround);
                addObject(block12, 500, 325);
                Block block14 = new Block(96, 48, dnGround);
                addObject(block14, 596, 325);
                Block block35 = new Block(96, 48, dnGround);
                addObject(block35, 404, 325);
                //creates wall and slope on the right
                Block block4 = new Block(96, 96, dnGround);
                addObject(block4, 692, 656);
                Block block5 = new Block(96, 96, dnGround);
                addObject(block5, 692, 560);
                slopeRightLeft slopeRL1 = new slopeRightLeft(this, 96, 96, 692, 512, dnSlopeRL);
                addObject(slopeRL1, 692, 512);
                slopeRightLeft slopeRL2 = new slopeRightLeft(this, 96, 96, 740, 464, dnSlopeRL);
                addObject(slopeRL2, 740, 464);
                Block block10 = new Block(96, 96, dnGround);
                addObject(block10, 836, 464);
                Block block11 = new Block(96, 96, dnGround);
                addObject(block11, 932, 464);
                //fill empty gap
                Block block36 = new Block(96, 96, dnStone);
                addObject(block36, 692, 752);
                Block block37 = new Block(96, 96, dnStone);
                addObject(block37, 788, 752);
                Block block38 = new Block(96, 96, dnStone);
                addObject(block38, 884, 752);
                Block block39 = new Block(96, 96, dnStone);
                addObject(block39, 788, 656);
                Block block40 = new Block(96, 96, dnStone);
                addObject(block40, 788, 560);
                Block block41 = new Block(96, 96, dnStone);
                addObject(block41, 884, 656);
                Block block42 = new Block(96, 96, dnStone);
                addObject(block42, 884, 560);
                Block block43 = new Block(96, 96, dnStone);
                addObject(block43, 980, 560);
                Block block44 = new Block(96, 96, dnStone);
                addObject(block44, 980, 656);
                Block block45 = new Block(96, 96, dnStone);
                addObject(block45, 980, 752);
                //creates wall and slope on the left
                Block block6 = new Block(96, 96, dnGround);
                addObject(block6, 308, 656);
                Block block7 = new Block(96, 96, dnGround);
                addObject(block7, 308, 560);
                slopeLeftRight slopeLR1 = new slopeLeftRight(this, 96, 96, 308, 512, dnSlopeLR);
                addObject(slopeLR1, 308, 512);
                slopeLeftRight slopeLR2 = new slopeLeftRight(this, 96, 96, 260, 464, dnSlopeLR);
                addObject(slopeLR2, 260, 464);
                Block block8 = new Block(96, 96, dnGround);
                addObject(block8, 164, 464);
                Block block9 = new Block(96, 96, dnGround);
                addObject(block9, 68, 464);
                //fill empty gap
                Block block46 = new Block(96, 96, dnStone);
                addObject(block46, 116, 752);
                Block block47 = new Block(96, 96, dnStone);
                addObject(block47, 212, 752);
                Block block48 = new Block(96, 96, dnStone);
                addObject(block48, 308, 752);
                Block block49 = new Block(96, 96, dnStone);
                addObject(block49, 20, 752);
                Block block50 = new Block(96, 96, dnStone);
                addObject(block50, 20, 560);
                Block block51 = new Block(96, 96, dnStone);
                addObject(block51, 116, 560);
                Block block52 = new Block(96, 96, dnStone);
                addObject(block52, 212, 560);
                Block block53 = new Block(96, 96, dnStone);
                addObject(block53, 20, 656);
                Block block54 = new Block(96, 96, dnStone);
                addObject(block54, 116, 656);
                Block block55 = new Block(96, 96, dnStone);
                addObject(block55, 212, 656);
                //creates platform
                Block block15 = new Block(96, 96, dnGround);
                addObject(block15, 164, 150);
                Block block16 = new Block(96, 96, dnGround);
                addObject(block16, 68, 150);
                //creates bounds
                Block block17 = new Block(48, 800, dnGround);
                addObject(block17, 976, 400);
                Block block18 = new Block(48, 800, dnGround);
                addObject(block18, 24, 400);
                break;
            case 2: //dungeon
                dungeonBg2.scale(getWidth(), getHeight());
                setBackground(dungeonBg2);
                //creates bounds
                Block block19 = new Block(48, 800, dnGround);
                addObject(block19, 976, 400);
                Block block20 = new Block(48, 800, dnGround);
                addObject(block20, 24, 400);
                //creates platforms
                Block block21 = new Block(96, 96, dnGround);
                addObject(block21, 592, 675);
                Block block22 = new Block(96, 96, dnGround);
                addObject(block22, 496, 675);
                Block block23 = new Block(96, 48, dnGround);
                addObject(block23, 904, 600);
                Block block24 = new Block(96, 48, dnGround);
                addObject(block24, 475, 450);
                Block block25 = new Block(96, 48, dnGround);
                addObject(block25, 285, 402);
                Block block26 = new Block(96, 48, dnGround);
                addObject(block26, 96, 354);
                Block block27 = new Block(96, 48, dnGround);
                addObject(block27, 210, 170);
                Block block28 = new Block(96, 96, dnGround);
                addObject(block28, 306, 48);
                Block block29 = new Block(96, 96, dnGround);
                addObject(block29, 306, 144);
                Block block31 = new Block(96, 48, dnGround);
                addObject(block31, 571, 450);
                //creates slopes
                slopeLeftRight slopeLR3 = new slopeLeftRight(this, 96, 96, 494, 48, dnSlopeLR);
                addObject(slopeLR3, 494, 48);
                slopeLeftRight slopeLR4 = new slopeLeftRight(this, 96, 96, 542, 96, dnSlopeLR);
                addObject(slopeLR4, 542, 96);
                Block block30 = new Block(96, 96, dnGround);
                addObject(block30, 542, 192);
                slopeLeftRight slopeLR5 = new slopeLeftRight(this, 96, 96, 590, 240, dnSlopeLR);
                addObject(slopeLR5, 590, 240);
                Block block32 = new Block(96, 96, dnGround);
                addObject(block32, 542, 242);
                Block block56 = new Block(96, 96, dnStones);
                addObject(block56, 494, 144);
                Block block57 = new Block(96, 96, dnStones);
                addObject(block57, 494, 240);
                
                slopeRightLeft slopeRL3 = new slopeRightLeft(this, 96, 96, 904, 48, dnSlopeRL);
                addObject(slopeRL3, 904, 48);
                slopeRightLeft slopeRL4 = new slopeRightLeft(this, 96, 96, 856, 96, dnSlopeRL);
                addObject(slopeRL4, 856, 96);
                Block block33 = new Block(96, 96, dnGround);
                addObject(block33, 856, 192);
                slopeRightLeft slopeRL5 = new slopeRightLeft(this, 96, 96, 808, 240, dnSlopeRL);
                addObject(slopeRL5, 808, 240);
                Block block34 = new Block (96, 96, dnGround);
                addObject(block34, 856, 240);
                Block block58 = new Block(96, 96, dnStones);
                addObject(block58, 904, 144);
                Block block59 = new Block(96, 96, dnStones);
                addObject(block59, 904, 240);
                break;
            case 3: //stronghold
                strongholdBg1.scale(getWidth(), getHeight());
                setBackground(strongholdBg1);
                
                Block block62 = new Block(48, 96, shGround);
                addObject(block62, 928, 752);
                Block block63 = new Block(48, 96, shGround);
                addObject(block63, 470, 752);
                Block block64 = new Block(96, 96, shGround);
                addObject(block64, 306, 752);
                Block block65 = new Block(48, 96, shGround);
                addObject(block65, 470, 656);
                Block block66 = new Block(48, 96, shGround);
                addObject(block66, 928, 656);
                Block block67 = new Block(48, 96, shGround);
                addObject(block67, 540, 196);
                slopeRightLeft slopeRL6 = new slopeRightLeft(this, 96, 96, 952, 460, shSlopeRL);
                addObject(slopeRL6, 952, 460);
                Block block68 = new Block(96, 48, shGround);
                addObject(block68, 446, 412);
                Block block69 = new Block(48, 96, shGround);
                addObject(block69, 374, 388);
                Block block70 = new Block(48, 96, shGround);
                addObject(block70, 374, 292);
                Block block71 = new Block(48, 96, shGround);
                addObject(block71, 470, 484);
                //creates bounds
                Block block60 = new Block(48, 800, shGround);
                addObject(block60, 976, 400);
                Block block61 = new Block(48, 800, shGround);
                addObject(block61, 24, 400);
                break;
            case 4: //stronghold
                bgmSkyBlue.stop();
                
                strongholdBg2.scale(getWidth(), getHeight());
                setBackground(strongholdBg2);
                break;
            case 5://grass
                musicTimer.mark();
                bgmFallenKing.stop();
                
                grassBg.scale(getWidth(), getHeight());
                setBackground(grassBg);
                
                slopeLeftRight sLR7 = new slopeLeftRight(this, 96, 96, 240, 368);
                addObject(sLR7, 48, 176);
                slopeLeftRight sLR8 = new slopeLeftRight(this, 96, 96, 288, 416);
                addObject(sLR8, 96, 224);
                slopeLeftRight sLR4 = new slopeLeftRight(this, 96, 96, 144, 272);
                addObject(sLR4, 144, 272);
                slopeLeftRight sLR3 = new slopeLeftRight(this, 96, 96, 192, 320);
                addObject(sLR3, 192, 320);
                slopeLeftRight sLR2 = new slopeLeftRight(this, 96, 96, 240, 368);
                addObject(sLR2, 240, 368);
                slopeLeftRight sLR1 = new slopeLeftRight(this, 96, 96, 288, 416);
                addObject(sLR1, 288, 416);
                
                Block borderR = new Block(96, 800, sideTile);
                addObject(borderR, 1000,400);
                Block borderL = new Block(96, 800, sideTile);
                addObject(borderL, 0,400);
                
                Block b9 = new Block(96, 96, grass);
                addObject(b9, 96,272);
                Block bl = new Block(96, 96, dirt);
                addObject(bl, 96,368);
                Block bl1 = new Block(96, 96, grass);
                addObject(bl1, 192,368);
                
                Block b3 = new Block(96, 96, grass);
                addObject(b3, 96,708);
                Block b4 = new Block(96, 96, grass);
                addObject(b4, 288,708);
                Block b5 = new Block(96, 96, grass);
                addObject(b5, 384,708);
                Block b28 = new Block(96, 96, grass);
                addObject(b28, 480,708);
                Block b29 = new Block(96, 96, grass);
                addObject(b29, 576,708);
                Block b30 = new Block(96, 96, grass);
                addObject(b30, 672,708);
                
                Block b6 = new Block(96, 96, dirt);
                addObject(b6, 96,464);
                Block b7 = new Block(96, 96, dirt);
                addObject(b7, 192,464);
                Block b8 = new Block(96, 96, dirt);
                addObject(b8, 288,464);
                 
                slopeRightLeft sRL2 = new slopeRightLeft(this, 96, 96, 760, 320, grassRL);
                addObject(sRL2, 760, 320);
                slopeRightLeft sRL1 = new slopeRightLeft(this, 96, 96, 712, 368, grassRL);
                addObject(sRL1, 712, 368);
                Block b13 = new Block(96, 96, grass);
                addObject(b13, 808,320);
                Block b10 = new Block(96, 96, dirt);
                addObject(b10, 808,416);
                Block b11 = new Block(96, 96, grass);
                addObject(b11, 712,416);
                Block b12 = new Block(96, 48, grass);
                addObject(b12, 616,440);
                
                Block bl2 = new Block(96, 96, grass);
                addObject(bl2, 480,176);
                
                
                break;
                
            case 6://grass
                cloudsBg.scale(getWidth(), getHeight());
                setBackground(cloudsBg);
                
                actor.windyLvl(true, "right");
                
                slopeRightLeft sRL4 = new slopeRightLeft(this, 96, 96, 952, 752, grassRL);
                addObject(sRL4, 952, 752);
                slopeRightLeft sRL5 = new slopeRightLeft(this, 96, 96, 952, 800, grassRL);
                addObject(sRL5, 904, 800);
                
                Block borderR1 = new Block(96, 800, sideTile);
                addObject(borderR1, 1000,400);
                Block borderL1 = new Block(96, 800, sideTile);
                addObject(borderL1, 0,400);
                
                Block b15 = new Block(96, 96, grass); //bottom left
                addObject(b15, 96,704);                
                Block b31 = new Block(96, 96, grass); 
                addObject(b31, 192,704);  
                
                Block b26 = new Block(96, 96, grass);
                addObject(b26, 432,608);
                
                Block b27 = new Block(96, 96, grass);
                addObject(b27, 624,608);
                
                Block b17 = new Block(96, 96, grass);
                addObject(b17, 816,608);
                
                Block b18 = new Block(96, 96, grass);
                addObject(b18, 768,344);
                
                Block b21 = new Block(96, 96, bridge);
                addObject(b21, 576,200);
                Block b23 = new Block(96, 96, bridge);
                addObject(b23, 480,200);
                Block b24 = new Block(96, 96, bridge);
                addObject(b24, 384,200);
                
                break;
            case 7://snow
                snowBg.scale(getWidth(), getHeight());
                setBackground(snowBg);
                
                Block borderR2 = new Block(96, 800, sideTile);
                addObject(borderR2, 1000,400);
                Block borderL2 = new Block(96, 800, sideTile);
                addObject(borderL2, 0,400);
                
                iceBlock ib1 = new iceBlock(96, 96, snow);
                addObject(ib1, 96,704);
                iceBlock ib2 = new iceBlock(96, 96, snow);
                addObject(ib2, 192,704);
                
                iceBlock ib3 = new iceBlock(96, 96, snow);
                addObject(ib3, 432,608);
                iceBlock ib4 = new iceBlock(96, 96, snow);
                addObject(ib4, 624,512);
                iceBlock ib5 = new iceBlock(96, 96, snow);
                addObject(ib5, 904,512);
                /*
                slopeRightLeft sRL12 = new slopeRightLeft(this, 96, 96, 432, 416, snowSlopeRL);
                addObject(sRL12, 432, 416);
                */
                slopeRightLeft sRL11 = new slopeRightLeft(this, 96, 96, 480, 176, snowSlopeRL);
                addObject(sRL11, 480, 176); 
                slopeRightLeft sRL10 = new slopeRightLeft(this, 96, 96, 432, 224, snowSlopeRL);
                addObject(sRL10, 432, 224); 
                slopeRightLeft sRL9 = new slopeRightLeft(this, 96, 96, 384, 272, snowSlopeRL);
                addObject(sRL9, 384, 272);
                
                
                iceBlock ib6 = new iceBlock(96, 96, snow);
                addObject(ib6, 528,176);
                
                iceBlock ib7 = new iceBlock(96, 96, snow);
                addObject(ib7, 764,272);
                
            case 8://snow
                snowBg.scale(getWidth(), getHeight());
                setBackground(snowBg);
                
                iceBlock ib8 = new iceBlock(96, 96, snow);
                addObject(ib8, 0,500);
                iceBlock ib9 = new iceBlock(96, 96, snow);
                addObject(ib9, 0,500);
                iceBlock ib10 = new iceBlock(96, 96, snow);
                addObject(ib10, 0,500);
                
                
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

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private int speed = 5;
    public int level = 4;
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
    GreenfootImage shStones = new GreenfootImage("images/Stronghold terrain/tile036.png");
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
    GreenfootImage snowStones = new GreenfootImage("images/Snow terrain/tile093.png");
    GreenfootImage snowStonesL = new GreenfootImage("images/Snow terrain/tile106.png");
    GreenfootImage snowStonesR = new GreenfootImage("images/Snow terrain/tile107.png");
    //Background images
    GreenfootImage dungeonBg1 = new GreenfootImage("images/Dungeon terrain/DungeonBg1.png");
    GreenfootImage dungeonBg2 = new GreenfootImage("images/Dungeon terrain/DungeonBg2.png");
    GreenfootImage strongholdBg1 = new GreenfootImage("images/Stronghold terrain/Strongholdbg1.png");
    GreenfootImage strongholdBg2 = new GreenfootImage("images/Stronghold terrain/Strongholdbg2.png");
    GreenfootImage grassBg = new GreenfootImage("images/Grass terrain/GrassBg.png");
    GreenfootImage cloudsBg = new GreenfootImage("images/Grass terrain/CloudsBg.png");
    GreenfootImage snowBg = new GreenfootImage("images/Snow terrain/SnowBg.png");     
    GreenfootImage wind = new GreenfootImage("wind.png");     
    //Background music
    GreenfootSound bgmFallenKing = new GreenfootSound("bgmFallenKing.mp3");
    GreenfootSound bgmSkyBlue = new GreenfootSound("bgmSkyBlue.mp3");
    //Progress bar images
    GreenfootImage progressBarImg[] = new GreenfootImage[9];
    //Button images
    GreenfootImage buttonImgs[] = new GreenfootImage[18];
    
    SimpleTimer musicTimer = new SimpleTimer();
    SimpleTimer gameTimer = new SimpleTimer();
    
    SquatKing actor;
    SmokingHotBBQ BBQ;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1, false); 
        //addObject(new fade(null), getWidth()/2, getHeight()/2);
        for(int i = 0; i < progressBarImg.length; i++)
        {
            progressBarImg[i] = new GreenfootImage("images/Progress Bar/lvl" + i + ".png");
        }
        for(int i = 0; i < buttonImgs.length; i++)
        {
            buttonImgs[i] = new GreenfootImage("images/Button Images/button" + i + ".png");
        }
        
        setPaintOrder(fade.class, progressBar.class, dummyBlock.class, SquatKing.class, wind.class, Slopes.class,Terrain.class);
        prepareActor();
        prepare();
    }
    
    public void act()
    {
        actor = getObjects(SquatKing.class).get(0);
        hiddenWorld();
        if(actor.getY() < 0)//going up
        {
            nextLevel();
            actor.setLocation(actor.getX(), 795);
        }
        if(actor.getY() > 800) //going down
        {
            previousLevel();
            actor.setLocation(actor.getX(), 0);
            Utils.setlvlsFall();
        }
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
        if(level >= 1 && level <= 4)
        {
            bgmSkyBlue.stop();
        }
        if(level >= 5 && level <= 8)
        {
            bgmFallenKing.stop();
        }
        if(musicTimer.millisElapsed() >= 10000 && (level >= 1 && level <= 4))
        {
            bgmFallenKing.playLoop();
        }
        if(musicTimer.millisElapsed() >= 10000 && (level >= 5 && level <= 8))
        {
            bgmSkyBlue.playLoop();
        }
    }
    
    private void prepareActor()
    {
        SquatKing squattyboi = new SquatKing(48,48);
        addObject(squattyboi,300, 500); 
        Utils utils = new Utils();
        addObject(utils, 0, 0);
        gameTimer.mark();
    }
    
    private void changeWorld()
    {
        Utils.setTime(gameTimer.millisElapsed());
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
                int terrainSizeXSizeYCoordsXCoordsY1[][]=
                {
                    {96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,48,48},
                    {96,96,96,48,48,48,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,800,600},
                    {500,404,596,500,596,404,692,692,836,932,692,788,884,788,788,884,884,980,980,980,308,164,68,116,212,308,20,20,116,212,164,68,976,24},
                    {752,752,752,325,325,325,656,560,464,464,752,752,752,656,560,656,560,560,656,752,560,464,464,752,752,752,752,560,560,560,150,150,400,300}
                };
                
                GreenfootImage terrainImgs1[] = new GreenfootImage[]{dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,
                                                                    dnStone,dnStone,dnStone,dnStone,dnStone,dnStone,dnStone,dnStone,dnStone,dnStone,
                                                                    dnGround,dnGround,dnGround,
                                                                    dnStone,dnStone,dnStone,dnStone,dnStone,dnStone,dnStone,
                                                                    dnGround,dnGround,dnGround,dnGround};
                //Creates all terrain blocks (specifically block.class)                                             
                for(int i = 0; i < 34; i++)
                {
                    Block block = new Block(terrainSizeXSizeYCoordsXCoordsY1[0][i],terrainSizeXSizeYCoordsXCoordsY1[1][i],terrainImgs1[i]);
                    addObject(block, terrainSizeXSizeYCoordsXCoordsY1[2][i], terrainSizeXSizeYCoordsXCoordsY1[3][i]);
                }
                
                musicTimer.mark();
                actor.windyLvl(false);
                //sets background
                dungeonBg1.scale(getWidth(), getHeight());
                setBackground(dungeonBg1);
                //progress bar image
                progressBar bar1 = new progressBar(30, 198, progressBarImg[0]);
                addObject(bar1, 980, 700);
                //Creates all slopes
                slopeRightLeft slopeRL1 = new slopeRightLeft(this, 96, 96, 692, 512, dnSlopeRL);
                addObject(slopeRL1, 692, 512);
                slopeRightLeft slopeRL2 = new slopeRightLeft(this, 96, 96, 740, 464, dnSlopeRL);
                addObject(slopeRL2, 740, 464);
                slopeLeftRight slopeLR1 = new slopeLeftRight(this, 96, 96, 308, 512, dnSlopeLR);
                addObject(slopeLR1, 308, 512);
                slopeLeftRight slopeLR2 = new slopeLeftRight(this, 96, 96, 260, 464, dnSlopeLR);
                addObject(slopeLR2, 260, 464);
                //Creates all dummyblocks
                dummyBlock block6 = new dummyBlock(96, 96, dnGround);
                addObject(block6, 308, 656);
                dummyBlock block53 = new dummyBlock(96, 96, dnStone);
                addObject(block53, 20, 656);
                dummyBlock block54 = new dummyBlock(96, 96, dnStone);
                addObject(block54, 116, 656);
                dummyBlock block55 = new dummyBlock(96, 96, dnStone);
                addObject(block55, 212, 656);
                break;
            case 2: //dungeon
                int terrainSizeXSizeYCoordsXCoordsY2[][]=
                {
                    {96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,48,48},
                    {96,96,48,48,48,48,48,96,96,48,96,96,96,96,96,96,96,96,800,800},
                    {592,496,904,475,285,96,210,306,306,571,542,542,494,494,856,856,904,904,24,976},
                    {675,675,600,450,402,354,170,48,144,450,192,242,144,240,192,240,144,240,400,400}
                };
                GreenfootImage terrainImgs2[] = new GreenfootImage[]{dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,
                                                                     dnStones, dnStones,
                                                                     dnGround,dnGround,dnStones,dnStones,dnGround,dnGround};
                                                                     
                for(int i = 0; i < 20; i++)
                {
                    Block block = new Block(terrainSizeXSizeYCoordsXCoordsY2[0][i],terrainSizeXSizeYCoordsXCoordsY2[1][i],terrainImgs2[i]);
                    addObject(block, terrainSizeXSizeYCoordsXCoordsY2[2][i], terrainSizeXSizeYCoordsXCoordsY2[3][i]);
                }
                actor.windyLvl(false);
                //Sets background
                dungeonBg2.scale(getWidth(), getHeight());
                setBackground(dungeonBg2);
                //progress bar image
                progressBar bar2 = new progressBar(30, 198, progressBarImg[1]);
                addObject(bar2, 980, 700);
                //creates slopes
                slopeLeftRight slopeLR3 = new slopeLeftRight(this, 96, 96, 494, 48, dnSlopeLR);
                addObject(slopeLR3, 494, 48);
                slopeLeftRight slopeLR4 = new slopeLeftRight(this, 96, 96, 542, 96, dnSlopeLR);
                addObject(slopeLR4, 542, 96);
                slopeLeftRight slopeLR5 = new slopeLeftRight(this, 96, 96, 590, 240, dnSlopeLR);
                addObject(slopeLR5, 590, 240);
                slopeRightLeft slopeRL3 = new slopeRightLeft(this, 96, 96, 904, 48, dnSlopeRL);
                addObject(slopeRL3, 904, 48);
                slopeRightLeft slopeRL4 = new slopeRightLeft(this, 96, 96, 856, 96, dnSlopeRL);
                addObject(slopeRL4, 856, 96);
                slopeRightLeft slopeRL5 = new slopeRightLeft(this, 96, 96, 808, 240, dnSlopeRL);
                addObject(slopeRL5, 808, 240);
                break;
            case 3: //stronghold
                int terrainSizeXSizeYCoordsXCoordsY3[][]=
                {
                    {48,48,96,48,48,48,96,48,48,48,48,48,96,96,48,48,48,48,96,144,144,72,72,48,48},
                    {96,96,96,96,96,96,48,96,96,96,96,96,48,48,48,96,96,96,48,48,48,96,96,800,800},
                    {928,470,306,470,928,540,446,374,374,470,540,540,302,96,216,264,264,264,96,345,459,338,410,24,974},
                    {752,752,752,656,656,196,412,388,292,484,100,4,412,412,250,226,130,34,75,24,24,484,484,400,400}
                };
                GreenfootImage terrainImgs3[] = new GreenfootImage[]{shGround,shGround,shGround,shGround,shGround,shGround,shGround,shGround,shGround,shGround,shGround,shGround,
                                                                     shGround,shGround,shGround,shGround,shGround,shGround,shGround,shGround,shGround,shStones,shStones,shGround,shGround};
                                                                     
                for(int i = 0; i < 25; i++)
                {
                    Block block = new Block(terrainSizeXSizeYCoordsXCoordsY3[0][i],terrainSizeXSizeYCoordsXCoordsY3[1][i],terrainImgs3[i]);
                    addObject(block, terrainSizeXSizeYCoordsXCoordsY3[2][i], terrainSizeXSizeYCoordsXCoordsY3[3][i]);
                }
                actor.windyLvl(false);
                //Sets background
                strongholdBg1.scale(getWidth(), getHeight());
                setBackground(strongholdBg1);
                //progress bar image
                progressBar bar3 = new progressBar(30, 198, progressBarImg[2]);
                addObject(bar3, 980, 700);
                //Creates all slopes
                slopeRightLeft slopeRL6 = new slopeRightLeft(this, 96, 96, 952, 460, shSlopeRL);
                addObject(slopeRL6, 952, 460);
                slopeRightLeft slopeRL13 = new slopeRightLeft(this, 96, 96, 248, 485, shSlopeRL);
                addObject(slopeRL13, 254, 484);
                break;
            case 4: //stronghold
                int terrainSizeXSizeYCoordsXCoordsY4[][]=
                {
                    {81,81,81,81,81,72,72,40,96,96,48,96,96,96,96,96,96,96,96,96,96,96,48,48,48},
                    {48,81,81,81,81,48,48,96,48,48,96,48,96,96,48,96,96,48,48,48,48,48,48,800,800},
                    {280,489,530,448,529,916,844,890,400,96,550,496,90,150,192,96,96,290,386,482,568,664,280,24,976},
                    {776,704,704,780,780,600,600,450,510,414,210,272,486,486,150,126,30,24,24,24,24,24,270,400,400}
                };
                GreenfootImage terrainImgs4[] = new GreenfootImage[]{shGround,shGround,shGround,shStones,shStones,shGround,shGround,shGround,shGround,shGround,shGround,shGround,shStones,shStones,shGround,
                                                                    shGround,shGround,shGround,shGround,shGround,shGround,shGround,shGround,shGround,shGround};
                                                                     
                for(int i = 0; i < 25; i++)
                {
                    Block block = new Block(terrainSizeXSizeYCoordsXCoordsY4[0][i],terrainSizeXSizeYCoordsXCoordsY4[1][i],terrainImgs4[i]);
                    addObject(block, terrainSizeXSizeYCoordsXCoordsY4[2][i], terrainSizeXSizeYCoordsXCoordsY4[3][i]);
                }
                actor.windyLvl(false);
                //bgmSkyBlue.stop();
                
                //progress bar image
                progressBar bar4 = new progressBar(30, 198, progressBarImg[3]);
                addObject(bar4, 980, 700);
                //Sets background
                strongholdBg2.scale(getWidth(), getHeight());
                setBackground(strongholdBg2);
                //Creates all slopes
                slopeRightLeft slopeRL8 = new slopeRightLeft(this, 81, 81, 361, 751, shSlopeRL);
                addObject(slopeRL8, 361, 751);
                slopeRightLeft slopeRL9 = new slopeRightLeft(this, 81, 81, 408, 704, shSlopeRL);
                addObject(slopeRL9, 408, 704);
                slopeRightLeft slopeRL7 = new slopeRightLeft(this, 96, 96, 840, 450, shSlopeRL);
                addObject(slopeRL7, 840, 450);
                slopeLeftRight slopeLR6 = new slopeLeftRight(this, 96, 96, 220, 486, shSlopeLR);
                addObject(slopeLR6, 220, 486);
                slopeLeftRight slopeLR7 = new slopeLeftRight(this, 96, 96, 172, 438, shSlopeLR);
                addObject(slopeLR7, 172, 438);
                slopeRightLeft slopeRL10 = new slopeRightLeft(this, 96, 96, 400, 296, shSlopeRL);
                addObject(slopeRL10, 400, 296);
                slopeRightLeft slopeRL11 = new slopeRightLeft(this, 96, 96, 952, 100, shSlopeRL);
                addObject(slopeRL11, 952, 100);
                slopeRightLeft slopeRL12 = new slopeRightLeft(this, 96, 96, 904, 148, shSlopeRL);
                addObject(slopeRL12, 904, 148);
                break;
            case 5://grass
                actor.windyLvl(false);
                musicTimer.mark();
                
                //progress bar image
                progressBar bar5 = new progressBar(30, 198, progressBarImg[4]);
                addObject(bar5, 980, 700);
                
                grassBg.scale(getWidth(), getHeight());
                setBackground(grassBg);
                
                slopeLeftRight sLR7 = new slopeLeftRight(this, 96, 96, 48, 176);
                addObject(sLR7, 48, 176);
                slopeLeftRight sLR8 = new slopeLeftRight(this, 96, 96, 96, 224);
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
                addObject(b9, 96,278);
                Block bl = new Block(96, 96, dirt);
                addObject(bl, 96,368);
                Block bl1 = new Block(96, 96, grass);
                addObject(bl1, 192,374);
                
                Block b3 = new Block(96, 96, grass);
                addObject(b3, 96,800);
                Block b4 = new Block(96, 96, grass);
                addObject(b4, 288,800);
                Block b5 = new Block(96, 96, grass);
                addObject(b5, 384,800);
                Block b28 = new Block(96, 96, grass);
                addObject(b28, 480,800);
                Block b29 = new Block(96, 96, grass);
                addObject(b29, 576,800);
                Block b30 = new Block(96, 96, grass);
                addObject(b30, 672,800);
                
                Block b6 = new Block(96, 96, dirt);
                addObject(b6, 96,464);
                Block b7 = new Block(96, 96, dirt);
                addObject(b7, 192,464);
                Block b8 = new Block(96, 96, dirt);
                addObject(b8, 288,464);
                 
                slopeRightLeft sRL2 = new slopeRightLeft(this, 96, 96, 760, 464, grassRL);
                addObject(sRL2, 760, 368);
                slopeRightLeft sRL1 = new slopeRightLeft(this, 96, 96, 712, 416, grassRL);
                addObject(sRL1, 712, 416);
                Block b13 = new Block(96, 96, grass);
                addObject(b13, 808,368);
                Block b10 = new Block(96, 96, dirt);
                addObject(b10, 808,464);
                Block b11 = new Block(96, 96, grass);
                addObject(b11, 712,464);
                Block b12 = new Block(96, 48, grass);
                addObject(b12, 616,488);
                
                Block bl2 = new Block(96, 96, grass);
                addObject(bl2, 480,176);
                
                
                break;
                
            case 6://grass
                cloudsBg.scale(getWidth(), getHeight());
                setBackground(cloudsBg);
                
                //progress bar image
                progressBar bar6 = new progressBar(30, 198, progressBarImg[5]);
                addObject(bar6, 980, 700);
                
                actor.windyLvl(true, "right");
                windImage1 wind1 = new windImage1(this, "right");
                addObject(wind1, getWidth() / 2, getHeight() / 2);
                
                slopeRightLeft sRL4 = new slopeRightLeft(this, 96, 96, 952, 752, grassRL);
                addObject(sRL4, 952, 752);
                slopeRightLeft sRL5 = new slopeRightLeft(this, 96, 96, 904, 800, grassRL);
                addObject(sRL5, 904, 800);
                
                Block borderR1 = new Block(96, 800, sideTile);
                addObject(borderR1, 1000,400);
                Block borderL1 = new Block(96, 800, sideTile);
                addObject(borderL1, 0,400);
                
                Block b15 = new Block(96, 96, grass);
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
                addObject(b21, 596,200);
                Block b23 = new Block(96, 96, bridge);
                addObject(b23, 500,200);
                Block b24 = new Block(96, 96, bridge);
                addObject(b24, 404,200);
                
                break;
            case 7://snow
                snowBg.scale(getWidth(), getHeight());
                setBackground(snowBg);
                
                //progress bar image
                progressBar bar7 = new progressBar(30, 198, progressBarImg[6]);
                addObject(bar7, 980, 700);
                
                actor.windyLvl(false);
                
                Block borderR2 = new Block(96, 800, sideTile);
                addObject(borderR2, 1000,400);
                Block borderL2 = new Block(96, 800, sideTile);
                addObject(borderL2, 0,400);
                
                iceBlock ib1 = new iceBlock(96, 96, snow);
                addObject(ib1, 96,752);
                iceBlock ib2 = new iceBlock(96, 96, snow);
                addObject(ib2, 192,752);
                
                iceBlock ib3 = new iceBlock(96, 96, snow);
                addObject(ib3, 432,608);
                iceBlock ib4 = new iceBlock(96, 96, snow);
                addObject(ib4, 624,512);
                iceBlock ib5 = new iceBlock(96, 96, snow);
                addObject(ib5, 904,512);

                slopeRightLeft sRL11 = new slopeRightLeft(this, 96, 96, 528, 176, snowSlopeRL);
                addObject(sRL11, 528, 176); 
                slopeRightLeft sRL10 = new slopeRightLeft(this, 96, 96, 480, 224, snowSlopeRL);
                addObject(sRL10, 480, 224); 
                slopeRightLeft sRL9 = new slopeRightLeft(this, 96, 96, 432, 272, snowSlopeRL);
                addObject(sRL9, 432, 272);
                
                iceBlock ib6 = new iceBlock(96, 96, snow);
                addObject(ib6, 576,176);
                
                iceBlock ib7 = new iceBlock(96, 96, snow);
                addObject(ib7, 764,272);
                
                break;
            case 8://snow
                snowBg.scale(getWidth(), getHeight());
                setBackground(snowBg);
                
                //progress bar image
                progressBar bar8 = new progressBar(30, 198, progressBarImg[7]);
                addObject(bar8, 980, 700);
                
                Block edge1 = new Block(96, 800);
                addObject(edge1, 0, 400);
                Block edge2 = new Block(96, 800);
                addObject(edge2, 1000, 400);
                
                Block b40 = new Block(72, 96, snowStonesL);
                addObject(b40, 288,0);
                Block b68 = new Block(72, 96, snowStonesL);
                addObject(b68, 288,288);
                Block b35 = new Block(72, 96, snowStonesL);
                addObject(b35, 288,384);
                Block b36 = new Block(72, 96, snowStonesL);
                addObject(b36, 288,480);
                Block b37 = new Block(72, 96, snowStonesL);
                addObject(b37, 288,576);
                Block b65 = new Block(72, 96, snowStonesL);
                addObject(b65, 288,672);
                
                Block b48 = new Block(72, 96, snowStonesR);
                addObject(b48, 712,0);
                Block b41 = new Block(72, 96, snowStonesR);
                addObject(b41, 712,96);
                Block b42 = new Block(72, 96, snowStonesR);
                addObject(b42, 712,192);
                Block b70 = new Block(72, 96, snowStonesR);
                addObject(b70, 712,288);
                Block b46 = new Block(72, 96, snowStonesR);
                addObject(b46, 712,576);
                Block b66 = new Block(72, 96, snowStonesR);
                addObject(b66, 712,672);
                
                iceBlock ib9 = new iceBlock(96, 48, snow);
                addObject(ib9, 372,720);
                iceBlock ib8 = new iceBlock(48, 48, snow);
                addObject(ib8, 724,528);
                Block b71 = new Block(48, 48, snowStones);
                addObject(b71, 452, 372);
                Block b72 = new Block(48, 48, snowStones);
                addObject(b72, 512, 168);
                iceBlock ib13 = new iceBlock(96, 48, snow);
                addObject(ib13, 276, 240);
                break;
            case 9:
                snowBg.scale(getWidth(), getHeight());
                setBackground(snowBg);
                
                //progress bar image
                progressBar bar9 = new progressBar(30, 198, progressBarImg[8]);
                addObject(bar9, 980, 700);
                
                Block edge3 = new Block(96, 800);
                addObject(edge3, 0, 400);
                Block edge4 = new Block(96, 800);
                addObject(edge4, 1000, 400);
                
                Block b52 = new Block(72, 96, snowStonesL);
                addObject(b52, 288,288);
                Block b53 = new Block(72, 96, snowStonesL);
                addObject(b53, 288,384);
                Block b54 = new Block(72, 96, snowStonesL);
                addObject(b54, 288,480);
                Block b55 = new Block(72, 96, snowStonesL);
                addObject(b55, 288,576);
                Block b32 = new Block(72, 96, snowStonesL);
                addObject(b32, 288,672);
                Block b33 = new Block(72, 96, snowStonesL);
                addObject(b33, 288,768);
                Block b49 = new Block(72, 96, snowStonesL);
                addObject(b49, 288,866);
                
                Block b58 = new Block(72, 96, snowStonesR);
                addObject(b58, 808,288);
                Block b59 = new Block(72, 96, snowStonesR);
                addObject(b59, 712,288);
                Block b60 = new Block(72, 96, snowStonesR);
                addObject(b60, 712,384);
                Block b64 = new Block(72, 96, snowStonesR);
                addObject(b64, 712,480);
                Block b61 = new Block(72, 96, snowStonesR);
                //addObject(b61, 712,576);
                Block b62 = new Block(72, 96, snowStonesR);
                //addObject(b62, 808,672);
                Block b63 = new Block(72, 96, snowStonesR);
                addObject(b63, 712,768);
                Block b67 = new Block(72, 96, snowStonesR);
                addObject(b67, 712,864);
                
                SmokingHotBBQ bbq = new SmokingHotBBQ(50, 100);
                addObject(bbq, 808, 192);
                
                iceBlock ib10 = new iceBlock(96, 48, snow);
                addObject(ib10, 724, 696);
                iceBlock ib12 = new iceBlock(96, 48, snow);
                addObject(ib12, 512, 168);
                break;
            case 10:
                dungeonBg1.scale(getWidth(), getHeight());
                setBackground(dungeonBg1);
                
                Block block112 = new Block(96, 96, dnGround);
                addObject(block112, 952, 752);
                Block block113 = new Block(96, 96, dnGround);
                addObject(block113, 856, 752);
                Block block114 = new Block(96, 96, dnGround);
                addObject(block114, 760, 752);
                Block block115 = new Block(96, 96, dnGround);
                addObject(block115, 664, 752);
                Block block116 = new Block(96, 96, dnGround);
                addObject(block116, 568, 752);
                Block block117 = new Block(96, 96, dnGround);
                addObject(block117, 472, 752);
                Block block118 = new Block(96, 96, dnGround);
                addObject(block118, 376, 752);
                Block block119 = new Block(96, 96, dnGround);
                addObject(block119, 280, 752);
                Block block120 = new Block(96, 96, dnGround);
                addObject(block120, 184, 752);
                Block block121 = new Block(96, 96, dnGround);
                addObject(block121, 88, 752);
                Block block122 = new Block(96, 96, dnGround);
                addObject(block122, -8, 752);
                
                button button1 = new button(40, 40, 1, buttonImgs[0], buttonImgs[1]);
                addObject(button1, 850, 500);
                button button2 = new button(40, 40, 2, buttonImgs[2], buttonImgs[3]);
                addObject(button2, 775, 500);
                button button3 = new button(40, 40, 3, buttonImgs[4], buttonImgs[5]);
                addObject(button3, 700, 500);
                button button4 = new button(40, 40, 4, buttonImgs[6], buttonImgs[7]);
                addObject(button4, 625, 500);
                button button5 = new button(40, 40, 5, buttonImgs[8], buttonImgs[9]);
                addObject(button5, 550, 500);
                button button6 = new button(40, 40, 6, buttonImgs[10], buttonImgs[11]);
                addObject(button6, 475, 500);
                button button7 = new button(40, 40, 7, buttonImgs[12], buttonImgs[13]);
                addObject(button7, 400, 500);
                button button8 = new button(40, 40, 8, buttonImgs[14], buttonImgs[15]);
                addObject(button8, 325, 500);
                button button9 = new button(40, 40, 9, buttonImgs[16], buttonImgs[17]);
                addObject(button9, 250, 500);
                
                SmokingHotBBQ bbq2 = new SmokingHotBBQ(96, 96);
                addObject(bbq2, 100, 656);
                
                Block block123 = new Block(48, 600, dnGround);
                addObject(block123, 976, 300);
                Block block124 = new Block(48, 800, dnGround);
                addObject(block124, 24, 400);
                break;
        }
    }
    public void hiddenWorld()
    {
        if(actor.getX() < 0 && actor.getY() > 600)
        {
            level = 10;
            clearWorld();
            actor.setLocation(1000, actor.getY());
        }
        if(actor.getX() > 1000 && actor.getY() > 600 && level == 10)
        {
            level = 1;
            clearWorld();
            actor.setLocation(0, actor.getY());
        }
    }
    //Teleports actor to differnt world depending on button pressed and sets location for each level
    public void teleportWorld(int level)
    {
        this.level = level;
        clearWorld();
        switch(level)
        {
            case 1:
                actor.setLocation(500, 700);
                break;
            case 2:
                actor.setLocation(550, 550);
                break;
            case 3:
                actor.setLocation(310, 550);
                break;
            case 4:
                actor.setLocation(300, 550);
                break;
            case 5:
                actor.setLocation(500, 700);
                break;
            case 6:
                actor.setLocation(100, 600);
                break;
            case 7:
                actor.setLocation(100, 600);
                break;
            case 8:
                actor.setLocation(375, 500);
                break;
            case 9:
                actor.setLocation(730, 600);
                break;
        }
    }
    //Removes all objects in the world
    public void clearWorld()
    {
        removeObjects(getObjects(gameAssets.class));
        prepare();
    }
    //Clears current screen and sets to next level 
    public void nextLevel()
    {
        removeObjects(getObjects(gameAssets.class));
        
        level++;
        prepare();
    }
    //Clears current screen and set to previous level
    public void previousLevel()
    {
        removeObjects(getObjects(gameAssets.class));

        level--;
        prepare();
    }
}

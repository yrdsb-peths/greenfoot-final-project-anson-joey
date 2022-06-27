import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * MyWorld world class; It's the main world where the game takes place.
 * Contains tileset & background images, level design, music, and
 * logic for moving between areas of the world.
 * 
 * Joey & Anson
 * June 17, 2022
 */
public class MyWorld extends World
{
    private int speed = 5;
    public int level = 1;
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
    GreenfootImage sideTile = new GreenfootImage("boundAsset.png");
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
    /**
     * Construtor to create a new gameworld
     * 
     * No returns or parameters
     */
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
            Utils.setlvlsFall(); //keeps track of how many levels you've fallen in total
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
    /**
     * Plays different music depending on the level the player is currently at
     * 
     * No returns or parameters
     */
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
        if(level == 11)
        {
            bgmSkyBlue.stop();
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
        addObject(squattyboi,500, 680); 
        Utils utils = new Utils();
        addObject(utils, 0, 0);
        gameTimer.mark();
    }
    
    /**
     * Initilizes and sets the world to BBQScene if it detects that the player has touched a BBQ
     * 
     * No returns or parameters
     */
    private void changeWorld()
    {
        Utils.setTime(gameTimer.millisElapsed());
        touchingbbq = true;
        BBQScene world1 = new BBQScene();
        fade fadeout1 = new fade(world1);
        addObject(fadeout1, getWidth() / 2, getHeight() / 2);
    }
    /**
     * Initilizes and creates all levels 
     * 
     * No returns or parameters
     */
    private void prepare()
    {
        actor = getObjects(SquatKing.class).get(0);
        switch(level)
        {
            case 1://dungeon biome                
                int terrainSizeXSizeYCoordsXCoordsY1[][]=
                {
                    {96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,48,48}, //Width for all blocks
                    {96,96,96,48,48,48,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,510,510}, //Height for all blocks
                    {500,404,596,500,596,404,692,692,836,932,692,788,884,788,788,884,884,980,980,980,308,164,68,116,212,308,20,20,116,212,164,68,976,24}, //X coordinates for all blocks
                    {752,752,752,325,325,325,656,560,464,464,752,752,752,656,560,656,560,560,656,752,560,464,464,752,752,752,752,560,560,560,150,150,255,255} //Y coordinates for all blocks
                };
                //GreenfootImages for all blocks
                GreenfootImage terrainImgs1[] = new GreenfootImage[]{dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,
                                                                    dnStone,dnStone,dnStone,dnStone,dnStone,dnStone,dnStone,dnStone,dnStone,dnStone,
                                                                    dnGround,dnGround,dnGround,
                                                                    dnStone,dnStone,dnStone,dnStone,dnStone,dnStone,dnStone,
                                                                    dnGround,dnGround,sideTile,sideTile}; 
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
            case 2: //dungeon biome
                int terrainSizeXSizeYCoordsXCoordsY2[][]=
                {
                    {96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,48,48}, //Width for all blocks
                    {96,96,48,48,48,48,48,96,96,48,96,96,96,96,96,96,96,96,800,800}, //Height for all blocks
                    {592,496,904,475,285,96,210,306,306,571,542,542,494,494,856,856,904,904,24,976}, //X coordinates for all blocks
                    {675,675,600,450,402,354,170,48,144,450,192,242,144,240,192,240,144,240,400,400} //Y coordinates for all blocks
                };
                //GreenfootImages for all blocks
                GreenfootImage terrainImgs2[] = new GreenfootImage[]{dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,dnGround,
                                                                     dnStones, dnStones,dnGround,dnGround,dnStones,dnStones,sideTile,sideTile}; 
                                                                     
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
            case 3: //stronghold biome
                int terrainSizeXSizeYCoordsXCoordsY3[][]=
                {
                    {48,48,96,48,48,48,96,48,48,48,48,48,96,96,48,48,48,48,96,144,144,72,72,48,48}, //Width for all blocks
                    {96,96,96,96,96,96,48,96,96,96,96,96,48,48,48,96,96,96,48,48,48,96,96,800,800}, //Height for all blocks
                    {928,470,306,470,928,540,446,374,374,470,540,540,302,96,216,264,264,264,96,345,459,338,410,24,974}, //X coordinates for all blocks
                    {752,752,752,656,656,196,412,388,292,484,100,4,412,412,250,226,130,34,75,24,24,484,484,400,400} //Y coordinates for all blocks
                };
                //GreenfootImages for all blocks
                GreenfootImage terrainImgs3[] = new GreenfootImage[]{shGround,shGround,shGround,shGround,shGround,shGround,shGround,shGround,shGround,shGround,shGround,shGround,
                                                                     shGround,shGround,shGround,shGround,shGround,shGround,shGround,shGround,shGround,shStones,shStones,sideTile,sideTile}; 
                                                                     
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
            case 4: //stronghold biome
                int terrainSizeXSizeYCoordsXCoordsY4[][]=
                {
                    {81,81,81,81,81,96,96,40,96,96,48,96,96,96,96,96,96,96,96,96,96,96,48,48,48,48}, //Width for all blocks
                    {48,81,81,81,81,48,48,96,48,48,96,48,96,96,48,96,96,48,48,48,48,48,48,96,800,800}, //Height for all blocks
                    {280,489,530,448,529,904,808,880,400,96,568,496,90,150,192,96,96,290,386,482,568,664,280,568,24,976}, //X coordinates for all blocks
                    {776,704,704,780,780,600,600,450,510,414,200,272,486,486,150,126,30,24,24,24,24,24,270,104,400,400} //Y coordinates for all blocks
                };
                //GreenfootImages for all blocks
                GreenfootImage terrainImgs4[] = new GreenfootImage[]{shGround,shGround,shGround,shStones,shStones,shGround,shGround,shGround,shGround,shGround,shGround,shGround,shStones,shStones,shGround,
                                                                    shGround,shGround,shGround,shGround,shGround,shGround,shGround,shGround,shGround,sideTile,sideTile}; 
                                                                     
                for(int i = 0; i < 26; i++)
                {
                    Block block = new Block(terrainSizeXSizeYCoordsXCoordsY4[0][i],terrainSizeXSizeYCoordsXCoordsY4[1][i],terrainImgs4[i]);
                    addObject(block, terrainSizeXSizeYCoordsXCoordsY4[2][i], terrainSizeXSizeYCoordsXCoordsY4[3][i]);
                }
                actor.windyLvl(false);
                
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
                slopeRightLeft slopeRL7 = new slopeRightLeft(this, 96, 96, 830, 450, shSlopeRL);
                addObject(slopeRL7, 830, 450);
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
            case 5://grass biome
                int terrainSizeXSizeYCoordsXCoordsY5[][]=
                {
                    {96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96}, //Width of all blocks
                    {96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,48,96,800,800}, //Height of all blocks
                    {96,96,192,96,288,384,480,576,672,96,192,288,808,808,712,616,480,0,1000}, //X coordinates for all blocks
                    {278,368,374,800,800,800,800,800,800,464,464,464,368,464,464,488,176,400,400} //Y coordinates for all blocks
                };
                //GreenfootImages for all blocks
                GreenfootImage terrainImgs5[] = new GreenfootImage[]{grass,dirt,grass,grass,grass,grass,grass,grass,grass,dirt,dirt,dirt,grass,dirt,grass,grass,grass,sideTile,sideTile};
                                                                     
                for(int i = 0; i < 19; i++)
                {
                    Block block = new Block(terrainSizeXSizeYCoordsXCoordsY5[0][i],terrainSizeXSizeYCoordsXCoordsY5[1][i],terrainImgs5[i]);
                    addObject(block, terrainSizeXSizeYCoordsXCoordsY5[2][i], terrainSizeXSizeYCoordsXCoordsY5[3][i]);
                }
                musicTimer.mark();
                //Sets background
                grassBg.scale(getWidth(), getHeight());
                setBackground(grassBg);
                //progress bar image
                progressBar bar5 = new progressBar(30, 198, progressBarImg[4]);
                addObject(bar5, 980, 700);
                //Sets windy level
                actor.windyLvl(false);
                //Creates all slopes
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
                slopeRightLeft sRL2 = new slopeRightLeft(this, 96, 96, 760, 368, grassRL);
                addObject(sRL2, 760, 368);
                slopeRightLeft sRL1 = new slopeRightLeft(this, 96, 96, 712, 416, grassRL);
                addObject(sRL1, 712, 416);
                break;
                
            case 6://grass biome
                int terrainSizeXSizeYCoordsXCoordsY6[][]=
                {
                    {96,96,96,96,96,96,96,96,96,96,96}, //Width of all blocks
                    {96,96,96,96,96,96,96,96,96,800,800}, //Height of all blocks
                    {96,192,432,624,816,768,596,500,404,0,1000}, //X coordinates for all blocks
                    {704,704,608,608,608,344,200,200,200,400,400} //Y coordinates for all blocks
                };
                //GreenfootImages for all blocks
                GreenfootImage terrainImgs6[] = new GreenfootImage[]{grass,grass,grass,grass,grass,grass,bridge,bridge,bridge,sideTile,sideTile};
                                                                     
                for(int i = 0; i < 11; i++)
                {
                    Block block = new Block(terrainSizeXSizeYCoordsXCoordsY6[0][i],terrainSizeXSizeYCoordsXCoordsY6[1][i],terrainImgs6[i]);
                    addObject(block, terrainSizeXSizeYCoordsXCoordsY6[2][i], terrainSizeXSizeYCoordsXCoordsY6[3][i]);
                }
                //Sets background
                cloudsBg.scale(getWidth(), getHeight());
                setBackground(cloudsBg);
                //progress bar image
                progressBar bar6 = new progressBar(30, 198, progressBarImg[5]);
                addObject(bar6, 980, 700);
                //Sets windy level
                actor.windyLvl(true, "right");
                windImage1 wind1 = new windImage1(this, "right");
                addObject(wind1, getWidth() / 2, getHeight() / 2);
                //Creates all slopes
                slopeRightLeft sRL4 = new slopeRightLeft(this, 96, 96, 952, 752, grassRL);
                addObject(sRL4, 952, 752);
                slopeRightLeft sRL5 = new slopeRightLeft(this, 96, 96, 904, 800, grassRL);
                addObject(sRL5, 904, 800);
                break;
            case 7://snow biome
                int terrainSizeXSizeYCoordsXCoordsY7[][]=
                {
                    {96,96,96,96,96,96,96,96,96}, //Width of all iceblocks
                    {96,96,96,96,96,96,96,800,800}, //Height of all iceblocks
                    {96,192,432,624,904,576,764,0,1000}, //X coordinates for all iceblocks
                    {752,752,608,512,512,176,272,400,400} //Y coordinates for all iceblocks
                };
                //GreenfootImages for all blocks
                GreenfootImage terrainImgs7[] = new GreenfootImage[]{snow,snow,snow,snow,snow,snow,snow,sideTile,sideTile};
                                                                     
                for(int i = 0; i < 9; i++)
                {
                    iceBlock block = new iceBlock(terrainSizeXSizeYCoordsXCoordsY7[0][i],terrainSizeXSizeYCoordsXCoordsY7[1][i],terrainImgs7[i]);
                    addObject(block, terrainSizeXSizeYCoordsXCoordsY7[2][i], terrainSizeXSizeYCoordsXCoordsY7[3][i]);
                }
                //Sets background
                snowBg.scale(getWidth(), getHeight());
                setBackground(snowBg);
                //progress bar image
                progressBar bar7 = new progressBar(30, 198, progressBarImg[6]);
                addObject(bar7, 980, 700);
                //Sets windy level
                actor.windyLvl(false);
                //Create all slopes
                slopeRightLeft sRL11 = new slopeRightLeft(this, 96, 96, 528, 176, snowSlopeRL);
                addObject(sRL11, 528, 176); 
                slopeRightLeft sRL10 = new slopeRightLeft(this, 96, 96, 480, 224, snowSlopeRL);
                addObject(sRL10, 480, 224); 
                slopeRightLeft sRL9 = new slopeRightLeft(this, 96, 96, 432, 272, snowSlopeRL);
                addObject(sRL9, 432, 272);
                break;
            case 8://snow biome
                int terrainSizeXSizeYCoordsXCoordsY8[][]=
                {
                    {72,72,72,72,72,72,72,72,72,72,72,72,48,48,96,96}, //Width of all blocks
                    {96,96,96,96,96,96,96,96,96,96,96,96,48,48,800,800}, //Height of all blocks
                    {288,288,288,288,288,288,712,712,712,712,712,712,452,560,0,1000}, //X coordinates of all blocks
                    {0,288,384,480,576,672,0,96,192,288,576,672,372,168,400,400} //Y coordinates of all blocks
                };
                //GreenfootImages for all blocks
                GreenfootImage terrainImgs8[] = new GreenfootImage[]{snowStonesL,snowStonesL,snowStonesL,snowStonesL,snowStonesL,snowStonesL,snowStonesR,snowStonesR,snowStonesR,snowStonesR,
                                                                    snowStonesR,snowStonesR,snowStones,snowStones,sideTile,sideTile};
                                                                     
                for(int i = 0; i < 16; i++)
                {
                    Block block = new Block(terrainSizeXSizeYCoordsXCoordsY8[0][i],terrainSizeXSizeYCoordsXCoordsY8[1][i],terrainImgs8[i]);
                    addObject(block, terrainSizeXSizeYCoordsXCoordsY8[2][i], terrainSizeXSizeYCoordsXCoordsY8[3][i]);
                }
                //Sets background
                snowBg.scale(getWidth(), getHeight());
                setBackground(snowBg);
                //progress bar image
                progressBar bar8 = new progressBar(30, 198, progressBarImg[7]);
                addObject(bar8, 980, 700);
                //Creates all iceblocks
                iceBlock ib9 = new iceBlock(96, 48, snow);
                addObject(ib9, 372,720);
                iceBlock ib8 = new iceBlock(96, 48, snow);
                addObject(ib8, 724,528);
                iceBlock ib13 = new iceBlock(96, 48, snow);
                addObject(ib13, 276, 240);
                break;
            case 9://snow biome
                int terrainSizeXSizeYCoordsXCoordsY9[][]=
                {
                    {72,72,72,72,72,72,72,72,48,48,96,96,96,96,96,96,96,96}, //Width of all blocks
                    {96,96,96,96,96,96,96,96,48,48,96,96,96,96,96,96,800,800}, //Height of all blocks
                    {288,288,712,712,712,712,712,712,516,228,480,528,624,720,816,910,0,1000}, //X coordinates for all blocks
                    {768,866,384,480,576,672,768,864,554,482,288,288,288,288,288,288,400,400} //Y coordinates for all blocks
                };
                //GreenfootImages for all blocks
                GreenfootImage terrainImgs9[] = new GreenfootImage[]{snowStonesL,snowStonesL,snowStonesR,snowStonesR,snowStonesR,snowStonesR,snowStonesR,snowStonesR,snowStones,snowStones,
                                                                     snowStones,snowStones,snowStones,snowStones,snowStones,snowStones,sideTile,sideTile};
                                                                     
                for(int i = 0; i < 18; i++)
                {
                    Block block = new Block(terrainSizeXSizeYCoordsXCoordsY9[0][i],terrainSizeXSizeYCoordsXCoordsY9[1][i],terrainImgs9[i]);
                    addObject(block, terrainSizeXSizeYCoordsXCoordsY9[2][i], terrainSizeXSizeYCoordsXCoordsY9[3][i]);
                }
                //Sets background
                snowBg.scale(getWidth(), getHeight());
                setBackground(snowBg);
                //progress bar image
                progressBar bar9 = new progressBar(30, 198, progressBarImg[8]);
                addObject(bar9, 980, 700);
                //Creates BBQ
                SmokingHotBBQ bbq = new SmokingHotBBQ(50, 100);
                addObject(bbq, 808, 192);
                //Creates iceBlock
                iceBlock ib10 = new iceBlock(96, 48, snow);
                addObject(ib10, 276, 696);
                break;
            case 10:
                //Sets background
                snowBg.scale(getWidth(), getHeight());
                setBackground(snowBg);
                break;
            case 11://Cheat world
                int terrainSizeXSizeYCoordsXCoordsY11[][]=
                {
                    {96}, //Widths of all blocks
                    {96}, //Heights of all blocks
                    {952,856,760,664,568,472,376,280,184,88,-8}, //X coordinates of all blocks
                    {752,752,752,752,752,752,752,752,752,752,752} //Y coordinates of all blocks
                };
                //GreenfootImages for all blocks
                GreenfootImage terrainImgs11[] = new GreenfootImage[]{dnGround};
                                                                     
                for(int i = 0; i < 11; i++)
                {
                    Block block = new Block(terrainSizeXSizeYCoordsXCoordsY11[0][0],terrainSizeXSizeYCoordsXCoordsY11[1][0],terrainImgs11[0]);
                    addObject(block, terrainSizeXSizeYCoordsXCoordsY11[2][i], terrainSizeXSizeYCoordsXCoordsY11[3][i]);
                }
                //Sets background
                dungeonBg1.scale(getWidth(), getHeight());
                setBackground(dungeonBg1);
                //Creates all buttons
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
                //Creates BBQ
                SmokingHotBBQ bbq2 = new SmokingHotBBQ(96, 96);
                addObject(bbq2, 100, 656);
                //Creates bounds
                Block block123 = new Block(48, 600, sideTile);
                addObject(block123, 976, 300);
                Block block124 = new Block(48, 800, sideTile);
                addObject(block124, 24, 400);
                break;
        }
    }
    /**
     * Teleports actor to hidden world
     * 
     * No parameters or returns
     */
    public void hiddenWorld()
    {
        if(actor.getX() < 0 && actor.getY() > 600)
        {
            level = 11;
            clearWorld();
            actor.setLocation(1000, actor.getY());
        }
        if(actor.getX() > 1000 && actor.getY() > 600 && level == 11)
        {
            level = 1;
            clearWorld();
            actor.setLocation(0, actor.getY());
        }
    }
    /**
     * Teleports actor to differnt world depending on button pressed and sets location for each level
     * 
     * No parameters or returns
     */
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
                actor.setLocation(276, 650);
                break;
        }
    }
    /**
     * Removes gameAsset.class objects in the world
     * 
     * No parameters or returns
     */
    public void clearWorld()
    {
        removeObjects(getObjects(gameAssets.class));
        prepare();
    }
    /**
     * Clears current screen and sets to next level 
     * 
     * No parameters or returns
     */
    public void nextLevel()
    {
        removeObjects(getObjects(gameAssets.class));
        
        level++;
        prepare();
    }
    /**
     * Clears current screen and set to previous level
     * 
     * No parameters or returns
     */
    public void previousLevel()
    {
        removeObjects(getObjects(gameAssets.class));

        level--;
        prepare();
    }
}
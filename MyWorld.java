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
    //Background music
    GreenfootSound bgmFallenKing = new GreenfootSound("bgmFallenKing.mp3");
    GreenfootSound bgmSkyBlue = new GreenfootSound("bgmSkyBlue.mp3");
    //Progress bar images
    GreenfootImage progressBarImg[] = new GreenfootImage[8];
    
    SimpleTimer musicTimer = new SimpleTimer();
    SimpleTimer gameTimer = new SimpleTimer();
    
    SquatKing actor;
    SmokingHotBBQ BBQ;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1, true); 
        //addObject(new fade(null), getWidth()/2, getHeight()/2);
        for(int i = 0; i < progressBarImg.length; i++)
        {
            progressBarImg[i] = new GreenfootImage("images/Progress Bar/lvl" + i + ".png");
        }
        
        setPaintOrder(fade.class, progressBar.class, SquatKing.class, Slopes.class,Terrain.class);
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
        if(musicTimer.millisElapsed() >= 10000 && (level >=1 && level <= 4))
        {
            bgmFallenKing.playLoop();
        }
        if(musicTimer.millisElapsed() >= 10000 && (level >=5 && level <= 8))
        {
            bgmSkyBlue.playLoop();
        }
    }
    
    private void prepareActor()
    {
        SquatKing squattyboi = new SquatKing(48,48);
        addObject(squattyboi,100, 500); 
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
                musicTimer.mark();
                actor.windyLvl(false);
                
                //sets background
                dungeonBg1.scale(getWidth(), getHeight());
                setBackground(dungeonBg1);
                //progress bar image
                progressBar bar1 = new progressBar(30, 198, progressBarImg[0]);
                addObject(bar1, 980, 700);
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
                actor.windyLvl(false);
                dungeonBg2.scale(getWidth(), getHeight());
                setBackground(dungeonBg2);
                //progress bar image
                progressBar bar2 = new progressBar(30, 198, progressBarImg[1]);
                addObject(bar2, 980, 700);
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
                actor.windyLvl(false);
                strongholdBg1.scale(getWidth(), getHeight());
                setBackground(strongholdBg1);
                
                //progress bar image
                progressBar bar3 = new progressBar(30, 198, progressBarImg[2]);
                addObject(bar3, 980, 700);
                
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
                Block block72 = new Block(48, 96, shGround);
                addObject(block72, 540, 100);
                Block block73 = new Block(48, 96, shGround);
                addObject(block73, 540, 4);
                Block block74 = new Block(96, 48, shGround);
                addObject(block74, 302, 412);
                Block block75 = new Block(48, 48, shGround);
                addObject(block75, 72, 412);
                Block block76 = new Block(48, 48, shGround);
                addObject(block76, 216, 250);
                Block block77 = new Block(48, 96, shGround);
                addObject(block77, 264, 226);
                Block block78 = new Block(48, 96, shGround);
                addObject(block78, 264, 130);
                Block block79 = new Block(48, 96, shGround);
                addObject(block79, 264, 34);
                Block block80 = new Block(96, 48, shGround);
                addObject(block80, 96, 75);
                Block block81 = new Block(114, 48, shGround);
                addObject(block81, 345, 24);
                Block block82 = new Block(114, 48, shGround);
                addObject(block82, 459, 24);
                //creates bounds
                Block block60 = new Block(48, 800, shGround);
                addObject(block60, 976, 400);
                Block block61 = new Block(48, 800, shGround);
                addObject(block61, 24, 400);
                break;
            case 4: //stronghold
                actor.windyLvl(false);
                //bgmSkyBlue.stop();
                
                //progress bar image
                progressBar bar4 = new progressBar(30, 198, progressBarImg[3]);
                addObject(bar4, 980, 700);
                
                strongholdBg2.scale(getWidth(), getHeight());
                setBackground(strongholdBg2);
                //Blocks top and bottom transition
                Block block85 = new Block(81, 48, shGround);
                addObject(block85, 280, 776);
                slopeRightLeft slopeRL8 = new slopeRightLeft(this, 81, 81, 361, 751, shSlopeRL);
                addObject(slopeRL8, 361, 751);
                slopeRightLeft slopeRL9 = new slopeRightLeft(this, 81, 81, 408, 704, shSlopeRL);
                addObject(slopeRL9, 408, 704);
                Block block86 = new Block(81, 81, shGround);
                addObject(block86, 489, 704);
                Block block87 = new Block(81, 81, shGround);
                addObject(block87, 530, 704);
                Block block88 = new Block(81, 81, shStones);
                addObject(block88, 448, 780);
                Block block93 = new Block(81, 81, shStones);
                addObject(block93, 529, 780);
                
                Block block89 = new Block(72, 48, shGround);
                addObject(block89, 916, 600);
                Block block90 = new Block(72, 48, shGround);
                addObject(block90, 844, 600);
                slopeRightLeft slopeRL7 = new slopeRightLeft(this, 96, 96, 840, 450, shSlopeRL);
                addObject(slopeRL7, 840, 450);
                Block block91 = new Block(40, 96, shGround);
                addObject(block91, 890, 450);
                
                Block block92 = new Block(96, 48, shGround);
                addObject(block92, 400, 510);
                
                slopeLeftRight slopeLR6 = new slopeLeftRight(this, 96, 96, 220, 486, shSlopeLR);
                addObject(slopeLR6, 220, 486);
                slopeLeftRight slopeLR7 = new slopeLeftRight(this, 96, 96, 172, 438, shSlopeLR);
                addObject(slopeLR7, 172, 438);
                Block block94 = new Block(96, 48, shGround);
                addObject(block94, 96, 414);
                Block block95 = new Block(48, 96, shGround);
                addObject(block95, 550, 210);
                
                slopeRightLeft slopeRL10 = new slopeRightLeft(this, 96, 96, 400, 296, shSlopeRL);
                addObject(slopeRL10, 400, 296);
                Block block96 = new Block(96, 48, shGround);
                addObject(block96, 496, 272);
                Block block97 = new Block(96, 96, shStones);
                addObject(block97, 90, 486);
                Block block98 = new Block(96, 96, shStones);
                addObject(block98, 150, 486);
                Block block99 = new Block(96, 48, shGround);
                addObject(block99, 192, 150);
                Block block100 = new Block(96, 96, shGround);
                addObject(block100, 96, 126);
                Block block101 = new Block(96, 96, shGround);
                addObject(block101, 96, 30);
                Block block102 = new Block(96, 48, shGround);
                addObject(block102, 290, 24);
                Block block103 = new Block(96, 48, shGround);
                addObject(block103, 386, 24);
                Block block104 = new Block(96, 48, shGround);
                addObject(block104, 482, 24);
                Block block105 = new Block(96, 48, shGround);
                addObject(block105, 578, 24);
                Block block106 = new Block(96, 48, shGround);
                addObject(block106, 674, 24);
                Block block107 = new Block(96, 48, shGround);
                addObject(block107, 770, 24);
                slopeRightLeft slopeRL11 = new slopeRightLeft(this, 96, 96, 952, 100, shSlopeRL);
                addObject(slopeRL11, 952, 100);
                slopeRightLeft slopeRL12 = new slopeRightLeft(this, 96, 96, 904, 148, shSlopeRL);
                addObject(slopeRL12, 904, 148);
                Block block108 = new Block(48, 48, shGround);
                addObject(block108, 280, 270);
                //creates bounds
                Block block83 = new Block(48, 800, shGround);
                addObject(block83, 976, 400);
                Block block84 = new Block(48, 800, shGround);
                addObject(block84, 24, 400);
                break;
            case 5://grass
                actor.windyLvl(false);
                musicTimer.mark();
                bgmFallenKing.stop();
                
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
                
                //progress bar image
                progressBar bar6 = new progressBar(30, 198, progressBarImg[5]);
                addObject(bar6, 980, 700);
                
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
                addObject(ib1, 96,704);
                iceBlock ib2 = new iceBlock(96, 96, snow);
                addObject(ib2, 192,704);
                
                iceBlock ib3 = new iceBlock(96, 96, snow);
                addObject(ib3, 432,608);
                iceBlock ib4 = new iceBlock(96, 96, snow);
                addObject(ib4, 624,512);
                iceBlock ib5 = new iceBlock(96, 96, snow);
                addObject(ib5, 904,512);

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
                
                break;
            case 8://snow
                snowBg.scale(getWidth(), getHeight());
                setBackground(snowBg);
                
                //progress bar image
                progressBar bar8 = new progressBar(30, 198, progressBarImg[7]);
                addObject(bar8, 980, 700);
                
                slopeRightLeft sRL12 = new slopeRightLeft(this, 96, 96, 432, 416, snowSlopeRL);
                addObject(sRL12, 432, 416);
                
                iceBlock ib8 = new iceBlock(96, 96, snow);
                addObject(ib8, 96,600);
                iceBlock ib9 = new iceBlock(96, 96, snow);
                addObject(ib9, 0,500);
                iceBlock ib10 = new iceBlock(96, 96, snow);
                addObject(ib10, 0,500);
                
                Block b40 = new Block(96, 96, snowStonesL);
                addObject(b40, 192,0);
                Block b32 = new Block(96, 96, snowStonesL);
                addObject(b32, 192,96);
                Block b33 = new Block(96, 96, snowStonesL);
                addObject(b33, 192,192);
                Block b34 = new Block(96, 96, snowStonesL);
                addObject(b34, 192,288);
                Block b35 = new Block(96, 96, snowStonesL);
                addObject(b35, 192,384);
                Block b36 = new Block(96, 96, snowStonesL);
                addObject(b36, 192,480);
                Block b37 = new Block(96, 96, snowStonesL);
                addObject(b37, 192,576);
                
                
                Block b48 = new Block(96, 96, snowStonesR);
                addObject(b48, 808,0);
                Block b41 = new Block(96, 96, snowStonesR);
                addObject(b41, 808,96);
                Block b42 = new Block(96, 96, snowStonesR);
                addObject(b42, 808,192);
                Block b43 = new Block(96, 96, snowStonesR);
                addObject(b43, 808,288);
                Block b44 = new Block(96, 96, snowStonesR);
                addObject(b44, 808,384);
                Block b45 = new Block(96, 96, snowStonesR);
                addObject(b45, 808,480);
                Block b46 = new Block(96, 96, snowStonesR);
                addObject(b46, 808,576);
                
                break;
            case 9:
                
                //progress bar image
                progressBar bar9 = new progressBar(30, 198, progressBarImg[9]);
                addObject(bar9, 980, 700);
                
                Block b49 = new Block(96, 96, snowStonesL);
                addObject(b49, 192,0);
                Block b50 = new Block(96, 96, snowStonesL);
                addObject(b50, 192,96);
                Block b51 = new Block(96, 96, snowStonesL);
                addObject(b51, 192,192);
                Block b52 = new Block(96, 96, snowStonesL);
                addObject(b52, 192,288);
                Block b53 = new Block(96, 96, snowStonesL);
                addObject(b53, 192,384);
                Block b54 = new Block(96, 96, snowStonesL);
                addObject(b54, 192,480);
                Block b55 = new Block(96, 96, snowStonesL);
                addObject(b55, 192,576);

                                
                Block b56 = new Block(96, 96, snowStonesR);
                addObject(b56, 808,0);
                Block b57 = new Block(96, 96, snowStonesR);
                addObject(b57, 808,96);
                Block b58 = new Block(96, 96, snowStonesR);
                addObject(b58, 808,192);
                Block b59 = new Block(96, 96, snowStonesR);
                addObject(b59, 808,288);
                Block b60 = new Block(96, 96, snowStonesR);
                addObject(b60, 808,384);
                Block b61 = new Block(96, 96, snowStonesR);
                addObject(b61, 808,576);
                Block b62 = new Block(96, 96, snowStonesR);
                addObject(b62, 808,672);
                Block b63 = new Block(96, 96, snowStonesR);
                addObject(b63, 808,768);
                Block b64 = new Block(96, 96, snowStonesR);
                addObject(b64, 808,864);
                
        }
        }

    public void nextLevel()
    {
        removeObjects(getObjects(Terrain.class)); //Removes all objects
        removeObjects(getObjects(Slopes.class));
        removeObjects(getObjects(SmokingHotBBQ.class));
        removeObjects(getObjects(progressBar.class));
        
        level++;
        prepare();
    }
    public void previousLevel()
    {
        removeObjects(getObjects(Terrain.class)); //Removes all objects 
        removeObjects(getObjects(Slopes.class));
        removeObjects(getObjects(SmokingHotBBQ.class));
        removeObjects(getObjects(progressBar.class));
        
        level--;
        prepare();
    }

    //Getter method for global speed variable
    public int getSpeed()
    {
        return speed;
    }

}

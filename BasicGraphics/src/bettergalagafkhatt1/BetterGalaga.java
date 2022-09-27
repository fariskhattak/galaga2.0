/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bettergalagafkhatt1;

import basicgraphics.*;
import basicgraphics.images.Picture;
import basicgraphics.sounds.ReusableClip;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Js4fs
 */
public class BetterGalaga {

    static int levelNumber = 1; // # of levels in the game
    static int totalPoints = 0; // counter for total points in game
    static int lives; // counter for # of player lives
    static JLabel levelCount; // JLabel to keep track of and display the current level
    static JLabel livesCount;
    static JLabel pointCount;
    static boolean activeGracePeriod = false; // grace period of player after taking a hit
    static boolean invincibilityItem = false; // keeps track of invincibility item
    static boolean spreadshotItem = false; // keeps track of spreadshot item
    static int spreadshotcount = 0; // counter for # of total spreadshots available
    static List<Sprite> allSprites; // a list in where i store all the sprites so that i can clear the screen when i
                                    // need to
    static Container content;

    public static void main(String[] args) {
        // if you want to change font and font size do
        // bf.setAllFonts(new Font(Font.MONOSPACED, Font.BOLD, BOX * 9 / 10))
        BasicFrame bf = new BasicFrame("Galaga 2.0");
        content = bf.getContentPane();
        CardLayout cards = new CardLayout();
        content.setLayout(cards);
        BasicContainer bc1 = new BasicContainer();
        content.add(bc1, "Splash");
        BasicContainer bc2 = new BasicContainer();
        content.add(bc2, "Game");

        SpriteComponent sc = new SpriteComponent() { // sprite component for the main game
            @Override
            public void paintBackground(Graphics g) {
                if (levelNumber <= 3) { // sets the background for Level 1
                    Dimension d = getSize();
                    g.setColor(Color.black);
                    g.fillRect(0, 0, d.width, d.height);
                    final int NUM_STARS = 100;
                    Random rand = new Random();
                    rand.setSeed(0);
                    g.setColor(Color.white);
                    for (int i = 0; i < NUM_STARS; i++) {
                        int diameter = rand.nextInt(5) + 1;
                        int xpos = rand.nextInt(d.width);
                        int ypos = rand.nextInt(d.height);
                        g.fillOval(xpos, ypos, diameter, diameter);
                    }
                }
                if (levelNumber >= 4 && levelNumber < 9) { // sets the background for Level 4 to Level 8
                    Dimension d = getSize();
                    g.setColor(Color.black);
                    g.fillRect(0, 0, d.width, d.height);
                    final int NUM_AST = 50;
                    final int NUM_STARS = 100;
                    Random rand = new Random();
                    rand.setSeed(0);
                    g.setColor(Color.GRAY);
                    for (int i = 0; i < NUM_AST; i++) {
                        int diameter = rand.nextInt(20) + 1;
                        int xpos = rand.nextInt(d.width);
                        int ypos = rand.nextInt(d.height);
                        g.fillOval(xpos, ypos, diameter, diameter);
                    }
                    g.setColor(Color.white);
                    for (int i = 0; i < NUM_STARS; i++) {
                        int diameter = rand.nextInt(5) + 1;
                        int xpos = rand.nextInt(d.width);
                        int ypos = rand.nextInt(d.height);
                        g.fillOval(xpos, ypos, diameter, diameter);
                    }
                }
                if (levelNumber >= 9) { // sets the background for Level 10
                    Dimension d = getSize();
                    g.setColor(Color.black);
                    g.fillRect(0, 0, d.width, d.height);
                    final int NUM_AST = 50;
                    final int NUM_STARS = 100;
                    final int NUM_RED_STARS = 20;
                    final int NUM_BLUE_STARS = 20;
                    Random rand = new Random();
                    rand.setSeed(0);
                    g.setColor(Color.GRAY);
                    for (int i = 0; i < NUM_AST; i++) {
                        int diameter = rand.nextInt(25) + 1;
                        int xpos = rand.nextInt(d.width);
                        int ypos = rand.nextInt(d.height);
                        g.fillOval(xpos, ypos, diameter, diameter);
                    }
                    g.setColor(Color.white);
                    for (int i = 0; i < NUM_STARS; i++) {
                        int diameter = rand.nextInt(5) + 1;
                        int xpos = rand.nextInt(d.width);
                        int ypos = rand.nextInt(d.height);
                        g.fillOval(xpos, ypos, diameter, diameter);
                    }
                    g.setColor(Color.red);
                    for (int i = 0; i < NUM_RED_STARS; i++) {
                        int diameter = rand.nextInt(10) + 1;
                        int xpos = rand.nextInt(d.width);
                        int ypos = rand.nextInt(d.height);
                        g.fillOval(xpos, ypos, diameter, diameter);
                    }
                    g.setColor(Color.blue);
                    for (int i = 0; i < NUM_BLUE_STARS; i++) {
                        int diameter = rand.nextInt(15) + 1;
                        int xpos = rand.nextInt(d.width);
                        int ypos = rand.nextInt(d.height);
                        g.fillOval(xpos, ypos, diameter, diameter);
                    }
                }
                if (invincibilityItem) { // adds a star to the bottom right corner if InvcStar has been gained
                    int xpos = getSize().width - 50;
                    int ypos = getSize().height - 40;
                    Picture star = new Picture("star.png");
                    Image i = star.getImage();
                    g.drawImage(i, xpos, ypos, star);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
                    g.drawString("Q", xpos + 10, ypos - 10); // draws the letter Q for the keyCode needed to use the
                                                             // item
                }
                if (spreadshotItem) { // adds the SpreadShot item icon to the bottom right corner if SpreadShot has
                                      // been gained
                    int groupSSCount = spreadshotcount / 5;
                    Picture ssitem = new Picture("spreadshot.png");
                    Image im = ssitem.getImage();
                    if (spreadshotcount > 0) { // draws the letter R for the keyCode needed to use the item
                        g.setColor(Color.WHITE);
                        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
                        int x = getSize().width - 90;
                        int y = getSize().height - 50;
                        g.drawString("R", x, y);
                    }
                    if (spreadshotcount % 5 == 0) {
                        for (int i = 0; i < groupSSCount; i++) {
                            int xpos = getSize().width - 100 - 50 * i;
                            int ypos = getSize().height - 45;
                            g.drawImage(im, xpos, ypos, ssitem);
                        }
                    } else {
                        for (int i = 0; i < groupSSCount + 1; i++) {
                            int xpos = getSize().width - 100 - 50 * i;
                            int ypos = getSize().height - 45;
                            g.drawImage(im, xpos, ypos, ssitem);
                        }
                    }
                }
            }
        };
        Dimension d = new Dimension(1300, 700);
        sc.setPreferredSize(d);
        String[][] splashLayout = { // creates the layout for the beginning card
                { "Title", "Title", "Title" },
                { "Desc1", "Desc1", "Desc1" },
                { "Desc2", "Desc2", "Desc2" },
                { "TutorialButton", "StartButton", "QuitButton" },
                { "Name", "Name", "Name" }
        };
        bc1.setStringLayout(splashLayout);
        JLabel title = new JLabel("Galaga 2.0");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        bc1.add("Title", title);

        bc1.add("Name", new JLabel("by Faris Khattak"));
        JLabel desc1 = new JLabel("Your objective is to shoot the aliens and prevent them from reaching Earth");
        desc1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        bc1.add("Desc1", desc1);

        bc1.add("Desc2", new JLabel(
                "WASD or ARROW KEYS to move     SPACEBAR to shoot      Q to use invincibility star      R to use spreadshot"));
        JButton tutorial = new JButton("Tutorial");
        tutorial.addActionListener(new ActionListener() { // button to start the tutorial
            @Override
            public void actionPerformed(ActionEvent e) {
                levelNumber = 1;
                cards.show(content, "Game");
                bc2.requestFocus();
                Clock.start(10);
                JOptionPane.showMessageDialog(sc, "Tutorial Mode Start");
                levelOne(sc); // starts the first tutorial level
            }
        });
        JButton jstart = new JButton("Start");
        jstart.addActionListener(new ActionListener() { // button to start the main game
            @Override
            public void actionPerformed(ActionEvent e) {
                levelNumber = 4; // set to 4 to start the first actual level
                cards.show(content, "Game");
                bc2.requestFocus();
                Clock.start(10);
                levelFour(sc);
            }
        });
        JButton jquit = new JButton("Quit");
        jquit.addActionListener(new ActionListener() { // button to start the main game
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        bc1.add("StartButton", jstart);
        bc1.add("TutorialButton", tutorial);
        bc1.add("QuitButton", jquit);
        String[][] layout = {
                { "LevelCount" },
                { "Galaga 2.0" },
                { "LivesCount" },
                { "PointsCount" }
        };
        bc2.setStringLayout(layout);
        bc2.add("Galaga 2.0", sc);
        levelCount = new JLabel("Galaga 2.0");
        levelCount.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        livesCount = new JLabel("Lives: " + lives);
        livesCount.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        pointCount = new JLabel("Points: " + totalPoints);
        pointCount.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        // adds the JLabels to the screen
        bc2.add("LevelCount", levelCount);
        bc2.add("LivesCount", livesCount);
        bc2.add("PointsCount", pointCount);

        bf.show();

        // each of the sounds used in the game
        final ReusableClip LASER_SOUND = new ReusableClip("beep.wav");
        final ReusableClip DEATH_SOUND = new ReusableClip("die.wav");
        final ReusableClip ENEMY_DEATH_SOUND = new ReusableClip("enemydeathsound.wav");
        final ReusableClip LIFE_GAIN = new ReusableClip("lifegain.wav");
        GalagaShip gs = new GalagaShip(sc); // player spaceship
        lives = gs.lives; // sets the lives of the game to the max lives of the player
        livesCount.setText("Lives: " + lives); // updates the livesCount JLabel to display the new lives
        allSprites = new ArrayList<>();

        final double INCR = 4;
        // Note: Adding the listener to basic container 2.
        bc2.addKeyListener(new KeyAdapter() { // movement keys for the character
            @Override
            public void keyPressed(KeyEvent ke) {
                // press right arrow key or D to move right
                if (ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_D) {
                    gs.moveX(INCR);
                }
                // press left arrow key or A to move right
                if (ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_A) {
                    gs.moveX(-INCR);
                }
                // press up arrow key or W to move right
                if (ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_W) {
                    gs.moveY(-INCR);
                }
                // press down arrow key or S to move right
                if (ke.getKeyCode() == KeyEvent.VK_DOWN || ke.getKeyCode() == KeyEvent.VK_S) {
                    gs.moveY(INCR);
                }
                // press spacebar to shoot
                if (ke.getKeyChar() == ' ') {
                    if (!activeGracePeriod) {
                        Laser laser = new Laser(sc);
                        laser.setVelY(-5);
                        laser.setCenterX(gs.centerX());
                        laser.setCenterY(gs.centerY());
                        LASER_SOUND.play();
                    }
                }
                // press Q to use the invincibility star
                if (ke.getKeyCode() == KeyEvent.VK_Q && invincibilityItem) {
                    invincibilityItem = false;
                    gs.setPicture(gs.invinciblePic[0]);
                    Task colorSwitch = new Task() {
                        @Override
                        public void run() {
                            if (iteration() >= 700) {
                                gs.setPicture(gs.initialPic);
                                setFinished();
                            }
                            if (!gs.isPicture(gs.initialPic)) {
                                gs.setPicture(gs.invinciblePic[iteration() % gs.invinciblePic.length]);
                            } else {
                                setFinished();
                            }
                        }
                    };
                    Clock.addTask(colorSwitch);
                }
                // press R to use the SpreadShot item
                if (ke.getKeyCode() == KeyEvent.VK_R && spreadshotItem) {
                    spreadshotcount--;
                    Laser[] lasers = new Laser[5]; // spawns 5 lasers
                    int count1 = 0;
                    int count2 = 1;
                    for (int i = 0; i < lasers.length; i++) {
                        lasers[i] = new Laser(sc);
                        if (i % 2 == 0) {
                            lasers[i].setX(gs.centerX() + 15 * count1);
                            if (count1 > 0) {
                                lasers[i].setVelX(count1 / 2.5);
                            }
                            count1++;
                        } else {
                            lasers[i].setX(gs.centerX() - 15 * count2);
                            count2++;
                            lasers[i].setVelX(-count2 / 2.5);
                        }
                        lasers[i].setY(gs.centerY() - 5);
                        lasers[i].setVelY(-5);
                    }
                    LASER_SOUND.play();
                    if (spreadshotcount < 1) {
                        spreadshotcount = 0;
                        spreadshotItem = false;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent k) { // improves movement
                if (k.getKeyChar() == ' ' || k.getKeyCode() == KeyEvent.VK_R || k.getKeyCode() == KeyEvent.VK_Q) {
                } else if (k.getKeyCode() == KeyEvent.VK_DOWN || k.getKeyCode() == KeyEvent.VK_UP
                        || k.getKeyCode() == KeyEvent.VK_W || k.getKeyCode() == KeyEvent.VK_S) {
                    gs.moveY(0);
                } else if (k.getKeyCode() == KeyEvent.VK_RIGHT || k.getKeyCode() == KeyEvent.VK_LEFT
                        || k.getKeyCode() == KeyEvent.VK_A || k.getKeyCode() == KeyEvent.VK_D) {
                    gs.moveX(0);
                }
            }
        });

        // A task that continually runs throughout the game and spawns items randomly
        Task spawnItems = new Task() {
            @Override
            public void run() {
                if (levelNumber > 3) {
                    Random rand = new Random();
                    int spawnChance = rand.nextInt(10000);
                    if (spawnChance < 3) { // rare chance to spawn an invincibility star
                        Items iStar = new InvcStar(sc);
                    }
                    if (spawnChance < 8) { // better chance to spawn a spreadshot item
                        Items ss = new SpreadShot(sc);
                    }
                }
            }
        };
        Clock.addTask(spawnItems);

        /*
         * The code below tries to fix the problem where killing two enemies at the same
         * time at the end of a level stops
         * the game from advancing
         */
        List<Enemies> allEnemies = new ArrayList<>();
        Task checkActiveEnemies = new Task() {
            @Override
            public void run() {
                for (Sprite s : allSprites) {
                    if (Enemies.class.isAssignableFrom(s.getClass())) {
                        Enemies e = (Enemies) s;
                        allEnemies.add(e);
                    }
                }
                int count = 0;
                for (Enemies e : allEnemies) {
                    if (!e.isActive()) {
                        count++;
                        if (count == allEnemies.size() && count > 0) {
                            // JOptionPane.showMessageDialog(sc, "Two or More Enemies Were Killed at the
                            // Same Time!");
                            levelNumber++;
                            if (levelNumber < 4) {
                                levelCount.setText("Tutorial Level " + levelNumber); // update the level JLabel
                            } else {
                                levelCount.setText("Level " + (levelNumber - 3)); // update the level JLabel
                            }
                            if (levelNumber == 2) { // creates level 2 enemies
                                levelTwo(sc);
                            }

                            if (levelNumber == 3) { // creates level 3 enemies
                                levelThree(sc);
                            }

                            if (levelNumber == 4) { // creates level 4 enemies
                                totalPoints = 0;
                                lives = gs.lives;
                                pointCount.setText("Points: " + totalPoints);
                                livesCount.setText("Lives: " + lives);
                                JOptionPane.showMessageDialog(sc, "Game Will Now Start");
                                restartGame();
                                resetPlayer(gs, sc);
                                levelFour(sc);
                            }

                            if (levelNumber == 5) { // creates level 5 enemies
                                levelFive(sc);
                            }

                            if (levelNumber == 6) { // creates level 6 enemies
                                levelSix(sc);
                            }

                            if (levelNumber == 7) { // creates level 7 enemies
                                levelSeven(sc);
                            }

                            if (levelNumber == 8) { // creates level 8 enemies
                                levelEight(sc);
                            }

                            if (levelNumber == 9) { // creates the Boss enemy
                                setFinished();
                                firstBossLevel(sc);
                            }
                        }
                    }
                }
                allEnemies.clear();
            }
        };
        Clock.addTask(checkActiveEnemies);

        // sprite collision listener for player lasers and all enemies
        sc.addSpriteSpriteCollisionListener(Laser.class, Enemies.class,
                new SpriteSpriteCollisionListener<Laser, Enemies>() {
                    @Override
                    public void collision(Laser sp1, Enemies sp2) {
                        Picture ge = new Picture("greenenemy.png");
                        Picture es = new Picture("enemyshooter.png");
                        // this if statement happens specifically for enemy1 and enemyshooter type
                        // enemies
                        if (sp2.isPicture(ge) || sp2.isPicture(es)) {
                            totalPoints += sp2.points; // increments the points
                            pointCount.setText("Points: " + totalPoints); // updates the JLabel
                            sp2.setVelY(0);
                            ENEMY_DEATH_SOUND.play();
                            sp1.setActive(false); // sets the laser to inactive

                            // explosion task for the enemy sprite
                            Task explosion = new Task(sp2.dyingSequence.length) {
                                @Override
                                public void run() {
                                    // set the sprite picture from the sequence using Task's iteration() method.
                                    sp2.setPicture(sp2.dyingSequence[iteration()]);
                                    if (iteration() + 1 == maxIteration()) {

                                        if (levelNumber >= 4) { // only spawns life heart if levels are not the
                                                                // tutorials
                                            Random rand = new Random();
                                            int spawnChance = rand.nextInt(1500); // random chance to spawn a LifeHeart
                                                                                  // item
                                            if (spawnChance > 1450) {
                                                LifeHeart lh = new LifeHeart(sc);
                                                lh.setX(sp2.getX());
                                                lh.setY(sp2.getY());
                                                double centerPoint = lh.getY();
                                                lh.setVelX(0);
                                                lh.setVelY(.5);
                                                Task hover = new Task() { // hover task for the item
                                                    @Override
                                                    public void run() {
                                                        if (iteration() + 1 < 200 && (lh.getY() > centerPoint + 5
                                                                || lh.getY() < centerPoint - 5)) {
                                                            lh.setVelY(-lh.getVelY());
                                                        } else if (iteration() + 1 >= 200) {
                                                            lh.setVelY(2);
                                                            setFinished();
                                                        }
                                                    }
                                                };
                                                Clock.addTask(hover);
                                            }
                                        }

                                        sp2.setActive(false);
                                    }
                                }
                            };
                            Clock.addTask(explosion);

                            if (sp2.totalEnemies() < 2) { // if all the enemies of a level are killed
                                levelNumber++;
                                gs.setVelX(0);
                                gs.setVelY(0);
                                if (levelNumber < 4) {
                                    levelCount.setText("Tutorial Level " + levelNumber); // update the level JLabel
                                } else {
                                    levelCount.setText("Level " + (levelNumber - 3)); // update the level JLabel
                                }
                                if (levelNumber == 2) { // creates level 2 enemies
                                    levelTwo(sc);
                                }

                                if (levelNumber == 3) { // creates level 3 enemies
                                    levelThree(sc);
                                }

                                if (levelNumber == 4) { // creates level 4 enemies
                                    totalPoints = 0;
                                    lives = gs.lives;
                                    pointCount.setText("Points: " + totalPoints);
                                    livesCount.setText("Lives: " + lives);
                                    JOptionPane.showMessageDialog(sc, "Game Will Now Start");
                                    restartGame();
                                    resetPlayer(gs, sc);
                                    levelFour(sc);
                                }

                                if (levelNumber == 5) { // creates level 5 enemies
                                    levelFive(sc);
                                }

                                if (levelNumber == 6) { // creates level 6 enemies
                                    levelSix(sc);
                                }

                                if (levelNumber == 7) { // creates level 7 enemies
                                    levelSeven(sc);
                                }

                                if (levelNumber == 8) { // creates level 8 enemies
                                    levelEight(sc);
                                }

                                if (levelNumber == 9) { // creates the Boss enemy
                                    firstBossLevel(sc);
                                }
                            }
                        }

                        Picture boss = new Picture("bossenemy.png");
                        Picture laser = new Picture("laser.png");
                        // this if statement only occurs if the laser hits the boss
                        if (sp2.isPicture(boss) && sp1.isPicture(laser)) {
                            sp1.setVelY(0);
                            sp1.setVelX(0);
                            Task laserExplosion = new Task(sp1.dyingSequence.length) { // shows a laser explosion upon
                                                                                       // contact
                                @Override
                                public void run() {
                                    sp1.setPicture(sp1.dyingSequence[iteration()]);
                                    if (iteration() + 1 == maxIteration()) {
                                        sp1.setActive(false);
                                    }
                                }
                            };
                            Clock.addTask(laserExplosion);
                            sp2.lives--; // decrement the boss's lives

                            // deactive the old healthbar and create a new one
                            sp2.hb.setActive(false);
                            sp2.hb = new HealthBar(sc, sp2.maxLives, sp2.lives, sc.getSize().width / 2 - 100, 20,
                                    Color.gray);

                            if (sp2.lives < 1) { // if the boss is dead
                                gs.setPicture(gs.gracePeriodPic); // don't want to completely set inactive so that we
                                                                  // can starts the game if needed
                                sp2.hb.setActive(false);
                                levelNumber++;
                                sp2.setVelX(0);
                                Task bossExplosion = new Task(sp2.dyingSequence.length) { // boss explosion task
                                    @Override
                                    public void run() {
                                        // set the sprite picture from the sequence using Task's iteration() method.
                                        sp2.setPicture(sp2.dyingSequence[iteration()]);
                                        if (iteration() + 1 == maxIteration()) {
                                            sp2.setActive(false);
                                        }
                                    }
                                };
                                Clock.addTask(bossExplosion);
                                totalPoints += sp2.points; // increment the total points
                                pointCount.setText("Points: " + totalPoints); // update point counter JLabel
                                JOptionPane.showMessageDialog(sc, "Congrats On Beating The Boss!");
                                JOptionPane.showMessageDialog(sc, "You Win!");
                                JOptionPane.showMessageDialog(sc, "You had " + totalPoints + " points + " + lives
                                        + " lives = " + (totalPoints + 100 * lives) + " total points");
                                Clock.stop();

                                // offers the player an option to play again or quit
                                int input = JOptionPane.showConfirmDialog(content, "Would you like to play again?",
                                        "One More?", JOptionPane.YES_NO_OPTION);

                                if (input == JOptionPane.YES_OPTION) {
                                    restartGame();
                                    levelFour(sc);
                                    resetPlayer(gs, sc);
                                    pointCount.setText("Points: " + totalPoints);
                                    livesCount.setText("Lives: " + lives);
                                    Clock.start(10);
                                } else if (input == JOptionPane.NO_OPTION) {
                                    System.exit(0);
                                }
                                /*
                                 * cards.show(content, "GameWin");
                                 * bc3.requestFocus();
                                 * final GameWinSprite gws = new GameWinSprite(scWin);
                                 */
                            }
                        }
                    }
                });

        // sprite collision listener for character and enemy lasers
        sc.addSpriteSpriteCollisionListener(GalagaShip.class, EnemyLaser.class,
                new SpriteSpriteCollisionListener<GalagaShip, EnemyLaser>() {
                    @Override
                    public void collision(GalagaShip sp1, EnemyLaser sp2) {
                        Picture spaceship = new Picture("spaceship.png");
                        if (sp1.isPicture(spaceship) && !activeGracePeriod) {
                            activeGracePeriod = true;
                            lives--; // decrement player lives
                            livesCount.setText("Lives: " + lives);
                            sp1.setVelX(0);
                            sp1.setVelY(0);
                            sp2.setActive(false);
                            if (lives >= 1) {
                                Task gracePeriod = new Task(120) { // enables a gracePeriod for the player when they get
                                                                   // hit
                                    @Override
                                    public void run() { // displays a blinking ship that cannot be hit until the task is
                                                        // completed
                                        sp1.setPicture(sp1.gracePeriodPic);
                                        if (iteration() % 20 == 0) {
                                            sp1.setPicture(sp1.initialPic);
                                        } else if (iteration() + 1 == maxIteration()) {
                                            activeGracePeriod = false;
                                            sp1.setPicture(sp1.initialPic);
                                        }
                                    }
                                };
                                Clock.addTask(gracePeriod);
                            } else {
                                Task shipDeath = new Task(sp1.dyingSequence.length) { // if a number is here, it's the
                                                                                      // number of iterations in this
                                                                                      // task
                                    @Override
                                    public void run() {
                                        // set the sprite picture from the sequence using Task's iteration() method.
                                        sp1.setPicture(sp1.dyingSequence[iteration()]);
                                        if (iteration() + 1 == maxIteration()) {
                                            sp1.setPicture(sp1.gracePeriodPic);
                                        }
                                    }
                                };
                                Clock.addTask(shipDeath);
                            }

                            DEATH_SOUND.play();
                            if (lives < 1) {
                                JOptionPane.showMessageDialog(sc, "You died!");
                                JOptionPane.showMessageDialog(sc, "GAME OVER");
                                JOptionPane.showMessageDialog(sc, "You had " + totalPoints + " points");
                                Clock.stop();
                                // offers the player the option to play again
                                int input = JOptionPane.showConfirmDialog(content, "Would you like to play again?",
                                        "One More?", JOptionPane.YES_NO_OPTION);

                                if (input == JOptionPane.YES_OPTION) {
                                    restartGame();
                                    levelFour(sc);
                                    resetPlayer(gs, sc);
                                    pointCount.setText("Points: " + totalPoints);
                                    livesCount.setText("Lives: " + lives);
                                    Clock.start(10);
                                } else if (input == JOptionPane.NO_OPTION) {
                                    System.exit(0);
                                }
                            }
                        } else {
                            sp2.setActive(false);
                            sp1.setPicture(sp1.initialPic);
                        }
                    }
                });

        // collision listener for character and enemies
        sc.addSpriteSpriteCollisionListener(GalagaShip.class, Enemies.class,
                new SpriteSpriteCollisionListener<GalagaShip, Enemies>() {
                    @Override
                    public void collision(GalagaShip sp1, Enemies sp2) {
                        Picture spaceship = new Picture("spaceship.png");
                        Picture boss = new Picture("bossenemy.png");
                        // this if statement only happens if the player does not have a gracePeriod
                        // active and the enemy colliding is not the boss
                        if (sp1.isPicture(spaceship) && !activeGracePeriod && !sp2.isPicture(boss)) {
                            totalPoints += sp2.points;
                            pointCount.setText("Points: " + totalPoints);
                            activeGracePeriod = true; // enables the gracePeriod
                            sp2.setVelX(0);
                            sp2.setVelY(0);
                            lives--;
                            livesCount.setText("Lives: " + lives); // update the lives counter JLabel
                            gs.setVelX(0);
                            gs.setVelY(0);
                            Task enemyDeath = new Task(sp2.dyingSequence.length) { // if a number is here, it's the
                                                                                   // number of iterations in this task
                                @Override
                                public void run() {
                                    // set the sprite picture from the sequence using Task's iteration() method.
                                    sp2.setPicture(sp2.dyingSequence[iteration()]);
                                    if (iteration() + 1 == maxIteration()) {
                                        sp2.setActive(false);
                                        if (sp2.totalEnemies() < 1) {
                                            JOptionPane.showMessageDialog(sc,
                                                    "You cannot sacrifice yourself to advance to the next level!");
                                            JOptionPane.showMessageDialog(sc,
                                                    "You had " + totalPoints + " total points");
                                            Clock.stop();
                                            // offers the player the option to play again or quit
                                            int input = JOptionPane.showConfirmDialog(content,
                                                    "Would you like to play again?", "One More?",
                                                    JOptionPane.YES_NO_OPTION);
                                            if (input == JOptionPane.YES_OPTION) {
                                                restartGame();
                                                levelFour(sc);
                                                resetPlayer(gs, sc);
                                                pointCount.setText("Points: " + totalPoints);
                                                livesCount.setText("Lives: " + lives);
                                                Clock.start(10);
                                            } else if (input == JOptionPane.NO_OPTION) {
                                                System.exit(0);
                                            }
                                        }
                                    }
                                }
                            };
                            Clock.addTask(enemyDeath);

                            if (lives >= 1) {
                                Task gracePeriod = new Task(100) {
                                    @Override
                                    public void run() {
                                        sp1.setPicture(sp1.gracePeriodPic);
                                        if (iteration() % 10 == 0) {
                                            sp1.setPicture(sp1.initialPic);
                                        } else if (iteration() + 1 == maxIteration()) {
                                            activeGracePeriod = false;
                                            sp1.setPicture(sp1.initialPic);
                                        }
                                    }
                                };
                                Clock.addTask(gracePeriod);
                            } else {
                                Task shipDeath = new Task(sp1.dyingSequence.length) { // if a number is here, it's the
                                                                                      // number of iterations in this
                                                                                      // task
                                    @Override
                                    public void run() {
                                        // set the sprite picture from the sequence using Task's iteration() method.
                                        sp1.setPicture(sp1.dyingSequence[iteration()]);
                                        if (iteration() + 1 == maxIteration()) {
                                            sp1.setPicture(sp1.gracePeriodPic);
                                        }
                                    }
                                };
                                Clock.addTask(shipDeath);
                            }

                            DEATH_SOUND.play();
                            if (lives < 1) {
                                JOptionPane.showMessageDialog(sc, "You died!");
                                JOptionPane.showMessageDialog(sc, "GAME OVER");
                                JOptionPane.showMessageDialog(sc, "You had " + totalPoints + " points");
                                Clock.stop();
                                int input = JOptionPane.showConfirmDialog(content, "Would you like to play again?",
                                        "One More?", JOptionPane.YES_NO_OPTION);

                                if (input == JOptionPane.YES_OPTION) {
                                    restartGame();
                                    levelFour(sc);
                                    resetPlayer(gs, sc);
                                    pointCount.setText("Points: " + totalPoints);
                                    livesCount.setText("Lives: " + lives);
                                    Clock.start(10);
                                } else if (input == JOptionPane.NO_OPTION) {
                                    System.exit(0);
                                }
                            }
                        } else if (!sp2.isPicture(boss)) { // else if the gracePeriod is still active but a collision
                                                           // happens
                            totalPoints += sp2.points;
                            pointCount.setText("Points: " + totalPoints);
                            sp2.setVelY(0);
                            sp2.setVelX(0);
                            sp2.setY(sp2.getY() - 20);
                            Task enemyDeath = new Task(sp2.dyingSequence.length) { // if a number is here, it's the
                                                                                   // number of iterations in this task
                                @Override
                                public void run() {
                                    // set the sprite picture from the sequence using Task's iteration() method.
                                    sp2.setPicture(sp2.dyingSequence[iteration()]);
                                    if (iteration() + 1 == maxIteration()) {
                                        sp2.setActive(false);
                                        if (sp2.totalEnemies() < 1) {
                                            JOptionPane.showMessageDialog(sc,
                                                    "You cannot sacrifice yourself to advance to the next level!");
                                            JOptionPane.showMessageDialog(sc,
                                                    "You had " + totalPoints + " total points");
                                            System.exit(0);
                                        }
                                    }
                                }
                            };
                            Clock.addTask(enemyDeath);
                            sp1.setPicture(sp1.initialPic);
                        }
                    }
                });

        // collision listener for the player's lasers and enemy lasers
        sc.addSpriteSpriteCollisionListener(Laser.class, EnemyLaser.class,
                new SpriteSpriteCollisionListener<Laser, EnemyLaser>() {
                    @Override
                    public void collision(Laser sp1, EnemyLaser sp2) {
                        EnemyLaser elaser = new EnemyLaser(sc);
                        Picture elpic = elaser.getPicture();
                        if (sp2.isPicture(elpic)) {
                            totalPoints += sp2.points; // adds points and updates
                            pointCount.setText("Points: " + totalPoints);
                            sp2.setVelY(0);
                            sp1.setActive(false);
                            Task laserExplosion = new Task(sp2.dyingSequence.length) { // explosion for the enemy laser
                                @Override
                                public void run() {
                                    // set the sprite picture from the sequence using Task's iteration() method.
                                    sp2.setPicture(sp2.dyingSequence[iteration()]);
                                    if (iteration() + 1 == maxIteration()) {
                                        sp2.setActive(false);
                                    }
                                }
                            };
                            Clock.addTask(laserExplosion);
                        }
                        elaser.setActive(false);
                    }
                });

        // collision listener for player and items
        sc.addSpriteSpriteCollisionListener(GalagaShip.class, Items.class,
                new SpriteSpriteCollisionListener<GalagaShip, Items>() {
                    @Override
                    public void collision(GalagaShip sp1, Items sp2) {
                        Picture heart = new Picture("heart.png");
                        Picture star = new Picture("star.png");
                        Picture spreadshot = new Picture("spreadshot.png");
                        if (sp2.isPicture(heart)) { // if the item is a LifeHeart
                            sp2.setActive(false);
                            lives++;
                            livesCount.setText("Lives: " + lives);
                            LIFE_GAIN.play();
                        }
                        if (sp2.isPicture(star)) { // if the item is a InvcStar
                            sp2.setActive(false);
                            invincibilityItem = true;
                        }
                        if (sp2.isPicture(spreadshot)) { // if the item is a SpreadShot
                            sp2.setActive(false);
                            spreadshotItem = true;
                            spreadshotcount += 5;
                            if (spreadshotcount > 20) { // caps the amount of spreadshots to 20
                                spreadshotcount = 20;
                            }
                        }
                    }
                });

        Clock.addTask(sc.moveSprites());
    }

    /**
     * Creates the enemies for level one and puts them on to the SpriteComponent
     * 
     * @param sc the sprite component
     */
    static void levelOne(SpriteComponent sc) {
        levelCount.setText("Tutorial Level " + levelNumber);
        spreadshotItem = true;
        spreadshotcount = 20;
        invincibilityItem = true;
        final Enemy1[] e1 = new Enemy1[3];
        int count1 = 0;
        int count2 = 1;
        for (int i = 0; i < e1.length; i++) { // creates level 1 enemies
            e1[i] = new Enemy1(sc);
            if (i % 2 == 0) {
                e1[i].setX(sc.getSize().width / 2 + 75 * count1);
                count1++;
            }
            if (i % 2 != 0) {
                e1[i].setX(sc.getSize().width / 2 - 75 * count2);
                count2++;
            }
            e1[i].setY(-100);
            double speed = .5;
            e1[i].setVelY(speed);
        }
    }

    /**
     * Creates the enemies for level two and puts them on to the SpriteComponent
     * 
     * @param sc the sprite component
     */
    static void levelTwo(SpriteComponent sc) {
        final Enemy1[] e = new Enemy1[5];
        int count1 = 0;
        int count2 = 1;
        for (int i = 0; i < e.length; i++) {
            e[i] = new Enemy1(sc);
            if (i % 2 == 0) {
                e[i].setX(sc.getSize().width / 2 + 75 * count1);
                count1++;
            }
            if (i % 2 != 0) {
                e[i].setX(sc.getSize().width / 2 - 75 * count2);
                count2++;
            }
            e[i].setY(-100);
            double speed = .5;
            e[i].setVelY(speed);
        }
    }

    /**
     * Creates the enemies for level three and puts them on to the SpriteComponent
     * 
     * @param sc the sprite component
     */
    static void levelThree(SpriteComponent sc) {
        final Enemy1[] e = new Enemy1[7];
        int count1 = 0;
        int count2 = 1;
        for (int i = 0; i < e.length; i++) {
            e[i] = new Enemy1(sc);
            if (i % 2 == 0) {
                e[i].setX(sc.getSize().width / 2 + 75 * count1);
                count1++;
            }
            if (i % 2 != 0) {
                e[i].setX(sc.getSize().width / 2 - 75 * count2);
                count2++;
            }
            e[i].setY(-100);
            double speed = .5;
            e[i].setVelY(speed);
        }
    }

    /**
     * Creates the enemies for level four and puts them on to the SpriteComponent
     * 
     * @param sc the sprite component
     */
    static void levelFour(SpriteComponent sc) {
        levelCount.setText("Level " + (levelNumber - 3));
        pointCount.setText("Points: " + totalPoints);
        livesCount.setText("Lives: " + lives);
        final Enemy1[] e = new Enemy1[10];
        Random RAND = new Random();
        double speed = 1.5;
        for (int i = 0; i < e.length; i++) {
            int randomN = RAND.nextInt(sc.getSize().width - 900) + 500;
            e[i] = new Enemy1(sc);
            e[i].setX(randomN);
            e[i].setY(-100 - 300 * i);
            e[i].setVelY(speed);
        }
    }

    /**
     * Creates the enemies for level five and puts them on to the SpriteComponent
     * 
     * @param sc the sprite component
     */
    static void levelFive(SpriteComponent sc) {
        final ReusableClip ENEMY_LASER_SOUND = new ReusableClip("betterenemylaser.wav");
        EnemyShooter es = new EnemyShooter(sc); // this enemy shoots lasers back
        es.setX(sc.getSize().width / 2);
        es.setY(-100);
        double speed = 1;
        es.setVelY(speed);
        Task shooting = new Task() { // this task enables the enemy to shoot automatically
            int count = 0;

            @Override
            public void run() {
                if (!es.isActive()) {
                    setFinished();
                }
                count++;
                if (es.getY() >= 100) {
                    es.setVelY(0);
                    es.setY(100);
                }
                if (count % 100 == 0) {
                    EnemyLaser laser = new EnemyLaser(sc);
                    laser.setVelY(5);
                    laser.setCenterX(es.centerX());
                    laser.setCenterY(es.centerY());
                    ENEMY_LASER_SOUND.play();
                }
            }
        };
        Clock.addTask(shooting);
    }

    /**
     * Creates the enemies for level six and puts them on to the SpriteComponent
     * 
     * @param sc the sprite component
     */
    static void levelSix(SpriteComponent sc) {
        EnemyShooter[] es = new EnemyShooter[6];
        int count1 = 0;
        int count2 = 1;
        for (int i = 0; i < es.length; i++) {
            es[i] = new EnemyShooter(sc);
            if (i % 2 == 0) {
                es[i].setX(sc.getSize().width / 2 + 100 * count1);
                count1++;
            }
            if (i % 2 != 0) {
                es[i].setX(sc.getSize().width / 2 - 100 * count2);
                count2++;
            }
            es[i].setY(-100 - 200 * i);
            double speed = 2;
            es[i].setVelY(speed);
        }
    }

    /**
     * Creates the enemies for level seven and puts them on to the SpriteComponent
     * 
     * @param sc the sprite component
     */
    static void levelSeven(SpriteComponent sc) {
        final ReusableClip ENEMY_LASER_SOUND = new ReusableClip("betterenemylaser.wav");
        EnemyShooter es1 = new EnemyShooter(sc); // first/left moving enemy shooter
        es1.setX(sc.getSize().width / 2 - 200);
        es1.setY(-100);
        double speedY = 5;
        double speedX = 10;
        es1.setVelY(speedY);
        Task es1Move = new Task() {
            int count;

            @Override
            public void run() {
                if (!es1.isActive()) {
                    setFinished();
                }
                count++;
                if (es1.getY() > 100) {
                    es1.setVelY(0);
                    es1.setY(100);
                    es1.setVelX(speedX);
                }
                if (es1.getX() > sc.getSize().width / 2 - 100 || es1.getX() < sc.getSize().width / 2 - 300) {
                    es1.setVelX(-es1.getVelX());
                }
                if (count % 100 == 0) { // how fast the enemy shoots
                    EnemyLaser laser = new EnemyLaser(sc);
                    laser.setVelY(5);
                    laser.setCenterX(es1.centerX());
                    laser.setCenterY(es1.centerY());
                    ENEMY_LASER_SOUND.play();
                }
            }
        };
        Clock.addTask(es1Move);

        EnemyShooter es2 = new EnemyShooter(sc); // second/right moving enemy shooter
        es2.setX(sc.getSize().width / 2 + 200);
        es2.setY(-150);
        es2.setVelY(speedY);
        Task es2Move = new Task() {
            int count;

            @Override
            public void run() {
                if (!es2.isActive()) {
                    setFinished();
                }
                count++;
                if (es2.getY() > 100) {
                    es2.setVelY(0);
                    es2.setY(100);
                    es2.setVelX(speedX);
                }
                if (es2.getX() > sc.getSize().width / 2 + 300 || es2.getX() < sc.getSize().width / 2 + 100) {
                    es2.setVelX(-es2.getVelX());
                }
                if (count % 100 == 0) { // how fast the enemy shoots
                    EnemyLaser laser = new EnemyLaser(sc);
                    laser.setVelY(5);
                    laser.setCenterX(es2.centerX());
                    laser.setCenterY(es2.centerY());
                    ENEMY_LASER_SOUND.play();
                }
            }
        };
        Clock.addTask(es2Move);

        EnemyShooter es3 = new EnemyShooter(sc); // third/middle moving enemy shooter
        es3.setX(sc.getSize().width / 2);
        es3.setY(-200);
        es3.setVelY(speedY);
        Task es3Move = new Task() { // change the normal task to a specialized one (in this case, the enemy bounces
                                    // in a smaller space)
            int count;

            @Override
            public void run() {
                if (!es3.isActive()) {
                    setFinished();
                }
                count++;
                if (es3.getY() > 100) {
                    es3.setVelY(0);
                    es3.setY(100);
                    es3.setVelX(speedX);
                }
                if (es3.getX() > sc.getSize().width / 2 + 100 || es3.getX() < sc.getSize().width / 2 - 100) {
                    es3.setVelX(-es3.getVelX());
                }
                if (count % 100 == 0) { // how fast the enemy shoots
                    EnemyLaser laser = new EnemyLaser(sc);
                    laser.setVelY(5);
                    laser.setCenterX(es3.centerX());
                    laser.setCenterY(es3.centerY());
                    ENEMY_LASER_SOUND.play();
                }
            }
        };
        Clock.addTask(es3Move);

        HorizontalEnemy[] he = new HorizontalEnemy[20];
        double hespeed = -2;
        int hecount = 0;
        int he2count = 0;
        for (int i = 0; i < he.length; i++) {
            if (i < he.length / 2) {
                he[i] = new HorizontalEnemy(sc);
                he[i].setX(sc.getSize().width + 500);
                he[i].setY(100 + 50 * hecount);
                he[i].setVelX(hespeed);
                hecount++;
            } else {
                he[i] = new HorizontalEnemy(sc);
                he[i].setX(sc.getSize().width + 1000);
                he[i].setY(100 + 50 * he2count);
                he[i].setVelX(hespeed);
                he2count++;
            }
        }
    }

    /**
     * Creates the enemies for level eight and puts them on to the SpriteComponent
     * 
     * @param sc the sprite component
     */
    static void levelEight(SpriteComponent sc) {
        Enemy1[] e = new Enemy1[10];
        Random RAND = new Random();
        double speed = 1.5;
        for (int i = 0; i < e.length; i++) {
            int randomN = RAND.nextInt(sc.getSize().width - 900) + 500;
            e[i] = new Enemy1(sc);
            e[i].setX(randomN);
            e[i].setY(-100 - 400 * i);
            e[i].setVelY(speed);
        }

        EnemyShooter[] es = new EnemyShooter[10];
        speed = 5;
        for (int i = 0; i < es.length; i++) {
            int randomN = RAND.nextInt(sc.getSize().width / 2 + 290 - (sc.getSize().width / 2 - 290))
                    + sc.getSize().width / 2 - 290;
            es[i] = new EnemyShooter(sc);
            es[i].setX(randomN);
            es[i].setY(-100 - 1000 * i);
            es[i].setVelY(speed);
        }
    }

    /**
     * Creates the enemies for the first boss level and puts them on to the
     * SpriteComponent
     * 
     * @param sc the sprite component
     */
    static void firstBossLevel(SpriteComponent sc) {
        levelCount.setText("First Boss Level");
        // JOptionPane.showMessageDialog(sc, "Boss Incoming...");
        final BossEnemy be = new BossEnemy(sc);
        be.setX(sc.getSize().width / 2 - 100);
        be.setY(-300);
        double speed = 2;
        be.setVelY(speed);
    }

    /**
     * Restarts the game by setting everything back to the original/base values
     */
    static void restartGame() {
        totalPoints = 0;
        levelNumber = 4;
        lives = 3;
        levelCount.setText("Level " + (levelNumber - 3));
        activeGracePeriod = false;
        invincibilityItem = false;
        spreadshotItem = false;
        spreadshotcount = 0;
        clearAllSprites();
    }

    /**
     * Resets the player to the beginning of the game's values
     * 
     * @param gs the player's sprite
     * @param sc the sprite component
     */
    static void resetPlayer(GalagaShip gs, SpriteComponent sc) {
        gs.setPicture(gs.initialPic);
        gs.setVelX(0);
        gs.setVelY(0);
        gs.setX(sc.getSize().width / 2);
        gs.setY(sc.getSize().height - 100);
    }

    /**
     * Clears all the sprites on the screen excluding the player
     */
    static void clearAllSprites() {
        for (Sprite e : allSprites) {
            if (e.isActive()) {
                e.setActive(false);
            }
        }
        allSprites.clear();
    }

}

/*
 * class GameWinSprite extends Sprite{
 * Picture initialPic;
 * public GameWinSprite(SpriteComponent sc) {
 * super(sc);
 * initialPic = new Picture("gamewin.png");
 * setPicture(initialPic);
 * setX(sc.getSize().width/2-300);
 * setY(50);
 * //setVelY(5);
 * Task scroll = new Task(){
 * 
 * @Override
 * public void run() {
 * if(getY() > sc.getSize().height/2){
 * setVelY(0);
 * setY(sc.getSize().height/2);
 * JOptionPane.showMessageDialog(sc, "Congrats!");
 * //setFinished();
 * //System.exit(0);
 * }
 * }
 * };
 * Clock.addTask(scroll);
 * }
 * 
 * }
 */

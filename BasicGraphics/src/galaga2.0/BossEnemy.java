/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bettergalagafkhatt1;

import basicgraphics.BasicFrame;
import basicgraphics.Clock;
import basicgraphics.CollisionEventType;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.Task;
import basicgraphics.images.Picture;
import basicgraphics.sounds.ReusableClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Js4fs
 */
public class BossEnemy extends Enemies {

    final ReusableClip ENEMY_LASER_SOUND = new ReusableClip("betterenemylaser.wav");

    public BossEnemy(SpriteComponent sc) {
        super(sc);
        points = 500;
        initialPic = new Picture("bossenemy.png");
        setPicture(initialPic);
        maxLives = 150; //sets the max lives of the boss enemy
        lives = maxLives; //sets the starting lives for the boss enemy to the max lives
        setVelX(0);
        setVelY(0);
        hb = new HealthBar(sc, maxLives, lives, sc.getSize().width / 2 - 100, 20, Color.BLACK);
        dyingSequence = new Picture[100]; //pretty big explosion
        Color purple = new Color(128, 0, 128);
        for (int i = 0; i < dyingSequence.length; i++) {
            int size = 5;
            Image im = BasicFrame.createImage(size + i, size + i);
            Graphics g = im.getGraphics();
            g.setColor(purple);
            g.fillOval(0, 0, size + i, size + i);
            dyingSequence[i] = new Picture(im);
        }
        Task bossEnemyMove = new Task() {
            int count;
            int count2;

            @Override
            public void run() {
                if (!isActive()) {
                    setFinished();
                }
                if (lives > maxLives / 2) {
                    count++;
                    if (getY() > 30) {
                        setVelY(0);
                        setY(30);
                        setVelX(2);
                    }
                    /*
                    if(getX() > sc.getSize().width/2+250 || getX() < sc.getSize().width/2-400){
                        setVelX(-getVelX());
                    }
                     */
                    if (count % 100 == 0) {
                        EnemyLaser laser = new EnemyLaser(sc);
                        laser.setVelY(5);
                        laser.setCenterX(centerX());
                        laser.setCenterY(centerY());
                        ENEMY_LASER_SOUND.play();
                    }
                    if (count % 200 == 0) {
                        final double INCR = Math.PI * 2 / 100.0;
                        final double speed = 7;
                        EnemyLaser laser1 = new EnemyLaser(sc);
                        laser1.setVelY(speed);
                        laser1.setCenterX(centerX() - 100);
                        laser1.setCenterY(centerY());
                        ENEMY_LASER_SOUND.play();

                        Task laser1Curve = new Task() {
                            int count;

                            @Override
                            public void run() {
                                count++;
                                if (count % 15 == 0) {
                                    laser1.turn(-INCR);
                                }
                            }
                        };
                        Clock.addTask(laser1Curve);

                        EnemyLaser laser2 = new EnemyLaser(sc);
                        laser2.setVelY(speed);
                        laser2.setCenterX(centerX() + 100);
                        laser2.setCenterY(centerY());
                        ENEMY_LASER_SOUND.play();

                        Task laser2Curve = new Task() {
                            int count;

                            @Override
                            public void run() {
                                count++;
                                if (count % 15 == 0) {
                                    laser2.turn(INCR);
                                }
                            }
                        };
                        Clock.addTask(laser2Curve);
                    }
                } else if (lives > maxLives / 4) {
                    if (count2 == 0) {
                        setVelX(3);
                        count2++;
                    }
                    count++;
                    /*
                    if(getX() > sc.getSize().width/2+250 || getX() < sc.getSize().width/2-400){
                        setVelX(-getVelX());
                    }
                     */
                    if (count % 50 == 0) {
                        EnemyLaser laser = new EnemyLaser(sc);
                        laser.setVelY(5);
                        laser.setCenterX(centerX());
                        laser.setCenterY(centerY());
                        ENEMY_LASER_SOUND.play();
                    }
                    if (count % 150 == 0) {
                        final double INCR = Math.PI * 2 / 100.0;
                        final double speed = 7;
                        EnemyLaser laser1 = new EnemyLaser(sc);
                        laser1.setVelY(speed);
                        laser1.setCenterX(centerX() - 100);
                        laser1.setCenterY(centerY());
                        ENEMY_LASER_SOUND.play();

                        Task laser1Curve = new Task() {
                            int count;

                            @Override
                            public void run() {
                                count++;
                                if (count % 15 == 0) {
                                    laser1.turn(-INCR);
                                }
                            }
                        };
                        Clock.addTask(laser1Curve);

                        EnemyLaser laser2 = new EnemyLaser(sc);
                        laser2.setVelY(speed);
                        laser2.setCenterX(centerX() + 100);
                        laser2.setCenterY(centerY());
                        ENEMY_LASER_SOUND.play();

                        Task laser2Curve = new Task() {
                            int count;

                            @Override
                            public void run() {
                                count++;
                                if (count % 15 == 0) {
                                    laser2.turn(INCR);
                                }
                            }
                        };
                        Clock.addTask(laser2Curve);
                    }
                    if (count % 300 == 0) {
                        Enemy1 e = new Enemy1(sc);
                        e.setX(centerX());
                        e.setY(centerY());
                        double speed = 1;
                        e.setVelY(speed);
                    }
                } else {
                    if (count2 == 1) {
                        setVelX(4);
                        count2++;
                    }
                    count++;
                    if (count % 50 == 0) {
                        EnemyLaser laser = new EnemyLaser(sc);
                        laser.setVelY(5);
                        laser.setCenterX(centerX());
                        laser.setCenterY(centerY());
                        ENEMY_LASER_SOUND.play();
                    }
                    if (count % 75 == 0) {
                        final double INCR = Math.PI * 2 / 100.0;
                        final double speed = 7;
                        EnemyLaser laser1 = new EnemyLaser(sc);
                        laser1.setVelY(speed);
                        laser1.setCenterX(centerX() - 100);
                        laser1.setCenterY(centerY());
                        ENEMY_LASER_SOUND.play();

                        Task laser1Curve = new Task() {
                            int count;

                            @Override
                            public void run() {
                                count++;
                                if (count % 15 == 0) {
                                    laser1.turn(-INCR);
                                }
                            }
                        };
                        Clock.addTask(laser1Curve);

                        EnemyLaser laser2 = new EnemyLaser(sc);
                        laser2.setVelY(speed);
                        laser2.setCenterX(centerX() + 100);
                        laser2.setCenterY(centerY());
                        ENEMY_LASER_SOUND.play();

                        Task laser2Curve = new Task() {
                            int count;

                            @Override
                            public void run() {
                                count++;
                                if (count % 15 == 0) {
                                    laser2.turn(INCR);
                                }
                            }
                        };
                        Clock.addTask(laser2Curve);
                    }
                    if (count % 400 == 0) {
                        EnemyShooter es = new EnemyShooter(sc);
                        es.setX(sc.getSize().width / 2);
                        es.setY(-50);
                        double speed = 5;
                        es.setVelY(speed);
                    }
                    if (count % 600 == 0 || count % 625 == 0) {
                        GiantEnemyLaser giantLaser = new GiantEnemyLaser(sc);
                        double speed = 7;
                        giantLaser.setX(centerX() + 100);
                        giantLaser.setY(centerY());
                        giantLaser.setVelY(speed);
                    }

                }
            }
        };
        Clock.addTask(bossEnemyMove);
    }

    @Override
    public void processEvent(SpriteCollisionEvent se) {
        SpriteComponent sc = getSpriteComponent();
        if (se.eventType == CollisionEventType.WALL) {
            if (se.xlo) {
                setVelX(-getVelX());
            }
            if (se.xhi) {
                setVelX(-getVelX());
            }
        }
    }

}

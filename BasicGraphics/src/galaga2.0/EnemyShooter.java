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
public class EnemyShooter extends Enemies {

    final ReusableClip ENEMY_LASER_SOUND = new ReusableClip("betterenemylaser.wav");

    public EnemyShooter(SpriteComponent sc) {
        super(sc);
        points = 50;
        initialPic = new Picture("enemyshooter.png");
        setPicture(initialPic);
        setVelX(0);
        setVelY(0);
        dyingSequence = new Picture[20];
        for (int i = 0; i < dyingSequence.length; i++) {
            int size = 5;
            Image im = BasicFrame.createImage(size + i, size + i);
            Graphics g = im.getGraphics();
            g.setColor(Color.orange);
            g.fillOval(0, 0, size + i, size + i);
            dyingSequence[i] = new Picture(im);
        }
        Task enemyMove = new Task() {
            @Override
            public void run() {
                if (!isActive()) {
                    setFinished();
                }
                if (getY() > 100) {
                    setVelY(0);
                    setY(100);
                    setVelX(1);
                }
                if (getX() > sc.getSize().width / 2 + 300 || getX() < sc.getSize().width / 2 - 300) {
                    setVelX(-getVelX());
                }
                if (iteration() % 150 == 0) {
                    EnemyLaser laser = new EnemyLaser(sc);
                    laser.setVelY(5);
                    laser.setCenterX(centerX());
                    laser.setCenterY(centerY());
                    ENEMY_LASER_SOUND.play();
                }
            }
        };
        Clock.addTask(enemyMove);
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

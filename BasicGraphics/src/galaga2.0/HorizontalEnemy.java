/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bettergalagafkhatt1;

import basicgraphics.BasicFrame;
import basicgraphics.CollisionEventType;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import static bettergalagafkhatt1.BetterGalaga.levelCount;
import static bettergalagafkhatt1.BetterGalaga.levelEight;
import static bettergalagafkhatt1.BetterGalaga.levelNumber;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Js4fs
 */
public class HorizontalEnemy extends Enemies {

    public HorizontalEnemy(SpriteComponent sc) {
        super(sc);
        points = 20;
        initialPic = new Picture("greenenemy.png");
        initialPic = initialPic.rotate(89.5); //89.5 degrees changes the picture to look left
        setPicture(initialPic);
        setVelX(0);
        setVelY(0);
        dyingSequence = new Picture[20];
        for (int i = 0; i < dyingSequence.length; i++) {
            int size = 5;
            Image im = BasicFrame.createImage(size + i, size + i);
            Graphics g = im.getGraphics();
            g.setColor(Color.red);
            g.fillOval(0, 0, size + i, size + i);
            dyingSequence[i] = new Picture(im);
        }
    }

    @Override
    public void processEvent(SpriteCollisionEvent se) {
        SpriteComponent sc = getSpriteComponent();
        if (se.eventType == CollisionEventType.WALL_INVISIBLE) {
            if (se.xlo) {
                setActive(false);
                if (enemyCount < 2) {
                    levelNumber++;
                    levelCount.setText("Level " + (levelNumber - 3));
                    if (levelNumber == 8) {
                        levelEight(sc);
                    }
                }
            }
        }
    }

}

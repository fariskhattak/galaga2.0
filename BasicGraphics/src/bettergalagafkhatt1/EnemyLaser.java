/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bettergalagafkhatt1;

import basicgraphics.BasicFrame;
import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import static bettergalagafkhatt1.BetterGalaga.allSprites;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Js4fs
 */
public class EnemyLaser extends Sprite {

    public Picture laser;
    public Picture[] dyingSequence;
    public int points;

    public EnemyLaser(SpriteComponent sc) {
        super(sc);
        points = 10;
        Image im = BasicFrame.createImage(8, 15);
        Graphics g = im.getGraphics();
        g.setColor(Color.green);
        g.fillOval(0, 0, 8, 15);
        laser = new Picture(im);
        setPicture(laser);
        dyingSequence = new Picture[10];
        for (int i = 0; i < dyingSequence.length; i++) {
            int size = 5;
            im = BasicFrame.createImage(size + i, size + i);
            g = im.getGraphics();
            g.setColor(Color.green);
            g.fillOval(0, 0, size + i, size + i);
            dyingSequence[i] = new Picture(im);
        }
        allSprites.add(this);
    }

    @Override
    public void processEvent(SpriteCollisionEvent se) {
        setActive(false);
    }

    public void turn(double incr) {
        double heading = Math.atan2(getVelY(), getVelX());
        heading += incr;
        //setVelY(Math.sin(heading));
        setVelY(4);
        setVelX(4 * Math.cos(heading));
        //setPicture(laser.rotate(heading));
    }
    
    /**
     * Compares the EnemyLaser sprite picture to another EnemyLaser picture using the width and height of each picture
     * @param o the object which should be a picture
     * @return the Boolean value depending on if the height and the width equal each other
     */
    public boolean isPicture(Object o) { //compares a picture to another picture using the width and height of the pictures
        Picture that = (Picture) o;
        return this.getPicture().getWidth() == that.getWidth() && this.getPicture().getHeight() == that.getHeight();
    }
}

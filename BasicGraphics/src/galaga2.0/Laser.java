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
public class Laser extends Sprite {

    Picture laser;
    Picture[] dyingSequence;

    public Laser(SpriteComponent sc) {
        super(sc);
        laser = new Picture("laser.png");
        setPicture(laser);
        allSprites.add(this);
        dyingSequence = new Picture[10];
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
        setActive(false);
    }

    /**
     * Compares the Laser sprite picture to another Laser picture using the width and height of each picture
     * @param o the object which should be a picture
     * @return the Boolean value depending on if the height and the width equal each other
     */
    public boolean isPicture(Object o) { //compares a picture to another picture using the width and height of the pictures
        Picture that = (Picture) o;
        return this.getPicture().getWidth() == that.getWidth() && this.getPicture().getHeight() == that.getHeight();
    }
}

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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JOptionPane;

/**
 *
 * @author Js4fs
 */
public class GalagaShip extends Sprite {

    public Picture initialPic;
    public Dimension spriteDimension;
    public Picture[] dyingSequence;
    public int lives;
    public Picture gracePeriodPic;
    public Picture invinciblePic[];

    public GalagaShip(SpriteComponent sc) {
        super(sc);
        lives = 3;
        initialPic = new Picture("spaceship.png");
        setPicture(initialPic);
        Picture red = new Picture("starshipred.png");
        Picture orange = new Picture("starshiporange.png");
        Picture yellow = new Picture("starshipyellow.png");
        Picture green = new Picture("starshipgreen.png");
        Picture blue = new Picture("starshipblue.png");
        Picture violet = new Picture("starshipviolet.png");
        invinciblePic = new Picture[]{red, red, orange, orange, yellow, yellow, green, green, blue, blue, violet, violet};
        spriteDimension = sc.getSize();
        setX(spriteDimension.width / 2);
        setY(spriteDimension.height - 100);
        setVelX(0);
        setVelY(0);
        dyingSequence = new Picture[50];
        for (int i = 0; i < dyingSequence.length; i++) {
            int size = 5;
            Image im = BasicFrame.createImage(size + i, size + i);
            Graphics g = im.getGraphics();
            g.setColor(Color.blue);
            g.fillOval(0, 0, size + i, size + i);
            dyingSequence[i] = new Picture(im);
        }
        Image im = BasicFrame.createImage(5, 5);
        Graphics g = im.getGraphics();
        g.setColor(Color.black);
        g.fillOval(0, 0, 5, 5);
        gracePeriodPic = new Picture(im);
    }

    public void moveX(double incr) {
        setVelX(incr);
    }

    public void moveY(double incr) {
        setVelY(incr);
    }

    /**
     * This sprite only reacts to collisions with the borders of the display
     * region. When it does, it wraps to the other side.
     *
     * @param se
     */
    @Override
    public void processEvent(SpriteCollisionEvent se) {
        SpriteComponent sc = getSpriteComponent();
        if (se.xlo) {
            setX(sc.getSize().width - getWidth());
        }
        if (se.xhi) {
            setX(0);
        }
        if (se.ylo) {
            JOptionPane.showMessageDialog(sc, "You left the battlefield!");
            System.exit(0);
        }
        if (se.yhi) {
            setY(sc.getSize().height - 100);
        }
    }

    /**
     * Compares the GalagaShip sprite picture to another GalagaShip picture using the width and height of each picture
     * @param o the object which should be a picture
     * @return the Boolean value depending on if the height and the width equal each other
     */
    public boolean isPicture(Object o) { //compares a picture to another picture using the width and height of the pictures
        Picture that = (Picture) o;
        return this.getPicture().getWidth() == that.getWidth() && this.getPicture().getHeight() == that.getHeight();
    }
}

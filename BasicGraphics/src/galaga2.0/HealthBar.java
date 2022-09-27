/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bettergalagafkhatt1;

import basicgraphics.BasicFrame;
import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import static bettergalagafkhatt1.BetterGalaga.allSprites;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class HealthBar extends Sprite {

    public HealthBar(SpriteComponent sc, int maxHealth, int health, double x, double y, Color color) {
        super(sc);
        allSprites.add(this);
        int length = 200;
        int height = 15;
        BufferedImage bf = BasicFrame.createImage(length, height);
        Graphics2D g = (Graphics2D) bf.getGraphics();
        g.setColor(color);

        //healthbar
        g.fillRect(0, 0, length, height);

        //color for health inside the bar
        if (health > maxHealth / 2) {
            g.setColor(Color.green);
        } else if (health > maxHealth / 4) {
            g.setColor(Color.yellow);
        } else {
            g.setColor(Color.red);
        }

        //the health inside the bar
        g.fillRect(1, 1, (int) ((length - 2) * (double) ((health * 1000) / maxHealth) * .001), height - 2);
        setX(x);
        setY(y);
        Picture hb = new Picture(bf);
        setPicture(hb);
    }
}

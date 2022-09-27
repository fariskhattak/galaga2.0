/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bettergalagafkhatt1;

import basicgraphics.BasicFrame;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Js4fs
 */
public class GiantEnemyLaser extends EnemyLaser {

    public GiantEnemyLaser(SpriteComponent sc) {
        super(sc);
        Image im = BasicFrame.createImage(25, 40);
        Graphics g = im.getGraphics();
        g.setColor(Color.green);
        g.fillOval(0, 0, 25, 40);
        laser = new Picture(im);
        setPicture(laser);
    }

}

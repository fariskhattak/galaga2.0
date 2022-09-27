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
import basicgraphics.images.Picture;
import static bettergalagafkhatt1.BetterGalaga.content;
import static bettergalagafkhatt1.BetterGalaga.levelFour;
import static bettergalagafkhatt1.BetterGalaga.restartGame;
import static bettergalagafkhatt1.BetterGalaga.totalPoints;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JOptionPane;

/**
 *
 * @author Js4fs
 */
public class Enemy1 extends Enemies {

    public Enemy1(SpriteComponent sc) {
        super(sc);
        points = 20;
        initialPic = new Picture("greenenemy.png");
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
                setX(sc.getSize().width - getWidth());
            }
            if (se.xhi) {
                setX(0);
            }
            if (se.yhi) {
                Clock.stop();
                JOptionPane.showMessageDialog(sc, "The aliens' forces reached Earth! You lose!");
                JOptionPane.showMessageDialog(sc, "You had " + totalPoints + " total points");
                
                int input = JOptionPane.showConfirmDialog(content, "Would you like to play again?", "One More?", JOptionPane.YES_NO_OPTION);
                if (input == JOptionPane.YES_OPTION) {
                    restartGame();
                    levelFour(sc);
                    Clock.start(10);
                } else if (input == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
            }
        }
    }
}

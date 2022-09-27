/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bettergalagafkhatt1;

import basicgraphics.Clock;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.Task;
import basicgraphics.images.Picture;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Js4fs
 */
public class InvcStar extends Items {

    Task hover;
    public InvcStar(SpriteComponent sc) {
        super(sc);
        Random rand = new Random();
        int randYPos = rand.nextInt(sc.getSize().height / 2 + 300) + sc.getSize().height / 2 - 300;
        initialPic = new Picture("star.png");
        setPicture(initialPic);
        setY(randYPos); //random part of the screen
        //setY(sc.getSize().height/2 - 50); //middle of the screen
        setX(sc.getSize().width + 200); //outside of boundary on the right
        double centerPoint = getY();
        double randYVel = rand.nextDouble(2) + .5;
        setVelY(randYVel);
        double randXVel = rand.nextDouble(4) + 1;
        setVelX(-randXVel);
        hover = new Task() {
            @Override
            public void run() {
                if (getY() > centerPoint + 25 || getY() < centerPoint - 25) {
                    setVelY(-getVelY());
                }
            }
        };
        Clock.addTask(hover);
    }

    @Override
    public void processEvent(SpriteCollisionEvent se) {
        SpriteComponent sc = getSpriteComponent();
        if (se.xlo || se.ylo || se.yhi) {
            setActive(false); //sets active to false if it touches top, bottom, or left side of screen
        }
    }

}

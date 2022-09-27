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

/**
 *
 * @author Js4fs
 */
public class SpreadShot extends Items {

    public SpreadShot(SpriteComponent sc) {
        super(sc);
        initialPic = new Picture("spreadshot.png");
        setPicture(initialPic);
        Random rand = new Random();
        int randYPos = rand.nextInt(sc.getSize().height / 2 + 200) + sc.getSize().height / 2 - 300;
        setY(randYPos); //random Y part of the screen
        setX(-200); //outside of boundary on the left
        double centerPoint = getY();
        double randYVel = rand.nextDouble(2) + .5;
        setVelY(randYVel);
        double randXVel = rand.nextDouble(3) + 1;
        setVelX(randXVel); //moves to the right
        Task hoverturn = new Task() {
            @Override
            public void run() {
                if (!isActive()) {
                    setFinished();
                }
                if (getY() > centerPoint + 25 || getY() < centerPoint - 25) {
                    setVelY(-getVelY());
                }
                setPicture(initialPic.rotate(-(5 + 2 * iteration())));
            }

        };
        Clock.addTask(hoverturn);
    }

    @Override
    public void processEvent(SpriteCollisionEvent se) {
        SpriteComponent sc = getSpriteComponent();
        if (se.xhi || se.ylo || se.yhi) {
            setActive(false); //sets active to false if it touches top, bottom, or right side of screen
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bettergalagafkhatt1;

import basicgraphics.Clock;
import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.Task;
import basicgraphics.images.Picture;

/**
 *
 * @author Js4fs
 */
public class GameWinSprite extends Sprite{
    Picture initialPic;
    public GameWinSprite(SpriteComponent sc) {
        super(sc);
        initialPic = new Picture("gamewin.png");
        setPicture(initialPic);
        setX(sc.getSize().width/2-300);
        setY(-200);
        setVelY(5);
        Task scroll = new Task(){
            @Override
            public void run() {
                if(getY() > sc.getSize().height/2){
                    setVelY(0);
                    setY(sc.getSize().height/2);
                    setFinished();
                    System.exit(0);
                }
            }
        };
       Clock.addTask(scroll);
    }
    
}
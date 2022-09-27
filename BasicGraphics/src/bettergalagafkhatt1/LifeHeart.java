/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bettergalagafkhatt1;

import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;

/**
 *
 * @author Js4fs
 */
public class LifeHeart extends Items {

    public LifeHeart(SpriteComponent sc) {
        super(sc);
        initialPic = new Picture("heart.png");
        setPicture(initialPic);
    }

}

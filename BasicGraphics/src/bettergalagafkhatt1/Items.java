/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bettergalagafkhatt1;

import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import static bettergalagafkhatt1.BetterGalaga.allSprites;

/**
 *
 * @author Js4fs
 */
public class Items extends Sprite {

    public Picture initialPic;

    public Items(SpriteComponent sc) {
        super(sc);
        allSprites.add(this);
    }

    @Override
    public void setActive(boolean b) {
        if (isActive() == b) {
            return;
        }
        super.setActive(b);
    }

    @Override
    public void processEvent(SpriteCollisionEvent se) {
        setActive(false);
    }

    /**
     * Compares the Items sprite picture to another Items picture using the width and height of each picture
     * @param o the object which should be a picture
     * @return the Boolean value depending on if the height and the width equal each other
     */
    public boolean isPicture(Object o) { //compares a picture to another picture using the width and height of the pictures
        Picture that = (Picture) o;
        return this.getPicture().getWidth() == that.getWidth() && this.getPicture().getHeight() == that.getHeight();
    }
}

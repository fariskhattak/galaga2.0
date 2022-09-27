/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bettergalagafkhatt1;

import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import static bettergalagafkhatt1.BetterGalaga.allSprites;

/**
 *
 * @author Js4fs
 */
public class Enemies extends Sprite {

    public static int enemyCount = 0;
    public Picture[] dyingSequence;
    public int points;
    public Picture initialPic;
    public int lives;
    public int maxLives;
    public HealthBar hb;

    public Enemies(SpriteComponent sc) {
        super(sc);
        enemyCount++;
        allSprites.add(this);
    }

    @Override
    public void setActive(boolean b) {
        if (isActive() == b) {
            return;
        }
        if (b) {
            enemyCount++;
        } else {
            enemyCount--;
        }
        super.setActive(b);
    }
    
    public int totalEnemies(){
        return enemyCount;
    }

    /**
     * Compares the Enemies sprite picture to another Enemies picture using the
     * width and height of each picture
     *
     * @param o the object which should be a picture
     * @return the Boolean value depending on if the height and the width equal
     * each other
     */
    public boolean isPicture(Object o) { //compares a picture to another picture using the width and height of the pictures
        Picture that = (Picture) o;
        return this.getPicture().getWidth() == that.getWidth() && this.getPicture().getHeight() == that.getHeight();
    }
}

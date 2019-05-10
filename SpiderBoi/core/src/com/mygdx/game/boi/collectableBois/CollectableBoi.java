package com.mygdx.game.boi.collectableBois;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.boi.SpiderBoi;
import com.mygdx.game.interfaces.Interactable;

/** Super class of non-moving and collectable FlyBois.
 * @author JavaBoiz
 * @version 0.1 on 09.05.2019
 */

public abstract class CollectableBoi extends FlyBoi implements Interactable
{
    // properties
    SpriteBatch cBSpriteBatch;

    // constructors

    /**
     * This is the constructor for the CollectableBoi calls the
     * parent constructor, interactable interface and creates an
     * instance of cBSpriteBatch.
     */
    public CollectableBoi(String fileName, int x, int y)
    {
        super(fileName, x, y);
        cBSpriteBatch = new SpriteBatch();
    }

    // methods

    /**
     * This method draws the character by using the spriteBatch
     * begin(), draw(), and end() methods.
     */
    public void drawCharacter() {
        cBSpriteBatch.begin();
        cBSpriteBatch.draw(image, 0, 0);
        cBSpriteBatch.end();
    }

    public abstract void performInteraction(SpiderBoi spiderBoi);
}



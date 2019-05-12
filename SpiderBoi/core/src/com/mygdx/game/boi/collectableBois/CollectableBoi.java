package com.mygdx.game.boi.collectableBois;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpiderBoiGame;
import com.mygdx.game.interfaces.Interactable;
import com.mygdx.game.boi.FlyBoi;
import com.mygdx.game.boi.SpiderBoi;

/** Super class of non-moving and collectable FlyBois.
 * @author JavaBoiz
 * @version 0.1 on 09.05.2019
 */

public abstract class CollectableBoi
        extends FlyBoi
        implements Interactable
{

    // constructors
    /**
     * This is the constructor for the CollectableBoi calls the
     * parent constructor, interactable interface and creates an
     * instance of cBSpriteBatch.
     */
    public CollectableBoi(Texture texture, int x, int y)
    {
        super(texture, x, y);
    }

    // methods

    /**
     * It is an abstract method which performs certain interactions
     * depending on the type of the boi.
     * @param game is the SpiderBoiGame class' instance.
     */
    public abstract void performInteraction(SpiderBoiGame game);
}



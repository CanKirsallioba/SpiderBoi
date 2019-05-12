package com.mygdx.game.boi.collectableBois;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.SpiderBoiGame;
import com.mygdx.game.interfaces.Interactable;
import com.mygdx.game.boi.FlyBoi;

/** Super class of non-moving and collectable FlyBois.
 * @author JavaBoiz
 * @version 0.1 on 09.05.2019
 */

public abstract class CollectableBoi
        extends FlyBoi
        implements Interactable {

    //constructor(s)
    /**
     * Creates a CollectableBoi with given texture and coordinates.
     * @param texture The texture of the CollectableBoi.
     * @param x X-coordinate of CollectableBoi.
     * @param y Y-coordinate of CollectableBoi.
     */
    public CollectableBoi(Texture texture, int x, int y) {
        super(texture, x, y);
    }

    // methods

    /**
     * This method is an abstract method which performs certain interactions
     * depending on the type of the boi.
     * @param game is the SpiderBoiGame class' instance.
     */
    public abstract void performInteraction(SpiderBoiGame game);
}



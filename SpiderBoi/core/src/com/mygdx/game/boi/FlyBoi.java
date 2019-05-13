package com.mygdx.game.boi;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.SpiderBoiGame;

public abstract class FlyBoi
        extends Boi {

    /**
     * A constructor that creates a FlyBoi with given texture and coordinates.
     * @param image The texture of the FlyBoi.
     * @param x The X-coordinate of the Flyboi.
     * @param y The Y-coordinate of the FlyBoi.
     */
    public FlyBoi(Texture image, int x, int y) {
        super(image);
        setPosition(new Vector2(x, y));
    }

    /**
     * An abstract method which performs the interaction when a particular
     * Boi is collected.
     * @param game The game instance.
     */
    public abstract void performInteraction(SpiderBoiGame game);
}

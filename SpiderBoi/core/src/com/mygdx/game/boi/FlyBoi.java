package com.mygdx.game.boi;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.SpiderBoiGame;
import com.mygdx.game.boi.Boi;
import com.mygdx.game.boi.SpiderBoi;

public abstract class FlyBoi
        extends Boi {

    /**
     * This is the constructor of the flyboi class, it calls the super constructor and
     * sets the position.
     * @param image is the image of the flyboi.
     * @param x is the x coordinate of the flyboi.
     * @param y is the y coordinate of the flyboi.
     */
    public FlyBoi(Texture image, int x, int y) {
        super(image);
        setPosition(new Vector2(x, y));
    }

    /**
     * This is the abstract method of performing interaction,
     * every flyboi type performs a different interaction.
     * @param game
     */
    public abstract void performInteraction(SpiderBoiGame game);
}

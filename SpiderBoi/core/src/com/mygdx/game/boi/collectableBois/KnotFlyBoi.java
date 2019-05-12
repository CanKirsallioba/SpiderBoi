package com.mygdx.game.boi.collectableBois;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.SpiderBoiGame;

public class KnotFlyBoi
        extends CollectableBoi {

    //constructor(s)
    /**
     * This is the constructor for the KnotFlyBoi class.
     * Calls the parent constructor and initialises the position.
     * @param x is the initial x coordinate of the StoreFlyBoi.
     * @param y is the initial y coordinate of the StoreFlyBoi.
     */
    public KnotFlyBoi(int x, int y)
    {
        super(new Texture("knotFlyBoi.png"), x, y);
    }

    //methods
    /**
     * A method which performs the interaction when a KnotFlyBoi is collected.
     * @param game The game instance.
     */
    @Override
    public void performInteraction(SpiderBoiGame game) {
        if (game.getSpiderBoi().getBoundary().overlaps(getBoundary())) {
            setPresent(false);
            game.getSilk().removeKnot();
        }
    }
}

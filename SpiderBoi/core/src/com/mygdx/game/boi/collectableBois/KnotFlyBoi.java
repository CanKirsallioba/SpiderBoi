package com.mygdx.game.boi.collectableBois;

import com.mygdx.game.boi.SpiderBoi;

public class KnotFlyBoi extends CollectableBoi {
    /**
     * This is the constructor for the KnotFlyBoi class.
     * Calls the parent constructor and initialises the position.
     * @param x is the initial x coordinate of the StoreFlyBoi
     * @param y is the initial y coordinate of the StoreFlyBoi
     */
    public KnotFlyBoi(int x, int y)
    {
        super("knotFlyBoi.png", x, y);
    }

    // methods
    @Override
    public void performInteraction(SpiderBoi spiderBoi)
    {
        if (spiderBoi.getBoundary().overlaps(getBoundary()))
        {
            setPresent(false);
            //todo
        }
    }
}

package com.mygdx.game.boi.collectableBois;

import com.mygdx.game.boi.SpiderBoi;

public class ImmunityFlyBoi extends CollectableBoi {
    /**
     * This is the constructor for the ImmunityFlyBoi class.
     * Calls the parent constructor and initialises the position.
     * @param x is the initial x coordinate of the StoreFlyBoi
     * @param y is the initial y coordinate of the StoreFlyBoi
     */
    public ImmunityFlyBoi(int x, int y)
    {
        super("immunityFlyBoi.png", x, y);
    }

    //methods
    @Override
    public void performInteraction(SpiderBoi spiderBoi)
    {
        //todo
    }
}

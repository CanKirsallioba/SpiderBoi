package com.mygdx.game.boi.collectableBois;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.boi.SpiderBoi;
import com.mygdx.game.boi.collectableBois.*;

/** FlyBois that can be collected and used to purchase skins and backgrounds in the store.
 * @author JavaBoiz
 * @version v0.1 on 05.05.2019
 */
public class StoreFlyBoi extends CollectableBoi
{
    static int totalFlyBoi = 0; //Need to get total flybois. Somehow...
    // constructors
    /**
     * This is the constructor for the StoreFlyBoi class.
     * Calls the parent constructor and initialises the position.
     * @param x is the initial x coordinate of the StoreFlyBoi
     * @param y is the initial y coordinate of the StoreFlyBoi
     */
    public StoreFlyBoi(int x, int y)
    {
        super("flyBoi.png", x, y);
    }

    // methods
    @Override
    public void performInteraction(SpiderBoi spiderBoi)
    {
        if (spiderBoi.getBoundary().overlaps())
    }

    /**
     * This method gets the total flyBoi count for the store.
     * @return the total number of the flyBoi's.
     */
    public static int getTotalFlyBoi(){
        return totalFlyBoi;
    }
}


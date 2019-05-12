package com.mygdx.game.boi.collectableBois;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.SpiderBoiGame;
import com.mygdx.game.boi.SpiderBoi;
import com.mygdx.game.boi.collectableBois.*;
import com.mygdx.game.menu.store.Store;

/** FlyBois that can be collected and used to purchase skins and backgrounds in the store.
 * @author JavaBoiz
 * @version v0.1 on 05.05.2019
 */
public class StoreFlyBoi
        extends CollectableBoi
{
    // constructors
    /**
     * This is the constructor for the StoreFlyBoi class.
     * Calls the parent constructor and initialises the position.
     * @param x is the initial x coordinate of the StoreFlyBoi
     * @param y is the initial y coordinate of the StoreFlyBoi
     */
    public StoreFlyBoi(int x, int y)
    {
        super(new Texture("storeFlyBoi.png"), x, y);
    }

    // methods

    /**
     * This method is the interaction provider method for the store fly boi,
     * it increments the general flyboi count.
     * @param game is the SpiderBoiGame class' instance.
     */
    @Override
    public void performInteraction(SpiderBoiGame game)
    {
        if (game.getSpiderBoi().getBoundary().overlaps(getBoundary()))
        {
            setPresent(false);
            Store.incrementTotalFlyBoi();
        }
    }
}


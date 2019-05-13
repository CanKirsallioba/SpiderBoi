package com.mygdx.game.menu.store;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.boi.*;
import com.mygdx.game.boi.collectableBois.StoreFlyBoi;

/**
 * SpiderBoi skin class
 * @author JavaBoiz
 * @version 1.0 on 08.05.2019
 */

public class SpiderBoiSkin {

    //properties
    Texture[] directions = new Texture[4]; //down, right, up, left
    int cost;

    /**
     * A constructor that creates a SpiderBoiSkin
     * @param fileNames four directions of the SpiderBoiSkin (down, right, up, left)
     * @param cost the market cost of the SpiderBoiSkin
     */
    public SpiderBoiSkin(String[] fileNames, int cost) {
        for (int index = 0; index < fileNames.length; index++)
            directions[index] = new Texture(fileNames[index]);

        this.cost = cost;
    }

    /**
     * This method returns the down-oriented image of the SpiderBoi.
     * @return The down-oriented image of the SpiderBoi.
     */
    public Texture getDownSpiderBoi() {
        return directions[0];
    }

    /**
     * This method returns the right-oriented image of the SpiderBoi.
     * @return The right-oriented image of the SpiderBoi.
     */
    public Texture getRightSpiderBoi() {
        return directions[1];
    }

    /**
     * This method returns the up-oriented image of the SpiderBoi.
     * @return The up-oriented image of the SpiderBoi.
     */
    public Texture getUpSpiderBoi() {
        return directions[2];
    }

    /**
     * This method returns the left-oriented image of the SpiderBoi.
     * @return The left-oriented image of the SpiderBoi.
     */
    public Texture getLeftSpiderBoi() {
        return directions[3];
    }

    /**
     * Checks whether the SpiderBoiSkin can be unlocked
     * @return true if the SpiderBoiSkin there are
     * sufficient StoreFlyBois to unlock, false otherwise
     */
    public boolean canUnlock() {
        if (Store.getTotalFlyBoi() >= cost) {
            return true;
        }

        return false;
    }

    /**
     * Returns the cost of the SpiderBoiSkin
     * @return the cost of the SpiderBoiSkin
     */
    public int getCost() {
        return cost;
    }
}

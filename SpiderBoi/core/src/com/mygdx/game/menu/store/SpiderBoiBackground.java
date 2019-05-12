package com.mygdx.game.menu.store;


import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.boi.collectableBois.StoreFlyBoi;

/**
 * SpiderBoi background class
 * @author JavaBoiz
 * @version 1.0 on 08.05.2019
 */

public class SpiderBoiBackground
        extends Texture {

    //properties
    private boolean isUnlocked;
    private int cost;

    //constructors
    /**
     * A constructor that creates a SpiderBoiBackground
     * @param fileName the filename of the SpiderBoiBackground
     * @param cost the market cost of the SpiderBoiBackground
     */
    public SpiderBoiBackground(String fileName, int cost) {
        super(fileName);
        this.cost = cost;
    }

    //methods
    /**
     * Checks whether the SpiderBoiBackground can be unlocked
     * @return true if the SpiderBoiBackground there are
     * sufficient StoreFlyBois to unlock, false otherwise
     */
    public boolean canUnlock() {
        if (Store.getTotalFlyBoi() >= cost) {
            return true;
        }

        return false;
    }

    /**
     * Returns the cost of the SpiderBoiBackground
     * @return the cost of the SpiderBoiBackground
     */
    public int getCost() {
        return cost;
    }
}

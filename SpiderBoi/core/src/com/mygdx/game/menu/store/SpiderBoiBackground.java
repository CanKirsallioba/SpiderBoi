package com.mygdx.game.menu.store;


import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.boi.collectableBois.StoreFlyBoi;

/**
 * SpiderBoi skin class
 * @author JavaBoiz
 * @version 1.0 on 08.05.2019
 */

public class SpiderBoiBackground
        extends Texture {

    //properties
    boolean isUnlocked;
    int cost;

    public SpiderBoiBackground(String fileName, int cost) {
        super(fileName);
        this.cost = cost;
    }

    public boolean canUnlock() {
        if (Store.getTotalFlyBoi() >= cost) {
            return true;
        }

        return false;
    }

    public int getCost() {
        return cost;
    }
}

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
    Texture[] directions; //down, right, up, left
    boolean isUnlocked;
    int cost;

    public SpiderBoiSkin(String[] fileNames, int cost) {
        for (int index = 0; index < fileNames.length; index++)
            directions[index] = new Texture(fileNames[index]);

        isUnlocked = false;
        this.cost = cost;
    }

    public boolean canUnlock() {
        if (StoreFlyBoi.getTotalFlyBoi() >= cost && !isUnlocked) {
            return true;
        }

        return false;
    }

    public void unlock() {
        isUnlocked = true;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public int getCost() {
        return cost;
    }
}

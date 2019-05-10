package com.mygdx.game.menu.store;

import com.mygdx.game.boi.collectableBois.CollectableBoi;
import com.mygdx.game.boi.collectableBois.StoreFlyBoi;

import java.util.ArrayList;

public class Store {
    //Properties
    ArrayList<SpiderBoiSkin> spiderBoiSkinList;
    ArrayList<SpiderBoiBackground> spiderBoiBackgroundList;
    ArrayList<SpiderBoiSkin> spiderBoiSkinUnlockedList;
    ArrayList<SpiderBoiBackground> spiderBoiBackgroundUnlockedList;
    SpiderBoiSkin selectedSpiderBoiSkin;
    SpiderBoiBackground selectedSpiderBoiBackground;
    StoreFlyBoi storeFlyBoi;
    SpiderBoiSkin spiderBoiSkin;
    int remainingFlyBoi;

    //Methods
    public void buySBSkin(int index) {
        SpiderBoiSkin target = spiderBoiSkinList.get(index);
        if (!target.isUnlocked()) {
            if (target.canUnlock()) {
                remainingFlyBoi = storeFlyBoi.getTotalFlyBoi();
                remainingFlyBoi = remainingFlyBoi - target.getCost();
                target.unlock();
                selectedSpiderBoiSkin = target;
                spiderBoiSkinUnlockedList.add(target);
            }
        }
    }

    public void buySBBackground(int index) {
        SpiderBoiBackground target = spiderBoiBackgroundList.get(index);
        if (!target.isUnlocked()) {
            if (target.canUnlock()) {
                remainingFlyBoi = storeFlyBoi.getTotalFlyBoi();
                remainingFlyBoi = remainingFlyBoi - target.getCost();
                target.unlock();
                selectedSpiderBoiBackground = target;
                spiderBoiBackgroundUnlockedList.add(target);
            }
        }
    }
}

package com.mygdx.game.menu.store;

import com.mygdx.game.boi.collectableBois.CollectableBoi;

import java.util.ArrayList;

public class Store {
    //Properties
    ArrayList<SpiderBoiSkin> spiderBoiSkinList;
    ArrayList<SpiderBoiBackground> spiderBoiBackgroundList;
    ArrayList<SpiderBoiSkin> spiderBoiSkinUnlockedList;
    ArrayList<SpiderBoiBackground> spiderBoiBackgroundUnlockedList;
    SpiderBoiSkin selectedSpiderBoiSkin;
    SpiderBoiBackground selectedSpiderBoiBackground;
    CollectableBoi collectableBoi;
    SpiderBoiSkin spiderBoiSkin;
    int remainingFlyBoi;

    //Methods
    public void buySBSkin(int index) {
        SpiderBoiSkin target = spiderBoiSkinList.get(index);
        if (!target.isUnlocked()) {
            if (target.canUnlock()) {
                remainingFlyBoi = collectableBoi.getTotalFlyBoi();
                remainingFlyBoi = remainingFlyBoi - target.getCost();
                target.unlock();
                spiderBoiSkinUnlockedList.add(target);
            }
        }

    }

    public void buySBBackground(int index) {
        SpiderBoiBackground target = spiderBoiBackgroundList.get(index);
        if (!target.isUnlocked()) {
            if (target.canUnlock()) {
                remainingFlyBoi = collectableBoi.getTotalFlyBoi();
                remainingFlyBoi = remainingFlyBoi - target.getCost();
                target.unlock();
                spiderBoiBackgroundUnlockedList.add(target);
            }
        }


    }
}

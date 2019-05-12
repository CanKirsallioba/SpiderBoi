package com.mygdx.game.menu.store;

import com.mygdx.game.boi.collectableBois.CollectableBoi;
import com.mygdx.game.boi.collectableBois.StoreFlyBoi;

import java.util.ArrayList;

public class Store {

    //Properties
    static int totalFlyBoi;
    static final ArrayList<SpiderBoiSkin> spiderBoiSkinList = new ArrayList<SpiderBoiSkin>();
    static final String[] defaultSkin = {"SpiderBD.png", "SpiderBR.png", "SpiderBU.png", "SpiderBL.png"};
    static final ArrayList<SpiderBoiBackground> spiderBoiBackgroundList = new ArrayList<SpiderBoiBackground>();
    static final String defaultBackground = "background.png";
    private ArrayList<SpiderBoiSkin> spiderBoiSkinUnlockedList;
    private ArrayList<SpiderBoiBackground> spiderBoiBackgroundUnlockedList;
    private SpiderBoiSkin selectedSpiderBoiSkin;
    private SpiderBoiBackground selectedSpiderBoiBackground;

    public Store() {
        spiderBoiSkinUnlockedList = new ArrayList<SpiderBoiSkin>();
        spiderBoiSkinUnlockedList.add(new SpiderBoiSkin(defaultSkin, 0));
        spiderBoiBackgroundUnlockedList = new ArrayList<SpiderBoiBackground>();
        spiderBoiBackgroundUnlockedList.add(new SpiderBoiBackground(defaultBackground, 0));
        selectedSpiderBoiSkin = spiderBoiSkinUnlockedList.get(0);
        selectedSpiderBoiBackground = spiderBoiBackgroundUnlockedList.get(0);
    }

    //Methods
    /**
     * This method gets the total flyBoi count for the store.
     * @return the total number of the flyBoi's.
     */
    public static int getTotalFlyBoi() {
        return totalFlyBoi;
    }

    public static void setTotalFlyBoi(int setTo) { totalFlyBoi = setTo; }

    public static void incrementTotalFlyBoi() { totalFlyBoi++; }

    public ArrayList<SpiderBoiSkin> getSpiderBoiSkinUnlockedList() {
        return spiderBoiSkinUnlockedList;
    }

    public void setSpiderBoiSkinUnlockedList(ArrayList<SpiderBoiSkin> spiderBoiSkinUnlockedList) {
        this.spiderBoiSkinUnlockedList = spiderBoiSkinUnlockedList;
    }

    public ArrayList<SpiderBoiBackground> getSpiderBoiBackgroundUnlockedList() {
        return spiderBoiBackgroundUnlockedList;
    }

    public void setSpiderBoiBackgroundUnlockedList(ArrayList<SpiderBoiBackground> spiderBoiBackgroundUnlockedList) {
        this.spiderBoiBackgroundUnlockedList = spiderBoiBackgroundUnlockedList;
    }

    public SpiderBoiSkin getSelectedSpiderBoiSkin() {
        return selectedSpiderBoiSkin;
    }

    public void setSelectedSpiderBoiSkin(SpiderBoiSkin selectedSpiderBoiSkin) {
        this.selectedSpiderBoiSkin = selectedSpiderBoiSkin;
    }

    public SpiderBoiBackground getSelectedSpiderBoiBackground() {
        return selectedSpiderBoiBackground;
    }

    public void setSelectedSpiderBoiBackground(SpiderBoiBackground selectedSpiderBoiBackground) {
        this.selectedSpiderBoiBackground = selectedSpiderBoiBackground;
    }

    public void buySBSkin(int index) {
        SpiderBoiSkin target = spiderBoiSkinList.get(index);
        if (!target.isUnlocked()) {
            if (target.canUnlock()) {
                totalFlyBoi = totalFlyBoi - target.getCost();
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
                totalFlyBoi = totalFlyBoi - target.getCost();
                target.unlock();
                selectedSpiderBoiBackground = target;
                spiderBoiBackgroundUnlockedList.add(target);
            }
        }
    }
}

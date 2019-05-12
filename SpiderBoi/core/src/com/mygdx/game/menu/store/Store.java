package com.mygdx.game.menu.store;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.game.SpiderBoiGame;
import com.mygdx.game.boi.collectableBois.CollectableBoi;
import com.mygdx.game.boi.collectableBois.StoreFlyBoi;

import java.util.ArrayList;

public class Store {

    //Properties
    static final String[] defaultSkin = {"SpiderBD.png", "SpiderBR.png", "SpiderBU.png", "SpiderBL.png"};

    static final String defaultBackground = "background.png";
    static final String sunsetBackground = "background2.png";
    static final String arcticBackground = "backgroundSpiked3.png";
    static final String cityBackground = "background4.png";

    static int totalFlyBoi;
    static ArrayList<SpiderBoiSkin> spiderBoiSkinList;
    static ArrayList<SpiderBoiBackground> spiderBoiBackgroundList;
    private ArrayList<SpiderBoiSkin> spiderBoiSkinUnlockedList;
    private ArrayList<SpiderBoiBackground> spiderBoiBackgroundUnlockedList;
    private SpiderBoiSkin selectedSpiderBoiSkin;
    private SpiderBoiBackground selectedSpiderBoiBackground;

    public Store() {
        //fixed code
        spiderBoiBackgroundList = new ArrayList<SpiderBoiBackground>();
        spiderBoiSkinList = new ArrayList<SpiderBoiSkin>();
        spiderBoiSkinUnlockedList = new ArrayList<SpiderBoiSkin>();
        spiderBoiBackgroundUnlockedList = new ArrayList<SpiderBoiBackground>();

        spiderBoiSkinList.add(new SpiderBoiSkin(defaultSkin, 0));

        spiderBoiBackgroundList.add(new SpiderBoiBackground(defaultBackground, 0));
        spiderBoiBackgroundList.add(new SpiderBoiBackground(sunsetBackground, 5));
        spiderBoiBackgroundList.add(new SpiderBoiBackground(arcticBackground, 10));
        spiderBoiBackgroundList.add(new SpiderBoiBackground(cityBackground, 15));

        spiderBoiSkinUnlockedList.add(spiderBoiSkinList.get(0));
        spiderBoiBackgroundUnlockedList.add(spiderBoiBackgroundList.get(0));

        for (int index = 0; index < SpiderBoiGame.getSavedState().getString("unlockedSkins").length(); index++) {
            spiderBoiSkinUnlockedList.add(spiderBoiSkinList.get(Integer.parseInt(SpiderBoiGame.getSavedState().getString("unlockedSkins").substring(index, index + 1))));
        }
        for (int index = 0; index < SpiderBoiGame.getSavedState().getString("unlockedBackgrounds").length(); index++) {
            spiderBoiBackgroundUnlockedList.add(spiderBoiBackgroundList.get(Integer.parseInt(SpiderBoiGame.getSavedState().getString("unlockedBackgrounds").substring(index, index + 1))));
        }
        selectedSpiderBoiSkin = spiderBoiSkinList.get(SpiderBoiGame.getSavedState().getInteger("selectedSkin"));
        selectedSpiderBoiBackground = spiderBoiBackgroundUnlockedList.get(SpiderBoiGame.getSavedState().getInteger("selectedBackground"));
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

    public String getUnlockedSkinData() {
        String s = "";
        for (int index = 0; index < spiderBoiSkinList.size(); index++) {
            if (spiderBoiSkinUnlockedList.contains(spiderBoiSkinList.get(index))) {
                s += index;
            }
        }

        return s;
    }

    public String getUnlockedBackgroundData() {
        String s = "";
        for (int index = 0; index < spiderBoiBackgroundList.size(); index++) {
            if (spiderBoiBackgroundUnlockedList.contains(spiderBoiBackgroundList.get(index))) {
                s += index;
            }
        }

        return s;
    }

    public int selectSkin(int index) {
        if (index >= 0 && index < spiderBoiSkinList.size()) {
            if (spiderBoiSkinUnlockedList.contains(spiderBoiSkinList.get(index)))
                selectedSpiderBoiSkin = spiderBoiSkinList.get(index);
                return index;
        }

        return -1;
    }

    public int selectBackground(int index) {
        if (index >= 0 && index < spiderBoiBackgroundList.size()) {
            if (spiderBoiBackgroundUnlockedList.contains(spiderBoiBackgroundList.get(index)))
                selectedSpiderBoiBackground = spiderBoiBackgroundList.get(index);
            return index;
        }

        return -1;
    }

    public int indexOfSelectedSkin() {
        for (int index = 0; index < spiderBoiSkinList.size(); index++) {
            if (selectedSpiderBoiSkin == spiderBoiSkinList.get(index)) {
                return index;
            }
        }

        return -1;
    }

    public int indexOfSelectedBackground() {
        for (int index = 0; index < spiderBoiBackgroundList.size(); index++) {
            if (selectedSpiderBoiBackground == spiderBoiBackgroundList.get(index)) {
                return index;
            }
        }

        return -1;
    }

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

    public int buySkin(int index) {
        SpiderBoiSkin target = spiderBoiSkinList.get(index);
        if (!spiderBoiSkinUnlockedList.contains(target)) {
            if (target.canUnlock()) {
                totalFlyBoi = totalFlyBoi - target.getCost();
                selectedSpiderBoiSkin = target;
                spiderBoiSkinUnlockedList.add(target);

                return index;
            }
        }
        return -1;
    }

    public int buyBackground(int index) {
        if (index >= 0 && index < spiderBoiBackgroundList.size()) {
            SpiderBoiBackground target = spiderBoiBackgroundList.get(index);
            if (!spiderBoiBackgroundUnlockedList.contains(target)) {
                if (target.canUnlock()) {
                    totalFlyBoi = totalFlyBoi - target.getCost();
                    selectedSpiderBoiBackground = target;
                    spiderBoiBackgroundUnlockedList.add(target);

                    return index;
                }
            }
        }
        return -1;
    }
}

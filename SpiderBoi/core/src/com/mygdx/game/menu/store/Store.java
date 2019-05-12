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

    /**
     * A constructor that creates an instance of store
     * @param savedState the saved data of unlocked skins, backgrounds and selected skin, background
     */
    public Store(Preferences savedState) {
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
            spiderBoiSkinUnlockedList.add(spiderBoiSkinList.get(Integer.parseInt(savedState.getString("unlockedSkins").substring(index, index + 1))));
        }
        for (int index = 0; index < SpiderBoiGame.getSavedState().getString("unlockedBackgrounds").length(); index++) {
            spiderBoiBackgroundUnlockedList.add(spiderBoiBackgroundList.get(Integer.parseInt(savedState.getString("unlockedBackgrounds").substring(index, index + 1))));
        }
        selectedSpiderBoiSkin = spiderBoiSkinList.get(savedState.getInteger("selectedSkin"));
        selectedSpiderBoiBackground = spiderBoiBackgroundUnlockedList.get(savedState.getInteger("selectedBackground"));
    }

    //Methods
    /**
     * This method gets the total flyBoi count for the store.
     * @return the total number of the flyBoi's.
     */
    public static int getTotalFlyBoi() { return totalFlyBoi; }

    /**
     * This method sets the total number of StoreFlyBois to the given amount
     * @param setTo New total number of StoreFlyBois
     */
    public static void setTotalFlyBoi(int setTo) { totalFlyBoi = setTo; }

    /**
     * This method increments the total number of StoreFlyBois by one
     */
    public static void incrementTotalFlyBoi() { totalFlyBoi++; }

    /**
     * This method returns unlocked SpiderBoiSkins' indexes concatenated into a String
     * @return Unlocked SpiderBoiSkins' indexes
     */
    public String getUnlockedSkinData() {
        String s = "";
        for (int index = 0; index < spiderBoiSkinList.size(); index++) {
            if (spiderBoiSkinUnlockedList.contains(spiderBoiSkinList.get(index))) {
                s += index;
            }
        }

        return s;
    }

    /**
     * This method returns unlocked SpiderBoiBackgrounds' indexes concatenated into a String
     * @return Unlocked SpiderBoiBackgrounds' indexes
     */
    public String getUnlockedBackgroundData() {
        String s = "";
        for (int index = 0; index < spiderBoiBackgroundList.size(); index++) {
            if (spiderBoiBackgroundUnlockedList.contains(spiderBoiBackgroundList.get(index))) {
                s += index;
            }
        }

        return s;
    }

    /**
     * This method selects a skin at a given index of SpiderBoiSkinList
     * @param index the location of a specific SpiderBoiSkin
     * @return a boolean indicating whether the SpiderBoiSkin can be selected or not
     */
    public boolean selectSkin(int index) {
        if (index >= 0 && index < spiderBoiSkinList.size()) {
            if (spiderBoiSkinUnlockedList.contains(spiderBoiSkinList.get(index)))
                selectedSpiderBoiSkin = spiderBoiSkinList.get(index);
                return true;
        }

        return false;
    }

    /**
     * This method selects a skin at a given index of SpiderBoiBackgroundList.
     * @param index the location of a specific SpiderBoiBackground
     * @return a boolean indicating whether the SpiderBoiBackground can be selected or not
     */
    public boolean selectBackground(int index) {
        if (index >= 0 && index < spiderBoiBackgroundList.size()) {
            if (spiderBoiBackgroundUnlockedList.contains(spiderBoiBackgroundList.get(index)))
                selectedSpiderBoiBackground = spiderBoiBackgroundList.get(index);
            return true;
        }

        return false;
    }

    /**
     * This method returns the index of the selected SpiderBoiSkin in order to properly save it.
     * @return the index of the selected SpiderBoiSkin, -1 if the SpiderBoiSkin is not selectable
     */
    public int indexOfSelectedSkin() {
        for (int index = 0; index < spiderBoiSkinList.size(); index++) {
            if (selectedSpiderBoiSkin == spiderBoiSkinList.get(index)) {
                return index;
            }
        }

        return -1;
    }

    /**
     * This method returns the index of the selected SpiderBoiBackground in order to properly save it.
     * @return the index of the selected skin, -1 if the SpiderBoiBackground is not selectable
     */
    public int indexOfSelectedBackground() {
        for (int index = 0; index < spiderBoiBackgroundList.size(); index++) {
            if (selectedSpiderBoiBackground == spiderBoiBackgroundList.get(index)) {
                return index;
            }
        }

        return -1;
    }

    /**
     * This method returns the collection of unlocked SpiderBoiSkins.
     * @return Collection of unlocked SpiderBoiSkins
     */
    public ArrayList<SpiderBoiSkin> getSpiderBoiSkinUnlockedList() {
        return spiderBoiSkinUnlockedList;
    }

    /**
     * This method sets the collection of unlocked SpiderBoiSkins to the given collection.
     * @param spiderBoiSkinUnlockedList The collection to set to
     */
    public void setSpiderBoiSkinUnlockedList(ArrayList<SpiderBoiSkin> spiderBoiSkinUnlockedList) {
        this.spiderBoiSkinUnlockedList = spiderBoiSkinUnlockedList;
    }

    /**
     * This method returns the collection of unlocked SpiderBoiBackgrounds.
     * @return Collection of unlocked SpiderBoiBackgrounds
     */
    public ArrayList<SpiderBoiBackground> getSpiderBoiBackgroundUnlockedList() {
        return spiderBoiBackgroundUnlockedList;
    }

    /**
     * This method sets the collection of unlocked SpiderBoiBackgrounds to the given collection.
     * @param spiderBoiBackgroundUnlockedList The collection to set to
     */
    public void setSpiderBoiBackgroundUnlockedList(ArrayList<SpiderBoiBackground> spiderBoiBackgroundUnlockedList) {
        this.spiderBoiBackgroundUnlockedList = spiderBoiBackgroundUnlockedList;
    }

    /**
     * This method returns the selected SpiderBoiSkin.
     * @return selected SpiderBoiSkin
     */
    public SpiderBoiSkin getSelectedSpiderBoiSkin() {
        return selectedSpiderBoiSkin;
    }

    /**
     * This method sets the selected SpiderBoiSkin to the given SpiderBoiSkin.
     * @param selectedSpiderBoiSkin The SpiderBoiSkin to set to
     */
    public void setSelectedSpiderBoiSkin(SpiderBoiSkin selectedSpiderBoiSkin) {
        this.selectedSpiderBoiSkin = selectedSpiderBoiSkin;
    }

    /**
     * This method returns the selected SpiderBoiBackground.
     * @return selected SpiderBoiBackground
     */
    public SpiderBoiBackground getSelectedSpiderBoiBackground() {
        return selectedSpiderBoiBackground;
    }

    /**
     * This method sets the selected SpiderBoiBackground to the given SpiderBoiSkin.
     * @param selectedSpiderBoiBackground The SpiderBoiBackground to set to
     */
    public void setSelectedSpiderBoiBackground(SpiderBoiBackground selectedSpiderBoiBackground) {
        this.selectedSpiderBoiBackground = selectedSpiderBoiBackground;
    }

    /**
     * This method buys a SpiderBoiSkin at a given location.
     * @param index The location of a particular SpiderBoiSkin to buy
     * @return The index of the SpiderBoiSkin which is purchased, -1 if cannot but the SpiderBoiSkin
     */
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

    /**
     * This method buys a SpiderBoiBackground at a given location.
     * @param index The location of a particular SpiderBoiBackground to buy
     * @return The index of the SpiderBoiBackground which is purchased, -1 if cannot but the SpiderBoiBackground
     */
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

package com.mygdx.game;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class Cell extends Sprite {
    public boolean isStoreFlyBoi;
    public boolean isDragonFlyBoi;
    public boolean isKnotFlyBoi;
    public boolean isObstacle;
    public boolean isEmptyCell;
    public boolean isFinishLine;
    public boolean isSpiderBoi;
    public boolean isSpawnLocation;
    public boolean isSlippery;
    public boolean isSpiked;
    public boolean isKeyFlyBoi;


    private ArrayList<Cell> cells;
    private Texture img;

    AssetManager manager = new AssetManager();


    public Cell(Texture img){
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++ ) {
                Pixmap pixmap = img.getTextureData().consumePixmap();
                Color color = new Color(pixmap.getPixel(i, j));
                int r = (int)(color.r * 255f);
                int g = (int)(color.g * 255f);
                int b = (int)(color.b * 255f);

                isStoreFlyBoi = false;
                isDragonFlyBoi = false;
                isKnotFlyBoi = false;
                isObstacle = false;
                isEmptyCell = false;
                isFinishLine = false;
                isSpiderBoi = false;
                isSpawnLocation = false;
                isSlippery = false;
                isSpiked = false;
                isKeyFlyBoi = false;

                if (r == 178 && g == 0 && b == 255) {
                    //spiderBoi
                    isSpiderBoi = true;
                }
                if (r == 0 && g == 255 && b == 33) {
                    //spawn location
                    isSpawnLocation = true;
                }
                if (r == 0 && g == 255 && b == 255) {
                    //finish line
                    isFinishLine = true;
                }
                if (r == 0 && g == 0 && b == 0) {
                    //obstacle
                    isObstacle = true;
                }
                if (r == 255 && g == 255 && b == 255) {
                    //empty cell
                    isEmptyCell = true;
                }
                if (r == 164 && g == 198 && b == 77) {
                    //store flyBoi
                    isStoreFlyBoi = true;
                }
                if (r == 255 && g == 0 && b == 0) {
                    //dragonFlyBoi
                    isDragonFlyBoi = true;
                }
                if (r == 255 && g == 216 && b == 0) {
                    //slippery
                    isSlippery = true;
                }
                if (r == 255 && g == 106 && b == 0) {
                    //spiked
                    isSpiked = true;
                }
                if (r == 12 && g == 105 && b == 66) {
                    //knot flyBoi
                    isKnotFlyBoi = true;
                }
                if (r == 0 && g == 19 && b == 127) {
                    //keyFlyBoi
                    isKeyFlyBoi = true;
                }
            }
        }





    }

}

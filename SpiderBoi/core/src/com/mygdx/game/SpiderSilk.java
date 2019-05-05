package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class SpiderSilk {

    //properties

    private ArrayList<Vector2> silkCoords;
    protected int knotCount;
    private SpiderBoi spiderBoi;
    Texture silk;


    public SpiderSilk (SpiderBoi spB) {
        spiderBoi = spB;
        knotCount = 0;
        silk = new Texture("silk10.png");
        silkCoords = new ArrayList<Vector2>();
    }

    //methods

    public ArrayList<Vector2> getSilkCoords() {
        return silkCoords;
    }

    public void addSilkCoords()
    {
        silkCoords.add(spiderBoi.getPosition());
    }

    public int checkKnot () {

        for (int i = 0; i < silkCoords.size() ; i++) {
            if (silkCoords.get(i) == spiderBoi.getPosition()) {
                knotCount ++;
            }
        }
        return knotCount;
    }

    public void drawSilk(SpriteBatch batch, float x, float y)
    {
        batch.draw(silk, x, y);
    }
}

package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.boi.*;


import java.util.ArrayList;

public class SpiderSilk {

    //properties

    private int knotCount;
    private SpiderBoi spiderBoi;
    private ShapeRenderer shapeRenderer;
    ArrayList <Rectangle> silkList;

    /**
     * Constructor for the SpiderSilk class initialises the spiderBoi, knotcount, shaperenderer, and silklist.
     * @param spB is the spiderBoi main character of the game, which will be used for the location in this class.
     */
    public SpiderSilk (SpiderBoi spB) {
        spiderBoi = spB;
        knotCount = 0;
        shapeRenderer = new ShapeRenderer();
        silkList = new ArrayList <Rectangle>();
    }

    //methods



    public int checkKnot () {
        int count = 0;
        for (int i = 0; i < silkList.size() - 1; i++) {
            for (int j = 0; j < silkList.size() - 1; j++) {
                if (i != j + 1 || i != j - 1) {
                    if (silkList.get(i).overlaps(silkList.get(j))) {
                        count++;
                    }
                }
            }
        }
        return count - 1;
    }

    /**
     * This method draws the silk coming out of the spiderBoi, taking the endpoint as th emiddle of the spiderBoi image
     * it stops drawing when it reaches an obstacle, so when it's velocity is zero.
     */
    public void drawSilk()
    {
        if(spiderBoi.isOnObstacle())
        {
            spiderBoi.getStopLocations().add(spiderBoi.getPosition().cpy().add(spiderBoi.getHalfSize()));
            spiderBoi.setOnObstacle(false);
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);


        if(spiderBoi.getStopLocations().size() > 1) {
            for (int i = 0; i < spiderBoi.getStopLocations().size() - 1; i++) {
                shapeRenderer.rectLine(spiderBoi.getStopLocations().get(i), spiderBoi.getStopLocations().get(i + 1), 10);

                if (spiderBoi.getStopLocations().get(i).y == spiderBoi.getStopLocations().get(i + 1).y)
                    silkList.add(new Rectangle(spiderBoi.getStopLocations().get(i).x, spiderBoi.getStopLocations().get(i).y,
                            Math.abs(spiderBoi.getStopLocations().get(i + 1).x - spiderBoi.getStopLocations().get(i).x), 10));

                else if (spiderBoi.getStopLocations().get(i).x == spiderBoi.getStopLocations().get(i + 1).x) {
                    silkList.add(new Rectangle(spiderBoi.getStopLocations().get(i).x, spiderBoi.getStopLocations().get(i).y,
                            10, Math.abs(spiderBoi.getStopLocations().get(i + 1).y - spiderBoi.getStopLocations().get(i).y)));
                }
            }
        }



        shapeRenderer.rectLine(spiderBoi.getStopLocations().get(spiderBoi.getStopLocations().size() - 1),
                spiderBoi.getPosition().cpy().add(spiderBoi.getHalfSize()), 10);
        shapeRenderer.end();


    }
}

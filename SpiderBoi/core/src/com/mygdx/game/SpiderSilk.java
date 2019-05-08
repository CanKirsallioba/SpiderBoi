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

    private ArrayList<Vector2> silkCoords;
    protected int knotCount;
    private SpiderBoi spiderBoi;
    Texture silk;
    private ShapeRenderer shapeRenderer;


    public SpiderSilk (SpiderBoi spB) {
        spiderBoi = spB;
        knotCount = 0;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
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

    public void drawSilk()
    {
        if(spiderBoi.getVelocity().x == 0 && spiderBoi.getVelocity().y == 0)
        {
            spiderBoi.setOnObstacle(true);
            spiderBoi.addStopLocations(spiderBoi.getPosition());
        }
        else {
            spiderBoi.setOnObstacle(false);
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 1);

        if(spiderBoi.getStopLocations().size() > 2) {
            for (int i = 0; i < spiderBoi.getStopLocations().size() - 1; i++) {
                shapeRenderer.rectLine(spiderBoi.getStopLocations().get(i), spiderBoi.getStopLocations().get(i + 1), 10);
            }
        }


        shapeRenderer.rectLine(spiderBoi.getStopLocations().get(spiderBoi.getStopLocations().size() - 1),
                spiderBoi.getPosition(), 10);
        shapeRenderer.end();
    }
}

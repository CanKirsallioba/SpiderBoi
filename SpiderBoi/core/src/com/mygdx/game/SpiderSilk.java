package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.boi.*;


import java.util.ArrayList;
import static java.lang.Math.min;

 /**Spider silk class. SpiderBoi leaves a trail of SpiderSilk behind him when moving.
  * @author JavaBoiz
  * @version 13.05.2019
  */
public class SpiderSilk {

    //properties
    private int knotCount;
    private SpiderBoi spiderBoi;
    private ShapeRenderer shapeRenderer;
    private int lastHit = -1;
    ArrayList<Rectangle> silkList;

   /** Constructor for the SpiderSilk class initialises the spiderBoi, knotCount, shapeRenderer, and silkList.
     * @param spB is the spiderBoi main character of the game, which will be used for the location in this class.
     */
    public SpiderSilk(SpiderBoi spB) {
        spiderBoi = spB;
        knotCount = 0;
        shapeRenderer = new ShapeRenderer();
        silkList = new ArrayList<Rectangle>();
    }

    //methods
    public boolean checkKnot () {
        if(spiderBoi.getVelocity().isZero()){
            lastHit = silkList.size()-1;
            return false;

        }
        Boolean ans = false;
        for(int i = 0; i< silkList.size();i++)
        {
            ans = (i != lastHit) && (ans || spiderBoi.getBoundary().overlaps(silkList.get(i)));
            if(spiderBoi.getBoundary().overlaps(silkList.get(i)))
                lastHit = i;
        }
        if(lastHit != -1 && !spiderBoi.getBoundary().overlaps(silkList.get(lastHit)))
            lastHit = -1;
        return ans;
    }

    /**
     * This method draws the silk coming out of the spiderBoi, taking the endpoint as th middle of the spiderBoi image
     * it stops drawing when it reaches an obstacle, so when it's velocity is zero.
     */
    public void drawSilk() {

        //records the stop locations of spiderboi, and the silks to an arraylist
        if (spiderBoi.isOnObstacle()) {
            spiderBoi.getStopLocations().add(spiderBoi.getPosition().cpy().add(spiderBoi.getHalfSize()));
            if (spiderBoi.getStopLocations().size() > 1) {
                Vector2 lastLoc = spiderBoi.getStopLocations().get(spiderBoi.getStopLocations().size() - 1);
                Vector2 lastLoc2 = spiderBoi.getStopLocations().get(spiderBoi.getStopLocations().size() - 2);
                if (lastLoc.y == lastLoc2.y)
                    silkList.add(new Rectangle(min(lastLoc.x, lastLoc2.x)-5, lastLoc.y - 5,
                            Math.abs(lastLoc.x - lastLoc2.x)+10, 10));

                else if (lastLoc.x == lastLoc2.x)
                    silkList.add(new Rectangle(lastLoc.x - 5, min(lastLoc.y, lastLoc2.y) - 5,
                            10, Math.abs(lastLoc.y - lastLoc2.y)));
            }
            spiderBoi.setOnObstacle(false);
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);

        /*records the stop locations of spiderboi to an arraylist and draws silk segment between two stop locations, and
        also draws silk between the last stop location and spiderboi */
        for (int i = 0; i < silkList.size(); i++) {
            Rectangle tmp = silkList.get(i);
            shapeRenderer.rect(tmp.getX(), tmp.getY(), tmp.getWidth(), tmp.getHeight());
        }

        shapeRenderer.rectLine(spiderBoi.getStopLocations().get(spiderBoi.getStopLocations().size() - 1),
                spiderBoi.getPosition().cpy().add(spiderBoi.getHalfSize()), 10);

        shapeRenderer.end();
    }

    /**
     * Getter method that returns the current number of knots.
     * @return the knotCount.
     */
    public int getKnotCount() {
        return knotCount;
    }

    /**
     * Increases the number of knots by 1.
     */
    public void addKnot() { knotCount++; }

    /**
     * Decreases the number of knots by 1.
     */
    public void removeKnot() {
        knotCount--;
    }
}


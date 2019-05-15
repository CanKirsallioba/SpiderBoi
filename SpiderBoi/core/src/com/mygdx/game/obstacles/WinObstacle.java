package com.mygdx.game.obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.boi.SpiderBoi;

/**
 * This class is the blueprint of the finishing obstacle.
 * It also has a checkCollision() method.
 * @author JavaBoiz
 * @version 13.05.2019
 */
public class WinObstacle extends Obstacle {

    /**
     * Constructor for the win obstacle, calls the parent constructor, it also initialises the image and the coordinates.
     * @param x is the x coordinate of the obstacle.
     * @param y is the y coordinate of the obstacle.
     */
    public WinObstacle(int x, int y)
    {
        super();
        position = new Vector2(x, y);
        image = new Texture("WinObstacle.jpg");
    }

    /**
     * Checks for collisions between SpiderBoi and the WinObstacle, removes SpiderBoi if there is one.
     * @param spiderBoi is the main character it will be checked if it collides with the WinObstacle.
     * @return a boolean value if it collides returns true if not returns false.
     */
    @Override
    public boolean checkCollision(SpiderBoi spiderBoi)
    {
        if(spiderBoi.getBoundary().overlaps(getBoundary()))
        {
            Vector2 reverseVel = spiderBoi.getVelocity().scl(-1);
            while(spiderBoi.getBoundary().overlaps(getBoundary())) {
                spiderBoi.setVelocity(reverseVel);
                spiderBoi.move();
            }
            spiderBoi.getVelocity().setZero();
            spiderBoi.setOnObstacle(true);
        }
        return spiderBoi.isOnObstacle();
    }
}

package com.mygdx.game.obstacles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * This class is not present or implemented in the game.
 */
public class SlipperyObstacle
        extends Obstacle {

    //properties
    //constructor
    /**
     * Constructor for the slippery obstacle, calls the parent constructor, it also initialises the image and the coordinates.
     * @param x is the x coordinate of the obstacle.
     * @param y is the y coordinate of the obstacle.
     */
    public SlipperyObstacle(int x, int y)
    {
        super();
        position = new Vector2(x, y);
        image = new Texture("SlipperyObstacle.png");
    }
}

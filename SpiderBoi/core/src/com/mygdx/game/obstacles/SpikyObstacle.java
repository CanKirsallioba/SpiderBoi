package com.mygdx.game.obstacles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class SpikyObstacle extends Obstacle {

    //constructor
    /**
     * Constructor for the spiky obstacle, calls the parent constructor, it also initialises the image and the coordinates.
     * @param x is the x coordinate of the obstacle.
     * @param y is the y coordinate of the obstacle.
     */
    public SpikyObstacle(int x, int y)
    {
        super();
        position = new Vector2(x, y);
        image = new Texture("SpikyObstacle.png");
    }
}

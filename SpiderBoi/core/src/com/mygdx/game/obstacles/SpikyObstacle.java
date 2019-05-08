package com.mygdx.game.obstacles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class SpikyObstacle extends Obstacle {

    //constructor
    public SpikyObstacle(int x, int y)
    {
        super();
        position = new Vector2(x, y);
        image = new Texture("SpikyObstacle.png");
    }
}

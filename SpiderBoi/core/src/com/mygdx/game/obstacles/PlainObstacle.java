package com.mygdx.game.obstacles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class PlainObstacle extends Obstacle {

    //properties

    //constructor
    public PlainObstacle(int x, int y)
    {
        super();
        position = new Vector2(x, y);
        image = new Texture("PlainObstacle.png");
    }

    //methods


}

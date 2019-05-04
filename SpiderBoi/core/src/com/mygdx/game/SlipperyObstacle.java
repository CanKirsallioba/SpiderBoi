package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class SlipperyObstacle extends Obstacle {

    //properties
    //constructor
    public SlipperyObstacle(int x, int y)
    {
        super();
        position = new Vector2(x, y);
        image = new Texture("SlipperyObstacle.png");
    }
}

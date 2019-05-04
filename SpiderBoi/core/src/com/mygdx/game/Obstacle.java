package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Obstacle {
    //properties
    private Vector2 position;
    private Color color;
    private char obstacleType;
    private Texture image;
    //methods

    public Vector2 getPosition()
    {
        return position;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    public char getObstacleType()
    {
        return obstacleType;
    }

    public void setObstacleType(char obstacleType) {
        this.obstacleType = obstacleType;
    }
}

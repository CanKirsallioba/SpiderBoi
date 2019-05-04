package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SpiderBoi extends Boi {

    //properties
    private double speed;
    private Vector2 velocity;
    SpriteBatch spriteBatch;
    private Texture image;

    public SpiderBoi()
    {
        super();
        speed = 2;
        velocity = new Vector2();
        position = new Vector2();
        spriteBatch = new SpriteBatch();
        image = new Texture("SpiderB1.png");
    }

    //methods

    public double getSpeed()
    {
        return speed;
    }

    public Vector2 getVelocity()
    {
        return velocity;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    public void setVelocity(Vector2 velocity)
    {
        this.velocity = velocity;
    }

    public void drawCharacter()
    {
        spriteBatch.begin();
        spriteBatch.draw(image, 0, 0);
        spriteBatch.end();
    }
}

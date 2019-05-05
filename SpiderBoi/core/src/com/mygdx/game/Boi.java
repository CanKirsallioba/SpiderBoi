package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Boi {
    //properties
    Vector2 position;
    Texture image;

    //methods

    public Vector2 getPosition()
    {
        return position;
    }

    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    public Texture getImage()
    {
        return image;
    }

    public Rectangle getBoundary()
    {
        return new Rectangle(position.x, position.y, image.getWidth(), image.getHeight());
    }

    public void setImage(Texture image) {
        this.image = image;
    }

    public void draw(SpriteBatch batch)
    {
        batch.draw(image, position.x, position.y);
    }
}

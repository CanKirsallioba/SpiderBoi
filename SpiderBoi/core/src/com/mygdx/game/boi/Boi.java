package com.mygdx.game.boi;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Boi {
    //properties
    Vector2 position;
    protected Texture image;

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

    public Vector2 getHalfSize()
    {
        return new Vector2(image.getWidth()/2, image.getHeight()/2);
    }

    public void setImage(Texture image) {
        this.image = image;
    }

    public void draw(SpriteBatch batch)
    {
        batch.draw(image, position.x, position.y);
    }
}

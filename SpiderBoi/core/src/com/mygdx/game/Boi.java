package com.mygdx.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Boi {
    //properties
    Vector2 position;
    private int width;
    private int height;
    //Texture image;

    //methods

    public Vector2 getPosition()
    {
        return position;
    }

    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    //public Texture getImage()
    //{
    //    return image;
    //}

    public void setHeight(int height)
    {
        this.height = height;
    }

    //public void setImage(Texture image) {
    //    this.image = image;
    //}

    public void setWidth(int width)
    {
        this.width = width;
    }

}

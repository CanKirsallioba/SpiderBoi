package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlyBoi extends Boi {

    //Properties

    SpriteBatch spriteBatch;
    private Texture image;
    int collected;

    public FlyBoi () {
        super();
        spriteBatch = new SpriteBatch();
        image = new Texture("FlyBoi.png");
    }

    //Methods

    public void drawCharacter()
    {
        spriteBatch.begin();
        spriteBatch.draw(image, 0, 0);
        spriteBatch.end();
    }



}

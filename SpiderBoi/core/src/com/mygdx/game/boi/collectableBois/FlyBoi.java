package com.mygdx.game.boi.collectableBois;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.boi.Boi;
import com.mygdx.game.boi.SpiderBoi;

public abstract class FlyBoi extends Boi {

    //Properties
    SpriteBatch spriteBatch;
    protected Texture image;

    public FlyBoi(String fileName, int x, int y) {
        super(fileName);
        setPosition(new Vector2(x, y));
        spriteBatch = new SpriteBatch();
    }

    //Methods

    public void drawCharacter()
    {
        spriteBatch.begin();
        spriteBatch.draw(image, 0, 0);
        spriteBatch.end();
    }

    public abstract void performInteraction(SpiderBoi spiderBoi);
}

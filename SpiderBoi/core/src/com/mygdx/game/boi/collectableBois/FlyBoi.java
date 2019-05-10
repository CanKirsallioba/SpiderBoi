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
    boolean isPresent;

    public FlyBoi(String fileName, int x, int y) {
        super(fileName);
        setPosition(new Vector2(x, y));
        spriteBatch = new SpriteBatch();
        isPresent = true;
    }

    //Methods

    public void drawCharacter()
    {
        spriteBatch.begin();
        spriteBatch.draw(image, 0, 0);
        spriteBatch.end();
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }

    public abstract void performInteraction(SpiderBoi spiderBoi);
}

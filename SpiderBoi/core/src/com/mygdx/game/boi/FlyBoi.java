package com.mygdx.game.boi;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.SpiderBoiGame;
import com.mygdx.game.boi.Boi;
import com.mygdx.game.boi.SpiderBoi;

public abstract class FlyBoi
        extends Boi {

    public FlyBoi(Texture image, int x, int y) {
        super(image);
        setPosition(new Vector2(x, y));
    }

    public abstract void performInteraction(SpiderBoiGame game);
}

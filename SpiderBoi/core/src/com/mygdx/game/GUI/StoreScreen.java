package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StoreScreen {

    //properties

    Button skinL, skinR, backL, backR, back, buyB, buyS;
    Texture title;

    //constructor
    public StoreScreen()
    {
        skinL = new Button("skinL1.png", "skinL2.png", 0, 700) ;
        skinR = new Button("skinR1.png", "skinR2.png", 0, 500);
        back = new Button("back1.png", "back2.png", 0, Gdx.graphics.getHeight() - 100);
        backL = new Button("backL1.png", "backL2.png", 0, 700) ;
        backR = new Button("backR1.png", "backR2.png", 0, 500);
        buyB = new Button("buyB1.png", "buyB2.png", 0, Gdx.graphics.getHeight() - 100);
        buyS = new Button("buyS1.png", "buyS2.png", 0, Gdx.graphics.getHeight() - 100);
        title = new Texture("store.png");
    }

    //methods

    public void draw(SpriteBatch batch)
    {
        batch.begin();
        batch.draw( title, Gdx.graphics.getWidth()/2 - title.getWidth()/2, 200);
        skinL.draw(batch);
        back.draw(batch);
        backL.draw(batch);
        buyB.draw(batch);
        skinR.draw(batch);
        backR.draw(batch);
        buyS.draw(batch);
        batch.end();
    }
}

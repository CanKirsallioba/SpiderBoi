package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class StoreScreen {

    //properties

    Button skinL, skinR, backL, backR, back, buyB, buyS;
    Texture title;

    //constructor
    public StoreScreen()
    {
        skinL = new Button("KratosBoiD.png", "KratosBoiL.png", 0, 700) ;
        skinR = new Button("KratosBoiR.png", "KratosBoiU.png", 0, 500);
        back = new Button("backBut1.png", "backBut2.png", 0, Gdx.graphics.getHeight() - 100);
        backL = new Button("backBut1.png", "backBut2.png", 0, 700) ;
        backR = new Button("backBut1.png", "backBut2.png", 0, 500);
        buyB = new Button("storeFlyBoi.png", "knotFlyBoi.png", 0, Gdx.graphics.getHeight() - 100);
        buyS = new Button("knotFlyBoi.png", "storeFlyBoi.png", 0, Gdx.graphics.getHeight() - 100);
        title = new Texture("levelTitle.png");
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

    public void resetAll()
    {
        skinL.reset();
        skinR.reset();
        back.reset();
        backL.reset();
        backR.reset();
        buyB.reset();
        buyS.reset();
    }

    public void touchDown(int x, int y)
    {
        Vector2 lastTouchInv = new Vector2(x, Gdx.graphics.getHeight() - y );
        if (skinL.isPressed(lastTouchInv))
            skinL.setDark();
        else if(skinR.isPressed(lastTouchInv))
            skinR.setDark();
        else if(back.isPressed(lastTouchInv))
            back.setDark();
        else if(backL.isPressed(lastTouchInv))
            backL.setDark();
        else if(backR.isPressed(lastTouchInv))
            backR.setDark();
        else if(buyB.isPressed(lastTouchInv))
            buyB.setDark();
        else if(buyS.isPressed(lastTouchInv))
            buyS.setDark();
    }

    public boolean skinLButtonPressed(Vector2 pos)
    {
        return skinL.isPressed(pos);
    }

    public boolean skinRButtonPressed(Vector2 pos)
    {
        return skinR.isPressed(pos);
    }

    public boolean backButtonPressed(Vector2 pos) { return back.isPressed(pos); }

    public boolean backLButtonPressed(Vector2 pos) { return backL.isPressed(pos); }

    public boolean backRButtonPressed(Vector2 pos) { return backR.isPressed(pos); }

    public boolean buyBButtonPressed(Vector2 pos) { return buyB.isPressed(pos); }

    public boolean buySButtonPressed(Vector2 pos) { return buyS.isPressed(pos); }
}

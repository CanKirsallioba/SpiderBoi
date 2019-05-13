package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class LevelSelection {

    //properties

    Button level1Button, level2Button, backButton;
    Texture title;

    public LevelSelection()
    {
        title = new Texture("levelTitle.png");
        level1Button = new Button("level1But1.png", "level1But2.png", 0, 700);
        level2Button = new Button("level2But1.png", "level2But2.png", 0, 500);
        backButton = new Button("backBut1.png", "backBut2.png", 100, Gdx.graphics.getHeight() - 100);
        level1Button.centralize();
        level2Button.centralize();
    }

    public void draw(SpriteBatch batch)
    {
        batch.begin();
        batch.draw(title, Gdx.graphics.getWidth()/2 - title.getWidth()/2, 1000);
        level1Button.draw(batch);
        level2Button.draw(batch);
        backButton.draw(batch);
        batch.end();
    }

    public void resetAll()
    {
        level1Button.reset();
        level2Button.reset();
        backButton.reset();
    }

    public void touchDown(int x, int y)
    {
        Vector2 lastTouchInv = new Vector2(x, Gdx.graphics.getHeight() - y );
        if (level1Button.isPressed(lastTouchInv))
            level1Button.setDark();
        else if(level2Button.isPressed(lastTouchInv))
            level2Button.setDark();
        else if(backButton.isPressed(lastTouchInv))
            backButton.setDark();
    }

    public boolean level1ButtonPressed(Vector2 pos)
    {
        return level1Button.isPressed(pos);
    }

    public boolean level2ButtonPressed(Vector2 pos)
    {
        return level2Button.isPressed(pos);
    }

    public boolean backButtonPressed(Vector2 pos)
    {
        return backButton.isPressed(pos);
    }
}

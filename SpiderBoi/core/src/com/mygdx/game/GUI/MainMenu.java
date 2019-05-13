package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

public class MainMenu {

    //properties
    Button playButton, storeButton, aboutButton, achievementsButton, exitButton;
    Texture title;

    //constructor
    public MainMenu()
    {
        playButton = new Button("playBut1.png", "playBut2.png", 0, 800) ;
        storeButton = new Button("storeBut1.png", "storeBut2.png", 0, 600);
        achievementsButton = new Button("achBut1.png", "achBut2.png", 0, 400);
        //aboutButton = new Button("aboutBut1.png", "aboutBut2.png", 0, 200);
        exitButton = new Button("exitBut1.png", "exitBut2.png", 0, 0);
        title = new Texture("mainTitle.png");
        playButton.centralize();
        storeButton.centralize();
        achievementsButton.centralize();
        //aboutButton.centralize();
        exitButton.centralize();
    }

    //methods
    public void draw(SpriteBatch batch)
    {
        batch.begin();
        batch.draw(title, Gdx.graphics.getWidth()/2 - title.getWidth()/2, 1000);
        playButton.draw(batch);
        storeButton.draw(batch);
        achievementsButton.draw(batch);
        //aboutButton.draw(batch);
        exitButton.draw(batch);
        batch.end();
    }

    public void resetAll()
    {
        playButton.reset();
        storeButton.reset();
        achievementsButton.reset();
        //aboutButton.reset();
        exitButton.reset();
    }

    public void touchDown(int x, int y)
    {
        Vector2 lastTouchInv = new Vector2(x, Gdx.graphics.getHeight() - y );
        if (playButton.isPressed(lastTouchInv))
            playButton.setDark();
        else if(storeButton.isPressed(lastTouchInv))
            storeButton.setDark();
        else if(achievementsButton.isPressed(lastTouchInv))
            achievementsButton.setDark();
            //else if (aboutButton.isPressed(lastTouchInv))
            //aboutButton.setDark();
        else if (exitButton.isPressed(lastTouchInv))
            exitButton.setDark();
    }

    public boolean playButtonPressed(Vector2 pos)
    {
        return playButton.isPressed(pos);
    }

    public boolean storeButtonPressed(Vector2 pos)
    {
        return storeButton.isPressed(pos);
    }

    public boolean achievementsButtonPressed(Vector2 pos)
    {
        return achievementsButton.isPressed(pos);
    }

    public boolean aboutButtonPressed(Vector2 pos)
    {
        return aboutButton.isPressed(pos);
    }

    public boolean exitButtonPressed(Vector2 pos)
    {
        return exitButton.isPressed(pos);
    }

}
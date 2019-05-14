package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

/**
 * A main menu class before starting the actual Spider Boi Game
 */
public class MainMenu {

    //properties
    Button playButton, storeButton, aboutButton, achievementsButton, exitButton;
    Texture title;

    //constructor
    /**
     * A constructor for the main menu class
     */
    public MainMenu()
    {
        playButton = new Button("playBut1.png", "playBut2.png", 0, 680) ;
        storeButton = new Button("storeBut1.png", "storeBut2.png", 0, 510);
        achievementsButton = new Button("achBut1.png", "achBut2.png", 0, 340);
        aboutButton = new Button("aboutBut1.png", "aboutBut2.png", 0, 170);
        exitButton = new Button("exitBut1.png", "exitBut2.png", 0, 0);
        title = new Texture("mainTitle.png");
        playButton.centralize();
        storeButton.centralize();
        achievementsButton.centralize();
        aboutButton.centralize();
        exitButton.centralize();
    }

    //methods
    /**
     * A method that draws the main menu
     * @param batch a SpriteBatch object that helps drawing
     */
    public void draw(SpriteBatch batch)
    {
        batch.begin();
        batch.draw(title, Gdx.graphics.getWidth()/2 - title.getWidth()/2, 900);
        playButton.draw(batch);
        storeButton.draw(batch);
        achievementsButton.draw(batch);
        aboutButton.draw(batch);
        exitButton.draw(batch);
        batch.end();
    }

    /**
     * A reset method for the main menu buttons
     */
    public void resetAll()
    {
        playButton.reset();
        storeButton.reset();
        achievementsButton.reset();
        aboutButton.reset();
        exitButton.reset();
    }

    /**
     * A method that finds and darkens the pressed button among others
     * @param x the x location of the press
     * @param y the y location of the press
     */
    public void touchDown(int x, int y)
    {
        Vector2 lastTouchInv = new Vector2(x, Gdx.graphics.getHeight() - y );
        if (playButton.isPressed(lastTouchInv))
            playButton.setDark();
        else if(storeButton.isPressed(lastTouchInv))
            storeButton.setDark();
        else if(achievementsButton.isPressed(lastTouchInv))
            achievementsButton.setDark();
        else if (aboutButton.isPressed(lastTouchInv))
            aboutButton.setDark();
        else if (exitButton.isPressed(lastTouchInv))
            exitButton.setDark();
    }

    /**
     * A method that checks if play button is pressed
     * @param pos the position of the press
     * @return playButton.isPressed(pos)
     */
    public boolean playButtonPressed(Vector2 pos)
    {
        return playButton.isPressed(pos);
    }

    /**
     * A method that checks if store button is pressed
     * @param pos the position of the press
     * @return storeButton.isPressed(pos)
     */
    public boolean storeButtonPressed(Vector2 pos)
    {
        return storeButton.isPressed(pos);
    }

    /**
     * A method that checks if achievements button is pressed
     * @param pos the position of the press
     * @return achievementsButton.isPressed(pos)
     */
    public boolean achievementsButtonPressed(Vector2 pos)
    {
        return achievementsButton.isPressed(pos);
    }

     /**
     * A method that checks if about button is pressed
     * @param pos the position of the press
     * @return aboutButton.isPressed(pos)
     */
    public boolean aboutButtonPressed(Vector2 pos)
    {
        return aboutButton.isPressed(pos);
    }

    /**
     * A method that checks if exit button is pressed
     * @param pos the position of the press
     * @return exitButton.isPressed(pos)
     */
    public boolean exitButtonPressed(Vector2 pos)
    {
        return exitButton.isPressed(pos);
    }

}

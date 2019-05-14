package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * A Level Selection class for the user to choose one
 */
public class LevelSelection {

    //properties

    Button level1Button, level2Button, level3Button, backButton;
    Texture title;

    /**
     * A constructor for the level selection
     */
    public LevelSelection()
    {
        title = new Texture("levelTitle.png");
        level1Button = new Button("level1But1.png", "level1But2.png", 0, 600);
        level2Button = new Button("level2But1.png", "level2But2.png", 0, 450);
        level3Button = new Button("level3But1.png", "level3But2.png", 0, 300 );
        backButton = new Button("backBut1.png", "backBut2.png", 100, Gdx.graphics.getHeight() - 100);
        level1Button.centralize();
        level2Button.centralize();
        level3Button.centralize();
    }

    /**
     * A method that draws the title and back button and the level buttons
     * @param batch a SpriteBatch object that helps to draw
     */
    public void draw(SpriteBatch batch)
    {
        batch.begin();
        batch.draw(title, Gdx.graphics.getWidth()/2 - title.getWidth()/2, 900);
        level1Button.draw(batch);
        level2Button.draw(batch);
        level3Button.draw(batch);
        backButton.draw(batch);
        batch.end();
    }

    /**
     * A method that resets this class' buttons
     */
    public void resetAll()
    {
        level1Button.reset();
        level2Button.reset();
        level3Button.reset();
        backButton.reset();
    }

    /**
     * A method that finds and sets the pressed button dark among other buttons 
     * @param x the x location of the press
     * @param y the y location of the press
     */
    public void touchDown(int x, int y)
    {
        Vector2 lastTouchInv = new Vector2(x, Gdx.graphics.getHeight() - y );
        if (level1Button.isPressed(lastTouchInv))
            level1Button.setDark();
        else if(level2Button.isPressed(lastTouchInv))
            level2Button.setDark();
        else if(level3Button.isPressed(lastTouchInv))
            level3Button.setDark();
        else if(backButton.isPressed(lastTouchInv))
            backButton.setDark();
    }

    /**
     * A method that checkes if level 1 button is pressed
     * @param pos the position of the press
     * @return level1Button.isPressed(pos)
     */
    public boolean level1ButtonPressed(Vector2 pos)
    {
        return level1Button.isPressed(pos);
    }

    /**
     * A method that checkes if level 2 button is pressed
     * @param pos the position of the press
     * @return level2Button.isPressed(pos)
     */
    public boolean level2ButtonPressed(Vector2 pos)
    {
        return level2Button.isPressed(pos);
    }

    /**
     * A method that checkes if back button is pressed
     * @param pos the position of the press
     * @return backButton.isPressed(pos)
     */
    public boolean backButtonPressed(Vector2 pos)
    {
        return backButton.isPressed(pos);
    }

    /**
     * A method that checkes if level 3 button is pressed
     * @param pos the position of the press
     * @return level2Button.isPressed(pos)
     */
    public boolean level3ButtonPressed(Vector2 pos)
    {
        return level3Button.isPressed(pos);
    }
}

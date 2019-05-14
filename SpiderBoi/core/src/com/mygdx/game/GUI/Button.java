package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Button {

    //properties

    Vector2 position;
    Texture texture, texture1, texture2;
    Rectangle buttonRect;

    //constructor
    /**
     * A constructor for the Button class
     * @param path1 the path of the light colored button 
     * @param path2 the path of the dark colored button
     * @param x the x location of the button
     * @param y the y location of the button
     */
    public Button( String path1, String path2, int x, int y )
    {
        this.texture1 = new Texture(path1);
        this.texture2 = new Texture(path2);
        texture = texture1;
        this.position = new Vector2(x, y);
        buttonRect = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    //methods
    /**
     * A method that draws the button
     * @param batch a SpriteBatch object which helps to draw the button
     */
    public void draw(SpriteBatch batch)
    {
        batch.draw( texture, position.x, position.y );
    }
    
    /**
     * A method that removes the light colored button when it is pressed
     * and draws the dark colored button representing the click event
     */
    public void setDark()
    {
        texture = texture2;
    }
    
    /**
     * A method that makes button return its initial and light colored look
     */
    public void reset()
    {
        texture = texture1;
    }
    
    /**
     * A method that checks if the user touch is inside the button or not
     * @param touch the position of the user touch
     * @return buttonRect.contains(touch)
     */
    public boolean isPressed(Vector2 touch)
    {
        return buttonRect.contains(touch);
    }

    /**
     * A method that centralizes the button
     */
    public void centralize()
    {
        position.x = Gdx.graphics.getWidth()/2 - texture1.getWidth()/2;
        buttonRect.x = Gdx.graphics.getWidth()/2 - texture1.getWidth()/2;
    }
}

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

    public Button( String path1, String path2, int x, int y )
    {
        this.texture1 = new Texture(path1);
        this.texture2 = new Texture(path2);
        texture = texture1;
        this.position = new Vector2(x, y);
        buttonRect = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    //methods

    public void draw(SpriteBatch batch)
    {
        batch.draw( texture, position.x, position.y );
    }

    public void setDark()
    {
        texture = texture2;
    }

    public void reset()
    {
        texture = texture1;
    }

    public boolean isPressed(Vector2 touch)
    {
        return buttonRect.contains(touch);
    }

    public void centralize()
    {
        position.x = Gdx.graphics.getWidth()/2 - texture1.getWidth()/2;
        buttonRect.x = Gdx.graphics.getWidth()/2 - texture1.getWidth()/2;
    }
}

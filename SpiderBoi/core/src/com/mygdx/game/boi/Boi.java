package com.mygdx.game.boi;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Boi {
    //properties
    private Vector2 position;
    private Texture image;
    boolean isPresent;

    //constructors
    public Boi(Texture texture) {
        image = texture;
        position = new Vector2(0,0);
        isPresent = true;
    }

    //methods
    /**
     * This method gets the position of the Boi object.
     * @return the position in vector2 type.
     */
    public Vector2 getPosition()
    {
        return position;
    }

    /**
     * This method sets the position of the boi object.
     * @param position is the desired position of the object in vector2 type.
     */
    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    /**
     * Gets the image of the Boi object.
     * @return the image of the object.
     */
    public Texture getImage()
    {
        return image;
    }

    /**
     * Gets the boundaries for the rectangle shaped image.
     * @return the sizes of the rectangle surrounding the object.
     */
    public Rectangle getBoundary()
    {
        return new Rectangle(position.x, position.y, image.getWidth(), image.getHeight());
    }

    /**
     * Gets the center of the object.
     * @return Sizes of the object in Vector2 type.
     */
    public Vector2 getHalfSize()
    {
        return new Vector2(image.getWidth()/2, image.getHeight()/2);
    }

    /**
     * Sets the image of the Boi type object.
     * @param image is the illustration of the character.
     */
    public void setImage(Texture image) {
        this.image = image;
    }

    /**
     * This method draws the SpriteBatch (the character) .
     * @param batch is the texture of the desired character.
     */
    public void draw(SpriteBatch batch)
    {
        batch.draw(image, position.x, position.y);
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }
}

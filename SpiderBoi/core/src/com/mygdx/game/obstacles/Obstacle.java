package com.mygdx.game.obstacles;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.boi.SpiderBoi;

/**
 * This class is the parent class of the obstacle type classes.
 * Has methods to maintain the obstacle logic such as checkCollision().
 * @author JavaBoiz
 * @version 13.05.2019
 */
public class Obstacle {
    //properties
    Vector2 position;
    Texture image;

    //methods

    /**
     * This method gets the position of the obstacle in type Vector2.
     * @return the position of the obstacle in type Vector2.
     */
    public Vector2 getPosition()
    {
        return position;
    }

    /**
     * This method sets the position of the obstacle with an arbitrary position value of type Vector2.
     * @param position is the position vector to be set to the SpiderBoi.
     */
    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    /**
     * This method gets the image of the obstacle.
     * @return the image of the obstacle.
     */
    public Texture getImage()
    {
        return image;
    }

    /**
     * This method sets the image of the obstacle, it is needed because there are multiple types of obstacles.
     * @param image is the image to be set ass an obstacle.
     */
    public void setImage(Texture image) {
        this.image = image;
    }

    /**
     * This method gets the boundaries of the obstacles.
     * @return the boundaries of the obstacles.
     */
    public Rectangle getBoundary()
    {
        return new Rectangle(position.x, position.y, image.getWidth(), image.getHeight());
    }

    /**
     * This method is used for drawing the spriteBatch of the obstacle.
     * @param batch is the obstacles texture that will be drawn.
     */
    public void draw(SpriteBatch batch)
    {
        batch.draw(image, position.x, position.y);
    }

    /**
     * This method checks for collision between SpiderBoi and the obstacles.
     * @param spiderBoi is the main character it will be checked if it collides with the obstacles.
     * @return a boolean value if it collides returns true if not returns false.
     */
    public boolean checkCollision(SpiderBoi spiderBoi)
    {
        if(spiderBoi.getBoundary().overlaps(getBoundary()))
        {
            Vector2 reverseVel = spiderBoi.getVelocity().scl(-1);
            while(spiderBoi.getBoundary().overlaps(getBoundary())) {
                spiderBoi.setVelocity(reverseVel);
                spiderBoi.move();
            }
            spiderBoi.getVelocity().setZero();
            spiderBoi.setOnObstacle(true);
        }
        return spiderBoi.isOnObstacle();
    }
}
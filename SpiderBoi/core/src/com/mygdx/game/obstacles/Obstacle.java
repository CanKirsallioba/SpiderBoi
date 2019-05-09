package com.mygdx.game.obstacles;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.boi.SpiderBoi;

public class Obstacle {
    //properties
    Vector2 position;
    Texture image;

    //methods

    public Vector2 getPosition()
    {
        return position;
    }

    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    public Texture getImage()
    {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }

    public Rectangle getBoundary()
    {
        return new Rectangle(position.x, position.y, image.getWidth(), image.getHeight());
    }

    public void draw(SpriteBatch batch)
    {
        batch.draw(image, position.x, position.y);
    }

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

        if (spiderBoi.getPosition().x > Gdx.graphics.getWidth() || spiderBoi.getPosition().x < 0
                || spiderBoi.getPosition().y > Gdx.graphics.getHeight() || spiderBoi.getPosition().y < 0) {
            spiderBoi.getVelocity().setZero();
        }
        return spiderBoi.isOnObstacle();
    }
}
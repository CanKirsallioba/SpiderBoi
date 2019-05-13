package com.mygdx.game.obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.boi.SpiderBoi;

public class SpikyObstacle
        extends Obstacle{

    //constructor
    /**
     * Constructor for the spiky obstacle, calls the parent constructor, it also initialises the image and the coordinates.
     * @param x is the x coordinate of the obstacle.
     * @param y is the y coordinate of the obstacle.
     */
    public SpikyObstacle(int x, int y)
    {
        super();
        position = new Vector2(x, y);
        image = new Texture("SpikyObstacle.png");
    }

    /**
     * Checks for collisions between SpiderBoi and the SpikyObstacle, removes SpiderBoi if there is one.
     * @param spiderBoi is the main character it will be checked if it collides with the SpikyObstacle.
     * @return a boolean value if it collides returns true if not returns false.
     */
    @Override
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

        if (spiderBoi.getPosition().x + spiderBoi.getImage().getWidth() / 2 > Gdx.graphics.getWidth()
                || spiderBoi.getPosition().x < 0
                || spiderBoi.getPosition().y + spiderBoi.getImage().getHeight() / 2 > Gdx.graphics.getHeight()
                || spiderBoi.getPosition().y < 0)
        {
            spiderBoi.getVelocity().setZero(); // Stop the SpiderBoi
            spiderBoi.setPresent(false); // rip
            // Open the "You lost the level" GUI screen.
        }
        return spiderBoi.isOnObstacle();
    }
}

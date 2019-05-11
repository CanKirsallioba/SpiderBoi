package com.mygdx.game.boi;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class SpiderBoi extends Boi {

    //properties
    private float speed;
    private Vector2 velocity;
    private final Texture spiderBoiUp = new Texture("SpiderBU.png");
    private final Texture spiderBoiDown = new Texture("SpiderBD.png");
    private final Texture spiderBoiLeft = new Texture("SpiderBL.png");
    private final Texture spiderBoiRight = new Texture("SpiderBR.png");
    boolean onObstacle;
    ArrayList<Vector2> stopLocations;

    /**
     * The constructor for the SpiderBoi object, calls the parent constructor,
     * initialises the speed of the SpiderBoi, initialises the image of the SpiderBoi in all four directions,
     * and creates an ArrayList to get the stopping locations on the obstacles.
     */
    public SpiderBoi(String fileName) {
        super(fileName);
        speed = 0;
        velocity = new Vector2(0, 0);
        stopLocations = new ArrayList<Vector2>();
        setImage(spiderBoiRight);
        onObstacle = true;
    }

    //methods

    /**
     * Gets the status of the SpiderBoi regarding being on the obstacle or not.
     * @return true or false considering being on a obstacle.
     */
    public boolean isOnObstacle() {
        return onObstacle;
    }

    /**
     * Sets the condition of SpiderBoi being on an obstacle or not.
     * @param onObstacle is the status of the spiderBoi' s being on the obstacle.
     */
    public void setOnObstacle(boolean onObstacle) {
        this.onObstacle = onObstacle;
    }

    /**
     * Gets the stop location in an arraylist.
     * @return the arraylist of the stop locations.
     */
    public ArrayList<Vector2> getStopLocations() {
        return stopLocations;
    }

    /**
     * Sets the elements of the arraylist with the stop locations.
     * @param stopLocations are the stopping points of the SpiderBoi.
     */
    public void setStopLocations(ArrayList<Vector2> stopLocations) {
        this.stopLocations = stopLocations;
    }

    /**
     * Gets the speed of the SpiderBoi.
     * @return the speed of the SpiderBoi in double type.
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Gets the velocity of the SpiderBoi.
     * @return the velocity of the Spiderboi in Vector2 type.
     */
    public Vector2 getVelocity() {
        return velocity;
    }

    /**
     * Sets the speed of thr SpiderBoi.
     * @param speed is the desired speed of the SpiderBoi to be set.
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Sets the velocity of the SpiderBoi.
     * @param velocity is the desired velocity of the SpiderBoi to be set.
     */
    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    /**
     * This method is the method that helps the SpiderBoi move
     * by adding up the velocity vectors, and changing the position.
     */
    public void move() {
        setPosition(getPosition().add(velocity));
    }

    /**
     * This method helps the SpiderBoi move to the right, and sets the velocity to 20.
     */
    public void moveRight() {
        setImage(spiderBoiRight);
        velocity.set(20, 0);
    }

    /**
     * This method helps the SpiderBoi move to the left, and sets the velocity to 20.
     */
    public void moveLeft() {
        setImage(spiderBoiLeft);
        velocity.set(-20, 0);
    }

    /**
     * This method helps the SpiderBoi move to the up, and sets the velocity to 20.
     */
    public void moveUp() {
        setImage(spiderBoiUp);
        velocity.set(0, 20);
    }

    /**
     * This method helps the SpiderBoi move to the doen, and sets the velocity to 20.
     */
    public void moveDown() {
        setImage(spiderBoiDown);
        velocity.set(0, -20);
    }

    //public Vector2 getLastVelocity()
    //{
    //    return lastVelocity;
    //}


}

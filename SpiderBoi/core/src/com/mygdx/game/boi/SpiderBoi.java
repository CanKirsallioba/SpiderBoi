package com.mygdx.game.boi;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.menu.store.SpiderBoiSkin;

import java.util.ArrayList;

public class SpiderBoi extends Boi {

    //properties
    private float speed;
    private Vector2 velocity;
    private boolean onObstacle;
    private ArrayList<Vector2> stopLocations;
    private SpiderBoiSkin spiderBoiSkin;

    //constructor(s)
    /**
     * The constructor for the SpiderBoi object, calls the parent constructor,
     * initialises the speed of the SpiderBoi, initialises the image of the SpiderBoi in all four directions,
     * and creates an ArrayList to get the stopping locations on the obstacles.
     */
    public SpiderBoi(SpiderBoiSkin spiderBoiSkin) {
        super(spiderBoiSkin.getDownSpiderBoi());
        this.spiderBoiSkin = spiderBoiSkin;
        speed = 20;
        velocity = new Vector2(0, 0);
        stopLocations = new ArrayList<Vector2>();
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
        setImage(spiderBoiSkin.getRightSpiderBoi());
        velocity.set(speed, 0);
    }

    /**
     * This method helps the SpiderBoi move to the left, and sets the velocity to 20.
     */
    public void moveLeft() {
        setImage(spiderBoiSkin.getLeftSpiderBoi());
        velocity.set(speed * (-1), 0);
    }

    /**
     * This method helps the SpiderBoi move to the up, and sets the velocity to 20.
     */
    public void moveUp() {
        setImage(spiderBoiSkin.getUpSpiderBoi());
        velocity.set(0, speed);
    }

    /**
     * This method helps the SpiderBoi move to the down, and sets the velocity to 20.
     */
    public void moveDown() {
        setImage(spiderBoiSkin.getDownSpiderBoi());
        velocity.set(0, speed * (-1));
    }

}

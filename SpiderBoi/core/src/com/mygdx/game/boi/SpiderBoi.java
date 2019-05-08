package com.mygdx.game.boi;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class SpiderBoi extends Boi {

    //properties
    private float speed;
    private Vector2 velocity;
    private Texture SpiderBoiUp;
    private Texture SpiderBoiDown;
    private Texture SpiderBoiLeft;
    private Texture SpiderBoiRight;

    public SpiderBoi() {
        super();
        speed = 20;
        velocity = new Vector2(speed, 0);
        position = new Vector2(0, 0);
        SpiderBoiUp = new Texture("SpiderBU.png");
        SpiderBoiDown = new Texture("SpiderBD.png");
        SpiderBoiRight = new Texture("SpiderBR.png");
        SpiderBoiLeft = new Texture("SpiderBL.png");
        image = SpiderBoiRight;
    }

    //methods

    public double getSpeed() {
        return speed;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void move() {
        position = position.add(velocity);
    }

    public void moveRight() {
        image = SpiderBoiRight;
        velocity.set(speed, 0);
    }

    public void moveLeft() {
        image = SpiderBoiLeft;
        velocity.set(-speed, 0);
    }

    public void moveUp() {
        image = SpiderBoiUp;
        velocity.set(0, speed);
    }

    public void moveDown() {
        image = SpiderBoiDown;
        velocity.set(0, -speed);
    }

    //public Vector2 getLastVelocity()
    //{
    //    return lastVelocity;
    //}


}

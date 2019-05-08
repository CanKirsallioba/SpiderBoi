package com.mygdx.game.boi;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class SpiderBoi extends Boi {

    //properties
    private float speed;
    private Vector2 velocity;
    private Texture spiderBoiUp;
    private Texture spiderBoiDown;
    private Texture spiderBoiLeft;
    private Texture spiderBoiRight;

    public SpiderBoi() {
        super();
        speed = 20;
        velocity = new Vector2(speed, 0);
        position = new Vector2(0, 0);
        spiderBoiUp = new Texture("SpiderBU.png");
        spiderBoiDown = new Texture("SpiderBD.png");
        spiderBoiRight = new Texture("SpiderBR.png");
        spiderBoiLeft = new Texture("SpiderBL.png");
        image = spiderBoiRight;
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
        image = spiderBoiRight;
        velocity.set(speed, 0);
    }

    public void moveLeft() {
        image = spiderBoiLeft;
        velocity.set(-speed, 0);
    }

    public void moveUp() {
        image = spiderBoiUp;
        velocity.set(0, speed);
    }

    public void moveDown() {
        image = spiderBoiDown;
        velocity.set(0, -speed);
    }

    //public Vector2 getLastVelocity()
    //{
    //    return lastVelocity;
    //}


}

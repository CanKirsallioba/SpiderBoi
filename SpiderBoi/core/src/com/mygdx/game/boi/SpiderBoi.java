package com.mygdx.game.boi;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

    public class SpiderBoi extends Boi {

        //properties
        private float speed;
        private Vector2 velocity;
        private Texture SpiderBoiUp;
        private Texture SpiderBoiDown;
        private Texture SpiderBoiLeft;
        private Texture SpiderBoiRight;
        boolean onObstacle;
        ArrayList<Vector2> stopLocations;

        public SpiderBoi() {
            super();
            speed = 0;
            velocity = new Vector2(0, 0);
            position = new Vector2(0, 0);
            SpiderBoiUp = new Texture("SpiderBU.png");
            SpiderBoiDown = new Texture("SpiderBD.png");
            SpiderBoiRight = new Texture("SpiderBR.png");
            SpiderBoiLeft = new Texture("SpiderBL.png");
            stopLocations = new ArrayList<Vector2>();
            image = SpiderBoiRight;
            onObstacle = true;
        }

        //methods
        public boolean isOnObstacle() {
            return onObstacle;
        }

        public void setOnObstacle(boolean onObstacle) {
            this.onObstacle = onObstacle;
        }

        public ArrayList<Vector2> getStopLocations() {
            return stopLocations;
        }

        public void setStopLocations(ArrayList<Vector2> stopLocations) {
            this.stopLocations = stopLocations;
        }

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
            velocity.set(20, 0);
        }

        public void moveLeft() {
            image = SpiderBoiLeft;
            velocity.set(-20, 0);
        }

        public void moveUp() {
            image = SpiderBoiUp;
            velocity.set(0, 20);
        }

        public void moveDown() {
            image = SpiderBoiDown;
            velocity.set(0, -20);
        }

        //public Vector2 getLastVelocity()
        //{
        //    return lastVelocity;
        //}


    }

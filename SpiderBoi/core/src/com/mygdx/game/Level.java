package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.boi.SpiderBoi;
import com.mygdx.game.boi.collectableBois.CollectableBoi;
import com.mygdx.game.boi.collectableBois.KnotFlyBoi;
import com.mygdx.game.boi.collectableBois.StoreFlyBoi;
import com.mygdx.game.obstacles.Obstacle;
import com.mygdx.game.obstacles.PlainObstacle;
import com.mygdx.game.obstacles.SlipperyObstacle;
import com.mygdx.game.obstacles.SpikyObstacle;
import com.mygdx.game.obstacles.WinObstacle;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is responsible for creating levels from the .txt files
 * and putting them in an arraylist.
 */
public class Level {

    //properties
    static int maxAllowedKnots;
    private Scanner mapScan;
    private int levelNo;
    ArrayList<Obstacle> obstacles;
    ArrayList<CollectableBoi> collectableBois;

    //constructor

    /**
     * Constructor for the level class it reads the .txt file located int he assests
     * folder for the locations of the entities, creates an arraylist for obstacles.
     * @param levelNo is the level number of the which is also the name of the .txt file.
     */
    public Level(int levelNo)
    {
        FileHandle levelFile = Gdx.files.internal(levelNo + ".txt");
        this.levelNo = levelNo;
        obstacles = new ArrayList<Obstacle>();
        collectableBois = new ArrayList<CollectableBoi>();
        mapScan = new Scanner(levelFile.readString());
        if (levelNo == 1) {
            setMaxAllowedKnots(0);
        }
        else if (levelNo == 2) {
            setMaxAllowedKnots(1);
        }
        else if (levelNo == 3) {
            setMaxAllowedKnots(3);
        }
    }

    //methods

    /**
     * This method scans the .txt file and puts the related objects in the given positions.
     * @param spiderBoi is the main character of the game since it will interact with the environment it is
     *                  taken as a parameter.
     */
    public void showLevel(SpiderBoi spiderBoi)
    {
        while(mapScan.hasNext())
        {
            String objectType = mapScan.next();
            if(objectType.equals("pl"))
            {
                obstacles.add(new PlainObstacle(Integer.parseInt(mapScan.next()) * Gdx.graphics.getWidth() / 100,
                        Integer.parseInt(mapScan.next()) * Gdx.graphics.getHeight() / 50));
            }
            else if(objectType.equals("sl"))
            {
                obstacles.add(new SlipperyObstacle(Integer.parseInt(mapScan.next()) * Gdx.graphics.getWidth() / 100,
                        Integer.parseInt(mapScan.next()) * Gdx.graphics.getHeight() / 50));
            }
            else if(objectType.equals("sk"))
            {
                obstacles.add(new SpikyObstacle((Integer.parseInt(mapScan.next())) * Gdx.graphics.getWidth() / 100,
                        Integer.parseInt(mapScan.next()) * Gdx.graphics.getHeight() / 50));
            }
            else if(objectType.equals("sp"))
            {
                spiderBoi.setPosition(new Vector2(Integer.parseInt(mapScan.next()) * Gdx.graphics.getWidth() / 100,
                        Integer.parseInt(mapScan.next()) * Gdx.graphics.getHeight() / 50));
            }
            else if(objectType.equals("wn"))
            {
                obstacles.add(new WinObstacle((Integer.parseInt(mapScan.next())) * Gdx.graphics.getWidth() / 100,
                        Integer.parseInt(mapScan.next()) * Gdx.graphics.getHeight() / 50));
            }
            else if(objectType.equals("sfb"))
            {
                collectableBois.add(new StoreFlyBoi((Integer.parseInt(mapScan.next())) * Gdx.graphics.getWidth() / 100,
                        Integer.parseInt(mapScan.next()) * Gdx.graphics.getHeight() / 50));
            }
            else if(objectType.equals("kfb"))
            {
                collectableBois.add(new KnotFlyBoi(((Integer.parseInt(mapScan.next())) * Gdx.graphics.getWidth() / 100),
                        Integer.parseInt(mapScan.next()) * Gdx.graphics.getHeight() / 50));
            }
        }
    }

    /**
     * This method returns the obstacle list to check collision
     * return The list of obstacles
     */
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    /**
     * This method returns the number of maximum allowed knots.
     * @return The number of maximum allowed knots.
     */
    public int getMaxAllowedKnots() {
        return maxAllowedKnots;
    }

    /**
     * This method sets the number maximum allowed knots to given value
     * @param maxAllowedKnots The value to set the number of maximum knots to.
     */
    public void setMaxAllowedKnots(int maxAllowedKnots) {
        this.maxAllowedKnots = maxAllowedKnots;
    }

    /**
     * This method returns the level number.
     * @return The level number.
     */
    public int getLevelNo() {
        return levelNo;
    }

    /**
     * This method sets the level number
     * @param levelNo The level number to set to.
     */
    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

}

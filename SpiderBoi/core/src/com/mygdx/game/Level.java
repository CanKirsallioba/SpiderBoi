package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.boi.SpiderBoi;
import com.mygdx.game.boi.collectableBois.CollectableBoi;
import com.mygdx.game.boi.collectableBois.StoreFlyBoi;
import com.mygdx.game.obstacles.Obstacle;
import com.mygdx.game.obstacles.PlainObstacle;
import com.mygdx.game.obstacles.SlipperyObstacle;
import com.mygdx.game.obstacles.SpikyObstacle;

import java.util.ArrayList;
import java.util.Scanner;

public class Level {

    //properties
    Scanner mapScan;
    int levelNo;
    ArrayList<Obstacle> obstacles;
    ArrayList<CollectableBoi> collectableBois;
    FileHandle levelFile;

    //constructor

    /**
     * Constructor for the level class it reads the .txt file located int he assests
     * folder for the locations of the entities, creates an arraylist for obstacles.
     * @param levelNo is the level number of the which is also the name of the .txt file.
     */
    public Level(int levelNo)
    {
        FileHandle levelFile = Gdx.files.internal("1.txt");
        this.levelNo = levelNo;
        obstacles = new ArrayList<Obstacle>();
        collectableBois = new ArrayList<CollectableBoi>();
        mapScan = new Scanner(levelFile.readString());
    }

    //methods

    /**
     * This method scans the .txt file and puts the related objects in the given positions
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
                obstacles.add(new PlainObstacle(Integer.parseInt(mapScan.next())*Gdx.graphics.getWidth()/100,
                        Integer.parseInt(mapScan.next())*Gdx.graphics.getHeight()/50));
            }
            else if(objectType.equals("sl"))
            {
                obstacles.add(new SlipperyObstacle(Integer.parseInt(mapScan.next())*Gdx.graphics.getWidth()/100,
                        Integer.parseInt(mapScan.next())*Gdx.graphics.getHeight()/50));
            }
            else if(objectType.equals("sk"))
            {
                obstacles.add(new SpikyObstacle((Integer.parseInt(mapScan.next()))*Gdx.graphics.getWidth()/100,
                        Integer.parseInt(mapScan.next())*Gdx.graphics.getHeight()/50));
            }
            else if(objectType.equals("sp"))
            {
                spiderBoi.setPosition(new Vector2(Integer.parseInt(mapScan.next())*Gdx.graphics.getWidth()/100,
                        Integer.parseInt(mapScan.next())*Gdx.graphics.getHeight()/50));
            }
            else if(objectType.equals("sfb"))
            {
                collectableBois.add(new StoreFlyBoi((Integer.parseInt(mapScan.next()))*Gdx.graphics.getWidth()/100,
                        Integer.parseInt(mapScan.next())*Gdx.graphics.getHeight()/50));
            }


        }
    }


}

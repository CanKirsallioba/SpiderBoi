package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.boi.SpiderBoi;
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
    FileHandle levelFile;

    //constructor
    public Level(int levelNo)
    {
        FileHandle levelFile = Gdx.files.internal("1.txt");
        this.levelNo = levelNo;
        obstacles = new ArrayList<Obstacle>();
        mapScan = new Scanner(levelFile.readString());
    }

    //methods
    public void showLevel(SpiderBoi spiderBoi)
    {
        while(mapScan.hasNext())
        {
            String objectType = mapScan.next();
            if(objectType.equals("pl"))
            {
                obstacles.add(new PlainObstacle(Integer.parseInt(mapScan.next()), Integer.parseInt(mapScan.next())));
            }
            else if(objectType.equals("sl"))
            {
                obstacles.add(new SlipperyObstacle(Integer.parseInt(mapScan.next()), Integer.parseInt(mapScan.next())));
            }
            else if(objectType.equals("sk"))
            {
                obstacles.add(new SpikyObstacle((Integer.parseInt(mapScan.next())), Integer.parseInt(mapScan.next())));
            }
            else if(objectType.equals("sp"))
            {
                spiderBoi.setPosition(new Vector2(Integer.parseInt(mapScan.next()), Integer.parseInt(mapScan.next())));
            }

        }
    }


}

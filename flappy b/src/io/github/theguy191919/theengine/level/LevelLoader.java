/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.theguy191919.theengine.level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author evan__000
 */
public class LevelLoader {
    
    //private static List<Level> levelList = new ArrayList<>();
    private static boolean levelLoaded = false;
    private static Map<String, Level> mapOfLevel = new HashMap<String, Level>();
    private static Level level;
    //private static boolean haveLevel
            
    public static void start(){
//        addToLevelList("EasyStart", new EasyStart());
        addToLevelList("MainGUILevel", new MainGUILevel());
//        addToLevelList("TrainsFun", new TrainsFun());
//        addToLevelList("Credits", new Credits());
        addToLevelList("FlappyLevel", new FlappyLevel());
        //loadLevel(easyStartKey);
    }
    
    private static void addToLevelList(String key, Level level){
        //int key = levelList.size();
        //levelList.add(key, new EasyStart());
        //return key;
        mapOfLevel.put(key, level);
    }
    
    public static void loadLevel(String key){
        levelLoaded = true;
        level = mapOfLevel.get(key);
        level.init();
        level.postinit();
    }
    
    public static void update(){
        if (levelLoaded){
        level.run();
        }
    }
    
    public static void reloadLevel(){
        level.destroy();
        //level = null;
        level.init();
        level.postinit();
        //loadLevel
    }
    
    public static void unloadLevel(){
        if(levelLoaded){
            level.destroy();
            levelLoaded = false;
            level = null;
        }
    }
}

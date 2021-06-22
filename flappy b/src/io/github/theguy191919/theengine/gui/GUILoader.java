/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.theguy191919.theengine.gui;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import io.github.theguy191919.theengine.texture.TextureLoader;
import io.github.theguy191919.theengine.texture.TextureRegestry;

/**
 *
 * @author evan__000
 */
public class GUILoader {
    
    //private static List<GUI> listOfGUI = new ArrayList<>();
    //private static List<GUI> loadedGUI = new LinkedList<>();
    
    private static Map<String, GUI> mapOfGUI = new HashMap<String, GUI>();
    private static Map<String, GUI> loadedGUI = new HashMap<String, GUI>();
    private static List<String> removeQueue = new LinkedList<String>();
    private static List<String> addQueue = new LinkedList<String>();
    
    public static void start(){
        TextureLoader load = new TextureLoader();
        io.github.theguy191919.theengine.texture.TextureRegestry.addTexture("Pause", "io/github/theguy191919/theengine/resource/Pause.png", load);
        addToGUIList("Pause", new Pause());
        io.github.theguy191919.theengine.texture.TextureRegestry.addTexture("Dead", "io/github/theguy191919/theengine/resource/Dead.png", load);
        addToGUIList("Death", new Death());
//        TextureRegestry.addTexture("Overlay", "io/github/theguy191919/theengine/resource/Overlay.png", load);
//        addToGUIList("Overlay", new Overlay());
        TextureRegestry.addTexture("Title", "io/github/theguy191919/theengine/resource/Title.png", load);
        addToGUIList("MainGUI", new MainGUI());
        TextureRegestry.addTexture("Clear", "io/github/theguy191919/theengine/resource/Clear.png", load);
        addToGUIList("ScoreCounter", new ScoreCounter());
    }
    
    private static void addToGUIList(String key, GUI gui){
        gui.init();
        mapOfGUI.put(key, gui);
    }
    
    public static void loadGUI(String key){
        GUI gui = mapOfGUI.get(key);
//        loadedGUI.put(key, gui);
//        gui.open();
        addQueue.add(key);
    }
    
    public static void unloadGUI(String key){
        GUI gui = mapOfGUI.get(key);
//        loadedGUI.remove(key);
//        gui.close();
        removeQueue.add(key);
    }
    
    public static void updateGUI(){
        for(String key : removeQueue){
            loadedGUI.remove(key);
            mapOfGUI.get(key).close();
        }
        removeQueue.clear();
        
        for(String key : addQueue){
            GUI gui = mapOfGUI.get(key);
            loadedGUI.put(key, gui);
            gui.open();
        }
        addQueue.clear();
        
        for (GUI gui : loadedGUI.values()){
            gui.update();
        }
        
        for(String key : removeQueue){
            loadedGUI.remove(key);
            mapOfGUI.get(key).close();
        }
        removeQueue.clear();
        
        for(String key : addQueue){
            GUI gui = mapOfGUI.get(key);
            loadedGUI.put(key, gui);
            gui.open();
        }
        addQueue.clear();
        //GUI[] listOfGUI = (GUI[]) loadedGUI.values().toArray();
//        Iterator ite = loadedGUI.values().iterator();
//        for(int a = listOfGUI.length - 1; a >= 0; a--){
//            listOfGUI[a].update();
//        }
//        ite.
//        for(int a = 0; a < loadedGUI.size(); a++) {
//            loadedGUI.get(a).update();
//            
//        }
    }
}

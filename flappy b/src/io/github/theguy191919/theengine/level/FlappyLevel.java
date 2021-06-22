/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine.level;

import io.github.theguy191919.theengine.ObjectRegestry;
import io.github.theguy191919.theengine.Tick;
import io.github.theguy191919.theengine.entity.BackGround;
import io.github.theguy191919.theengine.entity.Entity;
import io.github.theguy191919.theengine.entity.Floor;
import io.github.theguy191919.theengine.entity.Pipe;
import io.github.theguy191919.theengine.entity.Player;
import io.github.theguy191919.theengine.entity.ScoreBox;
import io.github.theguy191919.theengine.event.Event;
import io.github.theguy191919.theengine.event.EventListener;
import io.github.theguy191919.theengine.event.EventManager;
import io.github.theguy191919.theengine.gui.GUILoader;
import io.github.theguy191919.theengine.sound.SoundEffect;
import io.github.theguy191919.theengine.sound.SoundMusic;
import io.github.theguy191919.theengine.texture.TextureLoader;
import io.github.theguy191919.theengine.utility.Location;
import io.github.theguy191919.theengine.utility.Vector;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author root
 */
public class FlappyLevel extends Level implements EventListener{
    
    private int spawnTimer = 0;
    private Map<Entity, Integer> mapOfEntity = new ConcurrentHashMap<Entity, Integer>();
    //private ClickBox pauseBox = new ClickBox(new Location(0,656), new Location(640, 720), InPut.leftMouseButton);
    private SoundMusic music;
    private SoundEffect death;
    private boolean crashed = false;
    
    @Override
    void init() {
        TextureLoader loader = new TextureLoader();
        final String resourceFolder = "io/github/theguy191919/theengine/resource/";
        
        io.github.theguy191919.theengine.texture.TextureRegestry.addTexture("Bird1", resourceFolder + "Bird1.png", loader, true);
        io.github.theguy191919.theengine.texture.TextureRegestry.addTexture("Bird2", resourceFolder + "Bird2.png", loader, true);
        io.github.theguy191919.theengine.texture.TextureRegestry.addTexture("Bird3", resourceFolder + "Bird3.png", loader, true);
        io.github.theguy191919.theengine.texture.TextureRegestry.addTexture("Pipes", resourceFolder + "Pipes.png", loader, true);
        io.github.theguy191919.theengine.texture.TextureRegestry.addTexture("BackGround", resourceFolder + "BackGroundDay.png", loader, true);
        //io.github.theguy191919.theengine.texture.TextureRegestry.addTexture("Wall", resourceFolder + "BlackWall.png", loader, true);
        io.github.theguy191919.theengine.texture.TextureRegestry.addTexture("Clear", resourceFolder + "Clear.png", loader, true);
        io.github.theguy191919.theengine.texture.TextureRegestry.addTexture("Floor", resourceFolder + "MovingBottom.png", loader, true);
        
        music = new SoundMusic("AcrossTheSkies.ogg", 0.23f);
        death = new SoundEffect("death.ogg");
    }

    @Override
    void postinit() {
        GUILoader.loadGUI("ScoreCounter");
        
        EventManager.registerListener(this, "death");
        EventManager.registerListener(this, "mousepress");
        EventManager.registerListener(this, "crash");
        
        BackGround background = new BackGround(new Location(0,0), new Location(1280,720), "BackGround");
        ObjectRegestry.placeNoneSolidEntity(background);
        
//        Wall wallT = new Wall(new Location(0,-5), new Location(1280,0), "Wall", 0);
//        Wall wallB = new Wall(new Location(0,720), new Location(1280,725), "Wall", 0);
//        
//        ObjectRegestry.placeSolidEntity(wallT);
//        ObjectRegestry.placeSolidEntity(wallB);
        
        //Pipe pipU = new Pipe(new Location(0, 0), "Pipes");
        //ObjectRegestry.placeSolidEntity(pipU);
        //Pipe pipL = new Pipe(new Location(0, gap), "Pipes");
        
        String[] textArray = {"Bird1", "Bird2", "Bird3", "Bird2"};
        Player player = new Player(new Location(300,500), textArray);
        ObjectRegestry.placeSolidEntity(player);
        
        Floor floor = new Floor(new Location(0,0), "Floor");
        floor.setVector(new Vector(-0.8f,0));
        ObjectRegestry.placeSolidEntity(floor);
        
        this.music.playSound();
        this.crashed = false;
    }

    @Override
    void run() {
        
        this.spawnTimer += Tick.getTick();
        
        Iterator entrySet = this.mapOfEntity.entrySet().iterator();
        while (entrySet.hasNext()) {
            Map.Entry<Entity, Integer> entry = (Map.Entry) entrySet.next();
            if (entry.getValue() > 20000) {
                ObjectRegestry.removeEntity(entry.getKey());
                entrySet.remove();
            } else {
                this.mapOfEntity.put(entry.getKey(), entry.getValue() + Tick.getTick());
            }
        }

        if(this.spawnTimer > 1100 && !this.crashed){
            this.spawnTimer = 0;
            
            final int min = 150;
            final int max = 650;
            final int gapSize = 170;
            int gap = (int)(Math.random() * (max - min - gapSize) + min + gapSize);
            
            //System.out.println(gap);
            
            Pipe pipU = new Pipe(new Location(1480, gap), "Pipes");
            Pipe pipL = new Pipe(new Location(1480, gap - gapSize - 512), "Pipes");
            ScoreBox sBox = new ScoreBox(new Location(1520, gap - gapSize), "Clear");
            
            pipU.setVector(new Vector(-0.8f, 0f));
            pipL.setVector(new Vector(-0.8f, 0f));
            sBox.setVector(new Vector(-0.8f, 0f));
            
            ObjectRegestry.placeSolidEntity(pipU);
            ObjectRegestry.placeSolidEntity(pipL);
            ObjectRegestry.placeSolidEntity(sBox);
            
            this.mapOfEntity.put(pipU, 0);
            this.mapOfEntity.put(pipL, 0);
            this.mapOfEntity.put(sBox, 0);
        }
        
    }

    @Override
    void destroy() {
        this.music.stop();
        ObjectRegestry.removeAll();
        GUILoader.unloadGUI("ScoreCounter");
        this.crashed = false;
    }

    @Override
    public void gotEvent(Event event) {
        if(event.getEventName().equalsIgnoreCase("death")){
            GUILoader.unloadGUI("ScoreCounter");
            GUILoader.loadGUI("Death");
        }
        if(event.getEventName().equalsIgnoreCase("mousepress")){
//            if(this.pauseBox.isClicked(event)){
//                GUILoader.loadGUI("Pause");
//            }
        }
        if(event.getEventName().equalsIgnoreCase("crash")){
            this.music.stop();
            this.death.playSound();
            this.crashed = true;
        }
    }
    
}

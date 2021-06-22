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
import io.github.theguy191919.theengine.entity.Pipe;
import io.github.theguy191919.theengine.entity.Player;
import io.github.theguy191919.theengine.texture.TextureLoader;
import io.github.theguy191919.theengine.utility.Location;
import io.github.theguy191919.theengine.utility.Vector;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author evan__000
 */
public class MainGUILevel extends Level{
    
    private int spawnTimer = 0;
    private Map<Entity, Integer> mapOfEntity = new ConcurrentHashMap<Entity, Integer>();
    private Player player;

    @Override
    void init() {
        TextureLoader loader = new TextureLoader();
        final String resourceFolder = "io/github/theguy191919/theengine/resource/";
        //io.github.theguy191919.theengine.texture.TextureRegestry.addTexture("Wall", resourceFolder + "BrickWall.png", loader);
        //io.github.theguy191919.theengine.texture.TextureRegestry.addTexture("Car1", resourceFolder + "car1.png", loader);
        //io.github.theguy191919.theengine.texture.TextureRegestry.addTexture("Player", resourceFolder + "Bird1.png", loader);
        //io.github.theguy191919.theengine.texture.TextureRegestry.addTexture("BlackWall", resourceFolder + "BlackWall.png", loader);
        io.github.theguy191919.theengine.texture.TextureRegestry.addTexture("BackGround", resourceFolder + "BackGroundDay.png", loader, true);
        io.github.theguy191919.theengine.texture.TextureRegestry.addTexture("Bird1", resourceFolder + "Bird1.png", loader, true);
        io.github.theguy191919.theengine.texture.TextureRegestry.addTexture("Bird2", resourceFolder + "Bird2.png", loader, true);
        io.github.theguy191919.theengine.texture.TextureRegestry.addTexture("Bird3", resourceFolder + "Bird3.png", loader, true);
        io.github.theguy191919.theengine.texture.TextureRegestry.addTexture("Pipes", resourceFolder + "Pipes.png", loader, true);
    }

    @Override
    void postinit() {
        BackGround background = new BackGround(new Location(0,0), new Location(1280,720), "BackGround");
        ObjectRegestry.placeNoneSolidEntity(background);
        
        String[] textArray = {"Bird1", "Bird2", "Bird3", "Bird2"};
        player = new Player(new Location(608,370), textArray);
        player.setGravity(0f);
        ObjectRegestry.placeNoneSolidEntity(player);
        
//        Wall wallRight = new Wall(new Location(0,0), new Location(5,720), "Wall", 1);
//        ObjectRegestry.placeSolidEntity(wallRight);
//        Wall wallLeft = new Wall(new Location(1275,0), new Location(1280,720), "Wall", 1);
//        ObjectRegestry.placeSolidEntity(wallLeft);
//        Wall wallUp = new Wall(new Location(0,715), new Location(1280,720), "BlackWall", Direction.NORTH.toInt());
//        ObjectRegestry.placeSolidEntity(wallUp);
//        Wall wallDown = new Wall(new Location(0,0), new Location(1280,5), "BlackWall", 0);
//        ObjectRegestry.placeSolidEntity(wallDown);
//        
//        for(int a = 0; a < 20; a++){
//            Car car = new Car(new Location(50,50), "Car1");
//            car.setVector(new Vector((float)Math.random(), (float)Math.random()));
//            ObjectRegestry.placeSolidEntity(car);
//        }
    }

    @Override
    void run() {
        //player.setVector(new Vector(0,0.002f*Tick.getTick()));
        player.setLocation(new Location(608,370));
        this.spawnTimer += Tick.getTick();
        
        Iterator entrySet = this.mapOfEntity.entrySet().iterator();
        while (entrySet.hasNext()) {
            Map.Entry<Entity, Integer> entry = (Map.Entry) entrySet.next();
            if (entry.getValue() > 200000) {
                ObjectRegestry.removeEntity(entry.getKey());
                entrySet.remove();
            } else {
                this.mapOfEntity.put(entry.getKey(), entry.getValue() + Tick.getTick());
            }
        }

        if(this.spawnTimer > 1500){
            this.spawnTimer = 0;
            
            //int gap = (int)(Math.random() * 220 + 351);
            
            //System.out.println(gap);
            
            Pipe pipU = new Pipe(new Location(1280, 494), "Pipes");
            Pipe pipL = new Pipe(new Location(1280, -202), "Pipes");
            //ScoreBox sBox = new ScoreBox(new Location(1280, gap - 200), "Clear");
            
            pipU.setVector(new Vector(-0.8f, 0f));
            pipL.setVector(new Vector(-0.8f, 0f));
            //sBox.setVector(new Vector(-0.8f, 0f));
            
            ObjectRegestry.placeNoneSolidEntity(pipU);
            ObjectRegestry.placeNoneSolidEntity(pipL);
            //ObjectRegestry.placeSolidEntity(sBox);
            
            this.mapOfEntity.put(pipU, 0);
            this.mapOfEntity.put(pipL, 0);
            //this.mapOfEntity.put(sBox, 0);
        }
    }

    @Override
    void destroy() {
        ObjectRegestry.removeAll();
    }
    
}

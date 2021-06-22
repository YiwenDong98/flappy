/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine.entity;

import io.github.theguy191919.theengine.ObjectRegestry;
import io.github.theguy191919.theengine.event.Event;
import io.github.theguy191919.theengine.event.EventListener;
import io.github.theguy191919.theengine.event.EventManager;
import io.github.theguy191919.theengine.utility.HitBox;
import io.github.theguy191919.theengine.utility.Location;
import io.github.theguy191919.theengine.utility.Vector;

/**
 *
 * @author root
 */
public class Floor extends Entity implements EventListener{
    
    private String textureName;
    private boolean spawnedSomething = false;

    public Floor(Location start, String textureName) {
        super(start, textureName);
        super.Height = 64;
        super.Width = 64;
        super.hitbox = new HitBox(new Location(0,0), new Location(64,64));
        this.textureName = textureName;
        EventManager.registerListener(this, "crash");
    }

    @Override
    public void colideWith(Entity other) {
        
    }

    @Override
    public void logic() {
        this.update();
        if(this.location.getX()< 1280 && !this.spawnedSomething){
            Floor floor = new Floor(new Location(this.location.getX()+64, this.location.getY()), textureName);
            floor.vector = this.vector;
            ObjectRegestry.placeSolidEntity(floor);
            this.spawnedSomething = true;
        } else if (this.location.getX() < -200){
            ObjectRegestry.removeEntity(this);
        }
    }

    @Override
    public void gotEvent(Event event) {
        if(event.getEventName().equalsIgnoreCase("crash")){
            this.vector = new Vector(0,0);
        }
    }
    
    @Override
    public void destory(){
        EventManager.removeListener(this);
    }
}

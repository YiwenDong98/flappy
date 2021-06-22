/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine.entity;

import io.github.theguy191919.theengine.event.Event;
import io.github.theguy191919.theengine.event.EventListener;
import io.github.theguy191919.theengine.event.EventManager;
import io.github.theguy191919.theengine.utility.HitBox;
import io.github.theguy191919.theengine.utility.Location;

/**
 *
 * @author root
 */
public class Pipe extends Entity implements EventListener{
    
    private boolean crashed = false;

    public Pipe(Location start, String textureName) {
        super(start, textureName);
        super.Height = 512;
        super.Width = 72;
        super.hitbox = new HitBox(new Location(0,0), new Location(72,512));
        EventManager.registerListener(this, "crash");
    }

    @Override
    public void colideWith(Entity other) {
        
    }

    @Override
    public void logic() {
        if(crashed){
            this.vector.setX(0f);
            this.vector.setY(0f);
        }
        this.update();
    }

    @Override
    public void gotEvent(Event event) {
        if(event.getEventName().equalsIgnoreCase("crash")){
            this.crashed = true;
        }
    }
    
    @Override
    public void destory(){
        EventManager.removeListener(this);
    }
    
}

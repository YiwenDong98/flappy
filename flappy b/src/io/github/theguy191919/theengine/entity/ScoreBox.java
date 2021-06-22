/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine.entity;

import io.github.theguy191919.theengine.Tick;
import io.github.theguy191919.theengine.event.Event;
import io.github.theguy191919.theengine.event.EventListener;
import io.github.theguy191919.theengine.event.EventManager;
import io.github.theguy191919.theengine.sound.SoundEffect;
import io.github.theguy191919.theengine.utility.HitBox;
import io.github.theguy191919.theengine.utility.Location;

/**
 *
 * @author root
 */
public class ScoreBox extends Entity implements EventListener{
    
    private boolean hit = false;
    private SoundEffect scoreEffect = new SoundEffect("point.ogg");
    private boolean crashed = false;
    
    public ScoreBox(Location start, String textureName) {
        super(start, textureName);
        this.Height = 200;
        this.Width = 64;
        this.hitbox = new HitBox(new Location(10, -20), new Location(128, 220));
        EventManager.registerListener(this, "crash");
    }

    @Override
    public void colideWith(Entity other) {
        if(other instanceof Player){
        if(!hit){
            hit = true;
            this.scoreEffect.playSound();
            EventManager.triggerEvent(new Event(Tick.currentTime(), "score", "The player scored something"));
        }
        }
    }

    @Override
    public void logic() {
        if(!crashed){
            this.update();
        }
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

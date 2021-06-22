/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.theguy191919.theengine.gui;

import io.github.theguy191919.theengine.InPut;
import io.github.theguy191919.theengine.ObjectRegestry;
import io.github.theguy191919.theengine.Tick;
import io.github.theguy191919.theengine.event.Event;
import io.github.theguy191919.theengine.event.EventListener;
import io.github.theguy191919.theengine.event.EventManager;
import io.github.theguy191919.theengine.level.LevelLoader;
import io.github.theguy191919.theengine.texture.Texture;
import io.github.theguy191919.theengine.texture.TextureLoader;
import io.github.theguy191919.theengine.texture.TextureRegestry;
import io.github.theguy191919.theengine.utility.ClickBox;
import io.github.theguy191919.theengine.utility.Location;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author evan__000
 */
public class Pause extends GUI implements EventListener{
    
    private ClickBox resumeBox = new ClickBox(new Location(535,276), new Location(745, 356), InPut.leftMouseButton);
    private ClickBox homeBox = new ClickBox(new Location(535,177), new Location(745, 257), InPut.leftMouseButton);
    private boolean loaded = false;
    private boolean resume = false;
    private boolean home = false;
    
    public Pause(){
        super(new Location(0, 0), TextureRegestry.getTexture("Pause"));
        super.Height = 720;
        super.Width = 1280;
    }

    @Override
    void init() {
        EventManager.registerListener(this, "mousepress");
        resume = false;
        home = false;
        //texture.
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void open() {
        Tick.pauseTick();
        EventManager.triggerEvent(new Event(Tick.currentTime(), "pause", "Game is being paused"));
        loaded = true;
        resume = false;
        home = false;
        //ObjectRegestry.pause();
    }

    @Override
    void update() {
//        if(InPut.pressed(Keyboard.KEY_SPACE)){
//            GUILoader.unloadGUI(0);
//        }
        io.github.theguy191919.theengine.draw.GUIDraw.draw(this);
        if(InPut.pressed(Keyboard.KEY_SPACE) || this.resume) {
            resume = false;
            GUILoader.unloadGUI("Pause");
            //LevelLoader.reloadLevel();
        }
        if(InPut.pressed(Keyboard.KEY_BACK) || this.home){
            home = false;
            GUILoader.unloadGUI("Pause");
            LevelLoader.unloadLevel();
            GUILoader.loadGUI("MainGUI");
        }
    }

    @Override
    void close() {
        Tick.startTick();
        EventManager.triggerEvent(new Event(Tick.currentTime(), "unpause", "Game is being unpaused"));
        loaded = false;
        resume = false;
        home = false;
        //ObjectRegestry.unPause();
    }

    @Override
    public void gotEvent(Event event) {
        if(event.getEventName().equalsIgnoreCase("mousepress")){
            if(this.homeBox.isClicked(event)){
                this.home = true;
            } else if (this.resumeBox.isClicked(event)){
                this.resume = true;
            }
        }
    }
    
}

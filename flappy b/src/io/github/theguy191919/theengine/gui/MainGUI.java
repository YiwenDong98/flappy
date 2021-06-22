/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine.gui;

import io.github.theguy191919.theengine.InPut;
import io.github.theguy191919.theengine.FlappyB;
import io.github.theguy191919.theengine.draw.GUIDraw;
import io.github.theguy191919.theengine.event.Event;
import io.github.theguy191919.theengine.event.EventListener;
import io.github.theguy191919.theengine.event.EventManager;
import io.github.theguy191919.theengine.level.LevelLoader;
import io.github.theguy191919.theengine.texture.TextureRegestry;
import io.github.theguy191919.theengine.utility.ClickBox;
import io.github.theguy191919.theengine.utility.Location;
import io.github.theguy191919.theengine.utility.font.Font;
import io.github.theguy191919.theengine.utility.font.FontUtil;
import java.util.List;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author evan__000
 */
public class MainGUI extends GUI implements EventListener{
    
    private List<Font> listOfFont;
    private ClickBox quitBox = new ClickBox(new Location(1005,20), new Location(1215, 100), InPut.leftMouseButton);
    private boolean loaded = false;
    

    public MainGUI() {
        super(new Location(0, 0), TextureRegestry.getTexture("Title"));
        super.Height = 720;
        super.Width = 1280;
    }

    @Override
    void init() {
        listOfFont = FontUtil.getFont(FlappyB.game.version, new Location(180,10));
        EventManager.registerListener(this, "mousepress");
    }

    @Override
    void open() {
        this.loaded = true;
        //System.out.println("thing");
        //LevelLoader.unloadLevel();
        LevelLoader.loadLevel("MainGUILevel");
        //System.out.print("hi");
    }

    @Override
    void update() {
        
        
        
        GUIDraw.draw(this);
//        List<Font> listOfFont = FontUtil.getFont("123: 123", location);
        for(Font font : listOfFont){
            GUIDraw.draw(font);
        }
        GUIDraw.draw(this);
        if(InPut.pressed(Keyboard.KEY_SPACE) 
                || InPut.pressed(Keyboard.KEY_W) 
                || InPut.pressed(Keyboard.KEY_UP) 
                || InPut.pressed(Keyboard.KEY_DOWN) 
                || InPut.pressed(Keyboard.KEY_S) 
                || InPut.mousePressed(InPut.leftMouseButton) 
                || InPut.mousePressed(InPut.rightMouseButton)){
            GUILoader.unloadGUI("MainGUI");
            LevelLoader.unloadLevel();
            LevelLoader.loadLevel("FlappyLevel");
        }
        if(InPut.pressed(Keyboard.KEY_ESCAPE)){
            FlappyB.game.running = false;
        }
    }

    @Override
    void close() {
        this.loaded = false;
    }

    @Override
    public void gotEvent(Event event) {
        if(event.getEventName().equalsIgnoreCase("mousepress")){
            if(this.quitBox.isClicked(event) && this.loaded){
                FlappyB.game.running = false;
            }
        }
    }

}

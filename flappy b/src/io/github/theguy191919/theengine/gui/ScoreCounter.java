/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine.gui;

import io.github.theguy191919.theengine.FlappyB;
import io.github.theguy191919.theengine.InPut;
import io.github.theguy191919.theengine.draw.GUIDraw;
import io.github.theguy191919.theengine.event.Event;
import io.github.theguy191919.theengine.event.EventListener;
import io.github.theguy191919.theengine.event.EventManager;
import io.github.theguy191919.theengine.io.PropertyManager;
import io.github.theguy191919.theengine.texture.Texture;
import io.github.theguy191919.theengine.texture.TextureLoader;
import io.github.theguy191919.theengine.texture.TextureRegestry;
import io.github.theguy191919.theengine.utility.ClickBox;
import io.github.theguy191919.theengine.utility.Location;
import io.github.theguy191919.theengine.utility.font.Font;
import io.github.theguy191919.theengine.utility.font.FontUtil;
import java.util.List;

/**
 *
 * @author root
 */
public class ScoreCounter extends GUI implements EventListener{
    
    private int score = 0;
    private boolean paused = false;
    private ClickBox pauseBox = new ClickBox(new Location(0,656), new Location(64, 720), InPut.leftMouseButton);
    private boolean crashed = false;
    private boolean loaded = false;
    
    public ScoreCounter() {
        super(new Location(600, 500), TextureRegestry.getTexture("Clear"));
    }

    @Override
    void init() {
        TextureLoader loader = new TextureLoader();
        final String resourceFolder = "io/github/theguy191919/theengine/resource/";
        
        io.github.theguy191919.theengine.texture.TextureRegestry.addTexture("PauseButton", resourceFolder + "PauseButton.png", loader, true);
        //TextureRegestry.addTexture("PauseButton", )
        EventManager.registerListener(this, "mousepress");
        EventManager.registerListener(this, "death");
        EventManager.registerListener(this, "score");
        EventManager.registerListener(this, "pause");
        EventManager.registerListener(this, "unpause");
        EventManager.registerListener(this, "crash");
    }

    @Override
    void open() {
        this.score = 0;
        this.loaded = true;
    }

    @Override
    void update() {
        if(!this.paused){
        List<Font> listOfFonts = FontUtil.getFont(String.valueOf(this.score), this.location);
        
        for(Font font : listOfFonts){
            GUIDraw.draw(font);
        }
        GUIDraw.draw(64, 64, 0, 656, TextureRegestry.getTexture("PauseButton"));
        }
    }

    @Override
    void close() {
        this.score = 0;
        this.crashed = false;
        this.loaded = false;
    }

    @Override
    public void gotEvent(Event event) {
        if(event.getEventName().equalsIgnoreCase("death")){
            PropertyManager.setProperty("lastscore", String.valueOf(this.score));
            if(Integer.valueOf(PropertyManager.getProperty("highscore", String.valueOf(0))) < this.score){
                if(!FlappyB.game.debug){
                    PropertyManager.setProperty("highscore", String.valueOf(this.score));
                }
            }
            this.score = 0;
        } else if (event.getEventName().equalsIgnoreCase("score")){
            this.score++;
        } else if (event.getEventName().equalsIgnoreCase("pause")){
            this.paused = true;
        } else if (event.getEventName().equalsIgnoreCase("unpause")){
            this.paused = false;
        } else if (event.getEventName().equalsIgnoreCase("mousepress")){
            if(this.pauseBox.isClicked(event) && !this.paused && !this.crashed && this.loaded){
                //System.out.println("pausing from score counter");
                GUILoader.loadGUI("Pause");
            }
        } else if (event.getEventName().equalsIgnoreCase("crash")){
            this.crashed = true;
        }
    }
    
}

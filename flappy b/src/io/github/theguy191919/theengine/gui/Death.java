/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.theguy191919.theengine.gui;

import io.github.theguy191919.theengine.InPut;
import io.github.theguy191919.theengine.Tick;
import io.github.theguy191919.theengine.draw.GUIDraw;
import io.github.theguy191919.theengine.event.Event;
import io.github.theguy191919.theengine.event.EventListener;
import io.github.theguy191919.theengine.event.EventManager;
import io.github.theguy191919.theengine.io.PropertyManager;
import io.github.theguy191919.theengine.level.LevelLoader;
import io.github.theguy191919.theengine.texture.Texture;
import io.github.theguy191919.theengine.texture.TextureLoader;
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
public class Death extends GUI implements EventListener{
    
    private ClickBox restartBox = new ClickBox(new Location(422,110), new Location(630, 190), InPut.leftMouseButton);
    private ClickBox homeBox = new ClickBox(new Location(653,110), new Location(864, 190), InPut.leftMouseButton);
    private boolean restart = false;
    private boolean home = false;

    public Death(){
        super(new Location(0, 0), TextureRegestry.getTexture("Dead"));
        super.Height = 720;
        super.Width = 1280;
    }

    @Override
    void init() {
        EventManager.registerListener(this, "mousepress");
    }

    @Override
    void open() {
        Tick.pauseTick();
        this.home = false;
        this.restart = false;
    }

    @Override
    void update() {
        GUIDraw.draw(this);
        String highScore = PropertyManager.getProperty("highscore", "0");
        String lastScore = PropertyManager.getProperty("lastscore", "0");
        List<Font> listOfFont = FontUtil.getFont(lastScore, new Location(700, 390));
        listOfFont.addAll(FontUtil.getFont(highScore, new Location(700,280)));
        for(Font font : listOfFont){
            GUIDraw.draw(font);
        }
        if(InPut.pressed(Keyboard.KEY_SPACE) || this.restart) {
            this.restart = false;
            this.home = false;
            GUILoader.unloadGUI("Death");
            this.reloadLevel();
        }
        if(InPut.pressed(Keyboard.KEY_BACK) || this.home){
            this.home = false;
            this.restart = false;
            GUILoader.unloadGUI("Death");
            LevelLoader.unloadLevel();
            GUILoader.loadGUI("MainGUI");
        }
        
    }
    
    private void reloadLevel(){
        LevelLoader.reloadLevel();
    }

    @Override
    void close() {
        Tick.startTick();
        this.home = false;
        this.restart = false;
    }

    @Override
    public void gotEvent(Event event) {
        if(event.getEventName().equalsIgnoreCase("mousepress")){
            if(this.homeBox.isClicked(event)){
                this.home = true;
//                GUILoader.unloadGUI("Death");
//                LevelLoader.unloadLevel();
//                GUILoader.loadGUI("MainGUI");
            } else if (this.restartBox.isClicked(event)){
                this.restart = true;
//                GUILoader.unloadGUI("Death");
//                this.reloadLevel();
            }
        }
    }
    
}

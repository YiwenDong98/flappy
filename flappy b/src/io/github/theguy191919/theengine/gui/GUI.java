/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.theguy191919.theengine.gui;

import io.github.theguy191919.theengine.draw.Drawable;
import io.github.theguy191919.theengine.texture.Texture;
import io.github.theguy191919.theengine.utility.Location;

/**
 *
 * @author evan__000
 */
public abstract class GUI implements Drawable{
    
    Location location;
    int Height = 0;
    int Width = 0;
    Texture texture;
    
    public GUI(Location location, Texture texture){
        this.location = location;
        this.texture = texture;
    }
    
    abstract void init();
    abstract void open();
    abstract void update();
    abstract void close();

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public int getHeight() {
        return Height;
    }

    @Override
    public int getWidth() {
        return Width;
    }
    
    @Override
    public float getRotation(){
        return 0;
    }
    
    @Override
    public float getScale(){
        return 1;
    }
}

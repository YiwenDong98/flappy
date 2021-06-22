/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine.utility.font;

import io.github.theguy191919.theengine.draw.Drawable;
import io.github.theguy191919.theengine.texture.Texture;
import io.github.theguy191919.theengine.utility.Location;

/**
 *
 * @author Yiwen Dong
 */
public class Font implements Drawable{
    
    Location location;
    Texture texture;
    int Height = 64;
    int Width = 32;
    int FontSize = 1;
    
    public Font(Location location, Texture texture){
    this.location = location;
    this.texture = texture;
    }

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
    public float getRotation() {
        return 0;
    }

    @Override
    public float getScale() {
        return 0;
    }
}

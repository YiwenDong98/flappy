/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine.draw;

import io.github.theguy191919.theengine.texture.Texture;
import io.github.theguy191919.theengine.utility.Location;

/**
 *
 * @author Yiwen Dong
 */
public interface Drawable {
    
    Location getLocation();
    Texture getTexture();
    int getHeight();
    int getWidth();
    float getRotation();
    float getScale();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.theguy191919.theengine.entity;

import io.github.theguy191919.theengine.utility.HitBox;
import io.github.theguy191919.theengine.utility.Location;

/**
 *
 * @author evan__000
 */
public class BackGround extends Entity{

    public BackGround(Location start, Location ending, String textureName) {
        super(start, textureName);
        super.Width = Math.abs((int)(ending.getX() - start.getX()));
        super.Height = Math.abs((int)(ending.getY() - start.getY()));
        this.hitbox = new HitBox(new Location(0,0), new Location(Width, Height));
    }

    @Override
    public void colideWith(Entity other) {
        
    }

    @Override
    public void logic() {
        io.github.theguy191919.theengine.draw.QuadDraw.draw(Width, Height, this.location.getX(), this.location.getY(), texture);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.theguy191919.theengine.utility;

/**
 *
 * @author evan__000
 */
public class Location {
    
    private float x;
    private float y;
    
    public Location(float x, float y){
        this.x = x;
        this.y = y;
    }
    
    public Location(Location loc) {
        this.x = loc.getX();
        this.y = loc.getY();
    }
    
    public Location addLocation(Location loc){
        this.x = this.x + loc.getX();
        this.y = this.y + loc.getY();
        return this;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
    public void setX(float x){
        this.x = x;
    }
    
    public void setY(float y){
        this.y = y;
    }
    
    public void addToX(float x){
        this.x = this.x + x;
    }
    
    public void addToY(float y){
        this.y = this.y + y;
    }
    
}

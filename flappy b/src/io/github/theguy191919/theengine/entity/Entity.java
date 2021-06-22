/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.theguy191919.theengine.entity;

import io.github.theguy191919.theengine.draw.Drawable;
import java.awt.Rectangle;
import io.github.theguy191919.theengine.utility.Location;
import io.github.theguy191919.theengine.utility.Vector;
import io.github.theguy191919.theengine.texture.Texture;
import io.github.theguy191919.theengine.texture.TextureRegestry;
import io.github.theguy191919.theengine.utility.HitBox;

/**
 *
 * @author evan__000
 */
public abstract class Entity implements Drawable{
    protected Location location;
    protected Texture texture;
    protected Vector vector = new Vector(0f,0f);
    protected int Height = 128;
    protected int Width = 256;
    protected Rectangle rectangleThis = new Rectangle();
    protected Rectangle rectangleThat = new Rectangle();
    protected HitBox hitbox = new HitBox(new Location(0,0), new Location(Width, Height));
    protected float rotation = 0.0f;

    /**
     *
     * @param ending
     * @param textureName
     */
    public Entity(Location start, String textureName) {
        //this.Starting = start;
        this.location = new Location(start);
        //this.Ending = ending;
        this.texture = TextureRegestry.getTexture(textureName);
    }
    
    /**
     *
     * @param Speed
     * @param Height
     * @param Length
     */
    public void update() {
        updateLocationX();
        updateLocationY();
        io.github.theguy191919.theengine.draw.QuadDraw.draw(this);
        //System.out.println("X: " + this.location.getX());
        //System.out.println("Y: " + this.location.getY());
    }
    
    /**
     *
     * @param Speed
     * @param range
     */
    private void updateLocationX() {
        this.location.setX(this.getLocationX() + io.github.theguy191919.theengine.Tick.getTick() * vector.getX() / 3);
    }
    
    private void updateLocationY() {
        this.location.setY(this.getLocationY() + io.github.theguy191919.theengine.Tick.getTick() * vector.getY() / 3);
    }

    /**
     *
     * @return
     */
    public float getLocationX() {
        return this.location.getX();
    }
    
    public float getLocationY() {
        return this.location.getY();
    }
    
    @Override
    public Location getLocation(){
        return this.location;
    }
    
    public Vector getVector() {
        return vector;
    }
    
    @Override
    public int getWidth(){
        return this.Width;
    }
    
    @Override
    public int getHeight(){
        return this.Height;
    }
    
    @Override
    public Texture getTexture(){
        return this.texture;
    }
    
    @Override
    public float getRotation(){
        return this.rotation;
    }
    
    @Override
    public float getScale(){
        return 0;
    }
    
//    public int getID(){
//        return id;
//    }
    
    public void setLocation(Location loc){
        this.location = loc;
    }
    
    public void setVector(Vector vec){
        this.vector = vec;
    }
    
//    public void setID(int id){
//        this.id = id;
//    }
    
    public boolean colidedWith(Entity other) {
        rectangleThis.setBounds(
                (int)(this.hitbox.getLocationBL().getX() + this.getLocationX()), 
                (int)(this.hitbox.getLocationBL().getY() + this.getLocationY()), 
                this.hitbox.getWidth(), 
                this.hitbox.getHeight());
        //rectangleThis.setBounds((int)this.getLocationX(), (int)this.getLocationY(), Width, Height);
        rectangleThat.setBounds(
                (int)(other.hitbox.getLocationBL().getX() + other.getLocationX()), 
                (int)(other.hitbox.getLocationBL().getY() + other.getLocationY()), 
                other.hitbox.getWidth(), 
                other.hitbox.getHeight());        
        //rectangleThat.setBounds((int)other.getLocationX(), (int)other.getLocationY(), other.Width, other.Height);

        return rectangleThis.intersects(rectangleThat);
    }
    
    public void destory(){
        
    }
                            
    public abstract void colideWith(Entity other);
    
    public abstract void logic();
}

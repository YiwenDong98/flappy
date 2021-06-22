/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine.entity;

import io.github.theguy191919.theengine.FlappyB;
import io.github.theguy191919.theengine.InPut;
import io.github.theguy191919.theengine.Tick;
import io.github.theguy191919.theengine.event.Event;
import io.github.theguy191919.theengine.event.EventListener;
import io.github.theguy191919.theengine.event.EventManager;
import io.github.theguy191919.theengine.utility.Location;
import io.github.theguy191919.theengine.sound.SoundEffect;
import io.github.theguy191919.theengine.texture.Texture;
import io.github.theguy191919.theengine.texture.TextureRegestry;
import io.github.theguy191919.theengine.utility.HitBox;
import io.github.theguy191919.theengine.utility.Vector;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Yiwen Dong
 */
public class Player extends Entity implements EventListener{
    
    private boolean isMoving = false;
    private boolean debug = FlappyB.game.debug;
    private SoundEffect flapSound = new SoundEffect("flap.ogg");
    private boolean paused = false;
    private boolean crashed = false;
    private boolean death = false;
    private Vector calcVector = new Vector(0.8f, 0.0f);
    private int animationTime = 0;
    private int currentFrame = 0;
    private float gravity = 0.0098f;
    private Texture[] arrayOfTextures;
    
    public Player(Location starting, String[] textureName){
        super(starting, textureName[0]);
        this.Height = 48;
        this.Width = 48;
        this.hitbox = new HitBox(new Location(6,6), new Location(42, 42));
        EventManager.registerListener(this, "pause");
        EventManager.registerListener(this, "unpause");
        EventManager.registerListener(this, "death");
        this.arrayOfTextures = new Texture[textureName.length];
        for(int a = 0; a < textureName.length; a++){
            this.arrayOfTextures[a] = TextureRegestry.getTexture(textureName[a]);
        }
        //EventManager.registerListener(this, "crashed");
        //this.Drag = 1;
    }
    
    @Override
    public void colideWith(Entity other){
        if (!FlappyB.game.debug && (other instanceof Pipe || other instanceof Floor)) {
            if(!this.crashed){
            EventManager.triggerEvent(new Event(Tick.currentTime(), "crash", "Player hit something"));
            this.crashed = true;
            }
            //GUILoader.loadGUI("Death");
        }
        //System.out.println("Colided" + other.location.getX() + "   " + other.location.getY());
        
        
    }
    
    @Override
    public void logic(){
        this.updateVector();
        if(!this.paused && !this.crashed){
            this.updateAnimation();
        }
        //vector = Momentum.applyDrag(vector);
        //System.out.println("X: " + vector.getX());
        this.update();
    }
    
    private void updateVector(){
        if(!this.paused && !this.crashed){
            this.vector.setX(0.0F);
            this.vector.setY(this.vector.getY() - 1.8f * getGravity() * Tick.getTick() / 3.0F);
            
            float flapSpeed = 1.7f;
        //this.vector.setY(0f);
//            if(io.github.theguy191919.theengine.InPut.pressed(Keyboard.KEY_LEFT)){
//                if (debug){
//                    //this.vector.setX(-1.0f);
//                    this.isMoving = true;
//                } else if (!debug && location.getX() >= 0){
//                    //this.vector.setX(-1.0f);
//                    this.isMoving = true;
//                }
//            }
//            if(io.github.theguy191919.theengine.InPut.pressed(Keyboard.KEY_RIGHT)){
//                if (debug){
//                    //this.vector.setX(1.0f);
//                    this.isMoving = true;
//                } else if (!debug && location.getX() <= 1216){
//                    //this.vector.setX(1.0f);
//                    this.isMoving = true;
//                }
//            }
            if(InPut.pressed(Keyboard.KEY_UP) || InPut.pressed(Keyboard.KEY_W) || InPut.pressed(Keyboard.KEY_SPACE) || InPut.mousePressed(InPut.leftMouseButton)) {
                //System.out.println("Work?");
                if (debug){
                    this.playEffect(flapSound);
                    this.vector.setY(flapSpeed);
//                    if(this.vector.getY() > 0){
//                        this.vector.setY(this.vector.getY() + flapSpeed);
//                    } else {
//                        this.vector.setY(flapSpeed);
//                    }
                    this.isMoving = true;
                } else if (!debug && location.getY() <= 650){
                    this.playEffect(flapSound);
                    this.vector.setY(flapSpeed);
//                    if(this.vector.getY() > 0){
//                        this.vector.setY(this.vector.getY() + flapSpeed);
//                    } else {
//                        this.vector.setY(flapSpeed);
//                    }
                    this.isMoving = true;
                }
            }
            if (InPut.pressed(Keyboard.KEY_DOWN) || InPut.pressed(Keyboard.KEY_LSHIFT) || InPut.pressed(Keyboard.KEY_S) || InPut.mousePressed(InPut.rightMouseButton)) {
                if (debug){
                    this.playEffect(flapSound);
                    this.vector.setY(-flapSpeed);
//                    if(this.vector.getY() < 0){
//                        this.vector.setY(this.vector.getY() - flapSpeed);
//                    } else {
//                        this.vector.setY(-flapSpeed);
//                    }
                    this.isMoving = true;
                } else if (!debug && location.getY() >= 0){
                    this.playEffect(flapSound);
                    this.vector.setY(-flapSpeed);
//                    if(this.vector.getY() < 0){
//                        this.vector.setY(this.vector.getY() - flapSpeed);
//                    } else {
//                        this.vector.setY(-flapSpeed);
//                    }
                    this.isMoving = true;
                }
                
            }
            
            this.rotation = this.findAngle();
        }
        if (this.crashed && !death) {
            if (this.location.getY() > 0) {
                this.vector.setX(0.0F);
                this.vector.setY(this.vector.getY() - 0.0098F * Tick.getTick() / 3.0F);
                this.rotation = (float)-Math.PI/2;
            } else {
                death = true;
                EventManager.triggerEvent(new Event(Tick.currentTime(), "death", "Player is dead"));
            }
        }
    }
    
    private float findAngle(){
        this.calcVector.setY(this.vector.getY());
        //System.out.println(this.calcVector.getY());
        return this.calcVector.getPitch();
    }
    
    private void updateAnimation(){
        this.animationTime = this.animationTime + Tick.getTick();
        while(animationTime > 70){
            this.currentFrame++;
            if(this.currentFrame >= this.arrayOfTextures.length){
                this.currentFrame = 0;
            }
            animationTime = animationTime - 70;
            this.texture = this.arrayOfTextures[this.currentFrame];
        }
        
    }
    
    private void playEffect(SoundEffect effect){
        if(!effect.playing()){
            effect.playSound();
        }
    }

    @Override
    public void gotEvent(Event event) {
        if(event.getEventName().equalsIgnoreCase("pause")){
            this.paused = true;
        }
        if(event.getEventName().equalsIgnoreCase("unpause")){
            this.paused = false;
        }
        if(event.getEventName().equalsIgnoreCase("death")){
            this.paused = true;
        }
    }

    /**
     * @return the gravity
     */
    public float getGravity() {
        return gravity;
    }

    /**
     * @param gravity the gravity to set
     */
    public void setGravity(float gravity) {
        this.gravity = gravity;
    }
    
    @Override
    public void destory(){
        EventManager.removeListener(this);
    }
}

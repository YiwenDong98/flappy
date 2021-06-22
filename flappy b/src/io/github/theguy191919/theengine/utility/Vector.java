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
public class Vector {
    
    private float x = 0.0f;
    private float y = 0.0f;
    private float mass;
    private final float StringdragCoeff = 1.75f;
    private boolean havePhysics = false;
    
    public Vector(float x, float y){
        this.x = x;
        this.y = y;
        //System.out.println(x + "is wtf");
    }
    
//    public void setSpeed(){
//        
//    }
    
    public void setPitch(float pitch){
        float power = this.getPower();
        this.x = power * (float)Math.cos(pitch);
        this.y = power * (float)Math.sin(pitch);
    }
    
    public float getPitch(){
        return (float)Math.atan2(y, x);
    }
    
    public void setX(float x){
        this.x = x;
    }
    
    public void setY(float y){
        this.y = y;
    }
    
    public void addVector(Vector vector){
        this.x = vector.getX() + x;
        this.y = vector.getY() + y;
    }
    
    public void addX(float x){
        this.x = this.x + x;
    }
    
    public void addY(float y){
        this.y = this.y + y;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
    public float getPower(){
//        if(x == 0){
//            System.out.println("X is 0");
//            return y;
//        }
//        if(y == 0){
//            return x;
//        }
        //System.out.println("vectorX" + x);
        float a = x * x + y * y;
        //System.out.println("thing: " + a);
        return (float)Math.pow(a, 0.5);
    }
    
    public void setPower(float pow){
//        if(pow == 0 || x == 0 && y == 0){
//            //System.out.println("Power is 0");
//            x = 0;
//            y = 0;
//        }
        float ratio = getPower() / pow;
//        if(ratio == 0){
//            x = 0;
//            y = 0;
//            System.out.println("What??");
//            return;
//        }
        x = x / ratio;
        y = y / ratio;
    }
}

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
public class HitBox {
    
    Location a1; //bottom left corner
    Location a2; //top right corner
    //int width;
    //int height;
    
    public HitBox(Location offSet, int width, int height){
        //this.width = width;
        //this.height = height;
        a1 = new Location(offSet);
        a2 = new Location(offSet);
        a2.addToX(width);
        a2.addToY(height);
        System.out.println("A1: " + a1.getX());
        System.out.println("A2: " + a2.getX());
    }
    public HitBox(Location a1, Location a2){
        this.a1 = a1;
        this.a2 = a2;
    }
    
    public Location getLocationBL(){
        return a1;
    }
    
    public Location getLocationBR(){
        return new Location(a2.getX(), a1.getY());
    }
    
    public Location getLocationTL(){
        return new Location(a1.getX(), a2.getY());
    }
    
    public Location getLocationTR(){
        return a2;
    }
    
    public int getWidth(){
        //System.out.println("Width: " + Math.abs((int)(a2.getX() - a1.getX())));
        return Math.abs((int)(a2.getX() - a1.getX()));
    }
    
    public int getHeight(){
        //System.out.println("Height: " + Math.abs((int)(a2.getY() - a1.getY())));
        return Math.abs((int)(a2.getY() - a1.getY()));
    }
}

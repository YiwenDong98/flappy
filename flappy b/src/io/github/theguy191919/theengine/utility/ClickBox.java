/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine.utility;

import io.github.theguy191919.theengine.event.Event;

/**
 *
 * @author root
 */
public class ClickBox {
    
    private Location blLocation;
    private Location trLocation;
    private int button;
    
    public ClickBox(Location blLocation, Location trLocation, int button){
        this.blLocation = blLocation;
        this.trLocation = trLocation;
        this.button = button;
    }
    
    public boolean isClicked(Event event){
        if(event.getEventName().equalsIgnoreCase("mousepress")){
            int lastIndex = 0;
            float[] listOfResult = new float[3];
            int arrayIndex = 0;
            for(int a = 0; a < event.getInfo().length(); a++){
                if(event.getInfo().charAt(a) == '|'){
                    listOfResult[arrayIndex] = Float.valueOf(event.getInfo().substring(lastIndex, a));
                    arrayIndex++;
                    lastIndex = a + 1;
                }
            }
            if((int)listOfResult[0] == button){
                if(listOfResult[1] >= this.blLocation.getX() && 
                        listOfResult[1] <= this.trLocation.getX() && 
                        listOfResult[2] >= this.blLocation.getY() && 
                        listOfResult[2] <= this.trLocation.getY()){
                    return true;
                }
            }
        }
        return false;
    }
    
}

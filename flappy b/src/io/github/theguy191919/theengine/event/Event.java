/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine.event;

/**
 *
 * @author root
 */
public class Event {
    
    private long time;
    private String eventName; //cannot be "all" unless you want truble
    private String info;
    
    public Event(long time, String eventName, String info){
        this.time = time;
        this.eventName = eventName;
        this.info = info;
    }

    /**
     * @return the time
     */
    public long getTime() {
        return time;
    }

    /**
     * @return the eventName
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * @return the info
     */
    public String getInfo() {
        return info;
    }
    
}

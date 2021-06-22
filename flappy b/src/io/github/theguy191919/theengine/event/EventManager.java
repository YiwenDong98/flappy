/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine.event;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author root
 */
public class EventManager {
    
    private static Map<String, List<EventListener>> mapOfEvent = new ConcurrentHashMap<String, List<EventListener>>();
    
    public static void registerListener(EventListener listener, String eventName){
        List<EventListener> listeners = EventManager.mapOfEvent.get(eventName.toLowerCase());
        if(listeners == null){
            listeners = new LinkedList<EventListener>();
            listeners.add(listener);
            EventManager.mapOfEvent.put(eventName.toLowerCase(), listeners);
        } else {
            listeners.add(listener);
        }
    }
    
    public static void triggerEvent(Event event){
        List<EventListener> listeners = EventManager.mapOfEvent.get(event.getEventName().toLowerCase());
        if(listeners == null){
            listeners = new LinkedList<EventListener>();
            EventManager.mapOfEvent.put(event.getEventName().toLowerCase(), listeners);
        }
        for(EventListener listener : listeners){
            listener.gotEvent(event);
        }
    }
    
    public static void removeListener(EventListener listener){
        for(List<EventListener> listeners : EventManager.mapOfEvent.values()){
            listeners.remove(listener);
        }
    }
}

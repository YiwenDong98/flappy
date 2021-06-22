/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine;

import io.github.theguy191919.theengine.event.Event;
import io.github.theguy191919.theengine.event.EventManager;
import io.github.theguy191919.theengine.utility.Location;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import io.github.theguy191919.theengine.gui.GUILoader;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 *
 * @author Yiwen Dong
 */
public class InPut {
    
    private static List<Integer> pressedKeys = new ArrayList<Integer>();
    private static List<Integer> pressedMouse = new ArrayList<Integer>();
    private static List<Integer> heldKeys = new ArrayList<Integer>();
    private static List<Integer> heldMouse = new ArrayList<Integer>();
    //private static List<Integer> listenedKeys = new LinkedList<Integer>();
    private static Location mouseLocation = new Location(0f, 0f);
    private static Location mouseOffset = new Location(0f, 0f);
    
    public static int leftMouseButton = 0;
    public static int rightMouseButton = 1;
    public static int middleMouseButton = 2;
    
    public static void initInPut() {
        //listenedKeys.add(Keyboard.KEY_ESCAPE);
    }

    public static void updateKey() {
        pressedKeys.clear();
        pressedMouse.clear();
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                pressedKeys.add(Keyboard.getEventKey());
                heldKeys.add(Keyboard.getEventKey());
                EventManager.triggerEvent(new Event(Tick.currentTime(), "keypress", String.valueOf(Keyboard.getEventKey())));
            } else {
                heldKeys.remove(Integer.valueOf(Keyboard.getEventKey()));
                EventManager.triggerEvent(new Event(Tick.currentTime(), "keyunpress", String.valueOf(Keyboard.getEventKey())));
            }
        }
        while (Mouse.next()) {
            if (Mouse.getEventButtonState()) {
                pressedMouse.add(Mouse.getEventButton());
                heldMouse.add(Mouse.getEventButton());
                //System.out.println((int)((float)Mouse.getEventY() / FlappyB.game.gameHeight * 720 - getMouseOffset().getY()));
                EventManager.triggerEvent(new Event(Tick.currentTime(), "mousepress", Mouse.getEventButton() + "|" + (int)((float)Mouse.getEventX() / FlappyB.game.gameWidth * 1280) + "|" + (int)((float)Mouse.getEventY() / FlappyB.game.gameHeight * 720 - getMouseOffset().getY()) + "|"));
            } else {
                heldMouse.remove(Integer.valueOf(Mouse.getEventButton()));
                EventManager.triggerEvent(new Event(Tick.currentTime(), "mouseunpress", Mouse.getEventButton() + "|" + (int)((float)Mouse.getEventX() / FlappyB.game.gameWidth * 1280) + "|" + (int)((float)Mouse.getEventY() / FlappyB.game.gameHeight * 720 - getMouseOffset().getY()) + "|"));
            }
        }
    }
    
    public static boolean pressed(int key){
        return pressedKeys.contains(key);
    }
    
    public static boolean held(int key){
        return heldKeys.contains(key);
    }
    
    public static List<Integer> getListOfPressed(){
        return InPut.pressedKeys;
    }
    
    public static boolean mousePressed(int key){
        return pressedMouse.contains(key);
    }
    
    public static boolean mouseHeld(int key){
        return heldMouse.contains(key);
    }
    
    public static int mousePositionX(){
        //System.out.println(Mouse.getX());
        return (int)((float)Mouse.getX() / FlappyB.game.displayWidth * 1280);
    }
    
    public static int mousePositionY(){
        //System.out.println(Mouse.getY());
        return (int)((float)Mouse.getY() / FlappyB.game.displayHeight * 720);
    }

    /**
     * @return the mouseOffset
     */
    public static Location getMouseOffset() {
        return mouseOffset;
    }

    /**
     * @param aMouseOffset the mouseOffset to set
     */
    public static void setMouseOffset(Location aMouseOffset) {
        mouseOffset = aMouseOffset;
    }
}

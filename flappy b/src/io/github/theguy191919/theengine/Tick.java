/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Yiwen Dong
 */
public class Tick {

    private static boolean isEnabled = false;
    private static long lastTime = getTime();
    private static long currentTime = getTime();
    private static long lastFPSTime = getTime();
    private static int time = 1;
    private static int fps;

    public static void updateTick() {
        updateFPS();
//        if (isEnabled && getTime() - lastTime > 50) {
//            time = time + 1;
//            System.out.println(time);
//            lastTime = lastTime + 50;
//            if (time >= 2000) {
//                time = time - 2000;
//            }
//        }

        currentTime = getTime();
        //System.out.println("Current Time is: " + currentTime);
        time = (int) (currentTime - lastTime);
        lastTime = currentTime;
        //System.out.println("Time is: " + time);
        if (time > 100) {
            System.out.println("Can't keep up, Skipping: " + time);
            time = 0;
        }
        if (!isEnabled) {
            time = 0;
        }

    }

    public static void updateFPS() {
        if (getTime() - lastFPSTime > 1000) {
            Display.setTitle("FPS: " + Tick.getFPS());
            fps = 0;
            lastFPSTime = lastFPSTime + 1000;
        }
        fps = fps + 1;
    }

    public static int getTick() {
        return time;
    }

    public static void setTick(int setTo) {
        time = setTo;
    }

    public static void startTick() {
        isEnabled = true;
        currentTime = getTime();
        lastTime = getTime();
        lastFPSTime = getTime();
        time = 0;
    }

    public static void pauseTick() {
        isEnabled = false;
        time = 0;
    }

    public static int getFPS() {
        return fps;
    }

    private static long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
    
    public static long currentTime(){
        return currentTime;
    }

    public static boolean isRunning() {
        return isEnabled;
    }
}

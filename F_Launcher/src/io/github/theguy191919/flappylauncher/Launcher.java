/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.flappylauncher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Launcher implements Runnable {

    private Thread thread;
    private int width = 1280;
    private int height = 720;
    private boolean debug = false;
    private boolean fullscreen = false;
    private boolean vsync = false;
    private MainGUI gui;

    public void launch(int width, int height, boolean debug, boolean fullscreen, boolean vsync, MainGUI gui) {
        synchronized (this) {
            this.width = width;
            this.height = height;
            this.debug = debug;
            this.fullscreen = fullscreen;
            this.vsync = vsync;
            this.gui = gui;
        }
        thread = new Thread(this, "Downloader");
        thread.start();
    }

    public synchronized void download(URL fileURL, URI outputDest) {
        try {
            ReadableByteChannel rbc = Channels.newChannel(fileURL.openStream());
            FileOutputStream stream = new FileOutputStream(new File(outputDest));
            stream.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (IOException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

        boolean haveClass = false;

        try {
            Class.forName("io.github.theguy191919.theengine.FlappyB");
            haveClass = true;
        } catch (ClassNotFoundException ex) {
            haveClass = false;
        }

        if (haveClass) {
            try {
                for(Method method : Class.forName("io.github.theguy191919.theengine.FlappyB").getDeclaredMethods()){
                    System.out.println(method.toString());
                    //System.out.println(method.toGenericString());
                }
                Class[] cArray = {int.class, int.class, boolean.class, boolean.class, boolean.class};
                
                Class.forName("io.github.theguy191919.theengine.FlappyB")
                        .getDeclaredMethod("start", cArray)
                        .invoke(null, width, height, debug, fullscreen, vsync);
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            try {
                Process process;
                //if(MainGUI.os.equalsIgnoreCase("Mac")){
                String[] command = new String[]{
                    "java",
                    "-Dorg.lwjgl.opengl.Display.allowSoftwareOpenGL=true",
                    "-Dio.github.theguy191919.theengine.nativepath=" + MainGUI.nativeLocation,
                    "-jar",
                    MainGUI.gameJar,
                    String.valueOf(this.width),
                    String.valueOf(this.height),
                    String.valueOf(this.debug),
                    String.valueOf(this.fullscreen),
                    String.valueOf(this.vsync)
                };
                System.out.println(Arrays.toString(command));
                process = Runtime.getRuntime().exec(command);
                //} else {
                //    process = Runtime.getRuntime().exec("java -Dorg.lwjgl.opengl.Display.allowSoftwareOpenGL=true -jar Flappy_B.jar " + this.width + " " + this.height + " " + this.debug + " " + this.fullscreen + " " + this.vsync);
                //}

                gui.setProgress(100);
                gui.guiAway();

//            boolean running = true;
//            while (running) {
//                byte[] out = new byte[1];
//                while (process.getInputStream().available() > 0) {
//                    process.getInputStream().read(out);
//                    if (out[0] == -1) {
//                        running = false;
//                    }
//                    System.out.print(new String(out));
//                }
//
//                while (process.getErrorStream().available() > 0) {
//                    process.getErrorStream().read(out);
//                    System.out.print(new String(out));
//                    if (out[0] == -1) {
//                        running = false;
//                    }
//                }
//                Thread.sleep(100);
//            }
                //process.waitFor();
                //System.out.println("java -Dorg.lwjgl.opengl.Display.allowSoftwareOpenGL=true -jar Flappy_B.jar " + this.width + " " + this.height + " " + this.debug + " " + this.fullscreen + " " + this.vsync);
                //System.out = process.getOutputStream();
                process.waitFor();
                gui.quit();
                thread.interrupt();
            } catch (IOException ex) {
                Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);

            } catch (InterruptedException ex) {
                Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
            }
            //gui.quit();
        }
        gui.quit();
    }

    public void stop() {

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.theguy191919.theengine;

import java.util.logging.Level;
import java.util.logging.Logger;
import io.github.theguy191919.theengine.gui.GUILoader;
import io.github.theguy191919.theengine.io.PropertyManager;
import io.github.theguy191919.theengine.level.LevelLoader;
import io.github.theguy191919.theengine.sound.AudioManager;
import io.github.theguy191919.theengine.utility.Location;
import io.github.theguy191919.theengine.utility.font.FontRegestry;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.PixelFormat;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.opengl.ImageIOImageData;

/**
 *
 * @author evan__000
 */
public class Game {
    
    //boolean paused = false;
    public final String version = ".5";
    public boolean running = true;
    public boolean debug = false;
    public boolean fullScreen = false;
    public boolean vsync = false;
    
    public int displayWidth = 1280;
    public int displayHeight = 720;
    
    public int gameWidth = 1280;
    public int gameHeight = 720;
    
     public void start(){
        try {
            ByteBuffer[] list = new ByteBuffer[5];
            //list[0] = new ImageIOImageData().imageToByteBuffer(ImageIO.read(), false, false, null);
            URL imageURL = this.getClass().getClassLoader().getResource("io/github/theguy191919/theengine/resource/Bird1.png");
            list[0] = this.getBufferedImage(imageURL, 16);
            list[1] = this.getBufferedImage(imageURL, 32);
            list[2] = this.getBufferedImage(imageURL, 64);
            list[3] = this.getBufferedImage(imageURL, 128);
            list[4] = this.getBufferedImage(imageURL, 256);
            //list[1] = createBuffer(ImageIO.read(new File("src/Images/Tests/icon32.png")));
            Display.setIcon(list);
            Display.setResizable(true);
            
            if(this.fullScreen){
                Display.setDisplayModeAndFullscreen(this.getDisplayMode(displayWidth, displayHeight, fullScreen));
            } else {
                Display.setDisplayMode(this.getDisplayMode(displayWidth, displayHeight, fullScreen));
            }
//            Display.setDisplayMode(this.getDisplayMode(displayWidth, displayHeight, fullScreen));
            //Display.setDisplayModeAndFullscreen(fullScreen);
            
//            Display.setFullscreen(fullScreen);
            
            Display.create();            
        } catch (Exception ex) {
            Logger.getLogger(FlappyB.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        GL11.glEnable(GL11.GL_TEXTURE_2D);     
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);         

        // enable alpha blending
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
 
        //GL11.glViewport(100,100,displayWidth,displayHeight);
        final float aspectRatio = 1280f / 720f;
        float displayRatio = displayWidth / displayHeight;
        if(aspectRatio >= displayRatio){
            gameWidth = displayWidth;
            gameHeight = (int)(displayWidth / aspectRatio);
            InPut.setMouseOffset(new Location(0f, (displayHeight - gameHeight) / 2));
            GL11.glViewport(0,(displayHeight - gameHeight) / 2,gameWidth,gameHeight);
        } else {
            gameWidth = (int)(displayHeight * aspectRatio);
            gameHeight = displayHeight;
            InPut.setMouseOffset(new Location((displayWidth - gameWidth) / 2, 0f));
            GL11.glViewport((displayWidth - gameWidth) / 2,0,gameWidth,gameHeight);
        }
        
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        // init OpenGL
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 1280, 0, 720, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        
        Display.setVSyncEnabled(vsync);
            
//        try {
//            Display.setFullscreen(fullScreen);
//        } catch (LWJGLException ex) {
//            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        //init game time
        Tick.startTick();
        
        AudioManager.start();
        
        FontRegestry.init();
        
        PropertyManager.start();
        
        GUILoader.start();
        GUILoader.loadGUI("MainGUI");
        //GUILoader.loadGUI(0);
        //GUILoader.unloadGUI(0);
        
        LevelLoader.start();
        //LevelLoader.loadLevel("EasyStart");
    }

    public void update() throws InterruptedException {

        while (!Display.isCloseRequested() && running) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            io.github.theguy191919.theengine.InPut.updateKey();
            //this.pauseOnKey();
            //Display.sync(10);
            ObjectRegestry.updateAll();
            //System.out.println("updatingGUI");
            GUILoader.updateGUI();
            if (Tick.isRunning()) {
                Collision.checkCollision();
                LevelLoader.update();
            }
            Display.update();
            SoundStore.get().poll(0);
            Tick.updateTick();
            //System.gc();
            //Display.sync(10);
        }
        this.stop();
    }
    
//    private void pauseOnKey(){
////        if (!paused && InPut.pressed(Keyboard.KEY_ESCAPE)){
////            GUILoader.loadGUI(0);
////            paused = true;
////        } else if (paused) {
////            paused = false;
////        }
//        if (InPut.pressed(Keyboard.KEY_ESCAPE)){
//            GUILoader.loadGUI("Pause");
//            //paused = true;
//        }
//        if (InPut.pressed(Keyboard.KEY_SPACE)) {
//            GUILoader.unloadGUI("Pause");
//            //paused = false;
//        } 
//    }
    
    private DisplayMode getDisplayMode(int width, int height, boolean fullScreen){
        if(fullScreen){
            this.displayHeight = Display.getDesktopDisplayMode().getHeight();
            this.displayWidth = Display.getDesktopDisplayMode().getWidth();
            return Display.getDesktopDisplayMode();
        }
        try {
            DisplayMode[] modes = Display.getAvailableDisplayModes();
            int bestIndex = 0;
            int closeness = Integer.MAX_VALUE; //closest resolution will jave a closeness value of 0
            if(fullScreen){
                for(int a = 0; a < modes.length; a++){
                    if(modes[a].isFullscreenCapable()){
                        if(Math.abs(modes[a].getHeight() - height) * Math.abs(modes[a].getWidth() - width) < closeness){
                            closeness = Math.abs(modes[a].getHeight() - height) * Math.abs(modes[a].getWidth() - width);
                            bestIndex = a;
                        }
                    }
                }
                this.displayHeight = modes[bestIndex].getHeight();
                this.displayWidth = modes[bestIndex].getWidth();
                return modes[bestIndex];
            } else {
                return new DisplayMode(width, height);
            }
        } catch (LWJGLException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        return null;
    }
    
    private ByteBuffer getBufferedImage(URL url, int size) throws IOException {
        Image img = ImageIO.read(url).getScaledInstance(size, -1, Image.SCALE_SMOOTH);
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return new ImageIOImageData().imageToByteBuffer(bimage, false, false, null);
        //return new ImageIOImageData().imageToByteBuffer(ImageIO.read(url, false, false, null));
    }
    
    private void stop(){
        Tick.pauseTick();
        Display.destroy();
        //AL.destroy();
        AudioManager.destory();
        PropertyManager.stop();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine;

import java.io.File;

/**
 *
 * @author Yiwen Dong
 */
public class FlappyB {

    //private final int frameCap = 1000000
    public static Game game;

    /**
     * @throws java.lang.InterruptedException
     */
//    public void start() throws InterruptedException {
//        try {
//            Display.setDisplayMode(new DisplayMode(1280, 720));
//            Display.create();
//        } catch (LWJGLException ex) {
//            Logger.getLogger(RoadCrossing.class.getName()).log(Level.SEVERE, null, ex);
//            System.exit(0);
//        }
//        GL11.glEnable(GL11.GL_TEXTURE_2D);     
//        GL11.glDisable(GL11.GL_DEPTH_TEST);
//
//        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);         
//
//        // enable alpha blending
//        GL11.glEnable(GL11.GL_BLEND);
//        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
// 
//        GL11.glViewport(0,0,1280,720);
//        GL11.glMatrixMode(GL11.GL_MODELVIEW);
//        // init OpenGL
//        GL11.glMatrixMode(GL11.GL_PROJECTION);
//        GL11.glLoadIdentity();
//        GL11.glOrtho(0, 1280, 0, 720, 1, -1);
//        GL11.glMatrixMode(GL11.GL_MODELVIEW);
//        
//        //init game time
//        Tick.startTick();
//        
//        LevelLoader.start();
//        LevelLoader.loadLevel(0);
//        
//        GUILoader.start();
//        //GUILoader.loadGUI(0);
//        //GUILoader.unloadGUI(0);
//    }
//
//    public void update() throws InterruptedException {
//        
//        
//        while (!Display.isCloseRequested()) {
//            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
//            main.InPut.updateKey();
//            //Display.sync(10);
//            ObjectRegestry.updateAll();
//            GUILoader.updateGUI();
//            if (Tick.isRunning()) {
//                Collision.checkCollision();
//                LevelLoader.update();
//            }
//            Display.update();
//            Tick.updateTick();
//        }
//        Display.destroy();
//    }
    public static void main(String[] args) throws InterruptedException {
        
        String path = System.getProperty("io.github.theguy191919.theengine.nativepath");
        
        if(path == null){
        
        if (System.getProperty("os.name").contains("Windows")) {
            // Windows
            System.setProperty("org.lwjgl.librarypath", new File("lib/native/windows").getAbsolutePath());
        } else if (System.getProperty("os.name").contains("Mac")) {
            // Mac OS X
            System.setProperty("org.lwjgl.librarypath", new File("/lib/native/macosx").getAbsolutePath());
        } else if (System.getProperty("os.name").contains("Linux")) {
            // Linux
            System.setProperty("org.lwjgl.librarypath", new File("lib/native/linux").getAbsolutePath());
        } else if (System.getProperty("os.name").contains("Sun")) {
            // SunOS (Solaris)
            System.setProperty("org.lwjgl.librarypath", new File("lib/native/solaris").getAbsolutePath());
        } else {
            throw new RuntimeException("Your OS is not supported");
        }
        
        } else {
            System.setProperty("org.lwjgl.librarypath", new File(path).getAbsolutePath());
        }
        
        int width = 1280;
        int height = 720;
        boolean debug = false;
        boolean fullscreen = false;
        boolean vsync = false;

        if (args.length == 5) {
            if (args[0] != null) {
                width = Integer.valueOf(args[0]);
            }
            if (args[1] != null) {
                height = Integer.valueOf(args[1]);
            }
            if (args[2] != null && args[2].equalsIgnoreCase("true")) {
                debug = true;
            }
            if (args[3] != null && args[3].equalsIgnoreCase("true")) {
                fullscreen = true;
            }
            if (args[4] != null && args[4].equalsIgnoreCase("true")) {
                vsync = true;
            }
        }

        FlappyB.start(width, height, debug, fullscreen, vsync);
    }
    
    public static void start(int width, int height, boolean debug, boolean fullscreen, boolean vsync) throws InterruptedException{
        //System.out.println("adasd");
        game = new Game();
        game.displayWidth = width;
        game.displayHeight = height;
        game.debug = debug;
        game.fullScreen = fullscreen;
        game.vsync = vsync;
        game.start();
        game.update();
    }
}

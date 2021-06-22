/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine.sound;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.codecs.CodecJOrbis;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;

/**
 *
 * @author root
 */
public class AudioManager {
    
    //private static Map<String, Audio> mapOfAudio = new HashMap<>();
    private static String resourceFolder = "io/github/theguy191919/theengine/resource/";
    private static ClassLoader loader = AudioManager.class.getClassLoader();
    
    private static SoundSystem soundSystem;
    
    public static void start(){
//        try {
//            //ResourceLoader.addResourceLocation(new ClasspathLocation());
//            //System.out.println(loader.getResourceAsStream(resourceFolder + "hit.ogg") == null);
//            AudioManager.mapOfAudio.put("hit", AudioLoader.getAudio("OGG", loader.getResourceAsStream(resourceFolder + "hit.ogg")));
//            AudioManager.mapOfAudio.put("death", AudioLoader.getAudio("OGG", loader.getResourceAsStream(resourceFolder + "death.ogg")));
//            //AudioManager.mapOfAudio.put("background", AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream(resourceFolder + ".ogg")));
//            AudioManager.mapOfAudio.put("point", AudioLoader.getAudio("OGG", loader.getResourceAsStream(resourceFolder + "point.ogg")));
//            AudioManager.mapOfAudio.put("flap", AudioLoader.getAudio("OGG", loader.getResourceAsStream(resourceFolder + "flap.ogg")));
//            AudioManager.mapOfAudio.put("woosh", AudioLoader.getAudio("OGG", loader.getResourceAsStream(resourceFolder + "woosh.ogg")));
//            AudioManager.mapOfAudio.put("across", AudioLoader.getAudio("OGG", loader.getResourceAsStream(resourceFolder + "AcrossTheSkies.ogg")));
//        } catch (IOException ex) {
//            Logger.getLogger(AudioManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        try {
                SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class);
                SoundSystemConfig.setCodec( "ogg", CodecJOrbis.class );
                SoundSystemConfig.setLogger(new SoundLogger());
            } catch (SoundSystemException ex) {
                Logger.getLogger(AudioManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            soundSystem = new SoundSystem();
    }
    
    public static void addAudio(String audioFile, String name, float volume, boolean looping){
        //System.out.println(soundSystem.toString());
        //System.out.println(loader.getResource(resourceFolder + audioFile).getPath());
        soundSystem.newStreamingSource(true, name, loader.getResource(resourceFolder + audioFile), audioFile, looping, 0, 0, 0, SoundSystemConfig.ATTENUATION_NONE, 0);
        soundSystem.setVolume(name, volume);
    }
    
//    public static void addAudio(String audioFile, String name, boolean override){
//        if(override || soundSystem){
//            addAudio(audioFile, name);
//        }
//    }
    
    public static void setResourceFolder(String folder){
        AudioManager.resourceFolder = folder;
    }
    
    public static void playAudio(String audioName){
        soundSystem.play(audioName);
    }
    
    public static boolean playing(String audioName){
        return soundSystem.playing(audioName);
    }
    
    public static void stopAudio(String audioName){
        soundSystem.stop(audioName);
    }
    
    public static void destory(){
        soundSystem.cleanup();
    }
    
//    public static Audio getAudio(String audioName){
//        return AudioManager.mapOfAudio.get(audioName);
//    }
    
}

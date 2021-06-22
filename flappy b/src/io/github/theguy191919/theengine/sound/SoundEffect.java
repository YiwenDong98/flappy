/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine.sound;

import io.github.theguy191919.theengine.utility.Location;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.SoundStore;

/**
 *
 * @author root
 */
public class SoundEffect {
    
    //private Audio audio;
    private String audioName;
    //private Location location;
    private float volume;
    
    public SoundEffect(String audioName){
        this(audioName, audioName, 1f);
    }
    
    public SoundEffect(String audioName, String fileName){
        this(audioName, fileName, 1f);
    }
    
    public SoundEffect(String audioName, String fileName, float volume){
        //AudioManager.
        this.audioName = audioName;
        AudioManager.addAudio(fileName, audioName, volume, false);
        //this.audio = AudioManager.getAudio(audioName);
        this.volume = volume;
    }
    
    public SoundEffect(String audioName, Location location){
        this(audioName, audioName, 1 / (float)Math.sqrt(location.getX() * location.getX() + location.getY() * location.getY()));
    }
    
    public SoundEffect(String audioName, float volume){
        this(audioName, audioName, volume);
    }
    
    public SoundEffect(String audioName, String fileName, Location location){
        this(audioName, fileName, 1 / (float)Math.sqrt(location.getX() * location.getX() + location.getY() * location.getY()));
    }
    
    public void playSound(){
        AudioManager.playAudio(audioName);
        //audio.stop();
        //audio.playAsSoundEffect(1f, volume, false);
        //SoundStore.get().poll(0);
    }
    
    public void stop(){
        AudioManager.stopAudio(audioName);
        //audio.stop();
    }
    
    public String getName(){
        return this.audioName;
    }
    
    public boolean playing(){
        //return audio.isPlaying();
        return AudioManager.playing(audioName);
    }
}

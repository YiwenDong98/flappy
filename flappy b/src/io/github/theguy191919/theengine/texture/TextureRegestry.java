/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine.texture;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Yiwen Dong
 */
public class TextureRegestry {
    
    private static Map<String, Texture> mapOfTexture = new HashMap<String, Texture>();
    private static TextureLoader loader = new TextureLoader();
    
    public static boolean addTexture(String name, String location, TextureLoader loader, boolean override){
        if(!mapOfTexture.containsKey(name) || override){
            try {
                Texture texture = loader.getTexture(location);
                mapOfTexture.remove(name);
                mapOfTexture.put(name, texture);
            } catch (IOException ex) {
                Logger.getLogger(TextureRegestry.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        
        return false;
    }
    
    public static boolean addTexture(String name, String location, TextureLoader loader){
        if(!mapOfTexture.containsKey(name)){
            try {
                Texture texture = loader.getTexture(location);
                mapOfTexture.put(name, texture);
            } catch (IOException ex) {
                Logger.getLogger(TextureRegestry.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        
        return false;
    }
    
    public static boolean addTexture(String name, String location){
        if(!mapOfTexture.containsKey(name)){
            try {
                Texture texture = loader.getTexture(location);
                mapOfTexture.put(name, texture);
            } catch (IOException ex) {
                Logger.getLogger(TextureRegestry.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        
        return false;
    }
    
    public static boolean reloadTexture(String name){
        return false;
    }
    
    public static boolean removeTexture(String name){
        
        return true;
    }
    
    public static Texture getTexture(String name){
        return mapOfTexture.get(name);
    }
}

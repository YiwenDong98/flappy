/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine.utility.font;

import io.github.theguy191919.theengine.texture.Texture;
import io.github.theguy191919.theengine.texture.TextureLoader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yiwen Dong
 */
public class FontRegestry {
    private static Map<Character, Texture> mapOfTexture = new HashMap<Character, Texture>();
    private static final String fontFolder = "io/github/theguy191919/theengine/resource/font/";
    private static TextureLoader loader = new TextureLoader();
    
    //add texture here if adding more fonts
    public static void init(){
        putTexture(Character.valueOf('0'));
        putTexture(Character.valueOf('1'));
        putTexture(Character.valueOf('2'));
        putTexture(Character.valueOf('3'));
        putTexture(Character.valueOf('4'));
        putTexture(Character.valueOf('5'));
        putTexture(Character.valueOf('6'));
        putTexture(Character.valueOf('7'));
        putTexture(Character.valueOf('8'));
        putTexture(Character.valueOf('9'));
        putTexture(Character.valueOf(':'), "Colon");
        putTexture(Character.valueOf(' '), "Space");
        putTexture(Character.valueOf('.'), "Period");
        
    }
    
    public static Texture getTexture(Character character){
        return mapOfTexture.get(character);
    }
    
    public static Texture getTexture(char fontName){
        Character character = new Character(fontName);
        return mapOfTexture.get(character);
    }
    
    private static void putTexture(char name){
        try {
            mapOfTexture.put(Character.valueOf(name), loader.getTexture(getPath(name)));
        } catch (IOException ex) {
            Logger.getLogger(FontRegestry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static void putTexture(char name, String textureName){
        try {
            mapOfTexture.put(Character.valueOf(name), loader.getTexture(getPath(textureName)));
        } catch (IOException ex) {
            Logger.getLogger(FontRegestry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static String getPath(char name){
        return fontFolder + String.valueOf(name) + ".png";
    }
    
    private static String getPath(String name){
        return fontFolder + name + ".png";
    }
}

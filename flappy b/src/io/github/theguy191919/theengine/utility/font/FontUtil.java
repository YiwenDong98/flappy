/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine.utility.font;

import io.github.theguy191919.theengine.texture.TextureRegestry;
import io.github.theguy191919.theengine.utility.Location;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
// *
 * @author Yiwen Dong
 */
public class FontUtil {
    
//    private static Map<Character, Font> mapOfFont = new HashMap<Character, Font>();
//    static {
//        mapOfFont.put('0', new Font())
//    }
//    
    //return a drawable, be placed into a compadable drawer of choise
    public static List<Font> getFont(String string, Location location){
        List<Font> listOfFont = new LinkedList<Font>();
        //Location loc = new Location(location);
        for(int a = 0; a < string.length(); a++){
            //listOfChar.add(Character.valueOf(string.charAt(a)));
            Font font = new Font(location, FontRegestry.getTexture(string.charAt(a)));
            listOfFont.add(font);
            location = new Location(location);
            location.addToX(font.Width);
        }
        return listOfFont;
    }
    
//    public static 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.flappylauncher;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class PropertyWriter {
    
    private static String rootDir = "./";
    private static Map<String, String> mapOfProperties = new HashMap<String, String>();
    private static Properties prop;
    
    //private static BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
    
    public static void start(){
        InputStream iStream = null;
        
        //textEncryptor.setPassword("FlappyPasswordYouAreBadIfYouSeeThis");
        
        try {
            prop = new Properties();
            File pFile = new File(rootDir + "launcher.config");
            if(!pFile.exists()){
                pFile.createNewFile();
            }
            iStream = new FileInputStream(pFile);
            
//            String propFile = "";
//            int content;
//            while ((content = iStream.read()) != -1) {
//                // convert to char and display it
//                //System.out.print((char) content);
//                propFile = propFile + (char) content;
//            }
//            iStream.close();
            
            //iStream.read();
            //InputStream propStream = new ByteArrayInputStream(textEncryptor.decrypt(propFile).getBytes());
            prop.load(iStream);
            for(Map.Entry<Object, Object> entry  : prop.entrySet()){
                mapOfProperties.put(entry.getKey().toString(), entry.getValue().toString());
            }
        } catch (Exception ex) {
            System.out.println("Error, cannot read properties file. Progress will not be saved");
        } finally {
            try {
                iStream.close();
            } catch (IOException ex) {
                //Logger.getLogger(PropertyManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @param aRootDir the rootDir to set
     */
    public static void setRootDir(String aRootDir) {
        rootDir = aRootDir;
    }
    
    public static void setProperty(String name, String value){
        mapOfProperties.put(name.toLowerCase(), value);
    }
    
    public static String getProperty(String name){
        return mapOfProperties.get(name.toLowerCase());
    }
    
    public static String getProperty(String name, String defaultValue){
        if(!mapOfProperties.containsKey(name.toLowerCase())){
            mapOfProperties.put(name.toLowerCase(), defaultValue);
        }
        return mapOfProperties.get(name.toLowerCase());
    }
    
    public static void stop(){
        prop = new Properties();
        for(Map.Entry<String, String> entry : mapOfProperties.entrySet()){
            prop.setProperty(entry.getKey(), entry.getValue());
        }
        
        
        
        File pFile = new File(rootDir + "launcher.config");
        try {
            OutputStream oStream = new FileOutputStream(pFile);
            //OutputStream oBStream = new ByteArrayOutputStream();
            prop.store(oStream, "Property File for Flappy Byrd Launcher");
            //System.out.println(oBStream.toString());
            //textEncryptor.setPassword("FlappyPasswordYouAreBadIfYouSeeThis");
            //oStream.write(textEncryptor.encrypt(oBStream.toString()).getBytes());
            oStream.flush();
            oStream.close();
        } catch (Exception ex) {
            Logger.getLogger(PropertyWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

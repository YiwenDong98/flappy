/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.theguy191919.theengine;

import java.util.LinkedList;
import java.util.List;
import io.github.theguy191919.theengine.entity.Entity;

/**
 *
 * @author evan__000
 */
public class ObjectRegestry {
    private static List<Entity> noneSolidEntity = new LinkedList<Entity>();
    private static List<Entity> listOfEntity = new LinkedList<Entity>();
    
    public static void placeSolidEntity(Entity entity){
        listOfEntity.add(entity);
    }
    
    public static void placeNoneSolidEntity(Entity entity){
        noneSolidEntity.add(entity);
    }
    
    public static void updateAll(){                
        for (int a = 0; a < noneSolidEntity.size(); a++) {
            Entity entity = noneSolidEntity.get(a);
            entity.logic();
        }
        
        for (int a = 0; a < listOfEntity.size(); a++) {
            Entity entity = listOfEntity.get(a);
            entity.logic();
        }
    }

    public static Entity getEntity(int key) {
        return listOfEntity.get(key);
    }
    
    public static int numberOfEntity() {
        return listOfEntity.size();
    }
    
    public static void removeEntity(Entity entity){
        entity.destory();
        listOfEntity.remove(entity);
        noneSolidEntity.remove(entity);
    }
    
    public static void removeAll(){
        for(int a = 0; a < listOfEntity.size(); a++){
            listOfEntity.get(a).destory();
        }
        for(int a = 0; a < noneSolidEntity.size(); a++){
            noneSolidEntity.get(a).destory();
        }
        listOfEntity.clear();
        noneSolidEntity.clear();
    }
}

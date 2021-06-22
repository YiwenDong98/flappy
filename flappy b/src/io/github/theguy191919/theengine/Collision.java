/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.theguy191919.theengine;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import io.github.theguy191919.theengine.entity.Entity;

/**
 *
 * @author evan__000
 */
public class Collision {
    public static void checkCollision(){
        
        int numberOfEntity = io.github.theguy191919.theengine.ObjectRegestry.numberOfEntity();
        List<Entity[]> colided = new LinkedList<Entity[]>();
        
        for (int a = 0; a < numberOfEntity; a++) {
            for (int b = a + 1; b < numberOfEntity; b++) {
                Entity aEntity = io.github.theguy191919.theengine.ObjectRegestry.getEntity(a);
                Entity bEntity = io.github.theguy191919.theengine.ObjectRegestry.getEntity(b);
                if (aEntity.colidedWith(bEntity)){
                    Entity[] entity = new Entity[2];
                    entity[0] = aEntity;
                    entity[1] = bEntity;
                    colided.add(entity);
                }
            }
        }
        for (Entity[] entity : colided) {
            entity[0].colideWith(entity[1]);
            entity[1].colideWith(entity[0]);
        }
    }
}

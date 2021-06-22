/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.theguy191919.theengine.level;

/**
 *
 * @author evan__000
 */
public abstract class Level {
    abstract void init(); //time to put objecct into regestry
    abstract void postinit(); //time to change property of object
    abstract void run();
    abstract void destroy();
}

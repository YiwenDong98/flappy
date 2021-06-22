/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine.utility;

/**
 *
 * @author Yiwen Dong
 */
public enum Direction {
    NORTH   (0),
    EAST    (1),
    South   (2),
    West    (3);
    
    private final int direction;
    Direction(int direction){
    this.direction = direction;
}
    public int toInt(){
        return direction;
    }
}

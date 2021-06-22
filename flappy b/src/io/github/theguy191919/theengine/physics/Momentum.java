/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.theguy191919.theengine.physics;

import io.github.theguy191919.theengine.utility.Vector;

/**
 *
 * @author evan__000
 */
public class Momentum {
    public static Vector applyDrag(Vector vec){
       // System.out.println(vec.getPower() + "??");
       float velocity = dragFormula(vec.getPower());
       //System.out.println("Velocity is " + velocity);
       vec.setPower(velocity);
       return vec;
    }
    
    private static float dragFormula(float velocity){
        //System.out.println(() + " a");
        float a = 3.56f * velocity;
        System.out.println(a + "a");
        System.out.println((Math.pow(a, (0.333))) + "Is the return");
        return (float)Math.pow(a, 0.333);//return new velosity
    }
}

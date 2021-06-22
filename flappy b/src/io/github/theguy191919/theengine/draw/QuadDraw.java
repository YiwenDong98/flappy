/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine.draw;

import io.github.theguy191919.theengine.texture.TextureLoader;
import io.github.theguy191919.theengine.texture.Texture;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

/**
 *
 * @author Yiwen Dong
 */
public class QuadDraw {
    
    public static void draw(int Length, int Height, float X, float Y, Texture texture){
        QuadDraw.draw(Length, Height, X, Y, texture, 0.0f);
//        texture.bind();
//        GL11.glMatrixMode(GL11.GL_MODELVIEW);
//        GL11.glPushMatrix();
//        GL11.glTranslatef(X, Y, 0.0f);
//        // draw quad
//        glBegin(GL_QUADS);
//        glTexCoord2f(0, 1);
//        glVertex2f(0, 0);
//        glTexCoord2f(1, 1);
//        glVertex2f(0 + Length, 0);
//        glTexCoord2f(1, 0);
//        glVertex2f(0 + Length, 0 + Height);
//        glTexCoord2f(0, 0);
//        glVertex2f(0, 0 + Height);
//        glEnd();
//        GL11.glPopMatrix();
    }
    
    public static void draw(Drawable object){
        QuadDraw.draw(object.getWidth(), object.getHeight(), object.getLocation().getX(), object.getLocation().getY(), object.getTexture(), object.getRotation());
//        object.getTexture().bind();
//        GL11.glMatrixMode(GL11.GL_MODELVIEW);
//        GL11.glPushMatrix();
//        GL11.glTranslatef(object.getLocation().getX(), object.getLocation().getY(), 0.0f);
//        // draw quad
//        glBegin(GL_QUADS);
//        glTexCoord2f(0, 1);
//        glVertex2f(0, 0);
//        glTexCoord2f(1, 1);
//        glVertex2f(0 + object.getWidth(), 0);
//        glTexCoord2f(1, 0);
//        glVertex2f(0 + object.getWidth(), 0 + object.getHeight());
//        glTexCoord2f(0, 0);
//        glVertex2f(0, 0 + object.getHeight());
//        glEnd();
//        GL11.glPopMatrix();
    }
    
    /**
     * Rotation in radians with
    **/
    public static void draw(int Length, int Height, float X, float Y, Texture texture, float rotation){
        
        texture.bind();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();
        
        GL11.glTranslatef(X+Length/2, Y+Height/2, 0.0f);
        GL11.glRotatef((float)Math.toDegrees((double)rotation), 0.0f, 0.0f, 1.0f);
        GL11.glTranslatef(-Length/2, -Height/2, 0.0f);
        
        // draw quad
        glBegin(GL_QUADS);
        glTexCoord2f(0, 1);
        glVertex2f(0, 0);
        glTexCoord2f(1, 1);
        glVertex2f(0 + Length, 0);
        glTexCoord2f(1, 0);
        glVertex2f(0 + Length, 0 + Height);
        glTexCoord2f(0, 0);
        glVertex2f(0, 0 + Height);
        glEnd();
        GL11.glPopMatrix();
        
        
    }
}

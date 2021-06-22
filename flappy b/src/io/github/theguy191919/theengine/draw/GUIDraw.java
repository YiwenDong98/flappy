/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.theguy191919.theengine.draw;

import io.github.theguy191919.theengine.texture.Texture;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

/**
 *
 * @author evan__000
 */
public class GUIDraw {
    public static void draw(int Length, int Height, float X, float Y, Texture texture){
        texture.bind();
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPushMatrix();
        GL11.glTranslatef(X, Y, 0.0f);
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
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
    
        public static void draw(Drawable object){
        object.getTexture().bind();
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPushMatrix();
        GL11.glTranslatef(object.getLocation().getX(), object.getLocation().getY(), 0.0f);
        // draw quad
        glBegin(GL_QUADS);
        glTexCoord2f(0, 1);
        glVertex2f(0, 0);
        glTexCoord2f(1, 1);
        glVertex2f(0 + object.getWidth(), 0);
        glTexCoord2f(1, 0);
        glVertex2f(0 + object.getWidth(), 0 + object.getHeight());
        glTexCoord2f(0, 0);
        glVertex2f(0, 0 + object.getHeight());
        glEnd();
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
}

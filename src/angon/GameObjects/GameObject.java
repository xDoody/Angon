/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angon.GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Deyu
 */
public abstract class GameObject {
    
    protected float x,y;
    protected ID id;
    protected float velX,velY;
    public GameObject(float x,float y,ID id)
    {
        this.x=x;
        this.y=y;
        this.id=id;
    }
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    //<editor-fold defaultstate="collapsed" desc="Set/Get">
    
    public void setX(float x)
    {
        this.x=x;
    }
    public void setY(float y)
    {
        this.y=y;
    }
    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }
    public void setID(ID id)
    {
        this.id = id;
    }
    public ID getId()
    {
        return id;
    }
    public void setVelX(float velX)
    {
     this.velX=velX;
    }
    public void setVelY(float velY)
    {
     this.velY=velY;
    }
    public float getVelX(float velX)
    {
    return velX;
    }
    public float getVelY(float velY)
    {
    return velY;
    }
//</editor-fold>
    
}

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
    
    protected int x,y;
    protected ID id;
    protected float velX,velY;
    public GameObject(int x,int y,ID id)
    {
        this.x=x;
        this.y=y;
        this.id=id;
    }
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    //<editor-fold defaultstate="collapsed" desc="Set/Get">
    
    public void setX(int x)
    {
        this.x=x;
    }
    public void setY(int x)
    {
        this.x=x;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
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
    public void setVelX(int velX)
    {
     this.velX=velX;
    }
    public void setVelY(int velY)
    {
     this.velY=velY;
    }
    public int getVelX(int velX)
    {
    return velX;
    }
    public int getVelY(int velY)
    {
    return velY;
    }
//</editor-fold>
    
}

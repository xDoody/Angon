/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angon.GameObjects;

import angon.GameObjects.ID;
import angon.GameObjects.GameObject;
import angon.Handler;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Deyu
 */
public class Trail extends GameObject {
    private float alpha=1;
    Handler handler;
    private Color color;
    private int width,height;
    private float life;
    public Trail(int x, int y, ID id,Color color,int width,int height,float life,Handler handler) {
        super(x, y, id);
        this.handler=handler;
        this.color= color;
        this.width=width;
        this.height=height;
        this.life=life;
    }

    @Override
    public void tick() {
        if(life<alpha)
        {
            alpha -=life-0.001f;
        }
        else{
        handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d =(Graphics2D)g;
        g2d.setComposite(makeTransparent(alpha));
        g.setColor(color);
        g.fillRect(x,y,16,16);
        g2d.setComposite(makeTransparent(1));
    }
    
    private AlphaComposite makeTransparent(float alpha)
    {
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type,alpha);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
    
}

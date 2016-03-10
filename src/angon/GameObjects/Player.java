/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angon.GameObjects;

import angon.Game;
import angon.HUD;
import angon.Handler;
import angon.readCoins;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.io.IOException;


/**
 *
 * @author Deyu
 */
public class Player extends GameObject{
    Handler handler;
    ImageObserver io;
    readCoins rc;
    public Player(int x, int y, ID id,Handler handler) throws IOException{
        super(x, y, id);
        this.handler=handler;
    }
    @Override
    public void tick() {
        x+=velX;
        y+=velY;
        
        x=Game.clamp(x, 0, Game.WIDTH-40);
        y=Game.clamp(y, 0, Game.HEIGHT-32);
        colision();
    }
    public void colision()
    {
        for(int i=0;i<handler.object.size();i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId()==ID.BasicEnemy)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                HUD.HEALTH-=0.2;
                
                }
            }
            else if(tempObject.getId()==ID.Coin)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                HUD.COINS++;
                handler.removeObject(tempObject);
                }
            }
            else if(tempObject.getId()==ID.MidEnemy)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                HUD.HEALTH-=0.3;
                }
            }
        }
    }
    @Override
    public void render(Graphics g) {
        
        g.setColor(new Color(32,43,63));
        g.fill3DRect(x,y,32,32,true);
      
}

    @Override
    public Rectangle getBounds() {
       return new Rectangle(x,y,32,32);
    }


    
}

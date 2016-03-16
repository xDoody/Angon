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
    int AdCoin;
    public Player(float x, float y, ID id,Handler handler,int AdCoin) throws IOException{
        super(x, y, id);
        this.handler=handler;
        this.AdCoin=AdCoin;
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
                HUD.HEALTH-=1;
                
                }
            }
            else if(tempObject.getId()==ID.Coin)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                HUD.COINS+=1+AdCoin;
                handler.removeObject(tempObject);
                }
            }
            else if(tempObject.getId()==ID.MidEnemy)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                HUD.HEALTH-=0.1;
                }
            }
            else if(tempObject.getId()==ID.SuperCoin)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                HUD.COINS+=5+AdCoin;
                handler.removeObject(tempObject);
                }
            }
        }
    }
    @Override
    public void render(Graphics g) {
        
        g.setColor(new Color(32,43,63));
        g.fill3DRect((int)x,(int)y,32,32,true);
      
}

    @Override
    public Rectangle getBounds() {
       return new Rectangle((int)x,(int)y,32,32);
    }


    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angon.GameObjects;

import angon.Game;
import angon.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Deyu
 */
public class MidEnemy extends GameObject {
    private Handler handler;
    public MidEnemy(int x, int y, ID id,Handler handler) {
        super(x, y, id);
        this.handler=handler;
        velX=(float) 1.5;
        velY=(float) 1.5;
    }

    @Override
    public void tick() {
        x+=velX;
        y+=velY;
        
        if(y<=0||y >=Game.HEIGHT-32)
        {
            velY*=-1;
        }
        if(x<=0||x>=Game.WIDTH-32)
        {
            velX*=-1;
        }
        handler.addObject(new Trail((int)x,(int)y,ID.Trail,Color.red,16,16, (float) 0.1,handler));
    }

    @Override
    public void render(Graphics g) {
       g.setColor(Color.red);
       g.fillRect((int)x,(int)y,16,16);
    }
    
    @Override
    public Rectangle getBounds() {
       return new Rectangle((int)x,(int)y,16,16);
    }
}

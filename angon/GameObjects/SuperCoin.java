/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angon.GameObjects;

import angon.Handler;
import angon.Logger;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Deyu
 */
public class SuperCoin extends GameObject {

    private final Handler handler;
    private final Logger log;
    private long baseDuration=5000;
    public SuperCoin(int x, int y, ID id,Handler handler,Logger log) {
        super(x, y, id);
        this.handler=handler;
        this.log=log;
    }

    @Override
    public void tick() {
        baseDuration--;
        if(baseDuration==0)
        {
        log.print("Super Coin Coin at "+this.x+":"+this.y+" has dispawned.");
        handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(250,0,250));
        g.fillRoundRect((int)x,(int)y,25,25,10000,25);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,25,25);
    }
}

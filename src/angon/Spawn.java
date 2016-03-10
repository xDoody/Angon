/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angon;

import angon.GameObjects.Coin;
import angon.GameObjects.ID;
import java.util.Random;

/**
 *
 * @author Deyu
 */
public class Spawn {
    private final Handler handler;
    private final HUD hud;
    private final int leveldif=1;
    private final Timer timer;
    private final Random r=new Random();
    private int x,y;
    private Logger log;
    private final boolean k=true;
    private int tickCount=0;
    Level level;
    public Spawn(Handler handler,HUD hud,Timer timer,Logger log)
    {
        this.log=log;
        this.handler=handler;
        this.hud=hud;
        this.timer=timer;
        this.level=new Level(handler,hud,hud.getLevel(),timer,log);
    }
    public long timerNow(){
    return timer.getSecodns();
    }
    public void tick()
    {   
    //<editor-fold defaultstate="collapsed" desc="CoinSpawn">
        tickCount++;
        if(tickCount==1200/leveldif)
        {
            x=r.nextInt(550) + 1;
            y=r.nextInt(400) + 1;
            handler.addObject(new Coin(x,y,ID.Coin,handler,log));
            tickCount=0;
        }
        if(timerNow()+1==timer.getSecodns())
        tickCount=0;
        
//</editor-fold>
    level.tick();
    }
    
    
}

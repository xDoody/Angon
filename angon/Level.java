/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angon;

import angon.GameObjects.BasicEnemy;
import angon.GameObjects.MidEnemy;
import angon.GameObjects.ID;
import java.util.Random;

public class Level {
    Logger log;
    Handler handler;
    int level;
    int tickCount=0;
    int tCount=0;
    HUD hud;
    private final Random r=new Random();
    private int x,y;
    Timer timer;
    int maxLevel=10;
    int leveldif=20;
    public Level(Handler handler,HUD hud,int Level,Timer timer,Logger log){
    this.log=log;
    this.handler=handler;
    this.hud=hud;
    this.timer=timer;
    this.level=Level;
    }
    public long timerNow(){
    return timer.getSecodns();
    }
    public void tick()
    {
        
        
        tickCount++;
        tCount++;
        if(tickCount==600*leveldif)//600=1 second
        {
            tickCount=0;
            y=r.nextInt(400-2)+2;
            x=r.nextInt(440-2)+2;
            handler.addObject(new BasicEnemy(x,y,ID.BasicEnemy,handler));
            log.print("BasicEnemy added to :"+x+","+y+".");
            
        }
        if(tCount==36000)
        {
            tCount=0;
            y=r.nextInt(400-2)+2;
            x=r.nextInt(440-2)+2;
            handler.addObject(new MidEnemy(x,y,ID.MidEnemy,handler));
            log.print("Mid Enemy added to:"+x+","+y+".");
        }
        if(timerNow()+1==timer.getSecodns())
        tickCount=0;
}

}

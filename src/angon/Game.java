/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angon;

import angon.GameObjects.BasicEnemy;
import angon.GameObjects.Player;
import angon.GameObjects.ID;
import angon.GameObjects.GameObject;
import angon.GameObjects.MidEnemy;
import angon.Shop.Shop;
import angon.Shop.ShopReader;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.Random;
/**
 *
 * @author Deyu
 */

public class Game extends Canvas implements Runnable {
    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private static final long serialVersionUID = 1L;
    public static int WIDTH=620;
    public static int HEIGHT=430;
    public Logger log= new Logger();
    public long gameTimes=0,gameTimeh,gameTimem;
    public Handler handler;
    private final Random r=new Random();
    private HUD hud;
    private ID id;
    private boolean f=true;
    private GameObject gameObject;
    private boolean running = false;
    private Thread thread;
    private Spawn spawner;
    public Timer timer;
    public readCoins rc= new readCoins(hud);
    public ShopReader sr= new ShopReader();
    public PlayerStats ps= new PlayerStats(hud,log);
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Game">
    public Game() throws IOException
    {
        new Display(WIDTH,HEIGHT,this);
        log.print("Display created.");
        handler = new Handler();
        log.print("Handler created.");
        timer=new Timer(gameTimeh,gameTimem,gameTimes);
        log.print("Timer created.");
        hud = new HUD(timer);
        log.print("HUD created.");
        spawner=new Spawn(handler,hud,timer,log);
        log.print("Spawner created.");
        this.addKeyListener(new KeyImput(handler,ps.getSpeed()));
        log.print("KeyImput initated.");
        handler.addObject(new Player(100,100,ID.Player,handler,ps.getAditionalCoins()));
        log.print("Player added.");
        int k=r.nextInt(550) + 1;
        int k1=r.nextInt(400) + 1;
        handler.addObject(new BasicEnemy(k,k1,ID.BasicEnemy,handler));
        log.print("BasicEnemy added at:"+k+","+k1+".");
        handler.addObject(new MidEnemy(k,k1,ID.MidEnemy,handler));
        setc();
        log.print("Coins have been set.");
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Set">
    public void setc(){
        hud.TOTALCOINS=rc.getCoins();
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Start/Stop">
    public synchronized void  start(){
        thread = new Thread(this);
        thread.start();
        running=true;
    }
    public synchronized void stop(){
        try{
            thread.join();
            running=false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void run(){
            long lastTime=System.nanoTime();
            double amountOfTicks=60.0;
            double ns=100000000/amountOfTicks;
            double delta=0;
            long tim = System.currentTimeMillis();
            int frames=0;
            while(running)
            {
                long now = System.nanoTime();
                delta +=(now-lastTime)/ns;
                lastTime=now;
                while(delta>=1)
                {
                tick();
                delta--;
                }
                if(running)
                    render();
                long t =System.currentTimeMillis()-tim;
                if(t>1000)
                {
                    tim+=1000;
                    gameTimes++;
                    if(gameTimes==60)
                    {
                    gameTimem++;
                    gameTimes=0;
                    }
                    if(gameTimem==60)
                    {
                    gameTimeh++;
                    gameTimem=0;
                    }
                    timer.setHour(gameTimeh);
                    timer.setMinutes(gameTimem);
                    timer.setSeconds(gameTimes);
                }
                
            }
            stop();
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Clamps">
    public static float clamp(float var,float min,float max){
        if(var>=max)
            return var=max;
        else if(var<=min)
            return var=min;
        else
            return var;
    }
    public static float HealthClamp(float var,float min,float max)
    {
        if(var>=max)
            return var=max;
        else if(var<=min)
            return var=min;
        else
            return var;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Render/Tick">
    private void tick()
    {
    if(hud.HEALTH==0 && f==true)
    {
        rc.onClose();
        f=false;
        hud.COINS=0;
        rc.onOpen();
        new Shop(ps.getSpeed(),ps.getHealth(),ps.getAditionalCoins(),ps);
    }
    handler.tick();
    hud.tick();
    spawner.tick();
    }
    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null)
        {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        handler.render(g);
        hud.render(g);
        g.dispose();
        bs.show();
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Main">
    public static void main(String[] args) throws IOException {
        new Game();
        System.out.println("Game initated.");
    }
//</editor-fold>


    
}

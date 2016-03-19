package angon;
import angon.GameObjects.BasicEnemy;
import angon.GameObjects.Player;
import angon.GameObjects.ID;
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
 *~Already did the comments
 * @author Deyu
 * 
 * 
 * Make a new class for Declarations so you can close it and remake new ones easier
 * newGame: -dispose the graphics
 *          -gc the display instance and all the instances beside this one
 *          -make new instances
 *          -redisplay the graphics
 * 
 * 
 * 
 * 
 * 
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
    public boolean k=false;
    private ID id;
    private boolean f=true;
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
        hud.HEALTH=ps.getHealth();
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
        //Load the coins from the file
        hud.TOTALCOINS=rc.getCoins();
    }
    public void setk(boolean l)
    {
        //for new game purposes , it will set the current status of the game
        //true = need new game
        k=l;
    }
    public boolean getk()
    {
        //returns the k variable
        return k;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Start/Stop">
    public synchronized void  start(){
        //Start a thread and make the running boolean true to start the game loop
        thread = new Thread(this);
        thread.start();
        running=true;
    }
    public synchronized void stop(){
        try{
            //join the thread to stop and set the running boolean false to stop the game loop
            thread.join();
            running=false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void run(){
        //game loop
            long lastTime=System.nanoTime();
            double amountOfTicks=60.0;
            double ns=100000000/amountOfTicks;
            double delta=0;
            long tim = System.currentTimeMillis();
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
        /**
         *Clamp method so it will not overflow / underflow
         **/
        if(var>=max)
            //if the variable is max or higher then max , return max
            return var=max;
        else if(var<=min)
            //if the variable is min or lower then min , return min
            return var=min;
        else
            //if none of the above are true , do nothing
            return var;
    }
    public static float HealthClamp(float var,float min,float max)
    {
        //same as above just it's for Health specific
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
        //check if the player has 0 hp
        if(hud.HEALTH==0 && f==true)
        {
            //if true , then save the coins to file
            rc.onClose();
            //set the boolean to false to avoid repetition
            f=false;
            //set the displayed coins to 0
            hud.COINS=0;
            //read coins from file
            rc.onOpen();
            //open the shop window
            new Shop(ps.getSpeed(),ps.getHealth(),ps.getAditionalCoins(),ps,hud,this);
            //gc whatever it can
            System.gc();
        }
        //tick the game
        handler.tick();
        hud.tick();
        spawner.tick();
    }
    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null)
        {
            //if the bufferstrategy is new , make a new one
            this.createBufferStrategy(3);
            return;
        }
        //initialize graphics
        Graphics g = bs.getDrawGraphics();
        //set the background color to black
        g.setColor(Color.black);
        //fill the background
        g.fillRect(0, 0, WIDTH, HEIGHT);
        //render the gameobjects
        handler.render(g);
        //render the hud
        hud.render(g);
        //dispose the graphics
        g.dispose();
        //paint the graphis
        bs.show();
    }
//</editor-fold>
}
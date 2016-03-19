package angon;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/**
 *~Already did the comments
 * @author Deyu
 */
public class HUD{
    //@todo:Fix HEALTH
    public static float HEALTH =100;
    //Initialize Coins/TotalCoins/Level/Timer/healthbar color
    public static int TOTALCOINS = 0;
    public static int COINS=0;
    public static int Level=1;
    private int greenValue=255;
    private Timer timer;
    public HUD(Timer timer){
    this.timer=timer;
    
    }
    public void tick()
    {
    //clamp the health so it will not go above 100 and below 0
    HEALTH=Game.HealthClamp(HEALTH, 0, 100);
    //clamp hud health bar
    greenValue=(int) Game.clamp(greenValue,0,255);
    
    greenValue=(int) (HEALTH*2+55);

    }
    public void render(Graphics g)
    {
        //set color for healthbar background
        g.setColor(Color.gray);
        //fill the healthbar background
        g.fillRect(15,15,200,32);
        //set color for healthbar
        g.setColor(new Color(75,greenValue,0));
        //fill the healthbar
        g.fillRect(15, 15, (int) (HEALTH*2), 32);
        //set the color for coins/timespent
        g.setColor(Color.white);
        //Scale the font to 18.0f
        g.setFont(g.getFont().deriveFont(18.0f));
        //draw the string
        g.drawString(""+COINS, 80, 80);
        g.drawString(timer.getTime(), 300, 15);
        //set color for totalcoins to be displayed
        //@todo: fix the totalcoins display 
        g.setColor(Color.YELLOW);
        g.drawString(""+TOTALCOINS,600-15,15);
    }
    public int getLevel()
    {
        //return the level you are at
    return Level;
    }
}

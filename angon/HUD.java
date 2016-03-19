/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angon;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/**
 *
 * @author Deyu
 */
public class HUD{
    public static float HEALTH =100;
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
    HEALTH=Game.HealthClamp(HEALTH, 0, 100);
    
    greenValue=(int) Game.clamp(greenValue,0,255);
    
    greenValue=(int) (HEALTH*2+55);

    }
    public void render(Graphics g)
    {
        
        g.setColor(Color.gray);
        g.fillRect(15,15,200,32);
        g.setColor(new Color(75,greenValue,0));
        g.fillRect(15, 15, (int) (HEALTH*2), 32);
        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(18.0f));
        g.drawString(""+COINS, 80, 80);
        g.drawString(timer.getTime(), 300, 15);
        g.setColor(Color.YELLOW);
        g.drawString(""+TOTALCOINS,600-15,15);
    }
    public int getLevel()
    {
    return Level;
    }
}

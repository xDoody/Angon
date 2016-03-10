/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angon;

import angon.GameObjects.ID;
import angon.GameObjects.GameObject;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Deyu
 */
public class KeyImput extends KeyAdapter {
    private final Handler handler;
    public KeyImput(Handler handler)
    {
    this.handler = handler;
    }
    @Override
    public void keyPressed(KeyEvent e)
    {
    int key = e.getKeyCode();
    
    for(int i=0;i<handler.object.size();i++)
        {
         GameObject tempObject = handler.object.get(i);
         if(tempObject.getId()==ID.Player)
            {
                if(key==KeyEvent.VK_W) tempObject.setVelY(-1);
                if(key==KeyEvent.VK_S) tempObject.setVelY(1);
                if(key==KeyEvent.VK_A) tempObject.setVelX(-1);
                if(key==KeyEvent.VK_D) tempObject.setVelX(1);
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e)
    {
    int key=e.getKeyCode();
    
    for(int i=0;i<handler.object.size();i++)
        {
         GameObject tempObject = handler.object.get(i);
         if(tempObject.getId()==ID.Player)
            {
                if(key==KeyEvent.VK_W) tempObject.setVelY(0);
                if(key==KeyEvent.VK_S) tempObject.setVelY(0);
                if(key==KeyEvent.VK_A) tempObject.setVelX(0);
                if(key==KeyEvent.VK_D) tempObject.setVelX(0);
            }
        }
    
    }
    
}

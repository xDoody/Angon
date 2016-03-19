/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angon;

import java.io.IOException;

/**
 *
 * @author Deyu
 */
public class GameStart {
        public static void main(String[] args) throws IOException {
        Game instance1=new Game();
        while (true)
        {
            System.out.append("");
            if(instance1.getk()==true)
            {
                System.out.println("New Game");
                instance1=null;
                System.out.println("Instance is nulled");
                System.gc();
                instance1.stop();
                System.out.println("Gc initiated");
                instance1=new Game();
                System.out.println("Game initated.");
                instance1.setk(false);
            }
            
        }
    }
}

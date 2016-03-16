/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angon.Shop;
import angon.HUD;
import angon.PlayerStats;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 *
 * @author Deyu
 */
public class shopDisplay extends JFrame {

    private static final long serialVersionUID = 1L;
    GridLayout gl = new GridLayout(4,3);
    ShopPrices sp;
    ShopReader sr;
    
    //<editor-fold defaultstate="collapsed" desc="Buttons">
    JButton HButton = new JButton("Buy");
    JButton ACButton= new JButton("Buy");
    JButton SButton = new JButton("Buy");
    //</editor-fold>
    
    public shopDisplay
        (
            int width , int height,
            int HPrice,int SPrice,int ACPrice,
            ShopPrices sp,ShopReader sr,PlayerStats ps,
            float Speed,int Health,int Coins
        )
    {
        this.sp=sp;
        this.sr=sr;
        //<editor-fold defaultstate="collapsed" desc="ActionListeners">
        HButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(HPrice<=Coins)
                {
                    
                    ps.setHBuf(10);
                }
            }
        });
        ACButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(ACPrice<=Coins)
                {
                    ps.setCBuf(1);
                }
            }
            
        });
        SButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(SPrice<=Coins)
                {
                    ps.setSBuf((float) 0.1);
                }
            }
            
        });
//</editor-fold>
        JFrame frame = new JFrame("Shop");
        frame.setLayout(gl);
        frame.setPreferredSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(new JLabel(""));
        frame.add(new JLabel("Total coins:"+HUD.TOTALCOINS));
        frame.add(new JLabel(""));
        frame.add(new JLabel("Health:"+Health));
        frame.add(new JLabel("+10 Cost:"+HPrice));
        frame.add(HButton);
        frame.add(new JLabel("Coins:"+Coins));
        frame.add(new JLabel("+1 Cost:"+ACPrice));
        frame.add(ACButton);
        frame.add(new JLabel("Speed:"+Speed));
        frame.add(new JLabel("+0.1 Cost:"+SPrice));
        frame.add(SButton);
    }
}

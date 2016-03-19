/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angon.Shop;

import angon.Game;
import angon.HUD;
import angon.PlayerStats;

/**
 *~Already did the comments
 * @author Deyu
 * 
 */
public class Shop {
    //Initialize a ShopReader to read from file
    ShopReader sr=new ShopReader();
    //Initialize a ShopPrices to set the prices
    ShopPrices sp=new ShopPrices(sr.hpCost,sr.SpeedCost,sr.AdCoinsCost,sr);
    
    public Shop(float Speed,int Health,int Coins,PlayerStats ps,HUD hud,Game game)
    {
    //Display the shop by calling shopDisplay()
    new shopDisplay(400,500,sr.getHPCOST(),sr.getSpeedCost(),sr.getAdCoinsCost(),sp,sr,ps,hud,Speed,Health,Coins,game);
    }
}

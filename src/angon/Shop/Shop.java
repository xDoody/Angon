/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angon.Shop;

import angon.PlayerStats;

/**
 *
 * @author Deyu
 * 
 */
public class Shop {
    ShopReader sr=new ShopReader();
    ShopPrices sp=new ShopPrices(sr.hpCost,sr.SpeedCost,sr.AdCoinsCost,sr);
    
    public Shop(float Speed,int Health,int Coins,PlayerStats ps)
    {
    new shopDisplay(400,500,sr.getHPCOST(),sr.getSpeedCost(),sr.getAdCoinsCost(),sp,sr,ps,Speed,Health,Coins);
    }
}

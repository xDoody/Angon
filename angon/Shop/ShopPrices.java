/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angon.Shop;

/**
 *
 * @author Deyu
 */
public class ShopPrices {
    //Initialize the health,speed,adcoins prices
    int HealthNPrice,SpeedNPrice,AdCoinsNPrice;
    //Initialize the ShopReader
    ShopReader sr;
    public ShopPrices(int HealthCurrentPrice,int SpeedCurrentPrice,int AdCoinsCurrentPrice,ShopReader sr)
    {
        //Class shopreader = already opened instance of shopreader;
        this.sr = sr;
        //Class int gets value
        HealthNPrice=HealthCurrentPrice;
        SpeedNPrice=SpeedCurrentPrice;
        AdCoinsNPrice=AdCoinsCurrentPrice;
    }
    public void setHealthPrice(int HealthNPrice)
    {
        //set the HPCOST to HealthNPrice
        sr.setHPCOST(HealthNPrice);
    }
    public void setSpeedPrice()
    {
        //set the SpeedCost to SpeedNPrice
        sr.setSpeedCost(SpeedNPrice);
    }
    public void setAdCoinsPrice()
    {
        //set the AdCoins to AdCoinsNPrice
        sr.setAdCoinsCost(AdCoinsNPrice);
    }
    public int getHealthNPrice()
    {
        //return the HealthNPrice
        return HealthNPrice;
    }
    public int getSpeedNPrice()
    {
        //return the SpeedNPrice
        return SpeedNPrice;
    }
    public int getAdCoinsNPrice()
    {
        //return the AdCoinsNPrice;
        return AdCoinsNPrice;
    }
    
}

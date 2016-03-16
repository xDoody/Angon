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
    int HealthNPrice,SpeedNPrice,AdCoinsNPrice;
    ShopReader sr;
    public ShopPrices(int HealthCurrentPrice,int SpeedCurrentPrice,int AdCoinsCurrentPrice,ShopReader sr)
    {
        this.sr = sr;
        HealthNPrice=HealthCurrentPrice-25*3;
        SpeedNPrice=SpeedCurrentPrice-25*3;
        AdCoinsNPrice=AdCoinsCurrentPrice-25*3;
    }
    public void setHealthPrice()
    {
        sr.setHPCOST(HealthNPrice);
    }
    public void setSpeedPrice()
    {
        sr.setSpeedCost(SpeedNPrice);
    }
    public void setAdCoinsPrice()
    {
        sr.setAdCoinsCost(AdCoinsNPrice);
    }
    public int getHealthNPrice()
    {
        return HealthNPrice;
    }
    public int getSpeedNPrice()
    {
        return SpeedNPrice;
    }
    public int getAdCoinsNPrice()
    {
        return AdCoinsNPrice;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angon.Shop;

import angon.readCoins;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Deyu
 */
public class ShopReader {
    int AdCoinsCost=0;
    int SpeedCost=0;
    int hpCost=0;
    public ShopReader()
    {
        onOpen();
    }
    //<editor-fold defaultstate="collapsed" desc="Set/Get">
    public void setSpeedCost(int speed)
    {
        this.SpeedCost=speed;
    }
    public int getSpeedCost()
    {
        return SpeedCost;
    }
    public void setAdCoinsCost(int adCoins)
    {
        this.AdCoinsCost=adCoins;
    }
    public int getAdCoinsCost()
    {
        return AdCoinsCost;
    }
    public void setHPCOST(int hp)
    {
        this.hpCost=hp;
    }
    public int getHPCOST()
    {
        return hpCost;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="onOpen">
    public void onOpen()
    {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("Shop.xml"));
            //<editor-fold defaultstate="collapsed" desc="Health">
            NodeList HPList = doc.getElementsByTagName("Health");
            for (int i = 0; i < HPList.getLength(); i++) {
                Node node = HPList.item(i);
                if (node.hasChildNodes()) {
                    
                    
                    NodeList textNodeList = node.getChildNodes();
                    StringBuilder textBuilder = new StringBuilder();
                    for (int j = 0; j < textNodeList.getLength(); j++) {
                        Node textNode = textNodeList.item(j);
                        if (textNode.getNodeType() == Node.TEXT_NODE) {
                            textBuilder.append(textNode.getNodeValue());
                        }
                    }
                    setHPCOST(Integer.parseInt(textBuilder.toString()));
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="AddedCoins">
NodeList CoinList = doc.getElementsByTagName("AddedCoins");
for (int i = 0; i < CoinList.getLength(); i++)
{
    Node node = CoinList.item(i);
    if (node.hasChildNodes())
    {
        NodeList textNodeList = node.getChildNodes();
        StringBuilder textBuilder = new StringBuilder();
        for (int j = 0; j < textNodeList.getLength(); j++) {
            Node textNode = textNodeList.item(j);
            if (textNode.getNodeType() == Node.TEXT_NODE) {
                textBuilder.append(textNode.getNodeValue());
            }
        }
        setAdCoinsCost(Integer.parseInt(textBuilder.toString()));
    }
}
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="Speed">
NodeList SpeedList = doc.getElementsByTagName("Speed");
for (int i = 0; i < SpeedList.getLength(); i++)
{
    Node node = SpeedList.item(i);
    if (node.hasChildNodes())
    {
        NodeList textNodeList = node.getChildNodes();
        StringBuilder textBuilder = new StringBuilder();
        for (int j = 0; j < textNodeList.getLength(); j++) {
            Node textNode = textNodeList.item(j);
            if (textNode.getNodeType() == Node.TEXT_NODE) {
                textBuilder.append(textNode.getNodeValue());
            }
        }
        setSpeedCost(Integer.parseInt(textBuilder.toString()));
    }
}
//</editor-fold>
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(readCoins.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(readCoins.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(readCoins.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//</editor-fold>
}

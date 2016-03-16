/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angon;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Deyu
 */
public class PlayerStats {
    int Health=0;
    int AditionalCoins=0;
    float Speed=0;
    int HBuf=0;
    float SBuf=0;
    int CBuf=0;
    HUD hud;
    Logger log;
    public PlayerStats(HUD hud,Logger log)
    {
    this.hud=hud;
    this.log=log;
    onOpen();
    }
    //<editor-fold defaultstate="collapsed" desc="Set/Get">
    public void setCBuf(int CBuf)
    {
        this.CBuf=CBuf;
    }
    public int getCBuf()
    {
        return CBuf;
    }
    public int getHBuf()
    {
        return HBuf;
    }
    public float getSBuf()
    {
        return SBuf;
    }
    public void setHBuf(int HBuf)
    {
        this.HBuf=HBuf;
    }
    public void setSBuf(float SBuf)
    {
        this.SBuf=SBuf;
    }
    public void setHealth(int Health)
    {
        this.Health=Health;
    }
    public int getHealth()
    {
        return Health;
    }
    public void setAditionalCoins(int AditionalCoins)
    {
        this.AditionalCoins=AditionalCoins;
    }
    public int getAditionalCoins()
    {
        return AditionalCoins;
    }
    public void setSpeed(float Speed)
    {
        this.Speed=Speed;
    }
    public float getSpeed()
    {
        return Speed;
    }
//</editor-fold>
    public void onClose()
    {
    try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("Player.xml"));
            Node staff = doc.getElementsByTagName("Stats").item(0);
            NodeList list = staff.getChildNodes();
            for (int i = 0; i < list.getLength(); i++)
            {
                
                Node node = list.item(i);
                
                if (node.getNodeType()==Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    if("Health".equals(eElement.getNodeName()))
                    {
                        String k=""+Health;
                        if(k.equals(eElement.getTextContent()))
                        {
                            int s=Health+HBuf;
                            eElement.setTextContent(""+s);
                        }
                    }
                    if("Speed".equals(eElement.getNodeName()))
                    {
                        String k=""+Speed;
                        if(k.equals(eElement.getTextContent()))
                        {
                            float s=Speed+SBuf;
                            eElement.setTextContent(""+s);
                        }
                    }
                    if("AditionalCoins".equals(eElement.getNodeName()))
                    {
                        String k=""+AditionalCoins;
                        if(k.equals(eElement.getTextContent()))
                        {
                            int s=AditionalCoins+CBuf;
                            eElement.setTextContent(""+s);
                        }
                    }
                }
            }
            //Writing
         TransformerFactory transformerFactory = 
         TransformerFactory.newInstance();
         Transformer transformer = transformerFactory.newTransformer();
         DOMSource source = new DOMSource(doc);
         StreamResult consoleResult = new StreamResult(new File("Player.xml"));
         transformer.transform(source, consoleResult);
         System.out.println("Coins saved");
        } catch (SAXException ex) {
            java.util.logging.Logger.getLogger(readCoins.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(readCoins.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            java.util.logging.Logger.getLogger(readCoins.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            java.util.logging.Logger.getLogger(readCoins.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            java.util.logging.Logger.getLogger(readCoins.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    //<editor-fold defaultstate="collapsed" desc="onOpen">
    public void onOpen()
    {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("Player.xml"));
            //<editor-fold defaultstate="collapsed" desc="Health">
            NodeList HealthList = doc.getElementsByTagName("Health");
            log.print("Starting Health extraction.");
            for (int i = 0; i < HealthList.getLength(); i++) {
                Node node = HealthList.item(i);
                if (node.hasChildNodes()) {
                    
                    
                    NodeList textNodeList = node.getChildNodes();
                    StringBuilder textBuilder = new StringBuilder();
                    for (int j = 0; j < textNodeList.getLength(); j++) {
                        Node textNode = textNodeList.item(j);
                        if (textNode.getNodeType() == Node.TEXT_NODE) {
                            textBuilder.append(textNode.getNodeValue());
                        }
                    }
                    setHealth(Integer.parseInt(textBuilder.toString()));
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="AditionalCoins">
NodeList AdCoinsList = doc.getElementsByTagName("AditionalCoins");
log.print("Starting AditionalCoins extraction.");
for (int i = 0; i < AdCoinsList.getLength(); i++) {
    Node node = AdCoinsList.item(i);
    if (node.hasChildNodes()) {
        
        
        NodeList textNodeList = node.getChildNodes();
        StringBuilder textBuilder = new StringBuilder();
        for (int j = 0; j < textNodeList.getLength(); j++) {
            Node textNode = textNodeList.item(j);
            if (textNode.getNodeType() == Node.TEXT_NODE) {
                textBuilder.append(textNode.getNodeValue());
            }
        }
        setAditionalCoins(Integer.parseInt(textBuilder.toString()));
    }
}
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="Speed">
NodeList SpeedList = doc.getElementsByTagName("Speed");
    log.print("Starting Speed extraction.");
for (int i = 0; i < SpeedList.getLength(); i++) {
    Node node = SpeedList.item(i);
    if (node.hasChildNodes()) {
        
        
        NodeList textNodeList = node.getChildNodes();
        StringBuilder textBuilder = new StringBuilder();
        for (int j = 0; j < textNodeList.getLength(); j++) {
            Node textNode = textNodeList.item(j);
            if (textNode.getNodeType() == Node.TEXT_NODE) {
                textBuilder.append(textNode.getNodeValue());
            }
        }
        setSpeed(Float.parseFloat(textBuilder.toString()));
    }
}
//</editor-fold>
        } catch (ParserConfigurationException ex) {
            java.util.logging.Logger.getLogger(readCoins.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            java.util.logging.Logger.getLogger(readCoins.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(readCoins.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
//</editor-fold>
}

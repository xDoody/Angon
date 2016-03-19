/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angon;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author Deyu
 */

public class readCoins {
    String xml ="C:\\Users\\Deyu\\Documents\\NetBeansProjects\\Angon\\src\\angon\\Coins.xml";
    int coins=0;
    int buf;
    HUD hud;
    public readCoins(HUD hud)
    {
        this.hud=hud;
        onOpen();
    }
//<editor-fold defaultstate="collapsed" desc="Set/Get">
    public void setCoins(int coins)
    {
        this.coins+=coins;
    }
    public int getCoins(){
        return coins;
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="onClose">
    public void onClose(){
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("Coins.xml"));
            Node staff = doc.getElementsByTagName("Coins").item(0);
            NamedNodeMap attr= staff.getAttributes();
            Node nodeAttr= attr.getNamedItem("id");
            NodeList list = staff.getChildNodes();
            for (int i = 0; i < list.getLength(); i++)
            {
                
                Node node = list.item(i);
                
                if (node.getNodeType()==Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    if("number".equals(eElement.getNodeName()))
                    {
                        String k=""+coins;
                        if(k.equals(eElement.getTextContent()))
                        {
                            int s=hud.COINS+coins;
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
         StreamResult consoleResult = new StreamResult(new File("Coins.xml"));
         transformer.transform(source, consoleResult);
         System.out.println("Coins saved");
        } catch (SAXException ex) {
            Logger.getLogger(readCoins.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(readCoins.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(readCoins.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(readCoins.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(readCoins.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="onOpen">
    public void onOpen() {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("Coins.xml"));
            NodeList nodeList = doc.getElementsByTagName("number");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.hasChildNodes()) {
                    
                    
                    NodeList textNodeList = node.getChildNodes();
                    StringBuilder textBuilder = new StringBuilder();
                    for (int j = 0; j < textNodeList.getLength(); j++) {
                        Node textNode = textNodeList.item(j);
                        if (textNode.getNodeType() == Node.TEXT_NODE) {
                            textBuilder.append(textNode.getNodeValue());
                        }
                    }
                    setCoins(Integer.parseInt(textBuilder.toString()));
                }
            }
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
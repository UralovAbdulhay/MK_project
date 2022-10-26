package newTest;

import org.w3c.dom.*;

import javax.naming.directory.Attribute;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class App {
    public static void main(String[] args) throws Exception {


        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc = db.parse("input/response_latin.xml");

        NodeList osis = doc.getElementsByTagName("div");

        System.out.println(osis.getLength());
        for (int i = 0; i < osis.getLength(); i++) {
            Node node = osis.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) node;
                NamedNodeMap attributes = e.getAttributes();

                for (int j = 0; j < attributes.getLength(); j++) {
                    Node n1 = attributes.item(j);
                    System.out.println(n1.getNodeName() + " = " + n1.getNodeValue());

                }


//                System.out.println(e.getAttribute("osisID"));
            }
            System.out.println("*************************");
        }


    }
}

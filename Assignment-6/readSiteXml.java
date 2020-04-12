package xml.assignment;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class readSiteXml {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();

            DocumentBuilder dBuilder =
                    dbFactory.newDocumentBuilder();

            File xmlFile = new File("src/site.xml");
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            String r = doc.getDocumentElement().getNodeName();
            System.out.println(r); // --> "site"

            NodeList views;
            views = doc.getElementsByTagName("view");
            NodeList components;
            components = doc.getElementsByTagName("component");

            int viewCount = views.getLength();
            int componentCount = components.getLength();

            for (int i = 0; i < viewCount; i++) {
                Node viewNode = views.item(i);
                short nodeType = viewNode.getNodeType();
                if (nodeType == Node.ELEMENT_NODE) {
                    Element view = (Element) viewNode;

                    String id = view.getAttribute("id");
                    String name = view.getAttribute("name");

                    System.out.println(id);
                    System.out.println(name);

                    Node descriptionNode = view
                            .getElementsByTagName("description").item(0);

                    Element description = (Element) descriptionNode;

                    String descript = description.getTextContent();

                    System.out.println(descript);
                }
            }
            for (int i = 0; i < componentCount; i++) {
                Node componentNode = components.item(i);
                short nodeType = componentNode.getNodeType();
                if (nodeType == Node.ELEMENT_NODE) {
                    Element component = (Element) componentNode;

                    String id = component.getAttribute("id");
                    String type = component.getAttribute("type");
                    String src = component.getAttribute("src");
                    String url = component.getAttribute("url");
                    String label = component.getAttribute("label");

                    System.out.println(id);
                    System.out.println(type);
                    System.out.println(src);
                    System.out.println(url);
                    System.out.println(label);

//                    Node textNode = component
//                            .getElementsByTagName("text").item(0);
//
//                    Element text = (Element) textNode;
//
//                    String textt = text.getTextContent();
//
//                    System.out.println(textt);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

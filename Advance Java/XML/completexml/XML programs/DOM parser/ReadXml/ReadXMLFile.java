import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ReadXMLFile 
{
    public static void main(String argv[]) 
	{
        try 
		{
            File fXmlFile = new File("staff.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            Element e = doc.getDocumentElement();
            e.normalize();

            System.out.println("Root element : " + e.getNodeName());

            NodeList nList = doc.getElementsByTagName("staff");
            System.out.println(nList.getLength());
            System.out.println("----------------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) 
			{
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element : " + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) 
				{
                    Element eElement = (Element) nNode;

                    System.out.println("Staff id : " + eElement.getAttribute("id"));

                    NodeList List1 = eElement.getElementsByTagName("firstname");
                    Node d = List1.item(0);
                    System.out.println("First Name : " + d.getTextContent());
                    System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
                    System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
                    System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
                }
            }
        } 
		catch (Exception e) 
		{
            e.printStackTrace();
        }
    }
}

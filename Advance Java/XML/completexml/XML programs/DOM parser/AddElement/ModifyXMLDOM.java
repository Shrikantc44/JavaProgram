// File aur Exception handling ke liye import
import java.io.File;
import java.io.IOException;

// XML parsing ke liye imports
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

// XML file ko update (transform) karne ke liye imports
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

// DOM API ke imports
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// XML parsing error handling ke liye
import org.xml.sax.SAXException;

public class ModifyXMLDOM
{
    public static void main(String[] args)
    {
        // XML file ka path specify karna
        String filePath = "employee.xml";
        File xmlFile = new File(filePath);

        // DocumentBuilderFactory instance banate hai (factory pattern)
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try 
		{
            // DocumentBuilder object banate hai
            dBuilder = dbFactory.newDocumentBuilder();

            // XML file ko parse karke Document object banate hai
            Document doc = dBuilder.parse(xmlFile);

            // Root element (EmployeeRecords ya Employees) ko normalize karna
            // Normalization ka matlab: XML ke text nodes ko properly combine karna
            Element e = doc.getDocumentElement();
            e.normalize();

            // Different operations call karte hai XML modify karne ke liye
            updateAttributeValue(doc);  // Step 1: Attribute update
            updateElementValue(doc);    // Step 2: Element text value update
            deleteElement(doc);         // Step 3: Element delete
            addElement(doc);            // Step 4: Naya element add

            // Updated document ko file me likhna
            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // Source (Document) aur destination (File) define karte hai
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("employee_updated.xml"));

            // XML ko readable banane ke liye indentation set karte hai
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Document ko transform karke updated file save karte hai
            transformer.transform(source, result);

            System.out.println("XML file updated successfully");

        } 
		catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) 
		{
            // Agar koi error hota hai toh stack trace print hoga
            e1.printStackTrace();
        }
    }

    // ----------------- Custom methods -----------------

    // Method: Naya element add karna (salary tag add hoga har Employee me)
    private static void addElement(Document doc) 
	{
        // Saare <Employee> elements fetch karo
        NodeList employees = doc.getElementsByTagName("Employee");
        Element emp = null;

        // Har employee ke andar ek <salary> element add kar do
        for(int i=0; i<employees.getLength(); i++)
		{
            emp = (Element) employees.item(i);

            // salary element create karke text value set karo
            Element salaryElement = doc.createElement("salary");
            salaryElement.appendChild(doc.createTextNode("10000"));

            // Employee ke andar salary tag ko append karna
            emp.appendChild(salaryElement);
        }
    }

    // Method: Kisi element ko delete karna (yaha <gender> element delete hota hai)
    private static void deleteElement(Document doc) 
	{
        NodeList employees = doc.getElementsByTagName("Employee");
        Element emp = null;

        // Har employee ke andar se <gender> element remove kar dete hai
        for(int i=0; i<employees.getLength(); i++)
		{
            emp = (Element) employees.item(i);

            // gender node fetch karo
            Node genderNode = emp.getElementsByTagName("gender").item(0);

            // gender ko remove kar do
            emp.removeChild(genderNode);
        }
    }
	
    // Method: Kisi element ka text value update karna
    private static void updateElementValue(Document doc) 
	{
        NodeList employees = doc.getElementsByTagName("Employee");
        Element emp = null;

        // Har employee ka name update karne ke liye array banaya
        String ename[] = {"lalu", "rabari"};

        for(int i=0; i<employees.getLength(); i++) 
		{
            emp = (Element) employees.item(i);

            // <name> element ka pehla child (text node) nikal lo
            Node name = emp.getElementsByTagName("name").item(0).getFirstChild();

            // Name ko update kar do (hardcoded array se)
            name.setNodeValue(ename[i]);
        }
    }
	
    // Method: Employee ke id attribute ko update karna based on gender
    private static void updateAttributeValue(Document doc) 
	{
        NodeList employees = doc.getElementsByTagName("Employee");
        Element emp = null;

        // Har employee ke gender ke basis par uske id attribute ko modify karna
        for(int i=0; i<employees.getLength(); i++) 
		{
            emp = (Element) employees.item(i);

            // Gender fetch karna (text content le kar)
            String gender = emp.getElementsByTagName("gender").item(0).getTextContent();

            if(gender.equalsIgnoreCase("male")) 
			{
                // Agar male hai toh id ke aage "M" prefix lagao
                emp.setAttribute("id", "M" + emp.getAttribute("id"));
            } 
			else 
			{
                // Agar female hai toh id ke aage "F" prefix lagao
                emp.setAttribute("id", "F" + emp.getAttribute("id"));
            }
        }
    }
}

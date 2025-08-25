// Required imports
import java.io.File;
import javax.xml.transform.OutputKeys;                 // Output formatting ke liye
import javax.xml.parsers.DocumentBuilder;             // Document build karne ke liye
import javax.xml.parsers.DocumentBuilderFactory;      // DocumentBuilder banane ke liye factory class
import javax.xml.parsers.ParserConfigurationException; // Parser ke configuration exception ke liye
import javax.xml.transform.Transformer;               // XML ko transform karne ke liye (object -> file)
import javax.xml.transform.TransformerException;      // Transformation error ke liye
import javax.xml.transform.TransformerFactory;        // Transformer banane ke liye factory
import javax.xml.transform.dom.DOMSource;             // DOM source (Document object ko input dene ke liye)
import javax.xml.transform.stream.StreamResult;       // Output stream/file ke liye

import org.w3c.dom.Attr;                              // XML attribute handle karne ke liye
import org.w3c.dom.Document;                          // Entire XML document ko represent karta hai
import org.w3c.dom.Element;                           // XML element ko represent karta hai

public class CreateXml
{
    public static void main(String... s)
    {
        try 
        {
            // 1. DocumentBuilderFactory banate hain (Factory design pattern ka use hota hai)
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            // 2. DocumentBuilder create karte hain (jo XML Document banayega)
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            // 3. Naya Document create karte hain (ye blank XML document hai)
            Document document = documentBuilder.newDocument();

            // 4. Root element <school> create karke Document me append kar dete hain
            Element rootElement = document.createElement("school");
            document.appendChild(rootElement);

            // 5. <Student> element create karke root ke andar add kar dete hain
            Element student = document.createElement("Student");
            rootElement.appendChild(student);

            // 6. <Student> element me ek attribute "id" create karke uska value "1" set karte hain
            Attr attribute = document.createAttribute("id");
            attribute.setValue("1");
            student.setAttributeNode(attribute);

            // 7. <firstname> element create karte hain aur usme text value "Shrikant" add karte hain
            Element firstname = document.createElement("firstname");
            firstname.appendChild(document.createTextNode("Shrikant"));
            student.appendChild(firstname);

            // 8. <lastname> element create karte hain aur usme text value "Chauhan" add karte hain
            Element lastname = document.createElement("lastname");
            lastname.appendChild(document.createTextNode("Chauhan"));
            student.appendChild(lastname);

            // 9. <email> element create karte hain aur usme email value add karte hain
            Element email = document.createElement("email");
            email.appendChild(document.createTextNode("abc@gmail.com"));
            student.appendChild(email);

            // 10. <phone> element create karte hain aur usme phone number add karte hain
            Element phone = document.createElement("phone");
            phone.appendChild(document.createTextNode("123456789"));
            student.appendChild(phone);

            // 11. TransformerFactory ka object banate hain (jo Document ko XML file me convert karega)
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            // 12. Transformer banate hain (jo actual transformation karega)
            Transformer transformer = transformerFactory.newTransformer();

            // 13. DOMSource object banate hain jisme input hai (hamara Document object)
            DOMSource domSource = new DOMSource(document);

            // 14. StreamResult object banate hain jisme output location set karte hain (createFile.xml file)
            StreamResult streamResult = new StreamResult(new File("createFile.xml"));

            // 15. Output property set karte hain taaki XML properly indented ho (readable format me)
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // 16. Transformation karte hain (Document ko file me likhna)
            transformer.transform(domSource, streamResult);

            // 17. Success message print karte hain
            System.out.println("File saved to specified path!");

        }
        catch (ParserConfigurationException pce) 
        {
            // Agar DocumentBuilder create nahi ho paya to ye exception aayega
            pce.printStackTrace();
        }
        catch (TransformerException tfe) 
        {
            // Agar Document ko XML file me transform karne me problem aayi to ye exception aayega
            tfe.printStackTrace();
        }
    }
}

/*
========================= SUMMARY =========================
Ye program DOM API ka use karke ek naya XML file banata hai.

1. Sabse pehle DocumentBuilderFactory aur DocumentBuilder banake ek blank Document create kiya.
2. Root element <school> banaya aur append kiya.
3. Uske andar ek <Student> element banaya jisme ek attribute "id" set kiya.
4. Student ke andar child elements banaye: <firstname>, <lastname>, <email>, <phone>.
5. Har element me ek text value add ki gayi (e.g. Shrikant, Chauhan, abc@gmail.com, 123456789).
6. Phir TransformerFactory aur Transformer ka use karke is Document ko ek XML file
   ("createFile.xml") me likha gaya.
7. Output XML properly indented (readable format) hota hai.

Final "createFile.xml" output kuch aisa dikhega:

<school>
    <Student id="1">
        <firstname>Shrikant</firstname>
        <lastname>Chauhan</lastname>
        <email>abc@gmail.com</email>
        <phone>123456789</phone>
    </Student>
</school>
===========================================================
*/

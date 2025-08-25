// Required packages import karna 
import java.io.*;                          // File handling ke liye
import javax.xml.parsers.*;                // XML parsing ke liye DocumentBuilderFactory, DocumentBuilder
import org.w3c.dom.*;                      // DOM API classes jaise Document, Element, NodeList, Text

// Main class definition
public class Dom
{
    public static void main(String s[])    // Program execution ka starting point
    {
        try
        {
            // 1. DocumentBuilderFactory ka object banate hai
            // Yeh ek factory class hai jo DocumentBuilder objects banati hai
            DocumentBuilderFactory fact1 = DocumentBuilderFactory.newInstance();
            
            // Validation enable karte hai (DTD/XML Schema ke against validation karega)
            fact1.setValidating(true);
            
            // Whitespace ko ignore karega (jo XML ke formatting ke liye hote hain, unhe content na maane)
            fact1.setIgnoringElementContentWhitespace(true);

            // 2. Factory se DocumentBuilder object banate hai
            DocumentBuilder build1 = fact1.newDocumentBuilder();

            // 3. XML file ka path specify karte hai
            String book1 = "book.xml";

            // 4. XML file ko parse karke Document object banate hai
            // Document puri XML tree ko memory me represent karta hai
            Document bookdoc = build1.parse(new File(book1));

            // 5. Root element (jaise <book>) ko fetch karte hai
            Element bookele = bookdoc.getDocumentElement();

            // Root ka naam print karte hai
            System.out.println("root " + bookele.getNodeName() + " ");

            // 6. Root ke andar jitne child nodes hai (chapters), unhe NodeList me store karte hai
            NodeList chapternodes = bookele.getChildNodes();

            // Total child nodes print karna
            System.out.println(chapternodes.getLength());

            // 7. For loop ke through har chapter node ko traverse karte hai
            for(int i = 0; i < chapternodes.getLength(); i++)
            {
                // Node ko typecast karke Element banate hai
                Element chapter = (Element) chapternodes.item(i);

                // Current element ka naam print karte hai (jaise <chapter>)
                System.out.println(chapter.getNodeName() + " ");

                // 8. Har chapter ke andar <chapNum> element find karte hai
                NodeList numberlist = chapter.getElementsByTagName("chapNum");

                // <chapNum> ke andar text node hota hai, use fetch karte hai
                Text number = (Text) numberlist.item(0).getFirstChild();

                // Chapter number print karte hai
                System.out.println(number.getData() + " ");

                // 9. Har chapter ke andar <chapTitle> element find karte hai
                NodeList titlelist = chapter.getElementsByTagName("chapTitle");

                // <chapTitle> ke andar text node hota hai, use fetch karte hai
                Text title = (Text) titlelist.item(0).getFirstChild();

                // Chapter title print karte hai
                System.out.println(title.getData());
            }
        }
        catch(Exception e) 
        { 
            // Agar koi error aata hai toh exception print kar dete hai
            System.out.println(e); 
        }
    }
}

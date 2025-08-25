import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class ModifyXMLFile {

    public static void main(String[] args)
    {
        try {

            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File("file.xml");

            Document doc = (Document) builder.build(xmlFile);
            Element rootNode = doc.getRootElement();

            // update staff id attribute
            Element staff = rootNode.getChild("staff");
            staff.getAttribute("id").setValue("4");

            // add new age element
            Element age = new Element("age").setText("28");
            staff.addContent(age);

            // update salary value
            staff.getChild("salary").setText("102");

            // remove firstname element
            staff.removeChild("lastname");

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("file9.xml"));

            // xmlOutput.output(doc, System.out);
            System.out.println("File updated!");
        } catch (IOException io) {
            io.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
    }
}

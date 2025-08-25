import java.io.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class SAX extends DefaultHandler
{
    public static void main(String arr[])
    {
        try
        {
            SAXParserFactory f = SAXParserFactory.newInstance();
            SAXParser p = f.newSAXParser();
            p.parse(new File("students.xml"), new SAX());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void startDocument() throws SAXException
    {
        System.out.println("document parsing started...");
    }

    public void endDocument() throws SAXException
    {
        System.out.println("document parsing ended...");
    }

    public void startElement(String uri, String sname, String qname, Attributes attr) throws SAXException
    {
        System.out.println(qname + " is an element");
        int len = attr.getLength();
        for(int i = 0; i < len; i++)
        {
            System.out.println("attribute name: " + attr.getQName(i));
            System.out.println("value: " + attr.getValue(i));
        }
    }

    public void characters(char buf[], int offset, int len) throws SAXException
    {
        String s = new String(buf, offset, len);
        System.out.println("value is: " + s);
    }
}

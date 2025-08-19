import java.util.*;

class MyLocale {
    public static void main(String S[]) {
        //Locale l = new Locale("fr", "FR");     // French locale (commented)
        //Locale l = new Locale("hi", "IN");     // Hindi locale (commented)

        Locale l = Locale.getDefault();         // System ki default locale le raha hai

        ResourceBundle rb = ResourceBundle.getBundle("ResourceBundle", l);
        System.out.println(Locale.getDefault().toString());

        Enumeration e = rb.getKeys();           // Sabhi keys fetch kar raha hai

        while (e.hasMoreElements()) {
            String k = (String) e.nextElement();    // key
            String v = rb.getString(k);             // key se corresponding value
            System.out.println(k + " = " + v);      // print key=value
        }
    }
}

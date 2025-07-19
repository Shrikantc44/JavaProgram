// DateFormat class aur Date class ko import karte hain
import java.text.DateFormat;
import java.util.Date;

public class DateFormatClass {
    public static void main(String[] args) {

        // Ek nayi current Date ka object banaya
        Date d1 = new Date();

        // 6 DateFormat objects ke liye array banaya
        DateFormat[] dfa = new DateFormat[6];

        // Default getInstance method se format object banaya
        dfa[0] = DateFormat.getInstance();  // default date-time format
        dfa[1] = DateFormat.getDateInstance();  // default date format
        dfa[2] = DateFormat.getDateInstance(DateFormat.SHORT);   // short format (e.g. 7/19/25)
        dfa[3] = DateFormat.getDateInstance(DateFormat.MEDIUM);  // medium format (e.g. Jul 19, 2025)
        dfa[4] = DateFormat.getDateInstance(DateFormat.LONG);    // long format (e.g. July 19, 2025)
        dfa[5] = DateFormat.getDateInstance(DateFormat.FULL);    // full format (e.g. Saturday, July 19, 2025)

        // Pehle normal for loop se sabhi formats ka output print kiya
        for (int i = 0; i < dfa.length; i++) {
            System.out.println(dfa[i].format(d1));
        }

        // Ab enhanced for-each loop se bhi wahi format print kiya
        for (DateFormat df : dfa) {
            System.out.println(df.format(d1));
        }
    }
}

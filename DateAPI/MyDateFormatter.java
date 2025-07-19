// SimpleDateFormat aur Date class ko import kiya gaya hai
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateFormatter {
    public static void main(String a[]) {

        //SimpleDateFormat object banaya gaya hai, jisme date format diya gaya hai:
        // yyyy.MM.dd = year.month.day
        // G = Era (AD ya BC)
        // 'at' = as a string literal (exactly likha jaayega)
        // HH:mm:ss = hours, minutes, seconds
        // z = time zone
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");

        // formatted date ko print karte hain pehle format ke according
        System.out.println("yyyy.MM.dd G 'at' HH:mm:ss z  ---> " + sdf.format(new Date()));

        // ab sdf ko naya format diya gaya hai
        // hh = 12-hour format, o'clock = string literal, a = AM/PM, zzzz = full time zone name
        sdf = new SimpleDateFormat("hh 'o''clock' a, zzzz");

        // naye format ke according current date/time ko print karte hain
        System.out.println("hh 'o''clock' a, zzzz  ---> " + sdf.format(new Date()));
    }
}

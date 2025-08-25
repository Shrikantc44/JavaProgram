// Importing all utility classes like Date, GregorianCalendar etc.
import java.util.*;

public class CurrentDate 
{
    public static void main(String args[])
	{

        // Getting today's current date and time
        Date today = new Date();

        // Alternate tareeke se date banane ke liye commented lines hain:
        // Date today = new Date("05/12/2014 18:30:45");
        // Date today = new Date(5, 12, 2014, 18, 30, 45);  // ye tareeka deprecated hai

        // Creating object of GregorianCalendar
        GregorianCalendar cal = new GregorianCalendar();

        // Calendar object ko aaj ki date ke according set kar rahe hain
        cal.setTime(today);

        // Current date ko print kar raha hai
        System.out.println("Today: " + today.toString());

        // Calendar ki detail info print karte hain
        displayDateInfo(cal);

        // Calendar object ko clear karte hain (sab data hata deta hai)
        cal.clear();
    }

    // Ye method calendar object se year, month, date, time, timezone ka data print karta hai
    static void displayDateInfo(GregorianCalendar cal)
	{

        // Week days ka naam store karne ke liye string array
        String days[] = {"", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        // Months ke naam store karne ke liye string array
        String months[] = {
            "January", "February", "March", "April", "May", "June", 
            "July", "August", "September", "October", "November", "December"
        };

        // AM aur PM ke liye string array
        String am_pm[] = {"AM", "PM"};

        // Calendar se alag-alag values nikal ke print kar rahe hain
        System.out.println("Year: " + cal.get(Calendar.YEAR));
        System.out.println("Month: " + months[cal.get(Calendar.MONTH)]);
        System.out.println("Date: " + cal.get(Calendar.DATE));
        System.out.println("Day: " + days[cal.get(Calendar.DAY_OF_WEEK)]);
        System.out.println("Hour: " + cal.get(Calendar.HOUR));
        System.out.println("Minute: " + cal.get(Calendar.MINUTE));
        System.out.println("Second: " + cal.get(Calendar.SECOND));
        System.out.println(am_pm[cal.get(Calendar.AM_PM)]); // AM ya PM print karega

        // Time zone ki information get karke print karte hain
        TimeZone tz = cal.getTimeZone();
        System.out.println("Time Zone: " + tz.getID());
    }
}

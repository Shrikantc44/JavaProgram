// Importing the Date class from java.util package to work with date and time
import java.util.Date;


public class CurrentDateTime
{

    public static void main(String[] args)
    {
        // Creating a Date object 'd' which holds the current date and time
        Date d = new Date();

        // Converting the Date object to a String representation using toString()
        String s = d.toString();

        // Printing the string representation of current date and time
        System.out.println(s);
    }
}

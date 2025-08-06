// The map() intermediate operation converts each element in the stream into another object
// via the given function.

// The following example converts each string into an UPPERCASE string.
// But we can use map() to transform an object into another type as well.

import java.util.stream.*;  // Stream operations ke liye import
import java.util.*;         // List aur ArrayList ke liye import

public class StreamMapMethod  // Class ka naam StreamMapMethod hai
{
    public static void main(String[] args)
    {
        // String list banayi gayi hai jisme names store honge
        List<String> names = new ArrayList<>();

        // List me names add kiye ja rahe hain
        names.add("Amitabh");
        names.add("Shekhar");
        names.add("Aman");
        names.add("Rahul");
        names.add("Shahrukh");
        names.add("Salman");
        names.add("Yana");
        names.add("Lokesh");

        // Stream start ki gayi list ke upar
        names.stream()

            // filter() se un elements ko select kar rahe hain jo 'A' se start hote hain
            .filter((s) -> s.startsWith("A"))

            // map() se har selected string ko uppercase me convert kar rahe hain
            .map(String::toUpperCase)

            // forEach() se har element ko print kar rahe hain
            .forEach(System.out::println);
    }
}

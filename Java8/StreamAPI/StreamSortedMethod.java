// The sorted() method is an intermediate operation that returns a sorted view of the stream.
// The elements in the stream are sorted in natural order unless we pass a custom Comparator.

import java.util.stream.*; // Stream API ko import kar raha hai
import java.util.*;        // List aur ArrayList jaise collections ke liye import

public class StreamSortedMethod // Class ka naam StreamSortedMethod hai
{
    public static void main(String[] args) // main method, program yahin se start hota hai
    {
        // String type ki List banayi gayi hai jisme names store honge
        List<String> names = new ArrayList<>();

        // List mein different names add kiye gaye hain
        names.add("Amitabh");
        names.add("Shekhar");
        names.add("Aman");
        names.add("Rahul");
        names.add("Shahrukh");
        names.add("Salman");
        names.add("Yana");
        names.add("Lokesh");

        // Stream bana kar names ko sorted order mein convert kiya ja raha hai
        names.stream()              // List ko stream mein convert karta hai
             .sorted()             // Stream ke elements ko natural (alphabetical) order mein sort karta hai
             .map(String::toUpperCase) // Har element ko uppercase mein convert karta hai
             .forEach(System.out::println); // Har element ko print karta hai (ek ek line mein)
    }
}

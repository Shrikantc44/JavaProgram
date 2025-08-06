//The map() intermediate operation converts each element in the stream into another object via the given function.

//The following example converts each string into an UPPERCASE string. But we can use map() to transform an object into another type as well.
import java.util.stream.*;
import java.util.*;

public class StreamMapMethod1 {
    public static void main(String[] args) {

        List<String> names = new ArrayList<>();
        names.add("Amitabh");
        names.add("Shekhar");
        names.add("Aman");
        names.add("Rahul");
        names.add("Shahrukh");
        names.add("Salman");
        names.add("Yana");
        names.add("Lokesh");

        names.stream().filter((s) -> s.startsWith("A"))
            .map(String::toUpperCase)
            .forEach(System.out::println);
    }
}

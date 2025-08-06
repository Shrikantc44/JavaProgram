//Q8: Count how many strings start with "A"

import java.util.*;
import java.util.stream.*;

public class String1 {
    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Arnold", "Bob", "Charlie", "Andrew");

        long count = names.stream()
            .filter(name -> name.startsWith("A"))
            .count();

        System.out.println(count);  // Output: 3
    }
}



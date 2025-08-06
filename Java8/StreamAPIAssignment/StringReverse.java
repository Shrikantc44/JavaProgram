import java.util.*;
import java.util.stream.*;

public class StringReverse {
    public static void main(String[] args) {
        List<String> fruits = List.of("apple", "banana", "cherry", "date");

        List<String> sortedFruits = fruits.stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());

        System.out.println(sortedFruits);  // Output: [date, cherry, banana, apple]
    }
}

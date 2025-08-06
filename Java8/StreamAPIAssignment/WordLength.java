import java.util.*;
import java.util.stream.*;

public class WordLength {
    public static void main(String[] args) {
        List<String> words = List.of("one", "two", "three", "four", "five");

        Map<Integer, List<String>> groupedByLength = words.stream()
            .collect(Collectors.groupingBy(String::length));

        System.out.println(groupedByLength);
        // Output: {3=[one, two], 5=[three], 4=[four, five]}
    }
}

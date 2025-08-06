//Q9: Given a list of strings, group them by anagram sets

import java.util.*;
import java.util.stream.*;

public class AnagramSet {
    public static void main(String[] args) {
        List<String> words = List.of("listen", "silent", "enlist", "rat", "tar", "art");

        Map<String, List<String>> anagramGroups = words.stream()
            .collect(Collectors.groupingBy(
                word -> word.chars()
                            .sorted()
                            .mapToObj(c -> String.valueOf((char) c))
                            .collect(Collectors.joining())
            ));

        System.out.println(anagramGroups);
        // Output: {eilnst=[listen, silent, enlist], art=[rat, tar, art]}
    }
}

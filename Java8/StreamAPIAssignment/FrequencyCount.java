// Q12: Given a list of sentences, count the frequency of each word (case-insensitive).

import java.util.*;
import java.util.stream.*;

public class FrequencyCount {
    public static void main(String[] args) {
        List<String> sentences = List.of("Java is fun", "Streams are powerful", "Java is powerful");

        Map<String, Long> wordFreq = sentences.stream()
            .flatMap(sentence -> Arrays.stream(sentence.toLowerCase().split("\\s+")))
            .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        System.out.println(wordFreq);
        // Output: {java=2, is=2, fun=1, streams=1, are=1, powerful=2}
    }
}

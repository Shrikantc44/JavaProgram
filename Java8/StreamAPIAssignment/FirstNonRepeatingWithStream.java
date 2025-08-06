import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeatingWithStream {
    public static void main(String[] args) {
        String s1 = "sssssHiiiili";

        Optional<Character> c1 = s1.chars() // IntStream
            .mapToObj(c -> (char) c) // Stream<Character>
            .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue() == 1)
            .map(Map.Entry::getKey)
            .findFirst();

        if (c1.isPresent()) {
            System.out.println("First non-repeating character is: " + c1.get());
        } else {
            System.out.println("No non-repeating character found.");
        }
    }
}


/* Working of code

s1.chars() – यह String को IntStream में convert करता है, यानी हर character को उसके ASCII code में बदल देता है।

.mapToObj(c -> (char) c) – हर character को वापस Character object में convert करता है ताकि हम उस पर stream operations कर सकें।

Collectors.groupingBy – सारे characters को group करता है और उनकी frequency (कितनी बार आए हैं) को count करता है।

LinkedHashMap का use इसलिए किया गया है ताकि characters की order वैसी ही बनी रहे जैसी वो string में थे। इससे हम पहले वाला non-repeating character आसानी से पकड़ सकते हैं।

उसके बाद stream को filter किया जाता है जहाँ value (count) सिर्फ 1 है, यानी जो character सिर्फ एक बार आया है।

.findFirst() – ऐसा पहला character return करता है जो repeat नहीं हुआ है।
*/
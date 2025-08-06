import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaUppercaseConversion {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("java", "spring", "lambda");

        // List ke elements ko uppercase me convert karna
        List<String> upperList = list.stream()
                                     .map(String::toUpperCase)
                                     .collect(Collectors.toList());

        // Print karna uppercase list
        upperList.forEach(System.out::println);
    }
}

/*

Explanation:

stream() se list ko stream me convert karte hain.

map() function har element par toUpperCase() method apply karta hai.

collect() se result ko list me collect karte hain.

forEach() se print kar dete hain.
*/
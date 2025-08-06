import java.util.Arrays;
import java.util.List;

public class LambdaStreamSorted {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Banana", "Pear", "Grapes");

        // Stream me elements ko sort karna aur print karna
        list.stream()
            .sorted()
            .forEach(System.out::println);
    }
}

/*
Explanation:

list.stream() se stream banta hai.

.sorted() se elements ko natural order (alphabetical) me sort karta hai.

.forEach(System.out::println) har sorted element ko print karta hai.
*/
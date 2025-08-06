import java.util.Arrays;
import java.util.List;

public class LambdaStreamDistinct {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 4, 4, 5);

        // Stream me se distinct elements ko filter karke print karna
        numbers.stream()
               .distinct()
               .forEach(System.out::println);  // Output: 1 2 3 4 5 (har element alag se print hoga)
    }
}

/*
Explanation:

numbers.stream() se stream banta hai.

.distinct() duplicate elements ko remove karta hai.

.forEach(System.out::println) har unique element ko print karta hai.
*/
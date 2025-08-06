import java.util.Arrays;
import java.util.List;

public class LambdaStreamCount {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        long count = numbers.stream()
                            .count();

        System.out.println("Count: " + count);  // Count: 5
    }
}

/*

Explanation:

numbers.stream() se list ke elements ka stream banta hai.

.count() stream ke total elements ki sankhya return karta hai.

Fir us result ko print karte hain.
*/
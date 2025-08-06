import java.util.Arrays;
import java.util.List;

public class LambdaStreamReduce {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Stream reduce use karke sum nikalna
        int sum = numbers.stream()
                         .reduce(0, (a, b) -> a + b);

        System.out.println("Sum: " + sum);  // Output: Sum: 15
    }
}

/*
Explanation:

numbers.stream() se list ko stream banaya.

reduce(0, (a, b) -> a + b) me 0 initial value hai aur lambda expression do numbers ka sum karta hai.

Result ko sum variable me store karke print kiya.

*/
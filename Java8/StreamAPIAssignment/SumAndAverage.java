// 15. Find the Sum and Average of a List of Numbers

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class SumAndAverage {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);

        int sum = numbers.stream()
            .mapToInt(Integer::intValue)
            .sum();

        OptionalDouble average = numbers.stream()
            .mapToInt(Integer::intValue)
            .average();

        System.out.println("Sum: " + sum);
        if (average.isPresent()) {
            System.out.println("Average: " + average.getAsDouble());
        } else {
            System.out.println("Average: Not available");
        }
    }
}


/* 

numbers.stream() → list ko stream mein convert karta hai.

.mapToInt(Integer::intValue) → Integer objects ko primitive int mein badalta hai.

.sum() → saare numbers ka sum nikalta hai.

.average() → saare numbers ka average nikalta hai (OptionalDouble return karta hai).

.isPresent() → check karta hai ki average calculate hua ya nahi.

.getAsDouble() → average ka value deta hai.

🧾 Output:

java
Copy
Edit
Sum: 150
Average: 30.0

 */
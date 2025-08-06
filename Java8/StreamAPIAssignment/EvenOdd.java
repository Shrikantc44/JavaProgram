// Q11: Given a list of integers, return a list of strings "even" or "odd" depending on whether the number is even or odd.

import java.util.*;
import java.util.stream.*;

public class EvenOdd {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        List<String> evenOrOdd = numbers.stream()
            .map(n -> n % 2 == 0 ? "even" : "odd")
            .collect(Collectors.toList());

        System.out.println(evenOrOdd); 
        // Output: [odd, even, odd, even, odd]
    }
}

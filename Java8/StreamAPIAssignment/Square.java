import java.util.*;
import java.util.stream.*;

public class Square {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4);

        int sumOfSquares = numbers.stream()
            .map(n -> n * n)            // square each number
            .reduce(0, Integer::sum);   // sum all squares

        System.out.println("Sum of Squares: " + sumOfSquares);  // Output: 30
    }
}

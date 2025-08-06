import java.util.*;
import java.util.stream.*;

public class MaximumNoInList {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(10, 20, 5, 80, 30);

        Optional<Integer> maxNumber = numbers.stream()
            .max(Integer::compareTo);

        maxNumber.ifPresent(System.out::println); 
        // Output: 80
    }
}

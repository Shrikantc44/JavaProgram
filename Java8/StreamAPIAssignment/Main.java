import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "Charlie", "David");

        Optional<String> firstNameStartingWithC = names.stream()
            .filter(name -> name.startsWith("C"))
            .findFirst();

        firstNameStartingWithC.ifPresent(System.out::println);  // Output: Charlie
    }
}

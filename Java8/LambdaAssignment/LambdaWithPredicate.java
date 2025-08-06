import java.util.function.Predicate;

public class LambdaWithPredicate {
    public static void main(String[] args) {
        // Predicate to check if a string is empty
        Predicate<String> isEmpty = s -> s.isEmpty();

        // Test the predicate with empty string
        System.out.println(isEmpty.test(""));    // true

        // Test the predicate with non-empty string
        System.out.println(isEmpty.test("Java")); // false
    }
}

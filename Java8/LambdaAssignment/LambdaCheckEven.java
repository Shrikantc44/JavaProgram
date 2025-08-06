import java.util.function.Predicate;

public class LambdaCheckEven {
    public static void main(String[] args) {
        // Predicate lambda jo check karta hai ki number even hai ya nahi
        Predicate<Integer> isEven = x -> x % 2 == 0;

        // Test karke print karna
        System.out.println(isEven.test(4));  // true
        System.out.println(isEven.test(5));  // false
    }
}

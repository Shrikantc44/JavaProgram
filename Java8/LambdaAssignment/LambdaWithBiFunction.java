import java.util.function.BiFunction;

public class LambdaWithBiFunction {
    public static void main(String[] args) {
        // BiFunction lambda expression jo do integers ko add karta hai
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

        // apply method se function call karna
        System.out.println(add.apply(2, 3));  // Output: 5
    }
}

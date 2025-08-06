import java.util.function.Consumer;

public class LambdaWithConsumer {
    public static void main(String[] args) {
        // Consumer lambda jo ek string print karta hai
        Consumer<String> print = s -> System.out.println(s);

        // accept method se lambda call karna
        print.accept("Hello, World!");  // Output: Hello, World!
    }
}

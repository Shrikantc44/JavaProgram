import java.util.function.Supplier;

public class LambdaWithSupplier {
    public static void main(String[] args) {
        // Supplier lambda jo "Java" string return karta hai
        Supplier<String> supplier = () -> "Java";

        // get() method se supplier se value lena
        System.out.println(supplier.get());  // Output: Java
    }
}

//Simple Runnable Example

import java.util.Arrays;
import java.util.List;

public class LambdaRunnableExample {
    public static void main(String[] args) {
        // 1. Simple Runnable Example
        Runnable r = () -> System.out.println("Hello, Lambda!");
        new Thread(r).start();

       
    }
}

/* Output:

Hello, Lambda!

*/
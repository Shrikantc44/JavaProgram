// Creating and starting a thread using lambda

public class ThreadLambdaExample {
    public static void main(String[] args) {
        // Creating and starting a thread using lambda
        new Thread(() -> System.out.println("Thread with Lambda!")).start();
    }
}

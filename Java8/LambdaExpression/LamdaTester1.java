class LamdaTester1
{
    final static String s = "Hello! ";

    public static void main(String args[]) {
        GreetingService greetService1 = message -> System.out.println(s + message);
        greetService1.sayMessage("Mahesh");
    }

    @FunctionalInterface
    interface GreetingService {
        void sayMessage(String message);
    }
}

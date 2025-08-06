import java.util.Scanner;

public class EvenOddCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Scanner object to take input

        

        System.out.print("Enter a number: ");
        int number = sc.nextInt();  // Taking integer input from user

        // Check if number is even or odd
        if (number % 2 == 0) {
            System.out.println(number + " is Even.");
        } else {
            System.out.println(number + " is Odd.");
        }

        sc.close();  // Close the scanner to avoid memory leak
    }
}

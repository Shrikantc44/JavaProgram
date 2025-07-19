// Two number are entered through the keyboard.Write a java program to find the value of one number raised to the power of another .(Do not use Java built-in-method).

import java.util.Scanner;

public class PowerCalculator 
{
    public static void main(String[] args)
	{
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the base number: ");
        int base = sc.nextInt();

        System.out.print("Enter the exponent: ");
        int exponent = sc.nextInt();

        int result = 1;

        // Loop to calculate base^exponent
        for (int i = 1; i <= exponent; i++) {
            result *= base;
        }

        System.out.println(base + " raised to the power " + exponent + " is: " + result);

        sc.close(); 
    }
}

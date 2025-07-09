import java.util.Scanner;

public class LeapYear
{
    public static void main(String[] args)
	{
        Scanner sc = new Scanner(System.in);  // Scanner object to take input

        System.out.print("Enter a year: ");
        int year = sc.nextInt();  // Taking integer input from user

        // Check if number is even or odd
        if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)))
		{

            System.out.println(year + " is Leap Year.");
        }
		else
		{
			
            System.out.println(year + " is not Leap Year.");
        }

        sc.close();  // Close the scanner to avoid memory leak
    }
}

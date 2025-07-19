//Write a program that prompts the user to input an integer and then outputs the number with the digit reversed.


import java.util.Scanner;
public class ReverseString
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the string:");
		String original=sc.nextLine();
		
		String reversed = "";
		
		for(int i = original.length() - 1; i>=0 ; i--)
		{
			reversed = reversed + original.charAt(i);
		}
		System.out.println("Reversed String:" + reversed);
	}
}
		

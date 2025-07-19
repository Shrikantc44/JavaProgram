//Write a program that prompts the user to input a positive integer.It should then print the multiplication table of that number.

import java.util.Scanner;
public class MultiplaicationNo
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number :");
		int num = sc.nextInt();
		
		System.out.println("Multiplication of "+ num + " is :" );
		
		for(int i=1;i<=10;i++)
		{
			System.out.println(num + " x " + i + "=" + (num * i));
		}
		
	}
}
//Write a program that prompts the user to input a positive integer.It should then output a message indicating whether the number is prime number.

import java.util.Scanner;
public class PrimeOrNot
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print(" Enter the number:");
		int num=sc.nextInt();
		
		int temp=0;
		for(int i=2 ;i<=num-1;i++)
		{
			if(num%i==0)
			{
				temp=temp+1;
			}
		}
		if(temp>0)
		{
			System.out.println(num + " is not a prime number");
		}
		else
		{
			System.out.println(num + " is a prime number");
		}
	}
}

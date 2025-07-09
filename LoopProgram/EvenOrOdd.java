import java.util.Scanner;
public class EvenOrOdd
{
	Scanner sc=new Scanner(System.in);
	System.out.print("Enter the number:");
	
	int number = sc.nextInt();
	
	if (number%2==0)
	{
		System.out.println(number + "is even");
	}
	else
	{
		System.out.println(number + "is odd");
	}
}
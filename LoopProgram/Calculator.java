import java.util.Scanner;
public class Calculator
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter first number:");
		int no1 = sc.nextInt();
		
		System.out.println("Enter second number:");
		int no2 = sc.nextInt();
		
		System.out.println("Select Symbol(+,-,*,/)");
		String sym = sc.next();
		
		int res;
		
		switch(sym)
		{
			case"+":
			res=no1+no2;
			System.out.println("Addition is :"+res);
			break;
			
			case"-":
			res=no1+no2;
			System.out.println("Substraction is :"+res);
			
			case"*":
			res=no1+no2;
			System.out.println("Multiplication is :"+res);
			
			case"/":
			res=no1+no2;
			System.out.println("Addition is :"+res);
			
			default:
			System.out.println("Invalid Symbol");
			break;
		}
	}
}
			
			
		

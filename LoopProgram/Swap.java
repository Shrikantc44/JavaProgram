import java.util.Scanner;

public class Swap
{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		
		//taking input from first number
		
		System.out.print("Enter first number(a):");
		int a = sc.nextInt();
		
		//taking input from second number
		
		System.out.print("Enter first number(b):");
		int b = sc.nextInt();
		
		int c;
		c=a;
		a=b;
		b=c;
		
		System.out.println("\n After Swapping :");
		System.out.println("a=" +a);
		System.out.println("b=" +b);
		
		sc.close();
	}
}
		
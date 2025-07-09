import java.util.Scanner;
public class FibonacciSerUsiRec
{
	static int a=0,b=1,c;
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the terms:");
		int n = sc.nextInt();
		
		System.out.print(a + " " + b);
		
		FibonacciSerUsiRec obj = new FibonacciSerUsiRec();
		obj.printFib(n-2);  // pehle 2 term print ho chuki hai
	}
	void printFib(int i)
	{
		if(i>=1)
		{
			c=a+b;
			System.out.print(" " + c);
			a=b;
			b=c;
			printFib(i-1); // recursive call
		}
	}
}
		
		
		
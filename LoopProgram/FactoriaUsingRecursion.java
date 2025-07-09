import java.util.Scanner;
public class FactoriaUsingRecursion
{
	static int fact=1;
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the number:");
		int no=sc.nextInt();
		
		FactoriaUsingRecursion ob=new FactoriaUsingRecursion();
		ob.calcFact(no);
		System.out.println("factorial of "+no+ "is" +fact);
	}
	void calcFact(int no)
	{
		if(no>=1)
		{
			fact=fact*no;
			calcFact(no-1);
		}
	}
}
		
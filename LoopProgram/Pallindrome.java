import java.util.Scanner;
public class Pallindrome
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number:");
		int n=sc.nextInt();
		int rem,rev=0;
		int temp=n;
		
		while(temp!=0)
		{
			rem=temp%10;
			rev= rev*10+rem;
			temp=temp/10;
		}
		if(n==rev)
		{
			System.out.println(n+ " is a pallindrome number");
		}
		else
		{
			System.out.println(n+ " is not  a pallindrome number");
		}
	}
}

class Rocket14

{
	public static void main(String... s)
	{
		int i,j,n,sp,x=1;
		
		System.out.println("Enter the size");
		n=new java.util.Scanner(System.in).nextInt();
		
		sp=n/2;
		if(n>=5 && n%2!=0)
		{
			for(i=0;i<n/2+1;i++)
			{
				for(j=0;j<sp-i;j++)
				{
					System.out.print(" ");
				}
				for(j=0;j<x;j++)
				{
					System.out.print("*");
				}
				x+=2;
				System.out.println();
			}
			sp=n-2;
			for(i=0;i<n/2+1;i++)
			{
				System.out.print("@");
				
				for(j=0;j<sp;j++)
				{
					System.out.print(" ");
				}
				System.out.print("@");
				System.out.println();
			}
			x=1;
			sp=1;
			for(i=0;i<n/2;i++)
			{
				System.out.print("@");
				for(j=0;j<n/2-sp;j++)
				{
					System.out.print(" ");
				}
				for(j=0;j<x;j++)
				{
					System.out.print("*");
				}
				for(j=0;j<n/2-sp;j++)
				{
					System.out.print(" ");
				}
				System.out.print("@");
				System.out.println();
				x+=2;
				sp++;
			}
			for(i=0;i<x;i++)
			{
				System.out.print("*");
			}
		}
		else
		{
			System.out.println("Invalid Input");
		}
	}
}
			
				
				
				
			
					
	
	
		
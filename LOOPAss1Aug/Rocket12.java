class Rocket12

{
	public static void main(String... s)
	{
		int i,j,n,sp;
		
		System.out.println("Enter the size");
		n=new java.util.Scanner(System.in).nextInt();
		
		sp=0;
		for(i=0;i<2*n;i++)
		{
			if(i<n)
			{
				System.out.print("@");
				for(j=0;j<sp;j++)
				{
					System.out.print(" ");
				}
				for( j=0;j<n;j++)
				{
					System.out.print("*");
				}
				sp=sp+n-1;
				if(i+1==n)
				{
					System.out.print("@");
					sp++;
				}
			}
			else if(i==n)
			{
				System.out.print("@");
				for(j=0;j<sp;j++)
				{
					System.out.print(" ");
				}
				System.out.print("@");
			}
			else
			{
				for(j=0;j<sp+1;j++)
				{
					System.out.print(" ");
				}
				System.out.print("@");
			}
			System.out.println(" ");
		}
	}
}
				
				
		
		
		
		
		
		
		
		
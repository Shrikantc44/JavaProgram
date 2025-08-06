class Rocket13

{
	public static void main(String... s)
	{
		int i,j,n,sp=1;
		
		System.out.println("Enter the size");
		n=new java.util.Scanner(System.in).nextInt();
	
	
		
			if(n>=2)
			{
				for(i=0;i<n*3;i++)
				{
					if(i<n)
					{
						System.out.print("@");
					}
					else if(i<n*2)
					{
						if(i==n)
						{
							System.out.print("@");
						}
						if(i!=n)
						{
							for(j=0;j<sp;j++)
							{
								System.out.print(" ");
							}
						}
						for(j=0;j<n;j++)
						{
							System.out.print("*");
						}
						sp=sp+n-1;
						if((i+1)==(2*n))
						{
							sp++;
							System.out.print("@");
						}
					}
					else{
						for(j=0;j<sp;j++)
						{
							System.out.print(" ");
						}
						System.out.print("@");
					}
					System.out.println(" ");
				}
			}
			else
			{
				System.out.println("invalid output");
			}
	}
}
							
			
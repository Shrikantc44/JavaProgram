public class Main1
{
	public static void main(String[] args)
	{
		int i,j,n=4;
		
		for( i=1;i<=n;i++)
		{
			for(j=1;j<=n-i;j++)
			{
				System.out.print(" ");
			}
			for(j=1;j<=i;j++)
			{
				System.out.print(i + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}


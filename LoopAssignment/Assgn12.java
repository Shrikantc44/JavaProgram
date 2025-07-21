/* 5 4 3 2 1
   6 5 4 3 2 
   7 6 5 4 3 
   8 7 6 5 4 
   9 8 7 6 5*/


class Assgn12
{
	public static void main(String[] args)
	{
		for(int i=0;i<=4;i++)
		{
			for(int j=5+i;j>=1+i;j--)
			{
				System.out.print(j + " ");
			}
			System.out.println();
		}
		
	}
}
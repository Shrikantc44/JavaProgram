/* 1 1 1 1 1
   0 0 0 0 0
   1 1 1 1 1
   0 0 0 0 0
   1 1 1 1 1
    */


class Assgn5
{
	public static void main(String[] args)
	{
		for(int i=1;i<=5;i++)
		{
			for(int j=1;j<=5;j++)
			{
				 System.out.print((i % 2 == 0 ? 0 : 1) + " ");
			}
			System.out.println();
		}
		
	}
}
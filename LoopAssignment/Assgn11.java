/* A B C D E 
   B C D E F
   C D E F G 
   D E F G H
   E F G H I
    */


class Assgn11
{
	public static void main(String[] args)
	{
		for(int i=0;i<=4;i++)
		{
			for(int j=0;j<=4;j++)
			{
				 char ch = (char) ('A' + i + j); 
                 System.out.print(ch + " ");
			}
			System.out.println();
		}
		
	}
}
interface My
{
	int x=10;
}

interface My1
{
	int x=10;
}

/*----------------------------------*/
class Child implements My,My1
{
	public static void main(String... s)
	{
		System.out.println(My.x);
		System.out.println(My1.x);
		//System.out.println(x);
		//My.x=1000;
	}
}
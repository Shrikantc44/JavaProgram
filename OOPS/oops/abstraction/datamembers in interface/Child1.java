class My
{
	static int x=10;
}

/*---------------------------*/
class Child1 extends My
{
	int x=100;
	
	public static void main(String... s)
	{
		System.out.println(My.x);
		//System.out.println(My1.x);
		//System.out.println(x);
		My.x=1000;
	}
}
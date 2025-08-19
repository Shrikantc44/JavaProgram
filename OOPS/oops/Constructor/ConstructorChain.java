class ConstructorChain
{
	ConstructorChain()
	{
		this(10);
		System.out.println("default");
	}
	
	ConstructorChain(int x)
	{
		this(10,20);
		System.out.println(x);
	}
	
	ConstructorChain(int x,int y)
	{
		System.out.println(x+y);
	}
	
	public static void main(String... s)
	{
		new ConstructorChain();
		//new ConstructorChain(10);
		//new ConstructorChain(10,30);
	}
}
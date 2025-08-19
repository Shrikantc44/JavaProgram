interface My
{
	default void show()
	{
		System.out.println("my");
	}
}

interface My1
{
	default void show()
	{
		System.out.println("my1");
	}
}

class MyRegister
{
	void register(My b)
	{
		b.show();
	}
}

/*---------------------------------------*/
class Child implements My,My1
{
	public void show()
	{
		System.out.println("Child");
	}
	
	public static void main(String... s)
	{
		Child c=new Child();
		MyRegister mr=new MyRegister();
		mr.register(c);
		
		My m=new Child();
		m.show();
		
		My1 m1=new Child();
		m1.show();
	}
} 

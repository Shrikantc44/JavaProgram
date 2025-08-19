class Base
{
	int x=20;
	void show()
	{
		System.out.println("Base");
	}
}

class Child extends Base
{
	int x=30;
	void show()
	{
		System.out.println("Child");
	}
	
	void display()
	{
		System.out.println("Display");
	}
	
	public static void main(String... s)
	{
		Base b=new Child();  //upcasting
		b.show();
		
		Child c=(Child)b;   //downcasting
		c.display();
		System.out.println(b.x);
		System.out.println(c.x);
	}
}
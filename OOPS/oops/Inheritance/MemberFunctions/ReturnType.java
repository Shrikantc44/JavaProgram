class Base
{
	A show()
	{
		System.out.println("Base");
		return new A();
	}
}

class Child extends Base
{
	B show()
	{
		System.out.println("Child");
		return new B();
	}
	
	public static void main(String... s)
	{
		Child c=new Child();
		c.show();
	}
}

class A
{
	
}

class B extends A
{
	
}
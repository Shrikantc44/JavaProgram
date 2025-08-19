class Base1
{
	int x=50;
}

class child extends Base1
{
	int y=20;
	void show()
	{
		System.out.println(y);
		System.out.println(super.x);
	}
	public static void main(String... s)
	{
		child c1=new child();
		c1.show();
	}
}
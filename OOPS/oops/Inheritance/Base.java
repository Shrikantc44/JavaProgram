class Base
{
	int x;
	int y;
	void show()
	{
		System.out.println(x);
		System.out.println(y);
	}
}

class child extends Base
{
	void get(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	public static void main(String... s)
	{
		child c1=new child();
		c1.get(10,20);
		c1.show();
	}
}
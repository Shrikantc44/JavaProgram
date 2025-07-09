class Test1
{
	void show(int a,String b)
	{
		System.out.println("1");
	}
	void show(String b,int a)
	{
		System.out.println("2");
	}
	
	public static void main(String[] args)
	{
		Test1 t=new Test1();
		t.show("abc",10);
	}
}
		
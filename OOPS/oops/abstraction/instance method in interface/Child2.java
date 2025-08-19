interface My
{
	static void show()
	{
		System.out.println("my");
	}
}


/*---------------------------------*/
class Child   //implements My
{
	public static void main(String... s)
	{
		My.show();
	}
}
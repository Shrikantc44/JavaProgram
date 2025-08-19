class ViaMethod
{
	int x=this.getX();
	
	
	int getX()
	{
		System.out.println(x);
		return 20;
	}
	ViaMethod()
	{
		//x=getX();
		System.out.println(x);
	}
	
	public static void main(String... s)
	{
		new ViaMethod();
	}
}
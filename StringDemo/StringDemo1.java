public class StringDemo1
{
	public static void main(String[] args)
	{
		String s1="Shrikant";  // 1object - String Constant Pool ke andar
		String s2=new String("abc");  //2 objects Heap Area and SCP
		System.out.println(s2.length());
		System.out.println(s1.length());
	}
}
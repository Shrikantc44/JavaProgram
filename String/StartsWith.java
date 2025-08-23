
public class StartsWith
{
	public static void main(String args[])
	{
		String Str = new String("Welcome to Tutorials of String");
		
		System.out.print("Return Value :");
		System.out.println(Str.startsWith("Welcome")); //true
		
		System.out.print("Return Value :");
		System.out.println(Str.startsWith("Tutorials"));  //false
		
		System.out.print("Return Value :");
		System.out.println(Str.startsWith("Tutorials",11));  //true
	}
}
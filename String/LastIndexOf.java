/*
int lastIndexOf(int ch)
public int lastIndexOf(int ch,int fromIndex)
public int lastIndexOf(String str)
public int lastIndexOf(String str,int fromIndex)
*/


public class LastIndexOf
{
	public static void main(String args[])
	{
		String Str = new String("Welcome to Tutorials");
		String SubStr1 = new String("Tutorials");
		String SubStr2 = new String("Sutorials");
		
		System.out.print("Found Last Index : ");
		System.out.println(Str.lastIndexOf('o')); //14
		System.out.print("Found Last Index : ");
		System.out.println(Str.lastIndexOf('o',10)); //9
		System.out.print("Found Last Index : ");
		System.out.println(Str.lastIndexOf(SubStr1));  //11
		System.out.print("Found Last Index : ");
		System.out.println(Str.lastIndexOf(SubStr1,15));  //11
		System.out.print("Found Last Index : ");
		System.out.println(Str.lastIndexOf(SubStr2));  //-1
	}
}
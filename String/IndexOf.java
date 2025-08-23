/*
public int indexOf(int ch)
public int indexOf(int ch,int fromindex)
int indexOf(String str)
int indexOf(String str,int fromindex)
*/
package p1;
public class IndexOf
{
	public static void main(String args[])
	{
		String Str = new String("Welcome to Tutorials");
		String SubStr1 = new String("Tutorials");
		String SubStr2 = new String("Sutorials");
		
		System.out.print("Found Index : ");
		System.out.println(Str.indexOf('o'));  //4
		System.out.print("Found Index : ");
		System.out.println(Str.indexOf('o',5));  //9
		System.out.print("Found Index : ");
		System.out.println(Str.indexOf(SubStr1));  //11
		System.out.print("Found Index : ");
		System.out.println(Str.indexOf(SubStr1,15));  //-1
		System.out.print("Found Index : ");
		System.out.println(Str.indexOf(SubStr2));  //-1
	}
}
import java.util.*;
class FinalArray1
{
	public static void main(String [] args)
	{
		final int z[]={10,20,30};
		System.out.println(z[0]);
		z[0]=20000;
		System.out.println(z[0]);
		int y[]={100,202,300};
		System.out.print(y.lenth);
		z=y;
		System.out.print(z[0]);
	}

}


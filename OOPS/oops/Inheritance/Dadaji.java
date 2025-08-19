class Dadaji    //Parent class
{
	int x=80;
}

class Base2 extends Dadaji   //child class inheriting from Dadaji
{
	int x=50;                //this x will shadow Dadaji's x.
}
class child extends Base2    //child class inheriting from Base2
{
	int y=20;
	void show()
	{
		System.out.println(y);   //Print 20(child class variable)
		System.out.println(super.x);  // Print 50(Base2's x)
		
		//Correct way to access Dadaji's x
		System.out.println(((Dadaji)this).x);    //Print 80
		System.out.println(((Base2)this).x);     //Print 50
	}
	public static void main(String... s)
	{
		child c1=new child();
		c1.show();
		
		//Accessing Dadaji's x directly
		System.out.println(((Dadaji)c1).x);    //Print 80
	}
}
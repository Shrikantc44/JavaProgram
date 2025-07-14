class Thread1 extends Thread
{
	Thread1(String s)
	{
		super(s);
	}
	public void run()
	{
		for(int i=1;i<=5;i++)
		{
			//System.out.println(Thread.currentThread().getName());
			System.out.println("har har modi ghar ghar modi mhi ");
			
			try{
				Thread.sleep(1000);
			}
			catch(Exception e) { }
		}
	System.out.println(getName() + " dead");
	}
}

class OneClassMObject
{
	public static void main(String s[])
	{
		Thread t1=new Thread1("thread1");
		Thread t2=new Thread2("thread2");
		Thread t3=new Thread3("thread3");
		
		t1.start();
		t2.start();
		t3.start();
		
		for(int i=1;i<=5;i++)
		{
			System.out.println(Thread.currentThread().getName());
			System.out.println("tata");
			try{
				Thread.sleep(1000);
			}
			catch(Exception e) { }
		}
		System.out.println(Thread.currentThread().getName()+ "dead");
	}
}
			
		
	
				

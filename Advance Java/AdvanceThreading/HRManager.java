/*CountDownLatch

This is a more advanced type of synchronization that can be done with concurrent package.
Consider the example where a Organization needs to recruit 3 Java Developers. For this HR
Manager has asked 3 Tech Leads to take interview.
The HR Manager wants to distribute the Offer letter only after all the 3 Java Developers
have been recruited. In Threading terminology the HR Manger should wait till 3 Java
Developers have been recruited.*/

import java.util.concurrent.CountDownLatch;

public class HRManager
{
    public static void main(String args[])
	{
        CountDownLatch countDownLatch = new CountDownLatch(3);

        TechLead techLead1 = new TechLead(countDownLatch,"Amit");
        TechLead techLead2 = new TechLead(countDownLatch,"Sachin");
        TechLead techLead3 = new TechLead(countDownLatch,"Dinesh");

        techLead1.start();
        techLead2.start();
        techLead3.start();

        try
		{
            System.out.println("HR Manager waiting for recruitment to complete...");
            countDownLatch.await();
            System.out.println("Distribute Offer Letter");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*
        try {
            techLead1.join();
            techLead2.join();
            techLead3.join();
        } catch(Exception e) { }
        */
    }
}

class TechLead extends Thread
{

    CountDownLatch countDownLatch;

    public TechLead(CountDownLatch countDownLatch, String name)
	{
        super(name);
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run()
	{
        try
		{
            Thread.sleep(2000);
        } catch (InterruptedException e)
		{
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " : recruited");
        countDownLatch.countDown();

        try
		{
            Thread.sleep(3000);
        } catch (InterruptedException e) 
		{
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " : Dead");
    }
}

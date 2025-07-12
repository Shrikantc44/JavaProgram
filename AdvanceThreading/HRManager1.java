/*
CyclicBarrier Example -> CyclicBarrier: A synchronization aid that allows a set of threads
to all wait for each other to reach a common barrier point. CyclicBarriers are useful in
programs involving a fixed sized party of threads that must occasionally wait for each
other. The barrier is called cyclic because it can be re-used after the waiting threads are
released.

CountDownLatch we saw how a master or main thread waits till the worker threads finish
their work. CyclicBarrier class also is a flavour of CountDownLatch with slight change.
Let's continue the same example as CountDownLatch.
A organization has to recruit 3 Java Developers. And so the HR Manager asks 3 Tech Leads to
interview the candidates. In CountDownLatch example the HR Manager wanted to distribute the
offer letter to all the 3 candidates that is the reason we made him to wait. Here the HR
manager wants the Tech Leads to give the offer letter once they have selected the candidate.
But the Tech Leads decide among themselves that they will give the offer letter to their
respective candidate only when all interviews are done.
*/

import java.util.concurrent.CyclicBarrier;

public class HRManager1
 {

    public static void main(String args[])
	{
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        TechLead techLead1 = new TechLead(cyclicBarrier, "lalu");
        TechLead techLead2 = new TechLead(cyclicBarrier, "rabari");
        TechLead techLead3 = new TechLead(cyclicBarrier, "meesa");

        techLead1.start();
        techLead2.start();
        techLead3.start();

        System.out.println("No work for HR manager");
    }
}

class TechLead extends Thread 
{

    CyclicBarrier cyclicBarrier;

    public TechLead(CyclicBarrier cyclicBarrier, String name)
	{
        super(name);
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() 
	{
        try
		{
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " recruited developer");
            System.out.println(Thread.currentThread().getName() + " waiting for others to complete....");
            cyclicBarrier.await();
            System.out.println("All finished recruiting, " + Thread.currentThread().getName()
                               + " gives offer letter to candidate");
        } catch (Exception e) 
		{
            e.printStackTrace();
        }
    }
}



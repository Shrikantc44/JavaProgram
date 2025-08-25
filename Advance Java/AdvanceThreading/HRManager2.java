/* 
 Semaphore Example -> 
Here Semaphore class in concurrent package is used as a pool that can be acquired and 
released. very much like lock but with a difference. When a thread acquires a lock not other 
thread can enter the synchronised block.
1. Here in Semaphore we can define the poolsize and threads can acquire locks till there is 
resource left in the pool. Lets see how it works ->

The organization needs to recruit 4 Java developers. HR Manager asks 4 Tech Leads to conduct 
test and recruit the candidates. The problem here is that the Tech Leads have only 2 test 
paper and the photo copy machine is down. That means at a time only two candidates can give 
the test and other 2 have to wait.
Lets simulate this by Semaphore example.
*/

import java.util.concurrent.Semaphore;

public class HRManager2 
{

    public static void main(String args[])
	{
        Semaphore questionPaperPool = new Semaphore(2);

        TechLead techLead1 = new TechLead(questionPaperPool, "Amit TL");
        TechLead techLead2 = new TechLead(questionPaperPool, "Sachin TL");
        TechLead techLead3 = new TechLead(questionPaperPool, "Dinesh TL");
        TechLead techLead4 = new TechLead(questionPaperPool, "Gaurav TL");

        techLead1.start();
        techLead2.start();
        techLead3.start();
        techLead4.start();

        System.out.println("No work for HR manager");
    }
}

class TechLead extends Thread 
{

    Semaphore questionPaperPool;

    public TechLead(Semaphore questionPaperPool, String name) 
	{
        super(name);
        this.questionPaperPool = questionPaperPool;
    }

    @Override
    public void run()
	{

        try
		{
            System.out.println(Thread.currentThread().getName() + " Waiting for test question paper");

            // Acquiring one question paper
			
            questionPaperPool.acquire();
            System.out.println(Thread.currentThread().getName() + " acquired test paper");
            System.out.println(Thread.currentThread().getName() + " Conducting test");

            Thread.sleep(3000);

            System.out.println(Thread.currentThread().getName() + " Test done giving back the paper");

            // Giving back the acquired question paper
            questionPaperPool.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

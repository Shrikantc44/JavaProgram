/*ReentrantLock Example->
 A company wants to hire 3 java developer. For this he select 3 Tech Leads to conduct test and select candidates.
 One Tech Lead will select one candidate. But their is only one question paper, and xerox machine is not working.
 So now at a time only one Tech Lead can conduct the test.
 When one Tech Lead is having question paper it conducts the test and other Tech Leads have to wait for question paper.
 When one Tech Lead has taken the test then he gives question paper back. Now other Tech Leads can take the question paper and conduct test.  */

import java.util.concurrent.locks.ReentrantLock;

class TechLead1 extends Thread 
   {
    ReentrantLock question_paper;
    String name;

    TechLead1(ReentrantLock question_paper, String name)
	{
        super(name);
        this.question_paper = question_paper;
    }

    public void run()
	{
        System.out.println(getName() + " is waiting for question paper ");
        question_paper.lock();
        System.out.println(getName() + " takes the question paper ");
        System.out.println(getName() + " starts taking test ");
        try 
		{
            Thread.sleep(1000);
        } catch (InterruptedException e) 
		{
            System.out.println(getName() + " is interrupted ");
        }
        System.out.println(getName() + " has taken the test");
        System.out.println(getName() + " returns the question paper");
        question_paper.unlock();
    }
}

class TechLead2 extends Thread
 {
    ReentrantLock question_paper;
    String name;

    TechLead2(ReentrantLock question_paper, String name)
	{
        super(name);
        this.question_paper = question_paper;
    }

    public void run() 
	{
        System.out.println(getName() + " is waiting for question paper ");
        question_paper.lock();
        System.out.println(getName() + " takes the question paper ");
        System.out.println(getName() + " starts taking test ");
        try
		{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(getName() + " is interrupted ");
        }
        System.out.println(getName() + " has taken the test");
        System.out.println(getName() + " returns the question paper");
        question_paper.unlock();
    }
}

class TechLead3 extends Thread
 {
    ReentrantLock question_paper;
    String name;

    TechLead3(ReentrantLock question_paper, String name)
	{
        super(name);
        this.question_paper = question_paper;
    }

    public void run()
	{
        System.out.println(getName() + " is waiting for question paper ");
        question_paper.lock();
        System.out.println(getName() + " takes the question paper ");
        System.out.println(getName() + " starts taking test ");
        try 
		{
            Thread.sleep(1000);
        } catch (InterruptedException e)
		{
            System.out.println(getName() + " is interrupted ");
        }
        System.out.println(getName() + " has taken the test");
        System.out.println(getName() + " returns the question paper");
        question_paper.unlock();
    }
}

public class Main_Hr
 {
    public static void main(String args[]) 
	{
        ReentrantLock question_paper = new ReentrantLock();
        new TechLead1(question_paper, "lalu").start();
        new TechLead2(question_paper, "mulayam").start();
        new TechLead3(question_paper, "nitish").start();
        System.out.println("Hr completes his work");
    }
}

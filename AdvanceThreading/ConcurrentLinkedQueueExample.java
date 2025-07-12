/*ConcurrentLinkedQueue Example->

ConcurrentLinkedQueue is an unbounded thread-safe queue based on linked nodes. This queue
orders elements as a FIFO (first-in-first-out). The head of the queue is that element that 
has been on the queue the longest time. The tail of the queue is that element that has been 
on the queue the shortest time. New elements are inserted at the tail of the queue, and the 
queue retrieval operations obtain elements at the head of the queue.

A ConcurrentLinkedQueue is a good choice when many threads share access to a common
collection. Like most other concurrent collection implementations, this class does not 
permit the use of null elements.

This java example spawns off two threads. One puts strings on the queue and the other takes 
strings off the queue.
*/

import java.util.concurrent.*;

public class ConcurrentLinkedQueueExample
 {
    public static void main(String[] args)
	{
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));
        producer.start();
        consumer.start();
    }
}

// the producer puts strings on the queue
class Producer implements Runnable
 {
    ConcurrentLinkedQueue<String> queue;

    Producer(ConcurrentLinkedQueue<String> queue)
	{
        this.queue = queue;
    }

    public void run()
	{
        System.out.println("Producer Started");
        try {
            for (int i = 1; i < 10; i++)
				{
                queue.add("String" + i);
                System.out.println("Added: String" + i);
                Thread.currentThread().sleep(1000);
            }
        } catch (Exception ex) 
		{
            ex.printStackTrace();
        }
    }
}

// the consumer removes strings from the queue
class Consumer implements Runnable
 {
    ConcurrentLinkedQueue<String> queue;

    Consumer(ConcurrentLinkedQueue<String> queue)
	{
        this.queue = queue;
    }

    public void run() 
	{
        String str;
        System.out.println("Consumer Started");
        for (int x = 0; x < 10; x++) {
            while ((str = queue.poll()) != null) 
			{
                System.out.println("Removed: " + str);
            }
            try {
                Thread.currentThread().sleep(1000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

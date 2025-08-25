/*The example uses the ArrayBlockingQueue implementation of the BlockingQueue interface.

The BlockingQueueExample class starts a Producer and a Consumer in separate threads.
The Producer inserts strings into a shared BlockingQueue while the Consumer takes them out.
*/

import java.util.concurrent.*;

public class BlockingQueueExample
 {

    public static void main(String[] args) throws Exception 
	{

        BlockingQueue queue = new ArrayBlockingQueue(5);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        System.out.println("Starting Producer.");
        new Thread(producer).start();

        System.out.println("Starting Consumer.");
        new Thread(consumer).start();
    }
}

class Producer implements Runnable
 {

    protected BlockingQueue queue = null;

    public Producer(BlockingQueue queue)
	{
        this.queue = queue;
    }

    public void run() {
        try {
            queue.put("String ONE");
            Thread.sleep(3000);
            queue.put("String TWO");
            Thread.sleep(3000);
            queue.put("String THREE");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Consumer class. It takes out the objects from the queue,
// and prints them to System.out.
class Consumer implements Runnable {

    protected BlockingQueue queue = null;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

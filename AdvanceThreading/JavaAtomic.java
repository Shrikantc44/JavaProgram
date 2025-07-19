/* 
To solve this issue, we will have to make sure that increment operation on count is atomic, 
we can do that using Synchronization but Java 5 java.util.concurrent.atomic provides wrapper 
classes for int and long that can be used to achieve this atomically without usage of Synchronization.

Here is the updated program that will always output count value as 8 because AtomicInteger 
incrementAndGet() atomically increments the current value by one.
*/

import java.util.concurrent.atomic.AtomicInteger;  // AtomicInteger class import ki ja rahi hai

public class JavaAtomic {

    public static void main(String[] args) throws InterruptedException {

        // Shared instance of ProcessingThread
        ProcessingThread pt = new ProcessingThread();

        // Thread t1 banaya gaya aur start kiya gaya
        Thread t1 = new Thread(pt, "t1");
        t1.start();

        // Thread t2 banaya gaya aur start kiya gaya
        Thread t2 = new Thread(pt, "t2");
        t2.start();

        // t1 aur t2 dono threads ke complete hone ka wait kiya gaya
        t1.join();
        t2.join();

        // Final count value print ki ja rahi hai
        System.out.println("Processing count=" + pt.getCount());
    }
}

// Runnable interface ko implement karne wali class
class ProcessingThread implements Runnable
 {

    // AtomicInteger ka use kiya gaya hai jisse increment operation thread-safe ho jaye
    private AtomicInteger count = new AtomicInteger();

    // run() method ko thread ke through execute kiya jata hai
    public void run() {
        // Har thread 4 baar loop chalayega, total 8 increments (2 threads × 4 = 8)
        for (int i = 1; i < 5; i++) {
            System.out.println("hello");  // Console par "hello" print karega
            processSomething(i);          // Dummy processing method call karega
            count.incrementAndGet();      // Count ko atomically 1 se increment karega
        }
    }

    // count ki current value ko return karta hai
    public int getCount() {
        return this.count.get();
    }

    // Dummy processing method — sirf 1 second ke liye thread ko sleep kar deta hai
    private void processSomething(int i) {
        try {
            Thread.sleep(1000);  // 1000 milliseconds = 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



/* 
Main Thread
   |
   |---> t1 (ProcessingThread)
   |        |
   |        |-- Loop i = 1 to 4
   |        |     |
   |        |     |-- print("hello")
   |        |     |-- processSomething(i)  // sleeps for 1 sec
   |        |     |-- count.incrementAndGet()
   |
   |---> t2 (ProcessingThread)
            |
            |-- Loop i = 1 to 4
                  |
                  |-- print("hello")
                  |-- processSomething(i)  // sleeps for 1 sec
                  |-- count.incrementAndGet()

Both Threads Share:
   ---> AtomicInteger count
             |
             |-- incrementAndGet() is atomic
             |-- Final value = 8 (4 from t1 + 4 from t2)

*/
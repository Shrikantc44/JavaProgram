public class ThreadLocalExample
{
    public static void main(String[] args)
	{
        MyRunnable sharedRunnableInstance = new MyRunnable();

        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);
        Thread thread3 = new Thread(sharedRunnableInstance);
        Thread thread4 = new Thread(sharedRunnableInstance);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}

class MyRunnable implements Runnable
 {
    private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

    public void run()
	{
        int z = (int)(Math.random() * 1000);
        System.out.println(Thread.currentThread().getName() + " " + z);
        threadLocal.set(z);
        // threadLocal.set(z++);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e)
		{
        }

        System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
    }
}

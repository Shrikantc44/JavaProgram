/*
Sometimes we wish that a thread could return some value that we can use. Java 5 introduced 
java.util.concurrent.Callable interface in concurrency package that is similar to Runnable 
interface but it can return any Object and able to throw Exception.

Callable interface use Generic to define the return type of Object. Executors class provide 
useful methods to execute Callable in a thread pool. Since callable tasks run in parallel, 
we have to wait for the returned Object. Callable tasks return java.util.concurrent.Future 
object. Using Future we can find out the status of the Callable task and get the returned 
Object. It provides get() method that can wait for the Callable to finish and then return 
the result.

Future provides cancel() method to cancel the associated Callable task. There is an 
overloaded version of get() method where we can specify the time to wait for the result, 
it's useful to avoid current thread getting blocked for longer time. There are isDone() and 
isCancelled() methods to find out the current status of associated Callable task.
Here is a simple example of Callable task that returns the name of thread executing the task 
after one second. We are using Executor framework to execute 10 tasks in parallel and use 
Future to get the result of the submitted tasks.

Once we execute the above program, you will notice the delay in output because Future get() 
method waits for the callable task to complete.

When you submit a Callable object to an Executor, the framework returns an object of type 
java.util.concurrent.Future. This Future object is used to check the results of a Callable.

Use the get() method to retrieve the result of the future.
*/

import java.util.concurrent.*;
import java.util.*;

class MyCallable1 implements Callable<String> 
{
    public String call() throws Exception
	{
        System.out.println(Thread.currentThread().getName() + " starts");
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " ends");
        // return name of thread.
        return (Thread.currentThread().getName());
    }
}

class MyCallableTest
 {
    public static void main(String args[])
	{
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // collection to store results
        // ArrayList<Future<String>> list = new ArrayList<Future<String>>();

        Future[] list = new Future[10];
        for (int i = 0; i < 10; i++)
			{
            // Future<String> future = executor.submit(new MyCallable1());
            // list.add(future);
            list[i] = executor.submit(new MyCallable1());
        }

        // print results
        for (Future<String> future : list)
			{
            try
			{
                System.out.println(future.get());
            } catch (Exception e) 
			{
            }
        }

        executor.shutdown();
    }
}

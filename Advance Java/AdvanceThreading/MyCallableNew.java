import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyCallableNew implements Callable<String>
 {

    @Override
    public String call() throws Exception
	{
        System.out.println("Thread started " + new Date());
        Thread.sleep(6000);
        return Thread.currentThread().getName();
    }

    public static void main(String args[])
	{

        ExecutorService executor = Executors.newFixedThreadPool(1);

        Callable<String> callable = new MyCallableNew();

        Future<String> future = executor.submit(callable);

        System.out.println("after");
        try
		{
            System.out.println(future.get() + "::" + new Date());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // shut down the executor service now
        executor.shutdown();
    }
}

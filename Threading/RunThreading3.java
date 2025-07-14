class Thread1 extends Thread {
    Thread1(String s) {
        super(s);
        // Optional: You can also start the thread from constructor
        // start();
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName());
            //System.out.println("chay from Thread1");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println(getName() + " dead");
    }
}

class Thread2 extends Thread {
    Thread2(String s) {
        super(s);
    }

    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName());
            //System.out.println("hay from Thread2");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println(getName() + " dead");
    }
}

class Thread3 extends Thread {
    Thread3(String s) {
        super(s);
    }

    public void run() {
        for (int i = 1; i <= 15; i++) {
            System.out.println(Thread.currentThread().getName());
           // System.out.println("bye from Thread3");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println(getName() + " dead");
    }
}

public class RunThreading3 {
    public static void main(String s[]) {
        Thread t1 = new Thread1("thread1");
        Thread t2 = new Thread2("thread2");
        Thread t3 = new Thread3("thread3");

        t1.start();
        t2.start();
        t3.start();

        for (int i = 1; i <= 20; i++) {
            System.out.println(Thread.currentThread().getName());
            //System.out.println("tata from main thread");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println(Thread.currentThread().getName() + " dead");
    }
}

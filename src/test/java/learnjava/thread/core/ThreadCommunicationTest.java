package learnjava.thread.core;

import org.junit.jupiter.api.Test;

public class ThreadCommunicationTest {

    private String message = null;

    @Test
    void testManual() throws InterruptedException {
        var thread1 = new Thread(() -> {
            while (message == null) {
                // do something
            }
            System.out.println(message);
        });

        var thread2 = new Thread(() -> message = "Arbi Dwi Wijaya");

        thread2.start();
        thread1.start();

        thread2.join();
        thread1.join();
    }

    @Test
    void testWaitNotify() throws InterruptedException {
        final Object lock = new Object();

        var thread1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        var thread2 = new Thread(() -> {
            synchronized (lock) {
                message = "Arbi Dwi Wijaya";
                lock.notify();
            }
        });

        thread2.start();
        thread1.start();

        thread2.join();
        thread1.join();
    }

    @Test
    void testNotifyAll() throws InterruptedException {
        final Object lock = new Object();

        var thread1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        var thread3 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        var thread2 = new Thread(() -> {
            synchronized (lock) {
                message = "Arbi Dwi Wijaya";
                lock.notifyAll();
            }
        });

        thread1.start();
        thread3.start();

        thread2.start();

        thread1.join();
        thread3.join();

        thread2.join();
    }
}

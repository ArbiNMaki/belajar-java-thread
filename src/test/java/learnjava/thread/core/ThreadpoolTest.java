package learnjava.thread.core;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadpoolTest {

    @Test
    void testCreate() throws InterruptedException {
        var minThread = 10;
        var maxThread = 100;
        var alive = 1;
        var time = TimeUnit.MINUTES;

        var queue = new ArrayBlockingQueue<Runnable>(100);
        var executor = new ThreadPoolExecutor(minThread, maxThread, alive, time, queue);

        executor.execute(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("Hello from Threadpool : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread.sleep(6000);
    }

    @Test
    void testShutdown() throws InterruptedException {
        var minThread = 10;
        var maxThread = 100;
        var alive = 1;
        var time = TimeUnit.MINUTES;

        var queue = new ArrayBlockingQueue<Runnable>(1000);
        var executor = new ThreadPoolExecutor(minThread, maxThread, alive, time, queue);

        for (int i = 0; i < 1000; i++) {
            final var task = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("Task " + task + " from Threadpool : " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    public static class LogRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("Task : " + r + " is rejected.");
        }
    }

    @Test
    void testRejected() throws InterruptedException {
        var minThread = 10;
        var maxThread = 100;
        var alive = 1;
        var time = TimeUnit.MINUTES;
        var queue = new ArrayBlockingQueue<Runnable>(10);
        var rejected = new LogRejectedExecutionHandler();

        var executor = new ThreadPoolExecutor(minThread, maxThread, alive, time, queue, rejected);

        for (int i = 0; i < 1000; i++) {
            final var task = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("Task " + task + " from Threadpool : " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}

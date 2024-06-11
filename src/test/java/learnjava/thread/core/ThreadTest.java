package learnjava.thread.core;

import org.junit.jupiter.api.Test;

public class ThreadTest {

    @Test
    void testMainThread() {
        var name = Thread.currentThread().getName();
        System.out.println(name);
    }

    @Test
    void testCreateThread() {
        Runnable runnable = () -> System.out.println("Hello from Thread : " + Thread.currentThread().getName());

        var thread = new Thread(runnable);
        thread.start();
    }

    @Test
    void testThreadSleep() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                Thread.sleep(2_000L);
                System.out.println("Hello from Thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        var thread = new Thread(runnable);
        thread.start();
        Thread.sleep(3_000L);
    }

    @Test
    void testThreadJoin() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                Thread.sleep(2_000L);
                System.out.println("Hello from Thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        var thread = new Thread(runnable);
        thread.start();
        System.out.println("Menunggu selesai.");
        thread.join();
        System.out.println("Program selesai.");
    }

    @Test
    void testInterruptThreadButWrong() throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable : " + i);
                try {
                    Thread.sleep(1_000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        var thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5_000L);
        thread.interrupt();
        System.out.println("Menunggu selesai.");
        thread.join();
        System.out.println("Program selesai.");
    }

    @Test
    void testInterruptThreadCorrect() throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable : " + i);
                try {
                    Thread.sleep(1_000L);
                } catch (InterruptedException e) {
                    return;
                }
            }
        };

        var thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5_000L);
        thread.interrupt();
        System.out.println("Menunggu selesai.");
        thread.join();
        System.out.println("Program selesai.");
    }

    @Test
    void testInterruptThreadManual() throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                if (Thread.interrupted()) {
                    return;
                }
                System.out.println("Runnable : " + i);
                try {
                    Thread.sleep(1_000L);
                } catch (InterruptedException e) {
                    return;
                }
            }
        };

        var thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5_000L);
        thread.interrupt();
        System.out.println("Menunggu selesai.");
        thread.join();
        System.out.println("Program selesai.");
    }

    @Test
    void testThreadName() {
        var thread = new Thread(() -> System.out.println("Hello from thread : " + Thread.currentThread().getName()));

        thread.setName("Arbi");
        thread.start();
    }

    @Test
    void testThreadState() throws InterruptedException {
        var thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getState());
            System.out.println("Hello from thread : " + Thread.currentThread().getName());
        });

        System.out.println(thread.getState());
        thread.start();
        thread.join();
        System.out.println(thread.getState());
    }
}

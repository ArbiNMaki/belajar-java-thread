package learnjava.thread.core;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ThreadLocalRandomTest {

    @Test
    void testLocalRandom() throws InterruptedException {
        final var executors = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; i++) {
            executors.execute(() -> {
                try {
                    Thread.sleep(1000);
                    int nextInt = ThreadLocalRandom.current().nextInt();
                    System.out.println(nextInt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executors.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void testLocalRandomStream() throws InterruptedException {
        final var executors = Executors.newSingleThreadExecutor();

        executors.execute(() -> {
            ThreadLocalRandom.current().ints(1000, 0, 1000)
                    .forEach(System.out::println);
        });

        executors.shutdown();
        executors.awaitTermination(1, TimeUnit.DAYS);
    }
}

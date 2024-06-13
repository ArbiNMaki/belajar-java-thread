package learnjava.thread.core;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTest {

    @Test
    void testCyclicBarrier() throws InterruptedException {
        final var cyclicBarrier = new CyclicBarrier(5);
        final var service = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            service.execute(() -> {
                try {
                    cyclicBarrier.await();
                    System.out.println("Done Waiting");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        service.awaitTermination(1, TimeUnit.DAYS);
    }
}

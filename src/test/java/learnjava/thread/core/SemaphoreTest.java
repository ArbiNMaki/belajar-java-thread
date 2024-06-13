package learnjava.thread.core;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {

    @Test
    void testSemaphore() throws InterruptedException {
        final Semaphore semaphore = new Semaphore(1);
        final ExecutorService service = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 1000; i++) {
            service.execute(() -> {
                try {
                    semaphore.acquire();
                    Thread.sleep(1000);
                    System.out.println("Finish");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }

        service.awaitTermination(1, TimeUnit.DAYS);
    }
}

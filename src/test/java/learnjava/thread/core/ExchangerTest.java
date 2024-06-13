package learnjava.thread.core;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExchangerTest {

    @Test
    void testExchange() throws InterruptedException {
        final var exchanger = new Exchanger<String>();
        final var executor = Executors.newFixedThreadPool(10);

        executor.execute(() -> {
            try {
                System.out.println("Thread 1 : Send : First");
                Thread.sleep(1000);
                var value = exchanger.exchange("First");
                System.out.println("Thread 1 : Receive : " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.execute(() -> {
            try {
                System.out.println("Thread 2 : Send : Second");
                Thread.sleep(2000);
                var value = exchanger.exchange("Second");
                System.out.println("Thread 2 : Receive : " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}

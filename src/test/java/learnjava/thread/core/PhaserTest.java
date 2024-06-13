package learnjava.thread.core;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserTest {

    @Test
    void testPhaserCountDownLatch() throws InterruptedException {
        final var phaser = new Phaser();
        final var executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                try {
                    phaser.register();
                    System.out.println("Start task");
                    Thread.sleep(2000);
                    System.out.println("End task");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    phaser.arrive();
                }
            });
        }

        executor.execute(() -> {
            phaser.awaitAdvance(0);
            System.out.println("All task finished.");
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void testPhaserCyclicBarrier() {
        final var phaser = new Phaser();
        final var service = Executors.newFixedThreadPool(10);

        phaser.bulkRegister(5);
        for (int i = 0; i < 5; i++) {
            service.execute(() -> {
                phaser.arriveAndAwaitAdvance();
                System.out.println("Done Waiting");
            });
        }
    }
}

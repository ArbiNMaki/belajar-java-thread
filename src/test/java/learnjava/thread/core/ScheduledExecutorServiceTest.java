package learnjava.thread.core;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTest {

    @Test
    void testDelayedJob() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        var future = scheduledExecutorService.schedule(() -> {
            System.out.println("Hello Delayed Job");
        },2, TimeUnit.SECONDS);

        System.out.println(future.getDelay(TimeUnit.SECONDS));
    }
}

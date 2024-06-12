package learnjava.thread.core;

import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

    @Test
    void testDelayJob() throws InterruptedException {
        var task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Delayed Job");
            }
        };

        var timer = new Timer();
        timer.schedule(task, 2000);

        Thread.sleep(3000);
    }

    @Test
    void testPeriodicJob() throws InterruptedException {
        var task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Periodic Job");
            }
        };

        var timer = new Timer();
        timer.schedule(task, 2000, 2000);

        Thread.sleep(6000);
    }
}

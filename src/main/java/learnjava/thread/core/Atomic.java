package learnjava.thread.core;

import java.util.concurrent.atomic.AtomicLong;

public class Atomic {

    private final AtomicLong value = new AtomicLong(0L);

    public void increment() {
        value.incrementAndGet();
    }

    public Long getValue() {
        return value.get();
    }
}

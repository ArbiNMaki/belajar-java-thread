package learnjava.thread.core;

public class SynchronizeCounter {

    private Long value = 0L;

    public void increment() {
        synchronized (this) {
            value++;
        }
    }

    public Long getValue() {
        return value;
    }
}

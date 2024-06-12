package learnjava.thread.core;

public class Balance {

    private Long value;

    public Balance(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public static void transfer(Balance from, Balance to, Long value) throws InterruptedException {
        // Deadlock
//        synchronized (from) {
//            Thread.sleep(1000);
//            synchronized (to) {
//                Thread.sleep(1000);
//                from.setValue(from.getValue() - value);
//                to.setValue(to.getValue() + value);
//            }
//        }

        // Solution
        synchronized (from) {
            Thread.sleep(1000L);
            from.setValue(from.getValue() - value);
        }
        synchronized (to) {
            Thread.sleep(1000L);
            to.setValue(to.getValue() + value);
        }
    }
}

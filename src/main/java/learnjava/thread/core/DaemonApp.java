package learnjava.thread.core;

public class DaemonApp {

    public static void main(String[] args) {
        var thread = new Thread(() -> {
            try {
                Thread.sleep(3_000L);
                System.out.println("Run thread.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.setDaemon(true);
        thread.start();
    }
}

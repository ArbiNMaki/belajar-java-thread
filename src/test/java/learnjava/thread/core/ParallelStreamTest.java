package learnjava.thread.core;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelStreamTest {

    @Test
    void testPS() {
        Stream<Integer> stream = IntStream.range(0, 1000).boxed();
        stream.parallel().forEach(integer -> {
            System.out.println(Thread.currentThread().getName() + " : " + integer);
        });
    }

    @Test
    void testCustomForkJoinPool() {
        ForkJoinPool pool = new ForkJoinPool(1);

        ForkJoinTask<?> task = pool.submit(() -> {
            Stream<Integer> stream = IntStream.range(0, 1000).boxed();
            stream.parallel().forEach(integer -> {
                System.out.println(Thread.currentThread().getName() + " : " + integer);
            });
        });

        task.join();
    }
}

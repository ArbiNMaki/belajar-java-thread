package learnjava.thread.core;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ForkJoinTest {

    @Test
    void testForkJoinPool() {
        ForkJoinPool pool1 = ForkJoinPool.commonPool(); // Total CPU Core Parallelism
        ForkJoinPool pool2 = new ForkJoinPool(5);

        ExecutorService service1 = Executors.newWorkStealingPool(); // Total CPU Core Parallelism
        ExecutorService service2 = Executors.newWorkStealingPool(5);
    }

    public static class SimpleForkJoinTask extends RecursiveAction {

        final private List<Integer> integers;

        public SimpleForkJoinTask(List<Integer> integers) {
            this.integers = integers;
        }

        @Override
        protected void compute() {
            if (integers.size() <= 10) {
                doCompute();
            } else {
                forkCompute();
            }
        }

        private void doCompute() {
            integers.forEach(integer -> {
                System.out.println(Thread.currentThread().getState() + " : " + integer);
            });
        }

        private void forkCompute() {
            List<Integer> integers1 = this.integers.subList(0, this.integers.size() / 2);
            SimpleForkJoinTask task1 = new SimpleForkJoinTask(integers1);

            List<Integer> integers2 = this.integers.subList(this.integers.size() / 2, this.integers.size());
            SimpleForkJoinTask task2 = new SimpleForkJoinTask(integers2);

            ForkJoinTask.invokeAll(task1, task2);
        }
    }

    @Test
    void testRecursiveActionExecute() throws InterruptedException {
        final var pool = ForkJoinPool.commonPool();
        List<Integer> integers = IntStream.range(0, 1000).boxed().toList();

        pool.execute(new SimpleForkJoinTask(integers));

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.DAYS);
    }

    public static class TotalForkJoinTask extends RecursiveTask<Long> {

        final private List<Integer> integers;

        public TotalForkJoinTask(List<Integer> integers) {
            this.integers = integers;
        }

        @Override
        protected Long compute() {
            if (integers.size() <= 10) {
                return doCompute();
            } else {
                return forkCompute();
            }
        }

        private Long doCompute() {
            return integers.stream().mapToLong(value -> value).peek(value -> {
                System.out.println(Thread.currentThread().getName() + " : " + value);
            }).sum();
        }

        private Long forkCompute() {
            List<Integer> integers1 = this.integers.subList(0, this.integers.size() / 2);
            TotalForkJoinTask task1 = new TotalForkJoinTask(integers1);

            List<Integer> integers2 = this.integers.subList(this.integers.size() / 2, this.integers.size());
            TotalForkJoinTask task2 = new TotalForkJoinTask(integers2);

            ForkJoinTask.invokeAll(task1, task2);
            return task1.join() + task2.join();
        }
    }

    @Test
    void testRecursiveTaskExecute() throws InterruptedException, ExecutionException {
        final var pool = ForkJoinPool.commonPool();
        List<Integer> integers = IntStream.range(0, 1000).boxed().toList();

        TotalForkJoinTask task = new TotalForkJoinTask(integers);
        ForkJoinTask<Long> submit = pool.submit(task);

        Long along = submit.get();
        System.out.println(along);

        long sum = integers.stream().mapToLong(value -> value).sum();
        System.out.println(sum);

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.DAYS);
    }
}

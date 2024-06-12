package learnjava.thread.core;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FutureTest {

    @Test
    void testFuture() throws ExecutionException, InterruptedException {
        var executor = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            Thread.sleep(5000);
            return "hello";
        };

        Future<String> future = executor.submit(callable);
        System.out.println("Selesai.");

        while (!future.isDone()) {
            System.out.println("waiting");
            Thread.sleep(1000);
        }

        String value = future.get();
        System.out.println(value);
    }

    @Test
    void testCancel() throws ExecutionException, InterruptedException {
        var executor = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            Thread.sleep(5000);
            return "hello";
        };

        Future<String> future = executor.submit(callable);
        System.out.println("Selesai.");

        Thread.sleep(2000);
        future.cancel(true);

        String value = future.get();
        System.out.println(value);
    }

    @Test
    void testInvokeAll() throws InterruptedException, ExecutionException {
        var executor = Executors.newFixedThreadPool(10);
        List<Callable<String>> callables = IntStream.range(1, 11).mapToObj(operand -> (Callable<String>) () -> {
            Thread.sleep(operand * 500L);
            return String.valueOf(operand);
        }).toList();

        List<Future<String>> futures = executor.invokeAll(callables);
        for (Future<String> stringFuture : futures) {
            System.out.println(stringFuture.get());
        }
    }

    @Test
    void testInvokeAny() throws InterruptedException, ExecutionException {
        var executor = Executors.newFixedThreadPool(10);
        List<Callable<String>> callables = IntStream.range(1, 11).mapToObj(operand -> (Callable<String>) () -> {
            Thread.sleep(operand * 500L);
            return String.valueOf(operand);
        }).toList();

        String futures = executor.invokeAny(callables);
        System.out.println(futures);
    }
}

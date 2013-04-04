package com.snuffbumble.lambda;

import java.util.stream.Stream;
import java.util.stream.Streams;

public class Prime {

    public static void main(String[] args) {
        timed( () -> numbersUpToN( 4_000_000, getIteratorBasedStream(false)) );
        timed( () -> numbersUpToN( 4_000_000, getRangeBasedStream(false)) );

        timed( () -> primesUpToN ( 4_000_000, getIteratorBasedStream(false)) );
        timed( () -> primesUpToN ( 4_000_000, getIteratorBasedStream(true)) );
        timed( () -> primesUpToN ( 4_000_000, getRangeBasedStream(true)) );

        timed( () -> firstNPrimes(   283_147, getIteratorBasedStream(false)) );
        timed( () -> firstNPrimes(   283_147, getIteratorBasedStream(true)) );
        timed( () -> firstNPrimes(   283_147, getRangeBasedStream(true)) );
    }

    public static void numbersUpToN(int n, Stream<Long> stream) {
        System.out.println
                (String.format("numbersUpToN (%10d, %5b): %10d", n, stream.isParallel(),
                        stream
                                .limit(n)
                                .map(e -> 1L)
                                .reduce(0L, (count, t) -> count + 1L, (countLeft, countRight) -> countLeft + countRight)));
    }

    public static void primesUpToN(int n, Stream<Long> stream) {
        System.out.println
                (String.format("primesUpToN  (%10d, %5b): %10d", n, stream.isParallel(),
                        stream
                                .limit(n) // limit before primality test
                                .filter(Prime::isPrime)
                                .reduce(0L, (count, t) -> count + 1L, (countLeft, countRight) -> countLeft + countRight)));
    }

    public static void firstNPrimes(int n, Stream<Long> stream) {
        System.out.println
                (String.format("firstNPrimes (%10d, %5b): %10d", n, stream.isParallel(),
                        stream
                                .filter(Prime::isPrime)
                                .limit(n) // limit after primality test
                                .reduce(0L, (count, t) -> count + 1L, (countLeft, countRight) -> countLeft + countRight)));
    }

    // create stream - either serial or parallel
    public static Stream<Long> getIteratorBasedStream(boolean parallel) {
        Stream<Long> s = Streams.iterate(1L, n -> n + 1L);
        return parallel ? s.parallel() : s;
    }

    public static Stream<Long> getRangeBasedStream(boolean parallel) {
        Stream<Long> s = Streams.longRange(1L, 10_000_000L).boxed();
        return parallel ? s.parallel() : s;
    }

    public static boolean isPrime(long n) {
        if (n < 1) { return false; }
        if (n == 2) { return true; }
        for (int i = 2; i <= Math.sqrt(n) + 1; i++) { if (n % i == 0) return false; }
        return true;
    }

    // run a Runnable and output timings
    public static void timed(Runnable r) {
        long t = System.currentTimeMillis();
        r.run();
        t = System.currentTimeMillis() - t;
        System.out.println(String.format("time: %.1f secs", t/1000.0));
    }
}

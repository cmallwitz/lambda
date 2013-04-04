package com.snuffbumble.lambda;

import java.util.Arrays;
import java.util.stream.Stream;

public class ParallelLimitSkip {

    private static final int n = 100;

    public static void main(String[] args) {
        testSequential();
        testParallel();
    }

    public static void testSequential() {
        System.out.println("sequential count after limit(10): " + getArrayBasedStream(false)
                .filter(l -> l%2==0) // filter even numbers
                .limit(10)
                .count());

        System.out.println("sequential count after skip(10) : " + getArrayBasedStream(false)
                .filter(l -> l%2==0) // filter even numbers
                .substream(10)
                .count());
    }

    public static void testParallel() {
        System.out.println("parallel   count after limit(10): " + getArrayBasedStream(true)
                .filter(l -> l%2==0) // filter even numbers
                .limit(10)
                .count());

        System.out.println("parallel   count after skip(10) : " + getArrayBasedStream(true)
                .filter(l -> l % 2 == 0) // filter even numbers
                .substream(10)
                .count());
    }

    public static Stream<Long> getArrayBasedStream(boolean parallel) {

        Long[] list = new Long[n];
        for(int i=0; i<n; i++) { list[i] = i+1L; }
        return parallel ? Arrays.asList(list).parallelStream() : Arrays.asList(list).stream();
    }
}

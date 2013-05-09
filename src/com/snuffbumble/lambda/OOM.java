package com.snuffbumble.lambda;

public class OOM {
    public static void main(String... ignored) {

        // prints 200000
        System.out.println(java.util.stream.LongStream.iterate(1L, n -> n + 1L)
                .filter(l -> l % 100 == 0).limit(200_000).count());

        // prints 100000
        System.out.println(java.util.stream.LongStream.iterate(1L, n -> n + 1L)
                .parallel()
                .filter(l -> l % 100 == 0).limit(100_000).count());

        // immediate OOM
        System.out.println(java.util.stream.LongStream.iterate(1L, n -> n + 1L)
                .parallel()
                .filter(l -> l % 100 == 0).limit(200_000).count());
    }
}

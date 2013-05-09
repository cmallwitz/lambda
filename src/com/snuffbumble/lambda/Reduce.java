package com.snuffbumble.lambda;

import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.Streams;

public class Reduce {

    private static final long n = 100;

    public static void main(String[] args) {

        System.out.println(
                LongStream.range(0L, n).boxed()
                        .map(e -> 1)
                        .reduce(0, Integer::sum)
        );

        System.out.println(
                LongStream.range(0L, n).boxed()
                        .map(e -> 1)
                        .count()
        );

        System.out.println(
                LongStream.range(0L, n).boxed()
                        .map(e -> 1)
                        .reduce(0L, (count, t) -> count + 1L, (countLeft, countRight) -> countLeft + countRight)
        );
    }
}

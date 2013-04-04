package com.snuffbumble.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class AtomicIntegerTest {

    public static void main(String[] args) {

        List<AtomicInteger> list = Arrays.asList(new AtomicInteger(0));
        Stream<AtomicInteger> stream = list.stream();

        stream
                .map((AtomicInteger atomic) -> atomic.get())
                .forEach(l -> { System.out.println(l); });
    }
}

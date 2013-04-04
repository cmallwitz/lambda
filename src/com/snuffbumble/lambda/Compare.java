package com.snuffbumble.lambda;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Compare {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>(); // JDK 7 diamond operator
        list.add("6");
        list.add("28");
        list.add("496");
        list.add("8128");
        list.add("33550336");

        list
                .stream()

                .sorted((o1, o2) -> (new Integer(o1)).compareTo(new Integer(o2)))
                .sorted(Comparators.<String, Integer>comparing(s -> Integer.parseInt(s)))
                .sorted(Comparators.<String, Integer>comparing(Integer::parseInt))
                .sorted(Comparators.comparing((Function<String, Integer>) Integer::parseInt))

                .sorted((o1, o2) -> Integer.compare(Integer.parseInt(o1), Integer.parseInt(o2))) // JDK 7 Integer.compare() method

                .forEach(x -> {
                    System.out.println(x);
                });

        // Function is defined using method reference
        schwartz(list.stream(), Integer::parseInt)
                .forEach(x -> { System.out.println(x); });
    }

    @SuppressWarnings("unchecked")
    public static<T, R extends Comparable<? super R>> Stream<T> schwartz(Stream<T> stream, Function<T, R> f) {

        return stream
                .map(t -> new Object[]{t, f.apply(t)})
                .sorted((o1, o2) -> ((Comparable) o1[1]).compareTo(o2[1]))
                .map(o -> ((T) o[0]));
    }
}

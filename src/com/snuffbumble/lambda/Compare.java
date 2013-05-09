package com.snuffbumble.lambda;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Compare {

    public static void main(String... ignored) {

        List<String> list = new ArrayList<>(); // JDK 7 diamond operator
        list.add("6");
        list.add("28");
        list.add("496");
        list.add("8128");
        list.add("33550336");
        list.add("8589869056");
        list.add("137438691328");

        list
                .stream()

                .sorted((o1, o2) -> (new Long(o1)).compareTo(new Long(o2)))
                .sorted(Comparators.<String, Long>comparing(s -> Long.parseLong(s)))
                .sorted(Comparators.<String, Long>comparing(Long::parseLong))
                .sorted(Comparators.comparing((ToLongFunction<String>) Long::parseLong))
                .sorted(Comparators.comparing((Function<String, Long>) Long::parseLong))

                .sorted((o1, o2) -> Long.compare(Long.parseLong(o1), Long.parseLong(o2))) // JDK 7 Long.compare() method

                .forEach(x -> {
                    System.out.println(x);
                });

        // function is defined using method reference
        schwartz(list.stream(), Long::parseLong)
                .forEach(x -> System.out.println(x));
    }

    @SuppressWarnings("unchecked")
    public static<T, R extends Comparable<? super R>> Stream<T> schwartz(Stream<T> stream, Function<T, R> f) {

        return stream
                .map(t -> new Object[]{t, f.apply(t)})
                .sorted((o1, o2) -> ((Comparable) o1[1]).compareTo(o2[1]))
                .map(o -> ((T) o[0]));
    }
}
